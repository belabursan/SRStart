package com.buri.srstart.exceptions;


/**
 *
 * @author bub
 */
public class MissingSettingsException extends Exception {

    public MissingSettingsException() {
        super();
    }
    
    public MissingSettingsException(String message) {
        super(message);
    }
}
