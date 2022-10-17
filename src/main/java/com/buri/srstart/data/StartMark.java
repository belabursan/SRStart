package com.buri.srstart.data;


/**
 *
 * @author bub
 */
public final class StartMark extends Position {
    private final String name;


    public StartMark(String name, long longitude, long latitude) {
        super(longitude, latitude);
        this.name = name;
    }        


    public StartMark(String m2, Position position) {
        super(position.getLatitude(), position.getLongitude());
        this.name = m2;
    }


    public String getName() {
        return name;
    }
}
