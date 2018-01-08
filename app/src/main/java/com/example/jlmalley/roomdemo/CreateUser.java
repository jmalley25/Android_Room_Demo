package com.example.jlmalley.roomdemo;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class CreateUser extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText email;
    Button addUserBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d( "CreateUser", "OnCreate" );
        super.onCreate(savedInstanceState);
        setContentView( R.layout.create_user );

        firstName = findViewById( R.id.first_name );
        lastName = findViewById( R.id.last_name );
        email = findViewById( R.id.email );
        addUserBtn = findViewById( R.id.addUserButton);

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production")
                .allowMainThreadQueries()   //Allow the database to read/write on main UI thread.  TODO: This should be done differently(Background Thread). Wrap all in background thread.
                .build();

        addUserBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Save to database
                User user = new User(firstName.getText().toString(), lastName.getText().toString(), email.getText().toString());

                db.userDao().insertAll(user);

                startActivity( new Intent( CreateUser.this, MainActivity.class ) );
            }
        } );

    }
}
