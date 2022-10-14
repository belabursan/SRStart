package com.buri.srstart.data;


/**
 *
 * @author bub
 */
public class SRTime {

    private final int hours;
    private final int minutes;
    private final int seconds;


    public SRTime(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }


    public int getHours() {
        return hours;
    }


    public int getMinutes() {
        return minutes;
    }


    public int getSeconds() {
        return seconds;
    }

    public String getHoursAsString() {
        return null;
    }
    
    public String getMinutesAsString() {
        return null;
    }


    public String getSecondsAsString() {
        return null;
    }
    

}
