package com.example.ppapb_uas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class CustomImage extends AppCompatActivity {

    private ImageView mImgView;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Paint mCirclePaint = new Paint();
    private Paint mHeadPaint = new Paint();
    private Paint mConnectorPaint = new Paint();
    private Point centerCanvas;
//    private View firstView;
//    private View secondView;

    private int mColorBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_image);

        mImgView = findViewById(R.id.my_img_view);

//         mCirclePaint.setColor(getResources().getColor(R.color.black));
//         mHeadPaint.setColor(getResources().getColor(R.color.white));

        mCirclePaint.setColor(ResourcesCompat.getColor(getResources(), R.color.black, null));
        mHeadPaint.setColor(ResourcesCompat.getColor(getResources(), R.color.white, null));
        mConnectorPaint.setColor(ResourcesCompat.getColor(getResources(), R.color.black, null));
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int vWidth = mImgView.getWidth();
        int vHeight = mImgView.getHeight();

        mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
        mImgView.setImageBitmap(mBitmap);
        mColorBackground = ResourcesCompat.getColor(getResources(), R.color.yellow, null);
        mCanvas = new Canvas(mBitmap);
        mCanvas.drawColor(mColorBackground);

        // get center point of the canvas
        centerCanvas = getCanvasCenter();

        // draw the custom images
        drawHead();
        drawRightEye();
        drawLeftEye();
        drawEyeConnector();

        animateCustomImage();
    }

    private void drawHead() {
        // initialize rectangle based on the center point
        int rectW = 700;
        int rectH = 400;

        int left = centerCanvas.x - (rectW / 2);
        int top = centerCanvas.y - (rectH / 2);
        int right = centerCanvas.x + (rectW / 2);
        int bottom = centerCanvas.y + (rectH / 2);

        RectF rect = new RectF(left, top, right, bottom);

        // draw the rectangle
        mConnectorPaint.setStrokeWidth(10);
        mCanvas.drawOval(rect, mHeadPaint);
    }

    private void drawRightEye() {
        // set width and height of circle
        int width = mCanvas.getWidth()/10;
        int height = mCanvas.getWidth()/10;

        // set the circle position
        int positionX = centerCanvas.x + (200 / 2);
        int positionY = centerCanvas.y + (10 / 2);

        // draw the circle
        float mRadius = (float) (Math.min(width, height/2*0.8));
        mCanvas.drawCircle(positionX, positionY, mRadius, mCirclePaint);
    }

    private void drawLeftEye() {
        // set width and height of circle
        int width = mCanvas.getWidth()/10;
        int height = mCanvas.getWidth()/10;

        // set the circle position
        int positionX = centerCanvas.x - (200 / 2);
        int positionY = centerCanvas.y + (10 / 2);

        // draw the circle
        float mRadius = (float) (Math.min(width, height/2*0.8));
        mCanvas.drawCircle(positionX, positionY, mRadius, mCirclePaint);
    }

    private void drawEyeConnector() {
        // initialize rectangle based on the center point
        int rectW = 200;
        int rectH = 20;

        int left = centerCanvas.x - (rectW / 2);
        int top = centerCanvas.y - (rectH / 2);
        int right = centerCanvas.x + (rectW / 2);
        int bottom = centerCanvas.y + (rectH / 2);

        Rect rect = new Rect(left, top, right, bottom);

        // draw the rectangle
        mConnectorPaint.setStrokeWidth(10);
        mCanvas.drawRect(rect, mConnectorPaint);
    }

    private Point getCanvasCenter() {
        int canvasWidth = mCanvas.getWidth();
        int canvasHeight = mCanvas.getHeight();
        Point center = new Point(canvasWidth / 2, canvasHeight / 2);
        return center;
    }

    private void animateCustomImage() {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator());
        fadeIn.setDuration(3000);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setStartOffset(1000);
        fadeOut.setDuration(1000);

        AnimationSet animation = new AnimationSet(false);
        animation.addAnimation(fadeIn);
        mImgView.setAnimation(animation);
        Handler delay = new Handler();

        delay.postDelayed(new Runnable(){
            public void run() {
                mImgView.animate().rotationYBy(180).setDuration(3000);
                mImgView.setAnimation(animation);
            }
        }, 1000);


        delay.postDelayed(new Runnable(){
            public void run() {
                animation.addAnimation(fadeOut);
                mImgView.setAnimation(animation);
                mImgView.setVisibility(View.GONE);
            }
        }, 2500);
    }
}