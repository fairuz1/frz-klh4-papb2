package com.example.papb_20230301;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensorLight;
    private Sensor mSensorProximity;

    private Sensor mSensorPressure;
    private Sensor mSensorHumidity;
    private Sensor mSensorAmbientTemperature;
    private Sensor mSensorGyroscope;

    private TextView mTextSensorLight;
    private TextView mTextSensorProximity;

    private Button mTextSensorPressure;
    private Button mTextSensorHumidity;
    private Button mTextSensorAmbientTemperature;
    private Button mTextSensorGyroscope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get sensor manager and search all sensors available to be put into sensorList
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        StringBuilder sensorText = new StringBuilder();
        for (Sensor currentSensor : sensorList) {
            sensorText.append(currentSensor.getName()).append(System.getProperty("line.separator"));
        }

        // display all sensorList data into TextView
        TextView sensorTextView = findViewById(R.id.sensor_list);
        sensorTextView.setText(sensorText);

        // get TextView or button of each sensors
        mTextSensorLight = findViewById(R.id.btn_sensor_color_light);
        mTextSensorProximity = findViewById(R.id.btn_sensor_color_proximity);

        mTextSensorHumidity = findViewById(R.id.btn_sensor_color_humidity);
        mTextSensorAmbientTemperature = findViewById(R.id.btn_sensor_color_ambient);
        mTextSensorPressure = findViewById(R.id.btn_sensor_color_pressure);
        mTextSensorGyroscope = findViewById(R.id.btn_sensor_color_gyroscope);

        // get each specific type of sensors
        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mSensorProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mSensorHumidity = mSensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        mSensorPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mSensorAmbientTemperature = mSensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        mSensorGyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        String sensor_error = "No Sensor";
        if (mSensorLight == null) {
            mTextSensorLight.setText(sensor_error);
        }
        if (mSensorProximity == null) {
            mTextSensorProximity.setText(sensor_error);
        }
        if (mSensorHumidity == null) {
            mTextSensorHumidity.setText(sensor_error);
        }
        if (mSensorPressure == null) {
            mTextSensorPressure.setText(sensor_error);
        }
        if (mSensorAmbientTemperature == null) {
            mTextSensorAmbientTemperature.setText(sensor_error);
        }
        if (mSensorGyroscope == null) {
            mTextSensorGyroscope.setText(sensor_error);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mSensorProximity != null) {
            mSensorManager.registerListener(this, mSensorProximity, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorLight != null) {
            mSensorManager.registerListener(this, mSensorLight, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorHumidity != null) {
            mSensorManager.registerListener(this, mSensorHumidity, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorPressure != null) {
            mSensorManager.registerListener(this, mSensorPressure, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorAmbientTemperature != null) {
            mSensorManager.registerListener(this, mSensorAmbientTemperature, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorGyroscope != null) {
            mSensorManager.registerListener(this, mSensorGyroscope, SensorManager.SENSOR_DELAY_NORMAL);
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
        float currentValue = sensorEvent.values[0];

        switch (sensorType) {
            case Sensor.TYPE_LIGHT:
                mTextSensorLight.setText(String.format("%s lux", String.valueOf(sensorEvent.values[0])));
                changeBackgroundColor(currentValue, (Button) mTextSensorLight);
                break;
            case Sensor.TYPE_PROXIMITY:
                mTextSensorProximity.setText(String.format("%s cm", String.valueOf(sensorEvent.values[0])));
                changeBackgroundColor(currentValue, (Button) mTextSensorProximity);
                break;
            case Sensor.TYPE_RELATIVE_HUMIDITY:
                mTextSensorHumidity.setText(String.valueOf(sensorEvent.values[0]) + " %");
                changeBackgroundColor(currentValue, (Button) mTextSensorHumidity);
                break;
            case Sensor.TYPE_PRESSURE:
                mTextSensorPressure.setText(String.format("%s hPa", String.valueOf(sensorEvent.values[0])));
                changeBackgroundColor(currentValue, (Button) mTextSensorPressure);
                break;
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                mTextSensorAmbientTemperature.setText(String.format("%s Celsius", String.valueOf(sensorEvent.values[0])));
                changeBackgroundColor(currentValue, (Button) mTextSensorAmbientTemperature);
                break;
            case Sensor.TYPE_GYROSCOPE:
                mTextSensorGyroscope.setText(String.format("%s Degree", String.valueOf(sensorEvent.values[0])));
                changeBackgroundColor(currentValue, (Button) mTextSensorGyroscope);
                break;
            default:
        }
    }

    private void changeBackgroundColor(float currentValue, Button button) {
        if (mTextSensorLight.equals(button)) {
            if (currentValue == 0) {
                button.setBackgroundColor(Color.BLACK);
                button.setTextColor(Color.WHITE);
            } else {
                if (currentValue <= 40000 && currentValue >= 20000) {
                    button.setBackgroundColor(Color.RED);
                    button.setTextColor(Color.WHITE);
                } else if (currentValue <= 20000 && currentValue >= 10) {
                    button.setBackgroundColor(Color.BLUE);
                    button.setTextColor(Color.WHITE);
                }
            }
        } else if (mTextSensorProximity.equals(button)) {
            if (currentValue == 0) {
                button.setBackgroundColor(Color.BLACK);
                button.setTextColor(Color.WHITE);
            } else {
                if (currentValue <= 2 && currentValue > 0) {
                    button.setBackgroundColor(Color.BLUE);
                    button.setTextColor(Color.WHITE);
                } else if (currentValue <= 6 && currentValue >= 2) {
                    button.setBackgroundColor(Color.YELLOW);
                    button.setTextColor(Color.BLACK);
                } else if (currentValue >= 6) {
                    button.setBackgroundColor(Color.RED);
                    button.setTextColor(Color.WHITE);
                }
            }
        } else if (mTextSensorAmbientTemperature.equals(button) || mTextSensorHumidity.equals(button)) {
            if (currentValue <= 0) {
                button.setBackgroundColor(Color.BLACK);
                button.setTextColor(Color.WHITE);
            } else {
                if (currentValue <= 20 && currentValue > 0) {
                    button.setBackgroundColor(Color.BLUE);
                    button.setTextColor(Color.WHITE);
                } else if (currentValue <= 60 && currentValue >= 20) {
                    button.setBackgroundColor(Color.YELLOW);
                    button.setTextColor(Color.BLACK);
                } else if (currentValue >= 60) {
                    button.setBackgroundColor(Color.RED);
                    button.setTextColor(Color.WHITE);
                }
            }
        } else if (mTextSensorPressure.equals(button)) {
            if (currentValue == 0) {
                button.setBackgroundColor(Color.BLACK);
                button.setTextColor(Color.WHITE);
            } else {
                if (currentValue <= 200 && currentValue > 0) {
                    button.setBackgroundColor(Color.BLUE);
                    button.setTextColor(Color.WHITE);
                } else if (currentValue <= 600 && currentValue >= 200) {
                    button.setBackgroundColor(Color.YELLOW);
                    button.setTextColor(Color.BLACK);
                } else if (currentValue >= 800) {
                    button.setBackgroundColor(Color.RED);
                    button.setTextColor(Color.WHITE);
                }
            }
        } else if (mTextSensorGyroscope.equals(button)) {
            if (currentValue <= 0) {
                button.setBackgroundColor(Color.BLACK);
                button.setTextColor(Color.WHITE);
            } else {
                if (currentValue <= 2) {
                    button.setBackgroundColor(Color.BLUE);
                    button.setTextColor(Color.WHITE);
                } else if (currentValue >= 2.5) {
                    button.setBackgroundColor(Color.YELLOW);
                    button.setTextColor(Color.BLACK);
                } else if (currentValue >= 3) {
                    button.setBackgroundColor(Color.RED);
                    button.setTextColor(Color.WHITE);
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}