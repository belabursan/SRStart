package com.buri.srstart.data;


/**
 *
 * @author bub
 */
public class Position {
    private final double longitude;
    private final double latitude;


    public Position(double latitude, double longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }


    public double getLongitude() {
        return longitude;
    }


    public double getLatitude() {
        return latitude;
    }
    
    
    @Override
    public String toString() {
        return "Lat: " + latitude + " Lon: " + longitude;
    }


    public String getFormattedLatitude() {
        if (latitude > 0) {
            return formatPos("N", latitude);
        }
        return formatPos("S", latitude);
    }

    
    public String getFormattedLongitude() {
        if (longitude < 0) {
            return formatPos("W", longitude);   
        }
        return formatPos("E", longitude);
    }
    
    
    /**
     * Returns the distance to another position
     * @param that Another position to check the distance to
     * @return distance in meters
     */
    public double distanceTo(Position that) {
        double latInRadThis = Math.toRadians(this.latitude);
        double latInRadThat = Math.toRadians(that.latitude);

        // great circle distance in radians, using law of cosines formula
        double angle = Math.acos(Math.sin(latInRadThis) * Math.sin(latInRadThat)
                               + Math.cos(latInRadThis) * Math.cos(latInRadThat)
                               * Math.cos(Math.toRadians(this.longitude) - Math.toRadians(that.longitude)));

        // each degree on a great circle of Earth is 60 nautical miles,
        //    each nautical mile is 1852 meters
        return 60 * Math.toDegrees(angle) * 1852;
    }


    
    
    private String formatPos(String swen, double pos) {
        StringBuilder sb = new StringBuilder();
        int degrees = (int)pos;
        double minutes = (((pos - degrees) * 3600) /60);

        sb.append(swen);
        sb.append(String.valueOf(degrees));
        sb.append("° ");
        sb.append(String.valueOf(minutes).substring(0, 7));

        sb.trimToSize();
        return sb.toString();
    }
    
}
