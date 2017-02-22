package com.msr.firebasesamples.activities;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

import com.msr.firebasesamples.R;

/**
 * Created by Sandeep Maram on 10/4/2016.
 */

public class BaseActivity extends AppCompatActivity {
    protected Dialog dialog = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void getToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected Dialog getProgressDialog(Context context) {
        Dialog dialog = null;
        try {
            dialog = new Dialog(context);
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.view_progress_dialog);
            dialog.setCancelable(true);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dialog;
    }

    protected void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
