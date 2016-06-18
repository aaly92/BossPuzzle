package com.example.aaly.bosspuzzle;


public enum Direction {
    UP(1),RIGHT(2),DOWN(3),LEFT(4);

    private final int value;

    private Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
