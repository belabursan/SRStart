package com.buri.srstart.src;

import com.buri.srstart.data.Position;
import com.buri.srstart.data.SRTime;
import com.buri.srstart.data.StartLine;
import com.buri.srstart.intf.SRPositioningIntf;
import com.buri.srstart.intf.SRSessionIntf;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author bub
 */
public class SRFiveMinSession implements SRSessionIntf {

    private static final int LIST_SIZE = 20;

    private LocalDateTime startTime;
    private double currentSpeed_kn;
    private double mediumSpeed_kn_3s;
    private boolean alive;
    private int distanceToLine_m;
    private Duration durationBetweenNowAndStartTime;
    //
    private final StartLine startLine;
    private final long preStartTime_min;
    private final SRPositioningIntf positioning;
    private final Runnable runnable;
    private final LinkedList<Position> positionList;
    private final LinkedList<LocalDateTime> timeList;
    private final LinkedList<Double> speedList;


    public SRFiveMinSession(StartLine startLine, SRPositioningIntf pos) {
        this.startTime = null;
        this.durationBetweenNowAndStartTime = null;
        this.preStartTime_min = 5;
        this.currentSpeed_kn = 0;
        this.distanceToLine_m = 0;
        this.alive = false;
        this.mediumSpeed_kn_3s = 0;
        this.positionList = new LinkedList<>();
        this.timeList = new LinkedList<>();
        this.speedList = new LinkedList<>();

        this.startLine = startLine;
        this.positioning = pos;

        runnable = new Runnable() {
            @Override
            public void run() {
                alive = true;
                System.out.println("running session");
                boolean checkSpeed = true;

                try {
                    while (alive) {
                        Position pos = getCurrentPosition();
                        LocalDateTime now = getGPSTimeNow();

                        durationBetweenNowAndStartTime = Duration.between(startTime, now);
                        updateDistanceToStartLine(pos);
                        if (checkSpeed) {
                            calculateSpeed(now, pos);
                        }
                        checkSpeed = !checkSpeed;

                        synchronized (runnable) {
                            runnable.wait(50);
                        }
                    }
                } catch (InterruptedException ex) {
                    alive = false;
                    Logger.getLogger(SRFiveMinSession.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        };
    }


    @Override
    public void start() {
        if (!alive) {
            this.startTime = getGPSTimeNow().plusMinutes(preStartTime_min);
            this.durationBetweenNowAndStartTime = Duration.between(startTime, getGPSTimeNow());
            new Thread(runnable).start();
        }
    }


    @Override
    public void stop() {
        if (alive) {
            alive = false;
            synchronized (runnable) {
                runnable.notify();
            }
            this.startTime = null;
        }
    }


    @Override
    public void syncRaceStartTimeDownToWholeMinute() {
        if (this.startTime != null) {
            int secs = durationBetweenNowAndStartTime.toSecondsPart();
            System.out.println("removing secs: " + secs);
           
            startTime = startTime.minusSeconds(Math.abs(secs));
        }
    }


    @Override
    public void addOneSecondToRaceStartTime() {
        if (this.startTime != null) {
            this.startTime = this.startTime.plusSeconds(1);
        }
    }


    @Override
    public void removeOneSecondFromRaceStartTime() {
        if (this.startTime != null) {
            this.startTime = this.startTime.minusSeconds(1);
        }
    }


    @Override
    public LocalDateTime getStartTime() {
        return this.startTime;
    }


    @Override
    public SRTime getRaceTime() {
        if (durationBetweenNowAndStartTime != null) {
            int h = durationBetweenNowAndStartTime.toHoursPart();
            int m = durationBetweenNowAndStartTime.toMinutesPart();
            int s = durationBetweenNowAndStartTime.toSecondsPart();
            return new SRTime(h, m, s);
        }
        return null;
    }


    @Override
    public LocalDateTime getGPSTimeNow() {
        return this.positioning.getCurrentGPSTime();
    }


    @Override
    public int getMetersToStartLine() {
        return distanceToLine_m;
    }


    @Override
    public double getSpeedInKnots() {
        return currentSpeed_kn;
    }


    @Override
    public double getMediumSpeed() {
        return this.mediumSpeed_kn_3s;
    }
    
    /**
     * Returns a suggestion to speed up or slow down
     *
     * @return negative number if the speed must be reduced, positive number if
     * speed can be increased, zero if current speed shall be kept. Bigger
     * number means stronger speed up/slow down
     */
    @Override
    public int getSuggetionForSpeed() {
        if (startTime == null) {
            return 0;
        }

        long secondsUntilStart = Duration.between(startTime, getGPSTimeNow()).toSeconds();
        int timeToLineWithCurrentSpeed_s = (int) (distanceToLine_m / ((1852 * currentSpeed_kn) * 3600));

        double toReturn = timeToLineWithCurrentSpeed_s - secondsUntilStart;

        if (toReturn >= 0 && toReturn < 2) {
            return 0;

        }
        return (int) toReturn;
    }


    @Override
    public Position getCurrentPosition() {
        return positioning.getCurrentPosition();
    }


    @Override
    public void close() throws Exception {
        stop();
    }


    private void updateDistanceToStartLine(Position pos) {
        double distanceToStarboardSide = pos.distanceTo(startLine.getStartBoat());
        double distanceToPortSide = pos.distanceTo(startLine.getStartMark());

        double s = (distanceToStarboardSide + distanceToPortSide + startLine.getLength_m()) / 2;
        double h = 2 * Math.sqrt(s * (s - distanceToStarboardSide) * (s - startLine.getLength_m()) * (s - distanceToPortSide)) / startLine.getLength_m();
        this.distanceToLine_m = (int) (h + 0.5);
    }


    private void calculateSpeed(LocalDateTime now, Position currentPos) {
        positionList.addFirst(currentPos);
        if (positionList.size() > LIST_SIZE) {
            positionList.removeLast();
        }

        timeList.addFirst(now);
        if (timeList.size() > LIST_SIZE) {
            timeList.removeLast();
        }
        if (timeList.size() > 1) {
            double distance_m = currentPos.distanceTo(positionList.getLast());
            double duration_s = Duration.between(now, timeList.getLast()).getSeconds();
            double speed_m_s = distance_m / duration_s;
            
            currentSpeed_kn = 1.94384 * speed_m_s;
            speedList.addFirst(currentSpeed_kn);
            if (speedList.size() > LIST_SIZE*3) {
                speedList.removeLast();
            }
            double speed_med = 0;
            int count = 0;
            for (double s : speedList) {
                count++;
                speed_med += s;
            }
            this.mediumSpeed_kn_3s = speed_med / count;
        }
    }
}
