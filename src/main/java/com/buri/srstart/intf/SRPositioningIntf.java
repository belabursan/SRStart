package com.buri.srstart.intf;

import com.buri.srstart.data.Position;
import com.buri.srstart.exceptions.AlreadyRunningException;
import java.time.LocalDateTime;


/**
 *
 * @author bub
 */
public interface SRPositioningIntf extends AutoCloseable {
    public enum PositionRunnerFrequency {
        POSITION_RUNNER_FREQUENCY_HIGH_MS(SRDefaults.POSITION_RUNNER_FREQUENCY_HIGH),
        POSITION_RUNNER_FREQUENCY_MEDIUM_MS(SRDefaults.POSITION_RUNNER_FREQUENCY_MEDIUM),
        POSITION_RUNNER_FREQUENCY_LOW_MS(SRDefaults.POSITION_RUNNER_FREQUENCY_LOW);
        private final long frequency;
        PositionRunnerFrequency(long frequency) {this.frequency = frequency; }
        public long value() { return this.frequency; }
    }
    
    public void start() throws AlreadyRunningException;
    public Position getCurrentPosition();
    public LocalDateTime getCurrentGPSTime();
    public void setFrequency(PositionRunnerFrequency frequency);
    public void stop();
}
