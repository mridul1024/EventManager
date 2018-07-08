package com.example.mridul.eventmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Mridul on 1/1/2018.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    //version of the database
   public static final int DATABASE_VERSION = 1;
    //the name of the database
    public static final String DATABASE_NAME = "userManager.db";
    //table for handling the registered user details
    public static final String TABLE_NAME = "user";
//table 2
    public static final String EVENT_TABLE_NAME = "events";
    //table 3
    public static final String TABLE_COMPANY = "company";



    //table fields for entering the user details
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String CONTACT_NO = "contact_no";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_PASSWORD = "user_password";

    //table 2 fields
    public static final String EVENT_ID = "event_id";
    public static final String EVENT_NAME = "event_name";
    public static final String EVENT_DESC = "event_description";
    public static final String EVENT_VENUE = "event_venue";
    public static final String EVENT_TYPE = "event_type";
    public static final String EVENT_PIC = "picture";
    public static final String USER_ID2 = "user_id2";

    //table 3 fields
    public static final String COMPANY_ID = "company_id";
    public static final String COMPANY_NAME = "company_name";
    public static final String COMPANY_ADDRESS = "company_address";
    public static final String COMPANY_CONTACT = "company_contact";
    public static final String USER_ID3 = "user_id3";


    //create table query
    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ USER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            USER_NAME+" TEXT,"+
            CONTACT_NO+" VARCHAR(10),"+
            USER_EMAIL+" TEXT,"+
            USER_PASSWORD+" TEXT);";
    //create table 2
    public static final String CREATE_TABLE_2 = "CREATE TABLE "+EVENT_TABLE_NAME+"("+
            EVENT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            EVENT_NAME+" VARCHAR(15),"+
            EVENT_DESC+" VARCHAR(40),"+
            EVENT_VENUE+" VARCHAR(30),"+
            EVENT_TYPE+" TEXT,"+
            EVENT_PIC+" TEXT,"+
            USER_ID2+" INTEGER);";

    //create table 3
    public static final String CREATE_TABLE_3 = "CREATE TABLE "+TABLE_COMPANY+"("+
            COMPANY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            COMPANY_NAME+" TEXT,"+
            COMPANY_ADDRESS+" VARCHAR(40),"+
            COMPANY_CONTACT+" VARCHAR(10),"+
            USER_ID3+" INTEGER);";


    //drop table query
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME;
    //drop table 2
    public static final String DROP_TABLE_2 = "DROP TABLE IF EXISTS "+EVENT_TABLE_NAME;
    //drop table 3
    public static final String DROP_TABLE_3 = "DROP TABLE IF EXISTS "+TABLE_COMPANY;


    //constructor for creating database

    public DatabaseHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    //main function to create table inside the database

    public void onCreate(SQLiteDatabase db){

        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_2);
        db.execSQL(CREATE_TABLE_3);

    }

    public void onUpgrade(SQLiteDatabase db,int oldversion, int nversion){
     //DROP TABLE IF EXISTS
        db.execSQL(DROP_TABLE);
        db.execSQL(DROP_TABLE_2);
        db.execSQL(DROP_TABLE_3);


        //create a new table
        onCreate(db);

    }

    public void addUser(User u){
        SQLiteDatabase d = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(USER_NAME, u.gName());
        v.put(CONTACT_NO,u.gContact());
        v.put(USER_EMAIL, u.gEmail());
        v.put(USER_PASSWORD, u.gPassword());

        //inserting values into user table
        d.insert(TABLE_NAME, null, v);
        d.close();

    }

    public void addCompany(Company c){

        SQLiteDatabase d = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(COMPANY_NAME,c.gCname());
        v.put(COMPANY_ADDRESS,c.gCaddress());
        v.put(COMPANY_CONTACT,c.gCcontact());
        v.put(USER_ID3,c.gCuid());

        d.insert(TABLE_COMPANY,null,v);
        d.close();

    }

    public void addEvent(Event e){

        SQLiteDatabase eve = this.getWritableDatabase();

        ContentValues ve = new ContentValues();
        ve.put(EVENT_NAME,e.gEventName());
        ve.put(EVENT_DESC,e.gEventDesc());
        ve.put(EVENT_VENUE,e.gEventVenue());
        ve.put(EVENT_TYPE,e.gType());
        ve.put(EVENT_PIC,e.gPic());
        ve.put(USER_ID2,e.gEuid());

        eve.insert(EVENT_TABLE_NAME,null,ve);
        eve.close();

    }

    public Cursor getProfile(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        String query= "SELECT * FROM "+TABLE_NAME+" WHERE "+USER_ID+" = '"+id+"'";
        Cursor data = db.rawQuery(query,null);
        return data;
    }

    public Cursor getOrganiserDetail(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_COMPANY+" WHERE "+USER_ID3+" = '"+id+"'";
        Cursor data = db.rawQuery(query,null);
        return data;

    }

    public Cursor getData(String cat){

        SQLiteDatabase getdb = this.getReadableDatabase();
        String query= "SELECT * FROM "+EVENT_TABLE_NAME+" WHERE "+EVENT_TYPE+" = '"+cat+"'";
        Cursor c = getdb.rawQuery(query,null);
        return c;
    }

    public Cursor getEventId(String name){

        SQLiteDatabase db = this.getReadableDatabase();
        String query= "SELECT "+EVENT_ID+","+USER_ID2+" FROM "+EVENT_TABLE_NAME+" WHERE "+EVENT_NAME+" = '"+name+"'";
        Cursor data = db.rawQuery(query,null);
        return data;

    }

    public Cursor getRowDetail(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+EVENT_TABLE_NAME+" WHERE "+EVENT_ID+" = '"+id+"'";
        Cursor data = db.rawQuery(query,null);
        return data;

    }

    public Cursor getUid(String email, String pass){


        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT "+USER_ID+" FROM "+TABLE_NAME+" WHERE "+USER_EMAIL+" = '"+email+"' AND "+USER_PASSWORD+" = '"+pass+"'";


        Cursor data = db.rawQuery(query,null);

        return data;

    }

    public Cursor listEventRemove(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+EVENT_TABLE_NAME+" WHERE "+USER_ID2+" ='"+id+"'";
        Cursor data = db.rawQuery(query,null);
        return data;

    }

    public void removeEvent(int id,String name){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+EVENT_TABLE_NAME+" WHERE "+USER_ID2+" = '"+id+"' AND "+EVENT_NAME+" = '"+name+"'";
        db.execSQL(query);

    }

    public void removeUser(int id, String email, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM "+TABLE_NAME+" WHERE "+USER_ID+" = '"+id+"' AND "+USER_EMAIL+" = '"+email+"' AND "+USER_PASSWORD+" = '"+password+"'";
        db.execSQL(query);

    }

    public Cursor getNumber(int id){

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT "+CONTACT_NO+" FROM "+TABLE_NAME+" WHERE "+USER_ID+ " = '"+id+"'";
        Cursor data = db.rawQuery(query,null);
        return data;

    }

    public boolean validateCredentials(String email, String password){
        //columns to search for using where clause
        String[] columns ={USER_ID};

        //sqlite database for read operation
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = USER_EMAIL+" =? AND "+USER_PASSWORD+" =?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0){
            return true;

        }
        else {
            return false;
        }


    }



}
