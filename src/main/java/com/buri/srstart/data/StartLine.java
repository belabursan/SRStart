package com.buri.srstart.data;


/**
 *
 * @author bub
 */
public final class StartLine {

private final StartBoat startBoat;
private final StartMark startMark;


    public StartLine(StartBoat startBoat, StartMark startMark) {
        this.startBoat = startBoat;
        this.startMark = startMark;
    }


    public StartBoat getStartBoat() {
        return startBoat;
    }


    public StartMark getStartMark() {
        return startMark;
    }

    
}
