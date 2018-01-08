package com.example.jlmalley.roomdemo;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import com.facebook.stetho.Stetho;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Toolbar toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        Stetho.initializeWithDefaults(this);

        recyclerView = findViewById( R.id.recycler_view );

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production")
                .allowMainThreadQueries()   //Allow the database to read/write on main UI thread.  TODO: This should be done differently(Background Thread). Wrap all in background thread.
                .build();

        List<User> users = db.userDao().getAllUsers();

        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        adapter = new UserAdapter( ( ArrayList<User> ) users );
        recyclerView.setAdapter( adapter );

        fab = findViewById( R.id.inputButton );
        fab.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make( view, "Replace with your own action", Snackbar.LENGTH_LONG )
                //        .setAction( "Action", null ).show();
                startActivity(new Intent( MainActivity.this, CreateUser.class ));
            }
        } );
    }
}
