package com.example.mridul.eventmanager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Mridul on 4/6/2018.
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;

    public PagerAdapter(FragmentManager fm, List<Fragment> fragments){

        super(fm);
        this.fragments = fragments;

    }

    @Override
    public Fragment getItem(int i){

        return fragments.get(i);

    }

    @Override
    public int getCount(){

        return fragments.size();

    }


}
