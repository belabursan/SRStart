package com.buri.srstart.data;


/**
 *
 * @author bub
 */
public final class StartLine {

private final StartBoat startBoat;
private final StartMark startMark;
private final double length_m;


    public StartLine(StartBoat startBoat, StartMark startMark) {
        this.startBoat = startBoat;
        this.startMark = startMark;
        this.length_m = startBoat.distanceTo((Position)startMark);
    }


    public StartBoat getStartBoat() {
        return startBoat;
    }


    public StartMark getStartMark() {
        return startMark;
    }


    /**
     * Returns the length of the line
     * @return length of the line in meters
     */
    public double getLength_m() {
        return length_m;
    }
    
}
