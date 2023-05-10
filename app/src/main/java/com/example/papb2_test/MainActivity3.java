package com.example.papb2_test;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

public class MainActivity3 extends AppCompatActivity {

    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Paint mPaintText = new Paint(Paint.UNDERLINE_TEXT_FLAG);
    private Bitmap mBitmap;
    private ImageView mImageView;
    private Rect mRect = new Rect();
    private Rect mBounds = new Rect();

    private static final int OFFSET = 120;
    private int mOffset = OFFSET;
    private static final int MULTIPLIER = 100;

    private int mColorBackground;
    private int mColorRectangle;
    private int mColorAccent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mColorBackground = ResourcesCompat.getColor(getResources(), R.color.colorBackground, null);
        mColorAccent = ResourcesCompat.getColor(getResources(), R.color.colorAccent, null);
        mColorRectangle = ResourcesCompat.getColor(getResources(), R.color.colorRectangle, null);

        mPaint.setColor(mColorBackground);
        mPaintText.setColor(ResourcesCompat.getColor(getResources(), R.color.black, null));
        mPaintText.setTextSize(70);

        mImageView = findViewById(R.id.myimageview);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawSomething(view);
            }
        });
    }

    private void drawSomething(View view) {
        int vWidth = view.getWidth();
        int vHeight = view.getHeight();
        int halfwidth = vWidth/2;
        int halfHeight = vHeight/2;

        if (mOffset == OFFSET) {
            mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
            mImageView.setImageBitmap(mBitmap);

            mCanvas = new Canvas(mBitmap);
            // mCanvas.drawColor(mColorBackground);
            // mCanvas.drawText(getString(R.string.keep_tappng), 100, 100, mPaintText);
            mOffset += OFFSET;

        } else {
            if (mOffset < halfwidth && mOffset < halfHeight) {
                mCanvas.drawCircle(halfwidth, halfHeight, halfwidth/1.5f, mPaint);
                mPaint.setColor(mColorRectangle - MULTIPLIER * mOffset);
                mOffset += OFFSET;
            } else {
                mPaint.setColor(mColorAccent - MULTIPLIER * mOffset);
                mOffset += OFFSET;
                mPaint.setColor(mColorRectangle - MULTIPLIER * mOffset);

                Path path = new Path();

                float mid = vWidth / 2;
                float min = Math.min(vWidth, vHeight);
                float half = (min / 1.35f) + 100;
                mid = mid - half;

                path.reset();
                Paint paint = new Paint();
                paint.setColor(Color.WHITE);
                mPaint.setStyle(Paint.Style.FILL);

                // Atas Kiri
                path.moveTo(mid + half * 0.5f, half * 0.84f);
                // Atas Kanan
                path.lineTo(mid + half * 1.5f, half * 0.84f);
                // Bawah Kiri
                path.lineTo(mid + half * 0.68f, half * 1.45f);
                // Tip Atas
                path.lineTo(mid + half * 1.0f, half * 0.5f);
                // Bawah Kanan
                path.lineTo(mid + half * 1.32f, half * 1.45f);
                // Atas Kiri
                path.lineTo(mid + half * 0.5f, half * 0.84f);

                path.close();
                mCanvas.drawCircle(halfwidth, halfHeight, halfwidth/1.5f, mPaint);
                mPaint.setColor(mColorRectangle - MULTIPLIER * mOffset);

                mCanvas.drawPath(path, paint);
            }
        }
    }
}