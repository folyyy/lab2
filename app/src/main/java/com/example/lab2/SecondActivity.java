package com.example.lab2;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    public static ArrayList name;
    public static ArrayList graphic;
    public static ArrayList helptext;
    public static int number;
    public static ViewPager pager;

    SecondActivity() {}

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
         name = (ArrayList) getIntent().getSerializableExtra("name");
         graphic = (ArrayList) getIntent().getSerializableExtra("graphic");
         helptext = (ArrayList) getIntent().getSerializableExtra("helptext");

        final ListView listView = findViewById(R.id.listView);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                number = position;
                pager = findViewById(R.id.pager);
                PagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
                pager.setAdapter(pagerAdapter);
                pager.setCurrentItem(number);

            }
        });
    }

    public void removePager(View view) {
        pager.removeView(view);
        pager.setAdapter(null);
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.d("POSITIOn", "POSITION = " + position + " NUMBER = " + number);
            return PageFragment.newInstance(position);

        }

        @Override
        public int getCount() {
            return name.size();
        }

    }


    public String getName(int i) {
        return (String) name.get(i);
    }

    public String getHelpText(int i) {
        return (String) helptext.get(i);
    }

    public int getCount() {
        return name.size();
    }

    public void setImg(int i, ImageView img) {
            String iconUrl = "https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/" + graphic.get(i);
        Glide
                .with(img)
                .load(iconUrl)
                .into(img);
    }


     //If "back" key pressed, finish the app.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    class CustomAdapter extends BaseAdapter {
        SecondActivity obj = new SecondActivity();
        @Override
        public int getCount() {
            return obj.getCount();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            view = getLayoutInflater().inflate(R.layout.customlayout,null);
            ImageView imageView = view.findViewById(R.id.imageView);
            TextView textView_name = view.findViewById(R.id.textView_name);
            String name;
            name = obj.getName(i);
            textView_name.setText(name);
            obj.setImg(i,imageView);
            return view;

        }

    }



}
