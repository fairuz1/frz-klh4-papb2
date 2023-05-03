package com.example.papb2_test;

import android.graphics.Bitmap;
import android.graphics.Canvas;
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
//            mCanvas.drawColor(mColorBackground);
            mCanvas.drawText(getString(R.string.keep_tappng), 100, 100, mPaintText);
            mOffset += OFFSET;

        } else {
            if (mOffset < halfwidth && mOffset < halfHeight) {
                mPaint.setColor(mColorRectangle - MULTIPLIER * mOffset);
                mRect.set(mOffset, mOffset, vWidth - mOffset, vHeight - mOffset);
                mCanvas.drawRect(mRect, mPaint);
                mOffset += OFFSET;
                Log.d("mOffset", String.valueOf(mOffset));
            } else {
                mPaint.setColor(mColorAccent - MULTIPLIER * mOffset);
                mCanvas.drawCircle(halfwidth, halfHeight, halfwidth/5, mPaint);
                mOffset += OFFSET;

//                String text = getString(R.string.done);
//                mPaintText.getTextBounds(text, 0, text.length(), mBounds);
//                int x = halfwidth - mBounds.centerX();
//                int y = halfHeight - mBounds.centerY();
//                mCanvas.drawText(text, x, y, mPaintText);

                Point a = new Point(halfwidth - 50, halfHeight - 50);
                Point b = new Point(halfwidth + 50, halfHeight - 50);
                Point c = new Point(halfwidth, halfHeight + 50);

                Path path = new Path();
                path.setFillType(Path.FillType.EVEN_ODD);
                path.lineTo(a.x, a.y);
                path.lineTo(b.x, b.y);
                path.lineTo(c.x, c.y);
                path.lineTo(a.x, a.y);
                path.close();

                mPaint.setColor(mColorRectangle - MULTIPLIER * mOffset);
                mCanvas.drawPath(path, mPaint);

                Log.d("mOffset", String.valueOf(mOffset));
            }
        }
    }
}