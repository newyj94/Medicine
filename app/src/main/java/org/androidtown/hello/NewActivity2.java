package org.androidtown.hello;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NewActivity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Calendar Time;

    private Intent intent;
    private PendingIntent ServicePending;
    private AlarmManager alarmManager;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 mm분 ss초");

    TextView textView;

    DatePickerDialog.OnDateSetListener eDateSetListener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthofYear, int dayofMonth){
            Time.set(Calendar.YEAR, year);
            Time.set(Calendar.MONTH, monthofYear);
            Time.set(Calendar.DAY_OF_MONTH, dayofMonth);

            updateLabel();
        }
    };

    private TimePickerDialog.OnTimeSetListener sTimeSetListener = new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker view, int hourofDay, int minute){
            Time.set(Calendar.HOUR_OF_DAY, hourofDay);
            Log.d("check","1 = "+hourofDay);
            Time.set(Calendar.MINUTE, minute);
            Log.d("check","2 = "+minute);
            Time.set(Calendar.SECOND, 0);

            updateLabel();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new2);

        Time = Calendar.getInstance();

        Button.OnClickListener bClickListener = new View.OnClickListener(){
            public void onClick(View v){
                switch (v.getId()){
                    case R.id.setAlarm:
                        setAlarm();
                        break;
                    case R.id.removeAlarm:
                        removeAlarm();
                        break;
                    case R.id.setTime:
                        new TimePickerDialog(NewActivity2.this, sTimeSetListener, Time.get(Calendar.HOUR_OF_DAY),Time.get(Calendar.MINUTE),false).show();
                        new DatePickerDialog(NewActivity2.this, eDateSetListener, Time.get(Calendar.YEAR),Time.get(Calendar.MONTH),Time.get(Calendar.DAY_OF_MONTH)).show();
                        break;
                    case R.id.repeatAlarm:
                        setRepeatAlarm();
                        break;
                }
            }
        };

        findViewById(R.id.setAlarm).setOnClickListener(bClickListener);
        findViewById(R.id.removeAlarm).setOnClickListener(bClickListener);
        findViewById(R.id.setTime).setOnClickListener(bClickListener);
        findViewById(R.id.repeatAlarm).setOnClickListener(bClickListener);

        alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        textView = (TextView)findViewById(R.id.textView);
        updateLabel();



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
    private void updateLabel(){
        textView.setText(simpleDateFormat.format(Time.getTime()));
    }

    void setAlarm(){
        intent = new Intent("NewActivity2_AlarmReceiver");

        ServicePending = PendingIntent.getBroadcast(NewActivity2.this,111,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP,Time.getTimeInMillis(),ServicePending);
        Toast.makeText(getBaseContext(),"알람 설정"+Time.getTime(),Toast.LENGTH_SHORT).show();
    }

    void removeAlarm(){
        intent = new Intent("AlarmReceiver");
        ServicePending = PendingIntent.getBroadcast(NewActivity2.this,111,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        Toast.makeText(getBaseContext(),"알람 해제"+Time.getTime(),Toast.LENGTH_SHORT).show();
        alarmManager.cancel(ServicePending);
    }

    void setRepeatAlarm(){
        intent = new Intent("AlarmReceiver");
        ServicePending = PendingIntent.getBroadcast(NewActivity2.this,111,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        Log.d("ServicePending : ",""+ServicePending.toString());
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,Time.getTimeInMillis(),20000,ServicePending);
        Toast.makeText(getBaseContext(),"알람 설정"+Time.getTime(),Toast.LENGTH_SHORT).show();

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

}