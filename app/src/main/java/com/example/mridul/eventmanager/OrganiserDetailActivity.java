package com.example.mridul.eventmanager;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class OrganiserDetailActivity extends AppCompatActivity {

    TextView name, address, contact;
    DatabaseHelper db = new DatabaseHelper(this);
    String nam;

    int id;
    int eid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organiser_detail);

        Intent receiveIntent = getIntent();
        id = receiveIntent.getIntExtra("uid",-1);
        eid = receiveIntent.getIntExtra("eid",-1);
        nam = receiveIntent.getStringExtra("name");

        name = (TextView) findViewById(R.id.textView12);
        address = (TextView) findViewById(R.id.textView23);
        contact = (TextView) findViewById(R.id.textView25);

        Cursor data = db.getOrganiserDetail(id);

        while(data.moveToNext()){

            name.setText(data.getString(1));
            address.setText(data.getString(2));
            contact.setText(data.getString(3));

        }

        contact.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+contact.getText().toString()));
                startActivity(callIntent);

            }

        });

    }

    public void onBackPressed(){

       // Toast.makeText(getApplicationContext(),Integer.toString(eid),Toast.LENGTH_SHORT).show();
        Intent goBack = new Intent(OrganiserDetailActivity.this, DetailActivity.class);
        goBack.putExtra("id",eid);
        goBack.putExtra("name",nam);
        OrganiserDetailActivity.this.startActivity(goBack);
        finish();

    }

}
