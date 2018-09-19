package com.example.dell.kishantest;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.kishantest.Common.currentValue;

public class MainActivity extends AppCompatActivity {

   // private SectionsPagerAdapter mSectionsPagerAdapter;
    private TabLayout tabLayout;
    private ViewPagerAdapter adapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tablayout_id);
        mViewPager = findViewById(R.id.viewpager_id);
        adapter =new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new TabContacts(),"Contacts");
        adapter.AddFragment(new TabMessage(),"Messages");
        adapter.notifyDataSetChanged();
        if(currentValue.isConnectedToInternet(getBaseContext())){

            mViewPager.setAdapter(adapter);
            tabLayout.setupWithViewPager(mViewPager);
        }
        else{

            Toast.makeText(MainActivity.this, "You are not Connected to Internet. Please Connect to it and Restart the APP", Toast.LENGTH_SHORT).show();
            return;

        }


    }


    
}
