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
        int m = Math.abs(minutes);
        int s = Math.abs(seconds);
        
        if (hours > 0) {
            stringBuilder
                    .append(Math.abs(hours))
                    .append(":");
        }
        
        if (m < 10) {
            stringBuilder.append("0");
        }
        stringBuilder
                .append(m)
                .append(":");
        if (s < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(s);
        stringBuilder.trimToSize();
        return stringBuilder.toString();
    }
    

}
