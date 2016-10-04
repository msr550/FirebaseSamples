package com.msr.firebasesamples.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Sandeep Maram on 10/4/2016.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void getToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
