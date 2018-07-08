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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@SuppressWarnings("deprecation")
public class LoginActivity extends AppCompatActivity {

    MainActivity m3 = new MainActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toast.makeText(getApplicationContext().getApplicationContext(),"Events fetched", Toast.LENGTH_SHORT).show();

        List<Fragment> fragments = new Vector<>();
        fragments.add(Fragment.instantiate(this, Fragment1.class.getName()));
        fragments.add(Fragment.instantiate(this, Fragment2.class.getName()));
        fragments.add(Fragment.instantiate(this, Fragment3.class.getName()));
        fragments.add(Fragment.instantiate(this, Fragment4.class.getName()));

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), fragments);
        final ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
        pager.setAdapter(adapter);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.TabListener tabListener = new ActionBar.TabListener(){


            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft){
                pager.setCurrentItem(tab.getPosition());
            }

            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft){

            }

            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft){

            }

        };

        actionBar.addTab(actionBar.newTab().setText("Technology").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Literature").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Sports").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Entertainment").setTabListener(tabListener));

        pager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){

            public void onPageSelected(int position) {

                actionBar.setSelectedNavigationItem(position);

            }

        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.commonmenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.menu_profile){

            Intent profile = new Intent(LoginActivity.this, ProfileActivity.class);
            profile.putExtra("id",m3.getUid());
            LoginActivity.this.startActivity(profile);

        }

        else if(id == R.id.menu_create){

            Intent cr = new Intent(LoginActivity.this, CreateEvent.class);
            LoginActivity.this.startActivity(cr);

        }

        else if(id == R.id.menu_remove){

            Intent rem = new Intent(LoginActivity.this, RemoveEvent.class);
            LoginActivity.this.startActivity(rem);

        }

        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed(){

        final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
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


                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(intent);
                finish();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
