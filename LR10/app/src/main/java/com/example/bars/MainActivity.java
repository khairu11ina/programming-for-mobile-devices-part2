package com.example.bars;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ToggleButton;
public class MainActivity extends AppCompatActivity implements
        View.OnClickListener {
    ProgressBar progressBar;
    RatingBar ratingBar;
    TextView text;
    ToggleButton button;
    boolean isRunning = false;
    static final int NUM_STARS = 5;
    float step = 0.5f;
    float rating = 1.0f;
    Thread background;
    Handler handler = new Handler(Looper.getMainLooper());
    Runnable updateProgress = new Runnable() {
        @Override
        public void run() {
            progressBar.incrementProgressBy(1);
            text.setText("Progress: " + progressBar.getProgress() + "%");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        text = (TextView) findViewById(R.id.progress_text);
        ratingBar = (RatingBar) findViewById(R.id.rating);
        button = (ToggleButton) findViewById(R.id.start);
        TextView label = (TextView) findViewById(R.id.text);
        ratingBar.setNumStars(NUM_STARS);
        ratingBar.setRating(rating);
        ratingBar.setStepSize(step);
        progressBar.setProgress(0);
        label.setText(String.valueOf(rating));
        ratingBar.setOnRatingBarChangeListener(new
                                                       RatingBar.OnRatingBarChangeListener() {
                                                           @Override
                                                           public void onRatingChanged(RatingBar ratingBar, float rating, boolean
                                                                   fromUser) {
                                                               label.setText(String.valueOf(ratingBar.getRating()));
                                                           }
                                                       });
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start) {
            if (button.isChecked()) {
                background = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (isRunning) {
                            try {
                                Thread.sleep((long) (1000 /
                                        ratingBar.getRating()));
                                handler.post(updateProgress);
                            } catch (InterruptedException e) {
                                Log.e("ERROR", "Thread Interrupted");
                            }
                        }
                    }
                });
                isRunning = true;
                background.start();
            } else isRunning = false;
        }
        if (v.getId() == R.id.reset) {

            isRunning = false;
            progressBar.setProgress(0);
            text.setText("Progress: 0%");
        }
        if (v.getId() == R.id.button_down) {
            rating -= step;
            if (rating < 0.5)
                rating = 0.5F;
            ratingBar.setRating(rating);
        }
        if (v.getId() == R.id.button_up) {
                rating += step;
                if (rating > NUM_STARS)
                    rating = NUM_STARS;
                ratingBar.setRating(rating);

        }
    }
}
