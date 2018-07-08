package com.example.mridul.eventmanager;


/**
 * Created by Mridul on 1/3/2018.
 */
public class Event {
    String Ename,Edesc,Evenue,pic,tp;
    int Euid;

    public int gEuid(){return Euid;}
    public String gEventName(){
        return Ename;
    }
    public String gEventDesc(){
        return Edesc;
    }
    public String gEventVenue(){
        return Evenue;
    }
    public String gPic(){
        return pic;
    }
    public String gType(){
        return tp;
    }

    public void sEuid(int eid){this.Euid = eid;}
    public void sEventName(String en){
        this.Ename = en;
    }
    public void sEventDesc(String ed){
        this.Edesc = ed;
    }
    public void sEventVenue(String ev){
        this.Evenue = ev;
    }
    public void sPic(String ep){
        this.pic = ep;
    }

    public void sType(String t){
        this.tp = t;
    }

}
