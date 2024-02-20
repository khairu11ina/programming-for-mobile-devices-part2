package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {
    private Chronometer chronometer;
    private List<View> allEds;

    private int counter = 0;

    long timeWhenStopped = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = (Chronometer) findViewById(R.id.chronometer);

        Button addButton = (Button) findViewById(R.id.button);
        allEds = new ArrayList<View>();
        final LinearLayout linear = (LinearLayout) findViewById(R.id.linear);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;

                //берем наш кастомный лейаут находим через него все наши кнопки и едит тексты, задаем нужные данные
                final View view = getLayoutInflater().inflate(R.layout.custom, null);
                Button deleteField = (Button) view.findViewById(R.id.button2);
                EditText text = (EditText) view.findViewById(R.id.editText);
                text.setText("Some text" + counter);

                allEds.add(view);

                linear.addView(view);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_start) {
            chronometer.setBase(SystemClock.elapsedRealtime()
                    + timeWhenStopped);
            chronometer.start();

        }
        if (v.getId() == R.id.button_stop) {
            timeWhenStopped = chronometer.getBase() -
                    SystemClock.elapsedRealtime();
            chronometer.stop();
        }
        if (v.getId() == R.id.button_reset) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            timeWhenStopped = 0;
        }
    }
}