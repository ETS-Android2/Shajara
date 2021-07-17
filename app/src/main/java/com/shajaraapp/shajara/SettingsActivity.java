package com.shajaraapp.shajara;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;

public class SettingsActivity extends AppCompatActivity {


    private PendingIntent pendingIntent;
    private ImageButton SettingsBackButton;
    private Switch Jummah_switch1;
    private Switch Fajr_switch2,Dhuhr_switch3,Asr_switch4, Magrib_switch5,Ishaa_switch6, Tahajjud_switch7;
    int k;
    int fajr = 0;
    public static int dhuhr = 0;
    public static int asr = 0;
    public static int magrib= 0;
    public static int ishaa= 0;
    public static int tahajjud= 0;
    public static int jummah = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
       // Intent alarmIntent = new Intent(SettingsActivity.this, AlarmReceiverClass.class);
        //PendingIntent pendingIntent = PendingIntent.getBroadcast(SettingsActivity.this, 0, alarmIntent, 0);
        ImageButton SettingsBackButton=(ImageButton)findViewById(R.id.Back_buttonSetting);
        Jummah_switch1=(Switch)findViewById(R.id.jummah_switch1);
        Fajr_switch2=(Switch)findViewById(R.id.fajr_switch2);
        Dhuhr_switch3=(Switch)findViewById(R.id.dhuhr_switch3);
        Asr_switch4=(Switch)findViewById(R.id.asr_switch4);
        Magrib_switch5=(Switch)findViewById(R.id.magrib_switch5);
        Ishaa_switch6=(Switch)findViewById(R.id.ishaa__switch6);
        Tahajjud_switch7=(Switch)findViewById(R.id.Tahajjud_switch7);
        boolean fajr_value1 = false;
        boolean dhuhr_value2 = false;
        boolean asr_value3 = false;
        boolean magrib_value4 = false;
        boolean Ishaa_value5 = false;
        boolean tahajjud_value6 = false;
        boolean jummah_value7 = false;

        final SharedPreferences sharedPreferences1 = getSharedPreferences("isCheckedfajr", 0);
        final SharedPreferences sharedPreferences2= getSharedPreferences("isCheckedDhuhr", 0);
        final SharedPreferences sharedPreferences3 = getSharedPreferences("isCheckedAsr", 0);
        final SharedPreferences sharedPreferences4 = getSharedPreferences("isCheckedMagrib", 0);
        final SharedPreferences sharedPreferences5 = getSharedPreferences("isCheckedIshaa", 0);
        final SharedPreferences sharedPreferences6 = getSharedPreferences("isCheckedTahajjud", 0);
        final SharedPreferences sharedPreferences7= getSharedPreferences("isCheckedJummah", 0);

        fajr_value1 = sharedPreferences1.getBoolean("isCheckedfajr", fajr_value1);
        dhuhr_value2 = sharedPreferences2.getBoolean("isCheckedDhuhr",  dhuhr_value2);
        asr_value3= sharedPreferences3.getBoolean("isCheckedAsr",asr_value3);
        magrib_value4= sharedPreferences4.getBoolean("isCheckedMagrib", magrib_value4);
        Ishaa_value5= sharedPreferences5.getBoolean("isCheckedIshaa",  Ishaa_value5);
        tahajjud_value6= sharedPreferences6.getBoolean("isCheckedTahajjud",tahajjud_value6);
        jummah_value7= sharedPreferences7.getBoolean("isCheckedJummah",jummah_value7);

        Fajr_switch2.setChecked( fajr_value1);
        Dhuhr_switch3.setChecked(dhuhr_value2);
        Asr_switch4.setChecked( asr_value3);
        Magrib_switch5.setChecked( magrib_value4);
        Ishaa_switch6.setChecked(Ishaa_value5);
        Tahajjud_switch7.setChecked( tahajjud_value6);
        Jummah_switch1.setChecked( jummah_value7);

        if(sharedPreferences1.getBoolean("isCheckedfajr",true))
        {
            fajr = 1;
        }
        else
            fajr=0;

        if(sharedPreferences2.getBoolean("isCheckedDhuhr",true))
        {
           dhuhr=1;
        }
        else
            dhuhr=0;

        if(sharedPreferences3.getBoolean("isCheckedAsr",true))
        {
          asr=1;
        }
        else
            asr=0;

        if(sharedPreferences4.getBoolean("isCheckedMagrib",true))
        {
            magrib = 1;
        }
        else
            magrib=0;

        if(sharedPreferences5.getBoolean("isCheckedIshaa",true))
        {
            ishaa= 1;
        }
        else
           ishaa=0;

        if(sharedPreferences6.getBoolean("isCheckedTahajjud",true))
        {
            tahajjud = 1;
        }
        else
            tahajjud=0;
        if(sharedPreferences7.getBoolean("isCheckedJummah",true))
        {
           jummah= 1;
        }
        else
            jummah=0;

        Jummah_switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked==true)
                {
                    jummah=1;
                    startAt1();
                    sharedPreferences7.edit().putBoolean("isCheckedJummah", true).apply();

                }
                else
                {
                    jummah=0;
                    sharedPreferences7.edit().putBoolean("isCheckedJummah",false).apply();
                    cancel();
                }
            }
        });
        Fajr_switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                {
                    fajr= 1;
                    startFajrNotification();
                    sharedPreferences1.edit().putBoolean("isCheckedfajr", true).apply();

                }
                else
                {
                    fajr=0;
                    sharedPreferences1.edit().putBoolean("isCheckedfajr", false).apply();
                    cancel();
                }
            }
        });
        Dhuhr_switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                {
                    dhuhr=1;
                    sharedPreferences2.edit().putBoolean("isCheckedDhuhr",true).apply();
                    startZuhrNotification();

                }
                else
                {
                    dhuhr=0;
                    sharedPreferences2.edit().putBoolean("isCheckedDhuhr", false).apply();
                    cancel();
                }
            }
        });
        Asr_switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                {
                    asr=1;
                    sharedPreferences3.edit().putBoolean("isCheckedAsr", true).apply();
                    startAsrNotification();

                }
                else
                {
                    asr=0;
                    sharedPreferences3.edit().putBoolean("isCheckedAsr", false).apply();
                    cancel();
                }
            }
        });
        Magrib_switch5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                {
                    magrib=1;
                    sharedPreferences4.edit().putBoolean("isCheckedMagrib",true).apply();
                    startMagribNotification();

                }
                else
                {
                    magrib=0;
                    sharedPreferences4.edit().putBoolean("isCheckedMagrib", false).apply();
                    cancel();
                }
            }
        });
        Ishaa_switch6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                {
                   ishaa =1;
                    sharedPreferences5.edit().putBoolean("isCheckedIshaa",true).apply();
                    startIshaaNotification();
                }
                else
                {
                    ishaa=0;
                    sharedPreferences5.edit().putBoolean("isCheckedIshaa",false).apply();
                    cancel();
                }
            }
        });
        Tahajjud_switch7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true)
                {
                    tahajjud=1;
                    sharedPreferences6.edit().putBoolean("isCheckedTahajjud",true).apply();
                    startTahajjudNotification();
                }
                else
                {
                    tahajjud=0;
                    sharedPreferences6.edit().putBoolean("isCheckedTahajjud",false).apply();
                    cancel();
                }
            }
        });
    }
    public  void backButton(View v)
    {
        backButtonSetting();
    }
    public  void backButtonSetting()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void cancel() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(SettingsActivity.this, AlarmReceiverClass.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(SettingsActivity.this, 8, alarmIntent, 0);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
    }

    public void startAt1() {


        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        /* Set the alarm*/
        Calendar calendar = Calendar.getInstance();
        Intent alarmIntent = new Intent(SettingsActivity.this, AlarmReceiverClass.class);
        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(SettingsActivity.this, 0, alarmIntent, 0);
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        calendar.set(Calendar.HOUR_OF_DAY, 13);
        calendar.set(Calendar.MINUTE,15);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY ,pendingIntent1 );


    }
    public void startFajrNotification() {


        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        Intent alarmIntent = new Intent(SettingsActivity.this, AlarmReceiverClass.class);
        PendingIntent pendingIntentfajr = PendingIntent.getBroadcast(SettingsActivity.this, 1, alarmIntent, 0);
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 4);
        Toast.makeText(this, "fajr!", Toast.LENGTH_SHORT).show();

        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY ,pendingIntentfajr);


    }
    public void startZuhrNotification() {


        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        Intent alarmIntent = new Intent(SettingsActivity.this, AlarmReceiverClass.class);
        PendingIntent pendingIntentZuhr = PendingIntent.getBroadcast(SettingsActivity.this, 2, alarmIntent, 0);
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 13);
        Toast.makeText(this, "zuhr!", Toast.LENGTH_SHORT).show();

        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY ,pendingIntentZuhr);


    }
    public void startAsrNotification() {


        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        Intent alarmIntent = new Intent(SettingsActivity.this, AlarmReceiverClass.class);
        PendingIntent pendingIntentAsr = PendingIntent.getBroadcast(SettingsActivity.this, 3, alarmIntent, 0);
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE,30);
        Toast.makeText(this, "Asr!", Toast.LENGTH_SHORT).show();
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY ,pendingIntentAsr);


    }
    public void startMagribNotification() {


        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        Intent alarmIntent = new Intent(SettingsActivity.this, AlarmReceiverClass.class);
        PendingIntent pendingIntentMagrib = PendingIntent.getBroadcast(SettingsActivity.this, 4, alarmIntent, 0);
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE,50);
        Toast.makeText(this, "Magrib!", Toast.LENGTH_SHORT).show();
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY ,pendingIntentMagrib);


    }
    public void startIshaaNotification() {


        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        Intent alarmIntent = new Intent(SettingsActivity.this, AlarmReceiverClass.class);
        PendingIntent pendingIntentIshaa = PendingIntent.getBroadcast(SettingsActivity.this, 5, alarmIntent, 0);
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE,30);
        Toast.makeText(this, "ishaa!", Toast.LENGTH_SHORT).show();
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY ,pendingIntentIshaa);


    }
    public void startTahajjudNotification() {


        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        Intent alarmIntent = new Intent(SettingsActivity.this, AlarmReceiverClass.class);
        PendingIntent pendingIntentTaha = PendingIntent.getBroadcast(SettingsActivity.this, 6, alarmIntent, 0);
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 02);
        Toast.makeText(this, "tahajjud!", Toast.LENGTH_SHORT).show();

        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY ,pendingIntentTaha);


    }



}
