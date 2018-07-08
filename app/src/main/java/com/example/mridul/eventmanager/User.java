package com.example.mridul.eventmanager;

/**
 * Created by Mridul on 1/1/2018.
 */
public class User {

    String nam, cont, em, pass;

    public String gName(){
        return nam;
    }
    public String gContact(){
        return cont;
    }
    public String gEmail(){
        return em;
    }
    public String gPassword(){
        return pass;
    }
    public void sName(String n){
        this.nam = n;
    }
    public void sContact(String c){
        this.cont = c;
    }
    public void sEmail(String e){
        this.em = e;
    }
    public void sPassword(String p){
        this.pass = p;
    }

}
