package com.example.aaly.bosspuzzle;

import android.graphics.Bitmap;

public class GameTile {
    private Bitmap image;
    private int correctIndex;
    private boolean isEmptyTile;

    public void setEmptyTile(boolean isEmptyTile) {
        this.isEmptyTile = isEmptyTile;
    }

    public Bitmap getImage() {
        return image;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public boolean isEmptyTile() {
        return isEmptyTile;
    }

    public GameTile(Bitmap image, int correctIndex, Boolean isEmptyTile) {
        this.image = image;
        this.correctIndex = correctIndex;
        this.isEmptyTile = isEmptyTile;
    }
}
