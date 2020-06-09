package com.vlonjatg.sample.progressactivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setToolbar();
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            setTitle("Progress Activity");
        }
    }

    public void onLoadingStateClick(View view) {
        Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
        intent.putExtra("STATE", "LOADING");
        startActivity(intent);
    }

    public void onEmptyStateClick(View view) {
        Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
        intent.putExtra("STATE", "EMPTY");
        startActivity(intent);
    }

    public void onErrorStateClick(View view) {
        Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
        intent.putExtra("STATE", "ERROR");
        startActivity(intent);
    }

    public void onContentStateClick(View view) {
        Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
        intent.putExtra("STATE", "CONTENT");
        startActivity(intent);
    }
}
