package com.example.mridul.eventmanager;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    TextView name, contact, email, password;
    String n,c,e,p;
    Button b2;
    int id;
    User user= new User();
    DatabaseHelper d1 = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = (TextView) findViewById(R.id.editText3);
        contact = (TextView) findViewById(R.id.editText4);
        email = (TextView) findViewById(R.id.editText5);
        password = (TextView) findViewById(R.id.editText6);

        b2 = (Button) findViewById(R.id.button2);

        b2.setOnClickListener(new View.OnClickListener(){

            public void onClick(View vr){

                if((name.getText().toString().length()==0)||(contact.getText().toString().length()==0)||(email.getText().toString().length()==0)||(password.getText().toString().length()==0)){

                    Toast.makeText(getApplicationContext(),"All fields are needed to be filled!",Toast.LENGTH_SHORT).show();

                }

                else if((contact.getText().toString().length()<10||contact.getText().toString().length()>10)||(!email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"))){

                    if(contact.getText().toString().length()<10||contact.getText().toString().length()>10){

                        Toast.makeText(getApplicationContext(),"Enter a valid 10 digit mobile no",Toast.LENGTH_SHORT).show();

                    }

                    else {

                        Toast.makeText(getApplicationContext(),"Enter a valid email!",Toast.LENGTH_SHORT).show();

                    }

                }

                else if(email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")&&(contact.getText().toString().length()==10)){

                    user.sName(name.getText().toString());
                    n = name.getText().toString();
                    user.sContact(contact.getText().toString());
                    c = contact.getText().toString();
                    user.sEmail(email.getText().toString());
                    e = email.getText().toString();
                    user.sPassword(password.getText().toString());
                    p = password.getText().toString();

                    d1.addUser(user);
                    registerPhp(vr);

                    Cursor data = d1.getUid(email.getText().toString(),password.getText().toString());

                    while(data.moveToNext()){

                        id = data.getInt(0);

                    }

                    Intent register2 = new Intent(RegisterActivity.this, RegisterActivity2.class);
                    register2.putExtra("uid",id);
                    register2.putExtra("email",e);
                    register2.putExtra("password",p);
                    RegisterActivity.this.startActivity(register2);
                    finish();

                }

            }

        });

    }

    public void registerPhp(View view){

        String method = "register";
        BackgroundActivity backgroundActivity = new BackgroundActivity(this);
        backgroundActivity.execute(method, n, c, e, p);



    }

    public void onBackPressed(){
        Intent loginScreen = new Intent(RegisterActivity.this, MainActivity.class);
        RegisterActivity.this.startActivity(loginScreen);
        finish();

    }

}
