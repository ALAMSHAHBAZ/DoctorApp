package com.example.lenovo.doctorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.lenovo.doctorapp.clerk.AddClerk;
import com.example.lenovo.doctorapp.clerk.DeleteClerk;
import com.example.lenovo.doctorapp.clerk.EditClerk;
import com.example.lenovo.doctorapp.clerk.SearchClerk;
import com.example.lenovo.doctorapp.clerk.ViewClerk;

public class DoctorActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public void add(View v)
    {
Intent i=new Intent(this,AddClerk.class);
        startActivity(i);
    }

    public void search(View v)
    {

        Intent i=new Intent(this,SearchClerk.class);
        startActivity(i);
    }


    public void edit(View v)
    {

        Intent i=new Intent(this,EditClerk.class);
        startActivity(i);
    }


    public void view(View v)
    {

        Intent i=new Intent(this,ViewClerk.class);
        startActivity(i);
    }

    public void delete(View v)
    {
        Intent i=new Intent(this, DeleteClerk.class);
        startActivity(i);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.doctor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent i=new Intent(this,SplashActivity.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.profile) {
            Intent i =new Intent(this,MyProfile.class);
            startActivity(i);

        } else if (id == R.id.appointment) {

          /*  Intent i=new Intent(this, ViewPatient.class);
            startActivity(i);*/
          Intent i=new Intent(this,NotificationActivity.class);
            startActivity(i);

        }  else if (id == R.id.signout) {
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
        }

        else if (id == R.id.abtus) {
            Intent i=new Intent(this,AboutUs.class);
            startActivity(i);

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
