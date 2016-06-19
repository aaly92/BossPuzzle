package com.example.aaly.bosspuzzle;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static final int MOVES_TO_SHUFFLE = 1;
    public static final String IMAGE_URL = "http://images.firstwefeast.com/complex/image/upload/lqraxly7fpssvft18z0s.jpg";
    private static int numOfPuzzlePieces;
    private static Target loadTarget;
    private static Bitmap image;
    private static ImageAdapter gameAdapter;

    @BindView(R.id.puzzle)
    GridView puzzleView;

    @BindView(R.id.puzzle_8)
    Button puzzle8;

    @BindView(R.id.puzzle_15)
    Button puzzle15;

    @BindView(R.id.puzzle_24)
    Button puzzle24;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.puzzle_8)
    public void puzzle8() {
        nPuzzleButtonAction(8);
    }

    @OnClick(R.id.puzzle_15)
    public void puzzle15() {
        nPuzzleButtonAction(15);
    }

    @OnClick(R.id.puzzle_24)
    public void puzzle24() {
        nPuzzleButtonAction(24);
    }

    void nPuzzleButtonAction(int pieces) {
        numOfPuzzlePieces = pieces;
        puzzleView.setNumColumns((int) Math.sqrt(pieces + 1));
        if (image != null) {
            onBitmapLoad(image);
        } else {
            loadBitmap(IMAGE_URL);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        puzzleView.setNumColumns((int) Math.sqrt(numOfPuzzlePieces + 1));
        puzzleView.setAdapter(gameAdapter);
    }

    void onBitmapLoad(Bitmap image) {
        gameAdapter = new ImageAdapter(MainActivity.this, image, numOfPuzzlePieces, MOVES_TO_SHUFFLE);
        puzzleView.setAdapter(gameAdapter);
    }

    public void loadBitmap(String url) {
        if (loadTarget == null) loadTarget = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                image = bitmap;
                onBitmapLoad(image);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        Picasso.with(this).load(url).into(loadTarget);
    }
}
