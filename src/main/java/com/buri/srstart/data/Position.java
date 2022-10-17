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

//55.59468654819864, 12.928926020700088
    public String getFormattedLongitude() {
        if (longitude < 0) {
            return formatPos("W", longitude);   
        }
        return formatPos("E", longitude);
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
