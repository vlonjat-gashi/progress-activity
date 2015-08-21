package com.vlonjatg.sample.progressactivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.malinskiy.materialicons.IconDrawable;
import com.malinskiy.materialicons.Iconify;
import com.vlonjatg.progressactivity.ProgressActivity;

public class DetailsActivity extends AppCompatActivity {

    Toolbar activityToolbar;

    ProgressActivity progressActivity;

    private View.OnClickListener errorClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Toast.makeText(getApplication(), "Try again button clicked", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        activityToolbar = (Toolbar) findViewById(R.id.activityToolbar);
        progressActivity = (ProgressActivity) findViewById(R.id.progress);

        setToolbar();

        Drawable emptyDrawable = new IconDrawable(this, Iconify.IconValue.zmdi_shopping_basket)
                .colorRes(android.R.color.white);

        Drawable errorDrawable = new IconDrawable(this, Iconify.IconValue.zmdi_wifi_off)
                .colorRes(android.R.color.white);


        String state = getIntent().getStringExtra("STATE");
        switch (state) {
            case "LOADING":
                progressActivity.showLoading();
                setTitle("Loading");
                break;
            case "EMPTY":
                progressActivity.showEmpty(emptyDrawable,
                        "Empty Shopping Cart",
                        "Please add things in the cart to continue.");
                setTitle("Empty");
                break;
            case "ERROR":
                progressActivity.showError(errorDrawable,
                        "No Connection",
                        "We could not establish a connection with our servers. Please try again when you are connected to the internet.",
                        "Try Again",
                        errorClickListener);
                setTitle("Error");
                break;
        }
    }

    private void setToolbar() {
        setSupportActionBar(activityToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle("Progress Activity");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
