package com.example.mridul.eventmanager;

/**
 * Created by Mridul on 5/22/2018.
 */
public class Company {

    String cname, caddress, ccontact;
    int Cuid;

    public void cName(String name){

        this.cname = name;

    }

    public void cAddress(String address){

        this.caddress = address;

    }

    public void cContact(String cont){

        this.ccontact = cont;

    }

    public void cUid(int uid){

        this.Cuid = uid;

    }

    public String gCname(){

        return cname;

    }

    public String gCaddress(){

        return caddress;

    }

    public String gCcontact(){

        return ccontact;

    }

    public int gCuid(){

        return Cuid;

    }

}
