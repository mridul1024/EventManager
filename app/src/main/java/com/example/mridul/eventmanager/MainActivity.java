package com.example.mridul.eventmanager;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DatabaseHelper d = new DatabaseHelper(this);

        final EditText edit1 = (EditText) findViewById(R.id.editText);
        final EditText edit2 = (EditText) findViewById(R.id.editText2);

        TextView guest = (TextView) findViewById(R.id.textView2);
        TextView reg = (TextView) findViewById(R.id.textView4);
        Button b1 = (Button) findViewById(R.id.button);


        guest.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v1){


                Intent guestIntent = new Intent(MainActivity.this,GuestActivity.class);
                MainActivity.this.startActivity(guestIntent);
                finish();

            }

        });

        reg.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v2){

                Intent regIntent = new Intent(MainActivity.this,RegisterActivity.class);
                MainActivity.this.startActivity(regIntent);
                finish();

            }

        });

        b1.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v3){

                if((edit1.getText().toString().length()==0)&&(edit2.getText().toString().length()==0)){

                    edit1.setError("Email required");
                    edit2.setError("Password required");

                }
                else if((edit1.getText().toString().length()==0)||(edit2.getText().toString().length()==0)){

                    if(edit1.getText().toString().length()==0){

                        edit1.setError("Email required");

                    }
                    else{

                        edit2.setError("Password required");

                    }

                }
                else{
                    if(edit1.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){

                    boolean result = d.validateCredentials(edit1.getText().toString(), edit2.getText().toString());

                    if (result) {

                        Cursor data = d.getUid(edit1.getText().toString(), edit2.getText().toString());
                        uid = -1;
                        while (data.moveToNext()) {
                            uid = data.getInt(0);
                        }

                        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                        MainActivity.this.startActivity(loginIntent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Wrong Email or Password", Toast.LENGTH_SHORT).show();
                    }
                    }
                    else{

                        Toast.makeText(getApplicationContext(),"Invalid Email",Toast.LENGTH_SHORT).show();
                        edit1.setError("Invalid");
                    }
                }


            }

        });

    }

    public void onBackPressed(){

        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Do you want to exit the application?");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    public int getUid(){
        return uid;
    }
}
