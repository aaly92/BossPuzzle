package com.example.aaly.bosspuzzle;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class NPuzzle {
    private ArrayList<GameTile> gameTiles;
    private int dimension;
    private int emptyTilePosition;

    public NPuzzle(Bitmap image, int numOfPuzzlePieces, int movesToShuffle) {
        this.dimension = (int) (Math.sqrt(numOfPuzzlePieces + 1));
        this.gameTiles = this.splitBitmap(image, dimension);
        shuffleTiles(movesToShuffle);
    }

    public ArrayList<GameTile> getGameTiles() {
        return gameTiles;
    }

    public boolean moveTile(int tileClickedPosition) {
        Coordinate emptyTileCoord = positionToCoordinates(emptyTilePosition, dimension);
        Coordinate currentTileCoord = positionToCoordinates(tileClickedPosition, dimension);
        if (Coordinate.sameCoordinates(emptyTileCoord, new Coordinate(currentTileCoord.x + 1, currentTileCoord.y)) ||
                Coordinate.sameCoordinates(emptyTileCoord, new Coordinate(currentTileCoord.x - 1, currentTileCoord.y)) ||
                Coordinate.sameCoordinates(emptyTileCoord, new Coordinate(currentTileCoord.x, currentTileCoord.y + 1)) ||
                Coordinate.sameCoordinates(emptyTileCoord, new Coordinate(currentTileCoord.x, currentTileCoord.y - 1))
                ) {
            Collections.swap(gameTiles, tileClickedPosition, emptyTilePosition);
            setEmptyTilePosition(tileClickedPosition);
            return true;
        } else {
            return false;
        }
    }

    public boolean isWon() {
        for (int i = 0; i < gameTiles.size(); i++) {
            if (gameTiles.get(i).getCorrectIndex() != i) {
                return false;
            }
        }
        return true;
    }

    private ArrayList<GameTile> splitBitmap(Bitmap bitmap, int dimension) {
        int imgWidth = bitmap.getWidth();
        int imgHeight = bitmap.getHeight();
        int tileWidth = imgWidth / dimension;
        int tileHeight = imgHeight / dimension;
        int x = 0;
        int y = 0;
        int arrayIndex = 0;
        ArrayList<GameTile> tiles = new ArrayList<>();

        for (int r = 0; r < dimension; r++) {
            for (int c = 0; c < dimension; c++) {
                tiles.add(new GameTile(Bitmap.createBitmap(bitmap, x, y, tileWidth, tileHeight), arrayIndex, false));
                arrayIndex++;
                x += tileWidth;
            }
            x = 0;
            y += tileHeight;
        }
        tiles.get(tiles.size() - 1).setEmptyTile(true);
        setEmptyTilePosition(tiles.size() - 1);
        return tiles;
    }

    private void shuffleTiles(int movesToShuffle) {
        Random rand = new Random();
        for (int i = 0; i < movesToShuffle; i++) {
            Direction directionToMoveEmptyTile = Direction.values()[rand.nextInt(4)];
            Coordinate emptyTileCoord = positionToCoordinates(emptyTilePosition, dimension);
            int positionTileToSwap = -1;
            switch (directionToMoveEmptyTile.name()) {
                case "UP":
                    positionTileToSwap = coordinatesToPosition(emptyTileCoord.x, emptyTileCoord.y - 1, dimension);
                    break;
                case "RIGHT":
                    positionTileToSwap = coordinatesToPosition(emptyTileCoord.x + 1, emptyTileCoord.y, dimension);
                    break;
                case "DOWN":
                    positionTileToSwap = coordinatesToPosition(emptyTileCoord.x, emptyTileCoord.y + 1, dimension);
                    break;
                case "LEFT":
                    positionTileToSwap = coordinatesToPosition(emptyTileCoord.x - 1, emptyTileCoord.y, dimension);
                    break;
            }
            if (positionTileToSwap >= 0 && isInBounds(positionTileToSwap)) {
                Collections.swap(gameTiles, emptyTilePosition, positionTileToSwap);
                setEmptyTilePosition(positionTileToSwap);
            } else {
                i--;
            }
        }
    }

    private void setEmptyTilePosition(int emptyTilePosition) {
        this.emptyTilePosition = emptyTilePosition;
    }

    public static Coordinate positionToCoordinates(int position, int dimension) {
        return new Coordinate(position / dimension, position % dimension);
    }

    public static int coordinatesToPosition(int x, int y, int dimension) {
        return y * dimension + x;
    }

    private boolean isInBounds(int position) {
        return position >= 0 && position < gameTiles.size();
    }

}
