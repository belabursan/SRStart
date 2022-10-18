package com.buri.srstart.src;

import com.buri.srstart.data.Position;
import com.buri.srstart.data.SRTime;
import com.buri.srstart.data.StartLine;
import com.buri.srstart.intf.SRPositioningIntf;
import com.buri.srstart.intf.SRSessionIntf;
import java.time.Duration;
import java.time.LocalDateTime;


/**
 *
 * @author bub
 */
public class SRFiveMinSession implements SRSessionIntf {
    
    private LocalDateTime startTime;
    private double currentSpeed_kn;
    private int distanceToLine_m;
    private final StartLine startLine;
    private final long preStartTime_min;
    private final SRPositioningIntf positioning;
    
    

    public SRFiveMinSession(StartLine startLine, SRPositioningIntf pos) {
        this.startTime = null;
        this.preStartTime_min = 5;
        this.currentSpeed_kn = 0;
        this.distanceToLine_m = 0;
        this.startLine = startLine;
        this.positioning = pos;
    }


    @Override
    public void start() {
        this.startTime = getGPSTimeNow().plusMinutes(preStartTime_min);
    }


    @Override
    public void stop() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public void syncRaceStartTimeDownToWholeMinute() {
        int secs = startTime.getSecond();
        if (secs == 0) {
            startTime.minusMinutes(1);
        } else {
            startTime.minusSeconds(secs);
        }
    }


    @Override
    public void addOneSecondToRaceStartTime() {
        this.startTime = this.startTime.plusSeconds(1);
    }
    
    
    @Override
    public void removeOneSecondFromRaceStartTime() {
        this.startTime = this.startTime.minusSeconds(1);
    }
    

    @Override
    public LocalDateTime getStartTime() {
        return this.startTime;
    }


    @Override
    public SRTime getRaceTime() {
        Duration d = Duration.between(startTime, getGPSTimeNow());
        int h = d.toHoursPart();
        int m = d.toMinutesPart();
        int s = d.toSecondsPart();
        
        return new SRTime(h, m, s);
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


    /**
     * Returns a suggestion to speed up or slow down
     * @return negative number if the speed must be reduced,
     *          positive number if speed can be increased,
     *          zero if current speed shall be kept.
     *      Bigger number means stronger speed up/slow down
     */
    @Override
    public int getSuggetionForSpeed() {
        long secondsUntilStart = Duration.between(startTime, getGPSTimeNow()).toSeconds();
        int timeToLineWithCurrentSpeed_s = (int)(distanceToLine_m / ((1852 * currentSpeed_kn) * 3600));
        
        double toReturn = timeToLineWithCurrentSpeed_s - secondsUntilStart;
        
        if (toReturn >= 0 && toReturn < 2) {
            return 0;
            
        }
        return (int)toReturn;
    }


    @Override
    public Position getCurrentPosition() {
        return positioning.getCurrentPosition();
    }


    @Override
    public void close() throws Exception {
        stop();
    }
    //55.598636051328455, 12.934975659769195
    /*******************************************************************************************************/
    /*
    double distance_on_geoid(double lat1, double lon1, double lat2, double lon2) {
        // Convert degrees to radians
        lat1 = lat1 * M_PI / 180.0;
        lon1 = lon1 * M_PI / 180.0;
        lat2 = lat2 * M_PI / 180.0;
        lon2 = lon2 * M_PI / 180.0;
        // radius of earth in metres
        double r = 6378100;
        // P
        double rho1 = r * cos(lat1);
        double z1 = r * sin(lat1);
        double x1 = rho1 * cos(lon1);
        double y1 = rho1 * sin(lon1);
        // Q
        double rho2 = r * cos(lat2);
        double z2 = r * sin(lat2);
        double x2 = rho2 * cos(lon2);
        double y2 = rho2 * sin(lon2);
        // Dot product
        double dot = (x1 * x2 + y1 * y2 + z1 * z2);
        double cos_theta = dot / (r * r);
        double theta = acos(cos_theta);
        // Distance in Metres
        return r * theta;
    }
    */
    // https://www.ridgesolutions.ie/index.php/2022/05/26/code-to-calculate-heading-bearing-from-two-gps-latitude-and-longitude/
    /*
        auto dist = distance_on_geoid(p1.latitude, p1.longitude, p2.latitude, p2.longitude);
        // timestamp is in milliseconds
        auto time_s = (p2.timestamp - p1.timestamp) / 1000.0;
        double speed_mps = dist / time_s;
        double speed_kph = (speed_mps * 3600.0) / 1000.0;
    */
}
