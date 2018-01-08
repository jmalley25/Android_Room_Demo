package com.example.jlmalley.roomdemo;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;


/**
 * Created by jlmalley on 12/26/2017.
 */

public class DeleteUser extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText email;
    Button deleteUserBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("DeleteUser", "ON CREATE!");
        super.onCreate( savedInstanceState );
        setContentView( R.layout.create_user );

        firstName = findViewById( R.id.first_name );
        lastName = findViewById( R.id.last_name );
        email = findViewById( R.id.email );
        deleteUserBtn = findViewById( R.id.deleteUserButton );

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production")
                .allowMainThreadQueries()   //Allow the database to read/write on main UI thread.  TODO: This should be done differently(Background Thread). Wrap all in background thread.
                .build();

        deleteUserBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Delete", "BUTTON CLICKED!!!");
                //Set Java List to Java Array
                User userArray[] = new User[db.userDao().getAllUsers().size()];
                List<User> users = db.userDao().getAllUsers();
                users.toArray(userArray);

                //temporary delete action for testing only
                for (int i = 0; i < users.size(); i++) {
                    if(userArray[i].getId() == 8) {

                        Log.d( "DeleteUser", "User ID found and should be deleted..." );

                        db.userDao().deleteUser( userArray[i] );
                    }
                }
                startActivity( new Intent( DeleteUser.this, MainActivity.class ) );
            }
        });
    }
}
