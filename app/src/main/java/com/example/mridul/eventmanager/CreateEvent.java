package com.example.mridul.eventmanager;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CreateEvent extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText edit,edit2,edit3,edit4;
    TextView t1;
    Spinner spinner;
    String category;
    ImageView img;
    Button bpost;
    public static final int PICK_IMAGE=1;
    Uri imageuri;
    Event in =new Event();
    MainActivity m1 = new MainActivity();
    DatabaseHelper dc = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        edit = (EditText) findViewById(R.id.editText7);
        edit2 = (EditText) findViewById(R.id.editText8);
        edit3 = (EditText) findViewById(R.id.editText9);
        //edit4 = (EditText) findViewById(R.id.editText10);


        t1 = (TextView) findViewById(R.id.textView6);
        spinner = (Spinner) findViewById(R.id.spinner);
        img = (ImageView) findViewById(R.id.imageView);
        bpost = (Button) findViewById(R.id.button3);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        t1.setOnClickListener(new View.OnClickListener(){

            public void onClick(View vim){

                operationGallery();

            }

        });

        bpost.setOnClickListener(new View.OnClickListener(){

            public void onClick(View vPost){

                if((edit.getText().toString().length()==0)||(edit2.getText().toString().length()==0)||(edit3.getText().toString().length()==0)||(img.getDrawable()==null)){

                    Toast.makeText(getApplicationContext(),"All fields must be filled!",Toast.LENGTH_SHORT).show();

                }

                else {

                    in.sEventName(edit.getText().toString());
                    in.sEventDesc(edit2.getText().toString());
                    in.sEventVenue(edit3.getText().toString());
                    in.sType(category);
                    in.sPic(imageuri.toString());
                    in.sEuid(m1.getUid());

                    createEventPhp(vPost);
                    dc.addEvent(in);

                    Toast.makeText(getApplicationContext(), "Data Saved", Toast.LENGTH_SHORT).show();
                    Intent gBack = new Intent(CreateEvent.this, LoginActivity.class);
                    CreateEvent.this.startActivity(gBack);
                    finish();

                }


            }

        });



    }  public void operationGallery(){

        Intent im = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(im , PICK_IMAGE);

    }

    public void onActivityResult(int requestcode, int resultcode, Intent data){
        super.onActivityResult(requestcode, resultcode, data);
        if((resultcode == RESULT_OK)&&(requestcode == PICK_IMAGE)){
            imageuri = data.getData();
            img.setImageURI(imageuri);

        }
    }

    public void createEventPhp(View view){

        String method = "createEvent";
        BackgroundActivity backgroundActivity = new BackgroundActivity(this);
        backgroundActivity.execute(method, edit.getText().toString(), edit2.getText().toString(), edit3.getText().toString(), category,
                imageuri.toString(),String.valueOf(m1.getUid()));

    }

    public void onBackPressed(){
        Intent intent = new Intent(CreateEvent.this, LoginActivity.class);
        CreateEvent.this.startActivity(intent);
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        category = spinner.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
