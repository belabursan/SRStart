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
        return String.valueOf(hours);
    }
    
    public String getMinutesAsString() {
        return String.valueOf(minutes);
    }


    public String getSecondsAsString() {
        return String.valueOf(seconds);
    }
    
    
    public String getTimeAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (hours > 0) {
            stringBuilder
                    .append(hours)
                    .append(":");
        }
        
        stringBuilder
                .append(minutes)
                .append(":")
                .append(seconds);
        stringBuilder.trimToSize();
        return stringBuilder.toString();
    }
    

}
