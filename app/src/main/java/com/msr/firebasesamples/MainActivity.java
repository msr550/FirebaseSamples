package com.msr.firebasesamples;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.msr.firebasesamples.activities.BaseActivity;
import com.msr.firebasesamples.activities.SignInActivity;
import com.msr.firebasesamples.activities.SignUpActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.signInBtn:
                startActivity(new Intent(this, SignInActivity.class));
                break;
            case R.id.signUpBtn:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
        }
    }
}
