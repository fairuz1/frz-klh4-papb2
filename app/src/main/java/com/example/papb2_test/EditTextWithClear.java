package com.example.papb2_test;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.res.ResourcesCompat;

import java.util.Locale;

public class EditTextWithClear extends AppCompatEditText {

    Drawable mCLearButtonImage;
    boolean isClearButtonClicked;

    private void init() {
        mCLearButtonImage = ResourcesCompat.getDrawable(
                getResources(), R.drawable.ic_clear_opaque_24dp, null
        );

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                showClearButton();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // check if compound in left
                if(getCompoundDrawables()[0] != null){
                    float clearButtonStart = (getWidth()-getPaddingEnd()-mCLearButtonImage.getIntrinsicWidth());
                    isClearButtonClicked = false;
                    if (event.getX() < clearButtonStart){
                        isClearButtonClicked = true;
                    }

                // check if compound in right
                } else if (getCompoundDrawables()[2] != null) {
                    float clearButtonStart = (getWidth()-getPaddingEnd()-mCLearButtonImage.getIntrinsicWidth());
                    isClearButtonClicked = false;
                    if (event.getX() > clearButtonStart){
                        isClearButtonClicked = true;
                    }
                }

                if (isClearButtonClicked){
                    // before click
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        mCLearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_black_24dp, null);
                        showClearButton();
                    }

                    // after click
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        mCLearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_opaque_24dp, null);
                        getText().clear();
                        hideClearButton();
                        return true;
                    }
                }
                else {
                    return false;
                }

                return false;
            }
        });
    }


    public EditTextWithClear(@NonNull Context context) {
        super(context);
        init();
    }

    public EditTextWithClear(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditTextWithClear(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void showClearButton() {
        setCompoundDrawablesRelativeWithIntrinsicBounds(
                null, null, mCLearButtonImage, null
        );
    }

    private void hideClearButton() {
        setCompoundDrawablesWithIntrinsicBounds(
                null, null, null, null
        );
    }

//    Drawable mClearButtonImage;
//
//    private void init() {
//        mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_opaque_24dp, null);
//        addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                showClearButton();
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
////        setOnClickListener(new OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                if (isClearButtonClicked) {
////                    isClearButtonClicked = false;
////                    mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_opaque_24dp, null);
////                    getText().clear();
////                    hideClearButton();
////
////                } else {
////                    isClearButtonClicked = true;
////                    mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_black_24dp, null);
////                    showClearButton();
////                }
////            }
////        });
//
//        setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                if (getCompoundDrawables()[2] != null) {
//                    float clearButtonStart = (getWidth() - getPaddingEnd() - mClearButtonImage.getIntrinsicWidth());
//                    boolean isClearButtonClicked = false;
//
//                    if (motionEvent.getX() > clearButtonStart) {
//                        isClearButtonClicked = true;
//                    }
//
//                    if (isClearButtonClicked) {
//                        if (motionEvent.getAction() == motionEvent.ACTION_DOWN) {
//                            mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_black_24dp, null);
//                            showClearButton();
//                        } else if (motionEvent.getAction() == motionEvent.ACTION_UP) {
//                            mClearButtonImage = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_clear_opaque_24dp, null);
//                            getText().clear();
//                            hideClearButton();
//                            return true;
//                        }
//                    } else {
//                        return false;
//                    }
//                }
//                return false;
//            }
//        });
//    }
//
//    public EditTextWithClear(@NonNull Context context) {
//        super(context);
//        init();
//    }
//
//    public EditTextWithClear(@NonNull Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        init();
//    }
//
//    public EditTextWithClear(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init();
//    }
//
//    private void showClearButton() {
//        setCompoundDrawables(null, null, mClearButtonImage, null);
//    }
//
//    private void hideClearButton() {
//        setCompoundDrawables(null, null, null, null);
//    }
}
