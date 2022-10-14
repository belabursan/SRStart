package com.buri.srstart.intf;

import com.buri.srstart.data.Position;


/**
 *
 * @author bub
 */
public interface SRCoreIntf extends AutoCloseable, SRDefaults {

    public void setStartBoatPosition(Position position);
    public void setStartMarkPosition(Position position);
    public void setMinutesUntilStart(int minutesUntilStart);
    public void stopEverything();
    public SRSessionIntf newSession(SRPositioningIntf pos);
}
