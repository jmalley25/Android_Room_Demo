package com.example.jlmalley.roomdemo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    public int mPosition;

    ArrayList<User> users;

    public UserAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.user_row, parent,  false);
       return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder holder, int position) {
        mPosition = position;

        holder.firstName.setText( users.get( position ).getFirstName() );
        holder.lastName.setText( users.get( position ).getLastName() );
        holder.email.setText( users.get( position ).getEmail() );

        //if position is odd
        if((position % 2) == 1) {
            holder.itemView.setBackgroundColor( Color.rgb(225, 225, 255 ) );
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView firstName;
        public TextView lastName;
        public TextView email;

        public ViewHolder(View itemView) {
            super( itemView );
            firstName = itemView.findViewById( R.id.first_name );
            lastName = itemView.findViewById( R.id.last_name );
            email = itemView.findViewById( R.id.email );

            itemView.setOnClickListener( this );
        }

        @Override
        public void onClick(View v) {

            //startActivity(new Intent( MainActivity.this, DeleteUser.class ));

            //Create option to delete using email as uid(Options: delete, edit, cancel)
            Log.d("ViewHolder - OnClick", this.firstName.getText().toString());
        }
    }


}
