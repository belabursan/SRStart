package com.buri.srstart.intf;

import com.buri.srstart.data.Position;
import com.buri.srstart.data.SRTime;
import java.time.LocalDateTime;


/**
 *
 * @author bub
 */
public interface SRSessionIntf extends AutoCloseable {
    
    public void start();
    public void stop();
    public void syncRaceStartTimeDownToWholeMinute();
    public void addOneSecondToRaceStartTime();
    //
    public LocalDateTime getStartTime();
    //
    public SRTime getRaceTime();
    public LocalDateTime getTimeNow();
    public int getMetersToStartLine();
    public int getSpeedInKnots();
    public int getSuggetionForSpeed();
    public Position getCurrentPosition();
}
