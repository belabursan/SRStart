package com.buri.srstart.data;


/**
 *
 * @author bub
 */
public final class StartBoat extends Position {
    
    private String name;


    public StartBoat(String name, double longitude, double latitude) {
        super(longitude, latitude);
        this.name = name;
    }
    
    
    public StartBoat(String name, Position pos) {
        super(pos.getLatitude(), pos.getLongitude());
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
