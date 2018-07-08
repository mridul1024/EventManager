package com.example.mridul.eventmanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.LayoutInflaterCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Mridul on 4/6/2018.
 */
public class Fragment2 extends Fragment {
    DatabaseHelper dp;
    ListView lv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstance){

        if( container == null ){

            return null;

        }

        View RootView = inflater.inflate(R.layout.fragment2_layout, container, false);

        dp = new DatabaseHelper(getContext());
        lv = (ListView) RootView.findViewById(R.id.listFrag2);

        String type = "Literature";

        Cursor data =dp.getData(type);
        ArrayList<String> eventList = new ArrayList<>();

        while(data.moveToNext()){

            eventList.add(data.getString(1));

        }

        ListAdapter adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,eventList);
        lv.setAdapter(adapter);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {

                String event_name = adapterView.getItemAtPosition(i).toString();
                Cursor data = dp.getEventId(event_name);
                int itemid=-1;
                while(data.moveToNext()){
                    itemid = data.getInt(0);

                }
                if( itemid >0){

                    Intent eventDetail = new Intent(getActivity(), DetailActivity.class);
                    eventDetail.putExtra("id",itemid);
                    eventDetail.putExtra("name",event_name);
                    startActivity(eventDetail);

                }


            }
        });



        return RootView;



    }

}
