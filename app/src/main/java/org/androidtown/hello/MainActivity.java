package org.androidtown.hello;

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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();

        if (id == R.id.nav_share) {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }  else if (id == R.id.nav_search) {
            Intent intent = new Intent(getApplicationContext(),NewActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_alarm) {
            Intent intent1 = new Intent(getApplicationContext(),NewActivity2.class);
            startActivity(intent1);
        } else if (id == R.id.nav_mypage) {
            Intent intent2 = new Intent(getApplicationContext(),NewActivity3main.class);
            startActivity(intent2);
        } else if (id == R.id.nav_near_pharm) {
            Intent intent3 = new Intent(getApplicationContext(),NewActivity4.class);
            startActivity(intent3);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawers();
        return true;
    }

    public void onButton1Clicked(View v){
        Intent intent = new Intent(getApplicationContext(),NewActivity.class);
        startActivity(intent);
    }
    public void onButton2Clicked(View v){
        Intent intent1 = new Intent(getApplicationContext(),NewActivity2.class);
        startActivity(intent1);
    }
    public void onButton3Clicked(View v){
        Intent intent2 = new Intent(getApplicationContext(),NewActivity3main.class);
        startActivity(intent2);
    }
    public void onButton4Clicked(View v){
        Intent intent3 = new Intent(getApplicationContext(),NewActivity4.class);
        startActivity(intent3);
    }

}
