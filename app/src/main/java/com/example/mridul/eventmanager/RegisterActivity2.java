package com.example.mridul.eventmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity2 extends AppCompatActivity {

    EditText name, address, contact;
    String email, pass;
    int companyUserId;

    Button post;

    Company c = new Company();

    DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        name = (EditText) findViewById(R.id.editText10);
        contact = (EditText) findViewById(R.id.editText11);
        address = (EditText)findViewById(R.id.editText12);

        post = (Button) findViewById(R.id.button4);

        Intent receiveIntent = getIntent();
        companyUserId = receiveIntent.getIntExtra("uid",-1);
        email = receiveIntent.getStringExtra("email");
        pass = receiveIntent.getStringExtra("password");


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((name.getText().toString().length()==0)||(contact.getText().toString().length()==0)||(address.getText().toString().length()==0)){
                    Toast.makeText(getApplicationContext(),"All fields are required", Toast.LENGTH_SHORT).show();
                }
                else{

                    c.cName(name.getText().toString());
                    c.cAddress(address.getText().toString());
                    c.cContact(contact.getText().toString());
                    c.cUid(companyUserId);

                }

               // Toast.makeText(getApplicationContext(),Integer.toString(companyUserId),Toast.LENGTH_SHORT).show();


                db.addCompany(c);

                Intent LoginIntent = new Intent(RegisterActivity2.this, MainActivity.class);
                RegisterActivity2.this.startActivity(LoginIntent);
                finish();

            }
        });

    }

    public void onBackPressed(){

        db.removeUser(companyUserId, email, pass);
        Intent cancelRegistration = new Intent(RegisterActivity2.this, LoginActivity.class);
        RegisterActivity2.this.startActivity(cancelRegistration);
        finish();

    }

}
