package com.example.mridul.eventmanager;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);
    int contactId;
    TextView a1,a2,b1,b2,c1,c2,d1,d2;
    ImageView imView;

    Button organiserDetails;

    Bitmap im ;
    int id;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent recievedIntent = getIntent();

        id = recievedIntent.getIntExtra("id",-1);
        name = recievedIntent.getStringExtra("name");

        organiserDetails = (Button) findViewById(R.id.button5);


       // a1 = (TextView) findViewById(R.id.textView12);
        a2 = (TextView) findViewById(R.id.textView13);
        b1 = (TextView) findViewById(R.id.textView14);
        b2 = (TextView) findViewById(R.id.textView15);
        c1 = (TextView) findViewById(R.id.textView16);
        c2 = (TextView) findViewById(R.id.textView17);
        d1 = (TextView) findViewById(R.id.textView18);
        d2 = (TextView) findViewById(R.id.textView19);

        imView = (ImageView) findViewById(R.id.imageView2);


        Cursor data = db.getRowDetail(id);

        while(data.moveToNext()) {
            a2.setText(data.getString(2));
            b2.setText(data.getString(3));
            c2.setText(data.getString(4));
try {
    im = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(data.getString(5)));
}catch(IOException e){
    e.printStackTrace();

}        imView.setImageBitmap(im);
        }

        Cursor data2 = db.getEventId(name);

        while(data2.moveToNext()){

            contactId = data2.getInt(1);

        }

        Cursor numb = db.getNumber(contactId);
        while(numb.moveToNext()){

         String n = numb.getString(0);
            d2.setText(n);

        }

        d2.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+d2.getText().toString()));
                startActivity(callIntent);

            }

        });

        organiserDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(),Integer.toString(id), Toast.LENGTH_SHORT).show();
                Intent organiserDetail = new Intent(DetailActivity.this, OrganiserDetailActivity.class);
                organiserDetail.putExtra("uid",contactId);
                organiserDetail.putExtra("eid",id);
                organiserDetail.putExtra("name",name);
                DetailActivity.this.startActivity(organiserDetail);
                finish();
            }
        });

    }

}
