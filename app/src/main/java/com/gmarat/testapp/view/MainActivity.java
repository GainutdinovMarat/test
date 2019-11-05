package com.gmarat.testapp.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gmarat.testapp.R;

public class MainActivity extends AppCompatActivity
        implements TabsFragment.OnTabItemSelectListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new TabsFragment(), "")
                    .commit();
        }
    }

    @Override
    public void onTabItemSelected() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new DetailsFragment(), "")
                .addToBackStack(null)
                .commit();
    }

}
