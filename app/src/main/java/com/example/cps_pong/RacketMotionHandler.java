package com.example.cps_pong;



import static android.app.PendingIntent.getActivity;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.security.AccessControlContext;

public class RacketMotionHandler {
    SensorManager sensorManager;
    Sensor sensor;
    Activity activity;

    Racket racket;
    public RacketMotionHandler(Activity activity,Racket racket){
        this.racket=racket;
        this.activity=activity;
        sensorManager = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float xValue = event.values[0];
            //Toast.makeText(RacketMotionHandler.this.activity, "shake function activated"+xValue, Toast.LENGTH_SHORT).show();
            racket.updateVelocity(xValue*1F);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
    public void getMovement(){

    }
}
