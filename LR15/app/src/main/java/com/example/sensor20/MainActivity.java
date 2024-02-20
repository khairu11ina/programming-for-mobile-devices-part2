package com.example.sensor20;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Sensor gyroscope;
    private Sensor humiditySensor;

    private TextView accelerometerData;
    private TextView gyroscopeData;
    private TextView humidityData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accelerometerData = findViewById(R.id.accelerometerData);
        gyroscopeData = findViewById(R.id.gyroscopeData);
        humidityData = findViewById(R.id.humidityData);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, humiditySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == accelerometer) {
            updateAccelerometerData(event.values);
        } else if (event.sensor == gyroscope) {
            updateGyroscopeData(event.values);
        } else if (event.sensor == humiditySensor) {
            updateHumidityData(event.values);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private void updateAccelerometerData(float[] values) {
        String data = "X: " + values[0] + "\nY: " + values[1] + "\nZ: " + values[2];
        accelerometerData.setText("Accelerometer Data: " + data);
    }

    private void updateGyroscopeData(float[] values) {
        String data = "X: " + values[0] + "\nY: " + values[1] + "\nZ: " + values[2];
        gyroscopeData.setText("Gyroscope Data: " + data);
    }

    private void updateHumidityData(float[] values) {
        String data = "Humidity: " + values[0] + " %";
        humidityData.setText("Humidity Data: " + data);
    }
}
