package com.buri.srstart.data;


/**
 *
 * @author bub
 */
public class Position {
    private final long longitude;
    private final long latitude;


    public Position(long longitude, long latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }


    public long getLongitude() {
        return longitude;
    }


    public long getLatitude() {
        return latitude;
    }
    
}
