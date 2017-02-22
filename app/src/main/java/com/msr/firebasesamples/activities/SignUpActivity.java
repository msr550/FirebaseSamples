package com.msr.firebasesamples.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.msr.firebasesamples.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SignUpActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.emailET)
    EditText emailET;
    @Bind(R.id.passwordET)
    EditText passwordET;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.registerBtn:
                String email = emailET.getText().toString().trim();
                String password = passwordET.getText().toString().trim();
                if (!email.equals("") && !password.equals("")) {
                    getProgressDialog(this);
                    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            dismissDialog();
                            if (task.isSuccessful()) {
                                getToast("Registration Status::" + task.isSuccessful());
                            } else {
                                getToast(task.getException().getMessage());
                            }

                        }
                    });
                } else {
                    getToast("All Fields are mandatory");
                }
                break;
            case R.id.forgotPasswordBtn:
                startActivity(new Intent(this, ResetPasswordActivity.class));
                break;
            case R.id.signInBtn:
                startActivity(new Intent(this, SignInActivity.class));
                break;
        }
    }
}
