package com.buri.srstart.intf;

import com.buri.srstart.data.Position;
import com.buri.srstart.exceptions.MissingSettingsException;


/**
 *
 * @author bub
 */
public interface SRCoreIntf extends AutoCloseable, SRDefaults {

    public void setStartBoatPosition(Position position);
    public void setStartMarkPosition(Position position);
    public void setMinutesUntilStart(int minutesUntilStart);
    public void stopEverything();
    public SRSessionIntf newSession(SRPositioningIntf pos) throws MissingSettingsException;
}
