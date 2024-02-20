package com.example.a2d;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView shapeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        shapeImageView = findViewById(R.id.shapeImageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.line) {
            shapeImageView.setImageResource(R.drawable.line);
            return true;
        } else if (item.getItemId() == R.id.oval) {
            shapeImageView.setImageResource(R.drawable.oval);
            return true;
        } else if (item.getItemId() == R.id.rectangle) {
            shapeImageView.setImageResource(R.drawable.rectangle);
            return true;
        } else if (item.getItemId() == R.id.roundrec) {
            shapeImageView.setImageResource(R.drawable.roundrec);
            return true;
        } else if (item.getItemId() == R.id.path) {
            shapeImageView.setImageResource(R.drawable.path);
            return true;
        } else if (item.getItemId() == R.id.arc) {
            shapeImageView.setImageResource(R.drawable.arc);
            return true;
        }
        else {
            super.onOptionsItemSelected(item);
        }
        return false;

    }
}