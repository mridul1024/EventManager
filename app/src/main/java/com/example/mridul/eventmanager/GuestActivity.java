package com.example.mridul.eventmanager;

import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@SuppressWarnings("deprecation")
public class GuestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guest);

        Toast.makeText(getApplicationContext().getApplicationContext(),"Events fetched", Toast.LENGTH_SHORT).show();


        List<Fragment> fragments2 = new Vector<>();
        fragments2.add(Fragment.instantiate(this, Fragment1.class.getName()));
        fragments2.add(Fragment.instantiate(this, Fragment2.class.getName()));
        fragments2.add(Fragment.instantiate(this, Fragment3.class.getName()));
        fragments2.add(Fragment.instantiate(this, Fragment4.class.getName()));

        PagerAdapter adapter2 = new PagerAdapter(getSupportFragmentManager(), fragments2);
        final ViewPager pager2 = (ViewPager) findViewById(R.id.viewpager1);
        pager2.setAdapter(adapter2);

        final ActionBar actionBar2 = getSupportActionBar();
        actionBar2.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener2 = new ActionBar.TabListener(){


            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft){
                pager2.setCurrentItem(tab.getPosition());
            }

            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft){

            }

            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft){

            }

        };

        actionBar2.addTab(actionBar2.newTab().setText("Technology").setTabListener(tabListener2));
        actionBar2.addTab(actionBar2.newTab().setText("Literature").setTabListener(tabListener2));
        actionBar2.addTab(actionBar2.newTab().setText("Sports").setTabListener(tabListener2));
        actionBar2.addTab(actionBar2.newTab().setText("Entertainment").setTabListener(tabListener2));

        pager2.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){

            public void onPageSelected(int position) {

                actionBar2.setSelectedNavigationItem(position);

            }

        });

    }

    public void onBackPressed(){

        final AlertDialog.Builder builder = new AlertDialog.Builder(GuestActivity.this);
        builder.setMessage("Do you want to logout?");
        builder.setCancelable(true);
        builder.setNegativeButton("No" ,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(GuestActivity.this, MainActivity.class);
                GuestActivity.this.startActivity(intent);
                finish();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
