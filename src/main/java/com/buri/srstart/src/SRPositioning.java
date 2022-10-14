package com.buri.srstart.src;

import com.buri.srstart.data.Position;
import com.buri.srstart.exceptions.AlreadyRunningException;
import com.buri.srstart.intf.SRCoreIntf;
import com.buri.srstart.intf.SRDefaults;
import com.buri.srstart.intf.SRPositioningIntf;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Reads and stores the current position and GPS time
 * which then can be retrieved by other objects
 * @author bub
 */
public class SRPositioning implements SRPositioningIntf, SRDefaults {

    private Position currentPosition;
    private LocalDateTime currentGPSTime;
    private final Runnable runnable;
    private boolean running;


    public SRPositioning() {
        currentGPSTime = null;
        currentPosition = null;
        running = false;
        
        runnable = new Runnable() {
            @Override
            public void run() {
                running = true;
                while (running) {
                    runUpdate();
                    synchronized (runnable) {
                        try {
                            runnable.wait(POSITION_RUNNER_FREQUENCY_SHORT_MS);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(SRPositioning.class.getName()).log(Level.SEVERE, "Positioning.runner interrupted", ex);
                        }
                    }
                }
            }
        };
    }
    
    


    @Override
    public void start() throws AlreadyRunningException {
        if (running) { 
            throw new AlreadyRunningException("Positioning already running");
        }
        java.awt.EventQueue.invokeLater(runnable);

    }


    private void runUpdate() {
        // TODO : fix update of time and pos
        currentPosition = new Position(1000000, 2000000);
        currentGPSTime = LocalDateTime.now();
        System.out.println("update : " + currentGPSTime.toLocalTime());
    }


    @Override
    public Position getCurrentPosition() {
        return currentPosition;
    }


    @Override
    public LocalDateTime getCurrentGPSTime() {
        return currentGPSTime;
    }


    @Override
    public void stop() {
        this.running = false;
        synchronized (runnable) {
            this.runnable.notify();
        }
        currentPosition = null;
        currentGPSTime = null;
    }


    @Override
    public void close() {
        this.stop();
    }

}
