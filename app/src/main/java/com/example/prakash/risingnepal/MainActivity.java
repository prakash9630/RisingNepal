package com.example.prakash.risingnepal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prakash.risingnepal.Fragment.Dashboard;
import com.example.prakash.risingnepal.Fragment.Gps_map;
import com.example.prakash.risingnepal.Fragment.Webview_activity;
import com.example.prakash.risingnepal.Helper.GPSTracker;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    boolean doubleBackToExitPressedOnce = false;
    GPSTracker gps;
    double latitude;
    double longitude;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {

        }else
        {

        }


        final FragmentManager fragmentmanager = getSupportFragmentManager();

        final FragmentTransaction fragmenttranscation = fragmentmanager.beginTransaction();
        Dashboard dashboard = new Dashboard();
        fragmenttranscation.replace(R.id.mainFragment, dashboard);
        fragmenttranscation.commit();

//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_phone) {
            Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:9779813178078"));
            startActivity(i);

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_messenger) {
            // Handle the camera action
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://m.me/retreatapartment"));
            startActivity(intent);
        } else if (id == R.id.nav_Sharelocation) {



            if (isOnline()) {

//                    Intent i=new Intent(getContext(),Location_feedback.class);
//                    startActivity(i);
//                sendLocation();
                getLatLong();




            }
            else
            {
                Toast.makeText(this, "Check your Internet connection", Toast.LENGTH_SHORT).show();
            }


        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_track) {


            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


            Gps_map map=new Gps_map();
            fragmentTransaction.replace(R.id.mainFragment,map);
            fragmentTransaction.addToBackStack("home page");
            fragmentTransaction.commit();








        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }


        void sendLocation()
        {
            Intent Email = new Intent(Intent.ACTION_SEND);

            Email.putExtra(Intent.EXTRA_EMAIL, new String[] { "info@retreatapartment.com" });
            Email.putExtra(Intent.EXTRA_SUBJECT, "Users location");
            Email.putExtra(Intent.EXTRA_TEXT,username +" is in "+latitude+","+longitude +" right now.  ");
            Email.setType("message/rfc822");
            try {
                startActivity(Intent.createChooser(Email, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }

    }


    void getLatLong() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        }
        else {

            gps = new GPSTracker(this, this);

            // Check if GPS enabled
            if (gps.canGetLocation()) {

                latitude = gps.getLatitude();
                longitude = gps.getLongitude();


                if (latitude == 0.0 && longitude == 0.0) {

                    Toast.makeText(this, "Could't get location coordinate please try again", Toast.LENGTH_SHORT).show();


                } else {

                    String uri = "http://maps.google.com/maps?saddr=" + latitude + "," + longitude;

                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);

                    sharingIntent.setType("text/plain");

                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, uri);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                }


            } else {
                // Can't get location.
                // GPS or network is not enabled.
                // Ask user to enable GPS/network in settings.
                gps.showSettingsAlert();
            }
        }

    }








    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {


            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
            } else if (!doubleBackToExitPressedOnce) {
                this.doubleBackToExitPressedOnce = true;


                FrameLayout frameLayout = (FrameLayout) findViewById(R.id.mainFragment);

                Snackbar snackbar = Snackbar
                        .make(frameLayout, "Please click BACK again to exit.", Snackbar.LENGTH_LONG);


                View snackBarView = snackbar.getView();
                snackBarView.setBackgroundColor(Color.BLUE);
                TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setTextColor(Color.WHITE);
                snackbar.show();


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

}
