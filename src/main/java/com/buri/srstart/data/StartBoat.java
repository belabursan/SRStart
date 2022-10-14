package com.buri.srstart.data;

import com.buri.srstart.data.Position;


/**
 *
 * @author bub
 */
public class StartBoat extends Position {
    
    private String name;


    public StartBoat(String name, long longitude, long latitude) {
        super(longitude, latitude);
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
