package com.example.myapplication;

import androidx.annotation.Nullable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class BouncingDVD extends View {
    private Bitmap dvd_bm;
    private Canvas canvas;
    private int dvd_x, dvd_y, x_dir, y_dir, dvd_height, dvd_width;

//    public BouncingDVD() {
//        // No args constructor
//    }

    public BouncingDVD(Context context) {
        super(context);

        // initialize bitmap
        dvd_x = 320; dvd_y = 470; x_dir = 1; y_dir = 1;
        dvd_bm = BitmapFactory.decodeResource(getResources(), R.drawable.dvd);

        // finding width and height of bitmap
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), R.drawable.dvd, option);

        dvd_height = option.outHeight;
        dvd_width = option.outWidth;

        // update location
        if (dvd_x >= canvas.getWidth()-dvd_width) {
            x_dir = -1;
        } else if (dvd_x <= 0) {
            x_dir = 1;
        }

        if (dvd_y >= canvas.getHeight()-dvd_height) {
            y_dir = -1;
        } else if (dvd_y <= 0) {
            y_dir = 1;
        }

        dvd_x = dvd_x + x_dir;
        dvd_y = dvd_y + y_dir;

        // draw bitmap
        canvas.drawBitmap(dvd_bm, dvd_x, dvd_y, null);
        invalidate();
    }

    public BouncingDVD(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BouncingDVD(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BouncingDVD(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}