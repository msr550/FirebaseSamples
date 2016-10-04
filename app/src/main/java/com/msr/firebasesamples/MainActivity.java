package com.msr.firebasesamples;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.msr.firebasesamples.activities.BaseActivity;
import com.msr.firebasesamples.activities.DashboardActivity;
import com.msr.firebasesamples.activities.SignUpActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(this, DashboardActivity.class));
        } else {
            startActivity(new Intent(this, SignUpActivity.class));
        }
    }
}
