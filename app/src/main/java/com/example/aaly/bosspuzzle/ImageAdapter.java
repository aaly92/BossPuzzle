package com.example.aaly.bosspuzzle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

public class ImageAdapter extends BaseAdapter {
    private Context context;

    private NPuzzle game;

    public ImageAdapter(Context c, Bitmap image, int numOfPuzzlePieces, int movesToShuffle) {
        this.context = c;
        this.game = new NPuzzle(image, numOfPuzzlePieces, movesToShuffle);
    }


    @Override
    public int getCount() {
        return game.getGameTiles().size();
    }

    @Override
    public GameTile getItem(int position) {
        return game.getGameTiles().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game.moveTile(position)) {
                    notifyDataSetChanged();
                    if (game.isWon()) {
                        Toast.makeText(context, "You Win!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        GameTile gameTile = game.getGameTiles().get(position);
        if (gameTile.isEmptyTile()) {
            gameTile.getImage().eraseColor(Color.BLACK);
        }
        imageView.setImageBitmap(gameTile.getImage());
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        return imageView;
    }


}