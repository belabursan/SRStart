package com.buri.srstart.data;

import com.buri.srstart.data.Position;


/**
 *
 * @author bub
 */
public class StartMark extends Position {
    private String name;


    public StartMark(String name, long longitude, long latitude) {
        super(longitude, latitude);
        this.name = name;
    }        


    public String getName() {
        return name;
    }
}
