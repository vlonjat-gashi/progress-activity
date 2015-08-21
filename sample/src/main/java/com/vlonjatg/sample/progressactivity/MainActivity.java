package com.vlonjatg.sample.progressactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Toolbar activityToolbar;

    Button loadingButton;
    Button emptyButton;
    Button errorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityToolbar = (Toolbar) findViewById(R.id.activityToolbar);
        loadingButton = (Button) findViewById(R.id.loadingButton);
        emptyButton = (Button) findViewById(R.id.emptyButton);
        errorButton = (Button) findViewById(R.id.errorButton);

        setToolbar();
        setListeners();
    }

    private void setToolbar() {
        setSupportActionBar(activityToolbar);

        if (getSupportActionBar() != null) {
            setTitle("Progress Activity");
        }
    }

    private void setListeners() {

        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                intent.putExtra("STATE", "LOADING");
                startActivity(intent);
            }
        });

        emptyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                intent.putExtra("STATE", "EMPTY");
                startActivity(intent);
            }
        });

        errorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                intent.putExtra("STATE", "ERROR");
                startActivity(intent);
            }
        });

    }
}
