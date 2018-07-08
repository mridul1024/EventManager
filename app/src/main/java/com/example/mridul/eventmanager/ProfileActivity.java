package com.example.mridul.eventmanager;

import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView p1,p2,p3,p4;
    DatabaseHelper db = new DatabaseHelper(this);
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        p1 = (TextView) findViewById(R.id.textView7);
        p2 = (TextView) findViewById(R.id.textView9);
        p3 = (TextView) findViewById(R.id.textView11);
        p4 = (TextView) findViewById(R.id.textView21);

        Intent receiveIntent = getIntent();
        id = receiveIntent.getIntExtra("id",-1);

        Cursor data = db.getProfile(id);

        while(data.moveToNext()){

            p1.setText(data.getString(0));
            p2.setText(data.getString(1));
            p4.setText(data.getString(2));
            p3.setText(data.getString(3));

        }

    }

    public void onBackPressed(){
        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
        ProfileActivity.this.startActivity(intent);
    }
}
