package com.example.prakash.risingnepal;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.prakash.risingnepal.Fragment.Dashboard;
import com.example.prakash.risingnepal.Fragment.Gps_map;
import com.example.prakash.risingnepal.Fragment.Setting_activity;

public class Bottomnavigation extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    Dashboard dashboard;
    Gps_map map;
    Setting_activity setting;

//    private boolean loadfragment(Fragment fragment)
//    {
//if (fragment !=null)
//{
//getSupportFragmentManager()
//        .beginTransaction()
//        .replace()
//    return true;
//}
//
//
//        return false;
//    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {



            switch (item.getItemId()) {

                case R.id.navigation_dashboard:


                    dashboardhome(dashboard);
                    return true;

                case R.id.navigation_track:

                    dashboard(map);


                    return true;

                case R.id.navigation_setting:

                    dashboard(setting);

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottomnavigation);

        dashboard = new Dashboard();
        map = new Gps_map();
        setting = new Setting_activity();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


//        dashboard(dashboard);
        //home();


            dashboardhome(dashboard);


    }

    void dashboard(Fragment fragment) {


        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.addToBackStack("home page");
        fragmentTransaction.commit();

    }

    void dashboardhome(Fragment fragment) {


        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        fragmentTransaction.replace(R.id.content, fragment);
//        fragmentTransaction.addToBackStack("home page");
        fragmentTransaction.commit();

    }


    @Override
    public void onBackPressed() {

            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
            } else if (!doubleBackToExitPressedOnce) {
                this.doubleBackToExitPressedOnce = true;


                Toast.makeText(this, "Please double tap to exit", Toast.LENGTH_SHORT).show();
this.finish();

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            } else {
                super.onBackPressed();

            }


    }
}
