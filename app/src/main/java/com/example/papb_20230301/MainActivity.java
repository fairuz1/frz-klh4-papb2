package com.example.papb_20230301;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensorAccelerometer;
    private Sensor mSensorMagnetometer;

    private TextView mTextSensorAzimuth;
    private TextView mTextSensorPitch;
    private TextView mTextSensorRoll;

    private float[] mAccelerometerData = new float[3];
    private float[] mMagnetometerData = new float[3];

    private static final float VALUE_DRIFT = (float) 0.05f;

    private ImageView mSpotTop;
    private ImageView mSpotBottom;
    private ImageView mSpotRight;
    private ImageView mSpotLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mTextSensorAzimuth = findViewById(R.id.value_azimuth);
        mTextSensorPitch = findViewById(R.id.value_pitch);
        mTextSensorRoll = findViewById(R.id.value_roll);

        mSpotTop = findViewById(R.id.spot_top);
        mSpotBottom = findViewById(R.id.spot_bottom);
        mSpotLeft = findViewById(R.id.spot_left);
        mSpotRight = findViewById(R.id.spot_right);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mSensorMagnetometer != null) {
            mSensorManager.registerListener(this, mSensorMagnetometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorAccelerometer != null) {
            mSensorManager.registerListener(this, mSensorAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();
        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER:
                mAccelerometerData = sensorEvent.values.clone();
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                mMagnetometerData = sensorEvent.values.clone();
                break;
            default:
        }

        float[] rotationMatrix = new float[9];
        float[] orientationValues = new float[3];
        boolean rotationOX = SensorManager.getRotationMatrix(rotationMatrix, null, mAccelerometerData, mMagnetometerData);

        if (rotationOX) {
            SensorManager.getOrientation(rotationMatrix, orientationValues);
        }

        float azimuth = orientationValues[0];
        float pitch = orientationValues[1];
        float roll = orientationValues[2];
//
//        mTextSensorPitch.setText(getResources().getString(R.string.value_format, pitch));
//        mTextSensorRoll.setText(getResources().getString(R.string.value_format, roll));
//        mTextSensorAzimuth.setText(getResources().getString(R.string.value_format, azimuth));

        mTextSensorPitch.setText(String.valueOf(pitch));
        mTextSensorRoll.setText(String.valueOf(roll));
        mTextSensorAzimuth.setText(String.valueOf(azimuth));

        if (Math.abs(pitch) < VALUE_DRIFT) {
            pitch = 0;
        } else if (Math.abs(roll) < VALUE_DRIFT) {
            roll = 0;
        }

        mSpotTop.setAlpha(0f);
        mSpotBottom.setAlpha(0f);
        mSpotLeft.setAlpha(0f);
        mSpotRight.setAlpha(0f);

        if (pitch > 0) {
            mSpotBottom.setAlpha(pitch);
        } else {
            mSpotTop.setAlpha(Math.abs(pitch));
        }

        if (roll > 0) {
            mSpotLeft.setAlpha(roll);
        } else {
            mSpotRight.setAlpha(Math.abs(roll));
        }
    }

//    private void changeBackgroundColor(float currentValue, Button button) {
//        if (mTextSensorLight.equals(button)) {
//            if (currentValue == 0) {
//                button.setBackgroundColor(Color.BLACK);
//                button.setTextColor(Color.WHITE);
//            } else {
//                if (currentValue <= 40000 && currentValue >= 20000) {
//                    button.setBackgroundColor(Color.RED);
//                    button.setTextColor(Color.WHITE);
//                } else if (currentValue <= 20000 && currentValue >= 10) {
//                    button.setBackgroundColor(Color.BLUE);
//                    button.setTextColor(Color.WHITE);
//                }
//            }
//        } else if (mTextSensorProximity.equals(button)) {
//            if (currentValue == 0) {
//                button.setBackgroundColor(Color.BLACK);
//                button.setTextColor(Color.WHITE);
//            } else {
//                if (currentValue <= 2 && currentValue > 0) {
//                    button.setBackgroundColor(Color.BLUE);
//                    button.setTextColor(Color.WHITE);
//                } else if (currentValue <= 6 && currentValue >= 2) {
//                    button.setBackgroundColor(Color.YELLOW);
//                    button.setTextColor(Color.BLACK);
//                } else if (currentValue >= 6) {
//                    button.setBackgroundColor(Color.RED);
//                    button.setTextColor(Color.WHITE);
//                }
//            }
//        } else if (mTextSensorAmbientTemperature.equals(button) || mTextSensorHumidity.equals(button)) {
//            if (currentValue <= 0) {
//                button.setBackgroundColor(Color.BLACK);
//                button.setTextColor(Color.WHITE);
//            } else {
//                if (currentValue <= 20 && currentValue > 0) {
//                    button.setBackgroundColor(Color.BLUE);
//                    button.setTextColor(Color.WHITE);
//                } else if (currentValue <= 60 && currentValue >= 20) {
//                    button.setBackgroundColor(Color.YELLOW);
//                    button.setTextColor(Color.BLACK);
//                } else if (currentValue >= 60) {
//                    button.setBackgroundColor(Color.RED);
//                    button.setTextColor(Color.WHITE);
//                }
//            }
//        } else if (mTextSensorPressure.equals(button)) {
//            if (currentValue == 0) {
//                button.setBackgroundColor(Color.BLACK);
//                button.setTextColor(Color.WHITE);
//            } else {
//                if (currentValue <= 200 && currentValue > 0) {
//                    button.setBackgroundColor(Color.BLUE);
//                    button.setTextColor(Color.WHITE);
//                } else if (currentValue <= 600 && currentValue >= 200) {
//                    button.setBackgroundColor(Color.YELLOW);
//                    button.setTextColor(Color.BLACK);
//                } else if (currentValue >= 800) {
//                    button.setBackgroundColor(Color.RED);
//                    button.setTextColor(Color.WHITE);
//                }
//            }
//        } else if (mTextSensorGyroscope.equals(button)) {
//            if (currentValue <= 0) {
//                button.setBackgroundColor(Color.BLACK);
//                button.setTextColor(Color.WHITE);
//            } else {
//                if (currentValue <= 2) {
//                    button.setBackgroundColor(Color.BLUE);
//                    button.setTextColor(Color.WHITE);
//                } else if (currentValue >= 2.5) {
//                    button.setBackgroundColor(Color.YELLOW);
//                    button.setTextColor(Color.BLACK);
//                } else if (currentValue >= 3) {
//                    button.setBackgroundColor(Color.RED);
//                    button.setTextColor(Color.WHITE);
//                }
//            }
//        }
//    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}