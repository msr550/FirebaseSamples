package com.msr.firebasesamples.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.msr.firebasesamples.R;
import com.msr.firebasesamples.adapters.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DashboardActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.emailTV)
    TextView emailTV;
    FirebaseDatabase mDatabase;
    private FirebaseAuth auth = null;
    private FirebaseAuth.AuthStateListener authListener = null;
    private List<DataSnapshot> list = new ArrayList<>();
    private RecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            emailTV.setText("Welcome:" + auth.getCurrentUser().getEmail());
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mAdapter = new RecyclerViewAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
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
        mDatabase = FirebaseDatabase.getInstance();
        mDatabase.getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("==Count22::", "==?" + dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("==Count22::", "==?databaseError" + databaseError.getMessage());
            }
        });
        mDatabase.getReference().child("products").child(auth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("==Count::", "==?" + dataSnapshot.getChildrenCount());
                list.clear();
                addAll(list, dataSnapshot.getChildren().iterator());
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("==Count::", "==?error" + databaseError.getMessage());
            }
        });
    }

    public <T> void addAll(Collection<T> collection, Iterator<T> iterator) {
        while (iterator.hasNext()) {
            collection.add(iterator.next());
        }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_product:
                startActivity(new Intent(this, AddDataActivity.class));
                return true;
            case R.id.reset_password:
                startActivity(new Intent(this, ResetPasswordActivity.class));
                return true;
            case R.id.logout:
                auth.signOut();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

    }
}
