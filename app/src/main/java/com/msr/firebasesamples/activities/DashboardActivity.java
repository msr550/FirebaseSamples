package com.msr.firebasesamples.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.msr.firebasesamples.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DashboardActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.emailTV)
    TextView emailTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            emailTV.setText("Welcome:" + auth.getCurrentUser().getEmail());
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.resetPasswordBtn:
                startActivity(new Intent(this, ResetPasswordActivity.class));
                break;
        }
    }
}
