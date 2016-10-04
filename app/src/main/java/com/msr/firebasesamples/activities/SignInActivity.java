package com.msr.firebasesamples.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.msr.firebasesamples.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignInActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.emailET)
    EditText emailET;
    @Bind(R.id.passwordET)
    EditText passwordET;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
        Log.i("===Email==", "===" + auth.getCurrentUser().getEmail());
    }

    @Override
    public void onClick(View view) {
        String email = emailET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();
        if (!email.equals("") && !password.equals("")) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        startActivity(new Intent(SignInActivity.this, DashboardActivity.class));
                    } else {
                        getToast("SignIn Status::" + task.isSuccessful());
                    }
                }
            });
        } else {
            getToast("All Fields are mandatory");
        }
    }
}
