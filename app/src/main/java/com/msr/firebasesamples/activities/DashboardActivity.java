package com.msr.firebasesamples.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.msr.firebasesamples.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DashboardActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.emailTV)
    TextView emailTV;
    private FirebaseAuth auth = null;
    private FirebaseAuth.AuthStateListener authListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            emailTV.setText("Welcome:" + auth.getCurrentUser().getEmail());
        }
        // this listener will be called when there is change in firebase user session
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(DashboardActivity.this, SignUpActivity.class));
                    finish();
                }
            }
        };
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.addDataBtn:
                startActivity(new Intent(this, AddDataActivity.class));
                break;
            case R.id.resetPasswordBtn:
                startActivity(new Intent(this, ResetPasswordActivity.class));
                break;
            case R.id.logoutBtn:
                auth.signOut();
                break;
        }
    }
}
