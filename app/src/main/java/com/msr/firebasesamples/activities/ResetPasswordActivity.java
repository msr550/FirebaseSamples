package com.msr.firebasesamples.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.msr.firebasesamples.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ResetPasswordActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.emailET)
    EditText emailET;
    private FirebaseAuth auth = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        String email = emailET.getText().toString();
        if (email.equals("")) {
            getToast("Please enter email");
            return;
        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                getToast("Reset Password link:" + task.isSuccessful());
            }
        });
    }
}
