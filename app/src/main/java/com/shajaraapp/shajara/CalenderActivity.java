package com.shajaraapp.shajara;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.shajaraapp.shajara.database.DatabaseHandler;
import com.shajaraapp.shajara.model.Timings;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalenderActivity extends AppCompatActivity {

    String[] data;
    private PendingIntent pendingIntent;
    DatabaseHandler db;
    List<String> myDates;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        db = new DatabaseHandler(this);
        SQLiteDatabase sd = db.getWritableDatabase();
        Intent alarmIntent = new Intent(CalenderActivity.this, AlarmReceiverClass.class);
        pendingIntent = PendingIntent.getBroadcast(CalenderActivity.this, 0, alarmIntent, 0);
        updateCalender();
        myDates = db.getAllValues();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myDates);
        final ListView navList = (ListView) findViewById(R.id.drawer);
        navList.setAdapter(adapter);
        navList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                String selectedFromList = (navList.getItemAtPosition(pos).toString());
                Intent intent = new Intent(CalenderActivity.this, HistoryActivity.class);
                intent.putExtra("SelectedDate", selectedFromList);
                startActivity(intent);
            }
        });

    }

    private void updateCalender() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        Intent alarmIntent = new Intent(CalenderActivity.this, AlarmReceiverClass.class);
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 3);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

    }
}
