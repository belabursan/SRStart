package com.buri.srstart.src;

import com.buri.srstart.data.Position;
import com.buri.srstart.data.StartBoat;
import com.buri.srstart.data.StartLine;
import com.buri.srstart.data.StartMark;
import com.buri.srstart.exceptions.MissingSettingsException;
import com.buri.srstart.intf.SRCoreIntf;
import com.buri.srstart.intf.SRPositioningIntf;
import com.buri.srstart.intf.SRSessionIntf;


/**
 *
 * @author bub
 */
 public class SRCore implements SRCoreIntf {
     private StartBoat startBoatPosition;
     private StartMark startMarkPosition;
     private int minutesToStart;
     private SRSessionIntf session;


    public SRCore() {
        startBoatPosition = null;
        startMarkPosition = null;
        minutesToStart = 5; // default to 5
        session = null;
    }
     
     
     

    @Override
    public void setStartBoatPosition(Position position) {
        startBoatPosition = new StartBoat("M", position);
    }


    @Override
    public void setStartMarkPosition(Position position) {
        startMarkPosition = new StartMark("M2", position);
    }


    @Override
    public void setMinutesUntilStart(int minutesUntilStart) {
        minutesToStart = minutesUntilStart;
    }


    @Override
    public void stopEverything() {
        System.out.println("close core");
        if (session != null) {
            session.stop();
            session = null;
        }
        startBoatPosition = null;
        startMarkPosition = null;
        minutesToStart = -1;
    }


    @Override
    public SRSessionIntf newSession(SRPositioningIntf pos) throws MissingSettingsException {
        if (minutesToStart == 5) {
            if(startBoatPosition != null && startMarkPosition != null) {
                return new SRFiveMinSession(new StartLine(startBoatPosition, startMarkPosition), pos);
            } else {
                throw new MissingSettingsException("Start Boat or Mark position is not set");
            }
        }
        throw new MissingSettingsException("Minutes to start not set");
    }


    @Override
    public void close() {
        this.stopEverything();
    }
    
}
