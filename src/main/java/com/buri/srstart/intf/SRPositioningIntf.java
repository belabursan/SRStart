package com.buri.srstart.intf;

import com.buri.srstart.data.Position;
import com.buri.srstart.exceptions.AlreadyRunningException;
import java.time.LocalDateTime;


/**
 *
 * @author bub
 */
public interface SRPositioningIntf extends AutoCloseable {
    public void start() throws AlreadyRunningException;
    public Position getCurrentPosition();
    public LocalDateTime getCurrentGPSTime();
    public void stop();
}
