package com.msr.firebasesamples.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.msr.firebasesamples.R;
import com.msr.firebasesamples.model.Items;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddDataActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.inputET)
    EditText inputET;
    @Bind(R.id.brandET)
    EditText brandET;
    @Bind(R.id.quantityET)
    EditText quantityET;
    private FirebaseAuth auth = null;
    private DatabaseReference mDatabase = null;
    private FirebaseUser mFirebaseUser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        ButterKnife.bind(this);
        auth = FirebaseAuth.getInstance();
        mFirebaseUser = auth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        //Value event listener for realtime data update
        // Retrieve new posts as they are added to Firebase
        mDatabase.addChildEventListener(new ChildEventListener() {
            // Retrieve new posts as they are added to Firebase
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
                Map<String, Object> newPost = (Map<String, Object>) snapshot.getValue();
                System.out.println("Email: " + newPost.get("email"));
                System.out.println("Brand: " + newPost.get("brand"));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
            //... ChildEventListener also defines onChildChanged, onChildRemoved,
            //    onChildMoved and onCanceled, covered in later sections.
        });
    }

    @Override
    public void onClick(View view) {
        String input = inputET.getText().toString();
        final String brand = brandET.getText().toString();
        String quantity = quantityET.getText().toString();

        if (input.trim().equals("") || brand.equals("") || quantity.equals("")) {
            getToast("Please enter some data");
        } else {
            dialog = getProgressDialog(this);
            Items items = new Items();
            items.setBrand(brand);
            items.setEmail(input);
            items.setQuantity(Integer.parseInt(quantity));
            //mDatabase.child("users").child(mFirebaseUser.getUid()).child("items").push().setValue(items).addOnCompleteListener(new OnCompleteListener<Void>() {
            mDatabase.child("products").child(mFirebaseUser.getUid()).push().setValue(items).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    dismissDialog();
                    if (task.isSuccessful()) {
                        inputET.setText("");
                        brandET.setText("");
                        quantityET.setText("");
                        getToast("Success");
                    } else {
                        getToast(task.getException().getMessage());
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    dismissDialog();
                }
            }).addOnCompleteListener(this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    dismissDialog();
                }
            }).addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    dismissDialog();
                }
            });
        }
    }

}
