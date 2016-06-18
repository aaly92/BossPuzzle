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

    public int numOfPuzzlePieces = -1;

    private Target loadtarget;

    private static ImageAdapter gameAdapter;
    private static int lastGridViewNumCols = 0;

    @BindView(R.id.puzzle)
    GridView puzzleView;

    @BindView(R.id.puzzle_8)
    Button puzzle8;

    @BindView(R.id.puzzle_15)
    Button puzzle15;

    @BindView(R.id.puzzle_24)
    Button puzzle24;

//    @BindView(R.id.save)
//    Button saveButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.puzzle_8)
    public void puzzle8() {
        lastGridViewNumCols = 3;
        puzzleView.setNumColumns(lastGridViewNumCols);
        numOfPuzzlePieces = 8;
        loadBitmap(IMAGE_URL);
    }

    @OnClick(R.id.puzzle_15)
    public void puzzle15() {
        lastGridViewNumCols = 4;
        puzzleView.setNumColumns(lastGridViewNumCols);
        numOfPuzzlePieces = 15;
        loadBitmap(IMAGE_URL);
    }

    @OnClick(R.id.puzzle_24)
    public void puzzle24() {
        lastGridViewNumCols = 5;
        puzzleView.setNumColumns(lastGridViewNumCols);
        numOfPuzzlePieces = 24;
        loadBitmap(IMAGE_URL);
    }

//    @OnClick(R.id.save)
//    public void save() {
//
//    }

    @Override
    protected void onResume() {
        super.onResume();
        puzzleView.setNumColumns(lastGridViewNumCols);
        puzzleView.setAdapter(gameAdapter);
    }

    public void loadBitmap(String url ) {
        if (loadtarget == null) loadtarget = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                gameAdapter = new ImageAdapter(MainActivity.this, bitmap, numOfPuzzlePieces, MOVES_TO_SHUFFLE );
                puzzleView.setAdapter(gameAdapter);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        Picasso.with(this).load(url).into(loadtarget);
    }
}
