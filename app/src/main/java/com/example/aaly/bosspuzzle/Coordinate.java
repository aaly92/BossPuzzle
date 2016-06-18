package com.example.aaly.bosspuzzle;


public class Coordinate {
    public int x;
    public int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static boolean sameCoordinates(Coordinate coordinate1, Coordinate coordinate2) {
        return coordinate1.x == coordinate2.x && coordinate1.y == coordinate2.y;
    }

}
