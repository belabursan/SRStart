package com.buri.srstart.src;

import com.buri.srstart.data.Position;
import com.buri.srstart.intf.SRCoreIntf;
import com.buri.srstart.intf.SRPositioningIntf;
import com.buri.srstart.intf.SRSessionIntf;


/**
 *
 * @author bub
 */
 public class SRCore implements SRCoreIntf {

    @Override
    public void setStartBoatPosition(Position position) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public void setStartMarkPosition(Position position) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public void setMinutesUntilStart(int minutesUntilStart) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public void stopEverything() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public SRSessionIntf newSession(SRPositioningIntf pos) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


    @Override
    public void close() {
        this.stopEverything();
    }
    
}
