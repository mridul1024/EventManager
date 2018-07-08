package com.example.mridul.eventmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RemoveEvent extends AppCompatActivity {

    ListView removalList;
    String name2;
    MainActivity mr = new MainActivity();
    DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_event);

        removalList = (ListView) findViewById(R.id.listView3);
        populate();

    }

    public void populate(){

        ArrayList<String> name = new ArrayList<>();
        Cursor data = db.listEventRemove(mr.getUid());

        while(data.moveToNext()){

            name.add(data.getString(1));

        }

        ListAdapter adpt = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,name);
        removalList.setAdapter(adpt);

        removalList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(final AdapterView<?> adapterView,final View view, final int position, long id) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(RemoveEvent.this);
                builder.setMessage("Do you want to delete this event?");
                builder.setCancelable(true);
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();

                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String name = adapterView.getItemAtPosition(position).toString();
                        name2 = name;
                        removeEventPhp(view);
                        db.removeEvent(mr.getUid(),name);
                        Toast.makeText(getApplicationContext(),"Event removed",Toast.LENGTH_SHORT).show();
                        finish();
                        startActivity(getIntent());

                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

    }

    public void removeEventPhp(View view){

        String method = "removeEvent";
        BackgroundActivity backgroundActivity = new BackgroundActivity(this);
        backgroundActivity.execute(method, name2,String.valueOf(mr.getUid()));

    }


    public void onBackPressed(){
        Intent intent = new Intent(RemoveEvent.this, LoginActivity.class);
        RemoveEvent.this.startActivity(intent);
        finish();

    }
}
