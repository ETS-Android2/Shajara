package com.shajaraapp.shajara;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.shajaraapp.shajara.database.DatabaseHandler;
import com.shajaraapp.shajara.model.Timings;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends Activity implements View.OnClickListener {
    RelativeLayout mainLayout;
    AnimationDrawable animationDrawable;
    private PendingIntent pendingIntent;
    public boolean check;

    de.hdodenhof.circleimageview.CircleImageView treeImage;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle DrawerToggle;
    final String[] data = {"settings", "About Us", "Challenges", "My Calender"};
    private ImageButton button;
    private DrawerLayout drawer;
    int addingDates;
    private CheckBox FajrCheckBox, DhuhrCheckBox, AsrCheckBox, MagribCheckBox, IshaaCheckBox, TahajjudCheckBox;
    String fajrcbStates = "0", DhuhrcbStates = "0", AsrcbStates = "0", MagribcbStates = "0", IshaacbStates = "0", TahajjudcbStates = "0", scoreOfTheDay = "0";

    public int score= 0;

    String today = getCurrentDate();

    Timings time=new Timings();
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent alarmIntent1 = new Intent(MainActivity.this, AlarmReceiverClass.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 1, alarmIntent1, PendingIntent.FLAG_UPDATE_CURRENT);
        db = new DatabaseHandler(this);
        SQLiteDatabase sd = db.getWritableDatabase();
        //for main_bg_animation to make a three layered gradient bg
        createBGAnimation();
        //for navDrawerbutton to make the hamburger icon work as a navigation Drawer
        makeHamburgerIconNavDrawer();
        //for initiating checkboxes
        initiatecheckboxes();
        //for trees to grow
        createTrees();

        today = getCurrentDate();
        everdayDates();
        Log.d("get_rekt", time.toString());

        Log.d("debugdate", getCurrentDate());
        Log.d("debugtime", getCurrentTime());

        disableallCB();
        logicallyEnableCB();
    }

    public void disableallCB() {
        disableCB(FajrCheckBox);
        disableCB(DhuhrCheckBox);
        disableCB(AsrCheckBox);
        disableCB(MagribCheckBox);
        disableCB(IshaaCheckBox);
        disableCB(TahajjudCheckBox);
    }


    @Override
    protected  void onRestart()
    {
        super.onRestart();
       disableallCB();
        logicallyEnableCB();

    }
    protected void onResume() {
        super.onResume();
        disableallCB();
        logicallyEnableCB();

        today = getCurrentDate();
        try {
            db.addnewDate(new Timings(today, "0","0","0","0","0","0","0"));
            time = db.getDateValuesFromRow(today);
            fajrcbStates = time.get_fajrCBS();
            DhuhrcbStates = time.get_dhuhrCBS();
            AsrcbStates = time.get_asrCBS();
            MagribcbStates = time.get_magribCBS();
            IshaacbStates = time.get_ishaaCBS();
            TahajjudcbStates = time.get_tahajjudCBS();
            score = Integer.parseInt(time.get_scoreoftheDay());
            createCheckBox();
            createTrees();

        } catch (Exception e) {


        } finally {
            time = db.getDateValuesFromRow(today);

            fajrcbStates = time.get_fajrCBS();
            DhuhrcbStates = time.get_dhuhrCBS();
            AsrcbStates = time.get_asrCBS();
            MagribcbStates = time.get_magribCBS();
            IshaacbStates = time.get_ishaaCBS();
            TahajjudcbStates = time.get_tahajjudCBS();
            score = Integer.parseInt(time.get_scoreoftheDay());
           createCheckBox();
           createTrees();

        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        today = getCurrentDate();
    }

    private void createTrees() {
        treeImage = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.trees);
        if (score == 0) {
            treeImage.setImageResource(R.drawable.tree0);
            Toast.makeText(this, "Start praying to plant seeds and grow trees", Toast.LENGTH_LONG).show();
        }

        if (score == 1)
            treeImage.setImageResource(R.drawable.tree1);
        if (score == 2)
            treeImage.setImageResource(R.drawable.tree2);
        if (score == 3)
            treeImage.setImageResource(R.drawable.tree3);
        if (score == 4)
            treeImage.setImageResource(R.drawable.tree4);
        if (score == 5)
            treeImage.setImageResource(R.drawable.tree5);
        if (score == 6)
            treeImage.setImageResource(R.drawable.tree6);
    }

    private void makeHamburgerIconNavDrawer() {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
        button = (ImageButton) findViewById(R.id.but);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ListView navList = (ListView) findViewById(R.id.drawer);
        navList.setAdapter(adapter);
        navList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int pos, long id) {
                if (pos == 0) {
                    settings();
                } else if (pos == 1) {
                    AboutUs();
                } else if (pos == 2) {
                    challenges();
                } else if (pos == 3) {
                    calender();
                }
                drawer.closeDrawer(navList);

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });
    }

    private void accordingToTimeGetVariables( Timings time ) {
         fajrcbStates = time.get_fajrCBS();
        DhuhrcbStates = time.get_dhuhrCBS();
        AsrcbStates = time.get_asrCBS();
        MagribcbStates = time.get_magribCBS();
        IshaacbStates = time.get_ishaaCBS();
        TahajjudcbStates = time.get_tahajjudCBS();
        score = Integer.parseInt(time.get_scoreoftheDay());

    }



    private void createBGAnimation() {

        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);
        animationDrawable = (AnimationDrawable) mainLayout.getBackground();
        animationDrawable.setEnterFadeDuration(6500);
        animationDrawable.setExitFadeDuration(6500);
        animationDrawable.start();
    }

    private void initiatecheckboxes() {
        FajrCheckBox = (CheckBox) findViewById(R.id.FajrCheckBox);

        FajrCheckBox.setOnClickListener(this);
        DhuhrCheckBox = (CheckBox) findViewById(R.id.DhuhrCheckBox);
        DhuhrCheckBox.setOnClickListener(this);
        AsrCheckBox = (CheckBox) findViewById(R.id.AsrCheckBox);
        AsrCheckBox.setOnClickListener(this);
        MagribCheckBox = (CheckBox) findViewById(R.id.MagribCheckBox);
        MagribCheckBox.setOnClickListener(this);
        IshaaCheckBox = (CheckBox) findViewById(R.id.IshaaCheckBox);
        IshaaCheckBox.setOnClickListener(this);
        TahajjudCheckBox = (CheckBox) findViewById(R.id.TahajjudCheckBox);
        TahajjudCheckBox.setOnClickListener(this);
    }


    public void createCheckBox() {
        if (fajrcbStates.equals("1")) {
            FajrCheckBox.setChecked(true);
        } else if (fajrcbStates.equals("0")){
            FajrCheckBox.setChecked(false);
        } if (DhuhrcbStates.equals("1")) {
            DhuhrCheckBox.setChecked(true);
        } else if (DhuhrcbStates.equals("0")) {
            DhuhrCheckBox.setChecked(false);
        } if (AsrcbStates.equals("1")) {
            AsrCheckBox.setChecked(true);
        } else if (AsrcbStates .equals("0")) {
            AsrCheckBox.setChecked(false);
        } if (MagribcbStates .equals("1")){
            MagribCheckBox.setChecked(true);
        } else if (MagribcbStates.equals("0")){
            MagribCheckBox.setChecked(false);
        } if (IshaacbStates .equals ("1")){
            IshaaCheckBox.setChecked(true);
        } else if (IshaacbStates .equals("0")) {
            IshaaCheckBox.setChecked(false);
        } if (TahajjudcbStates.equals("1")) {
            TahajjudCheckBox.setChecked(true);
        } else if (TahajjudcbStates.equals("0")) {
            TahajjudCheckBox.setChecked(false);
        }

    }

    public void logicallyEnableCB() {
        disableallCB();
        if ((getCurrentTime()).compareTo(Test_Database.ISHAA_TIME) >= 0) {
            enableCB(FajrCheckBox);
            enableCB(DhuhrCheckBox);
            enableCB(AsrCheckBox);
            enableCB(MagribCheckBox);
            enableCB(IshaaCheckBox);
        } else if ((getCurrentTime()).compareTo(Test_Database.MAGRIB_TIME) >= 0) {
            enableCB(FajrCheckBox);
            enableCB(DhuhrCheckBox);
            enableCB(AsrCheckBox);
            enableCB(MagribCheckBox);

        } else if ((getCurrentTime()).compareTo(Test_Database.ASR_TIME) >= 0) {
            enableCB(FajrCheckBox);
            enableCB(DhuhrCheckBox);
            enableCB(AsrCheckBox);
        } else if ((getCurrentTime()).compareTo(Test_Database.DHUHR_TIME) >= 0) {
            enableCB(FajrCheckBox);
            enableCB(DhuhrCheckBox);
        } else if ((getCurrentTime()).compareTo(Test_Database.FAJR_TIME) >= 0) {
            enableCB(FajrCheckBox);
        } else if ((getCurrentTime()).compareTo(Test_Database.TAHAJJUD_TIME) >= 0) {

            fajrcbStates="0";
            DhuhrcbStates="0";
            AsrcbStates="0";
            MagribcbStates="0";
            IshaacbStates="0";
            TahajjudcbStates="0";
            enableCB(TahajjudCheckBox);
        }
    }

    public void settings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void challenges() {
        Intent intent = new Intent(this, ChallengesActivity.class);
        startActivity(intent);
    }

    public void AboutUs() {
        Intent intent = new Intent(this, AboutUSActivity.class);
        startActivity(intent);
    }

    private void calender() {
        Intent intent = new Intent(this, CalenderActivity.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        db.getWritableDatabase();

        switch (view.getId()) {
            case R.id.FajrCheckBox:
                if (FajrCheckBox.isChecked()) {

                    fajrcbStates = "1";
                    score++;
                    check =  db.updateFajr( fajrcbStates,today);
                    if(check==true )
                    {
                        ToasttextMashAllah();
                    }
                    db.updateScore(String.valueOf(score),today);
                    createTrees();

                }
                if (!FajrCheckBox.isChecked()) {
                    fajrcbStates = "0";
                    score--;
                    check =  db.updateFajr( fajrcbStates,today);
                    db.updateScore(String.valueOf(score),today);
                    createTrees();
                }
                break;
            case R.id.DhuhrCheckBox:
                if (DhuhrCheckBox.isChecked()) {
                    DhuhrcbStates = "1";
                    check =  db.updateDhuhr( DhuhrcbStates,today);
                    if(check==true )
                    {
                        ToasttextMashAllah();
                    }
                    score++;
                    db.updateScore(String.valueOf(score),today);
                    createTrees();
                }
                if (!DhuhrCheckBox.isChecked()) {
                    DhuhrcbStates = "0";
                    score--;
                    check =  db.updateDhuhr( DhuhrcbStates,today);
                    db.updateScore(String.valueOf(score),today);
                    createTrees();
                }
                break;
            case R.id.AsrCheckBox:
                if (AsrCheckBox.isChecked()) {
                    AsrcbStates = "1";
                    check =  db.updateAsr(AsrcbStates,today);
                    if(check==true )
                    {
                        ToasttextMashAllah();
                    }
                    score++;
                    db.updateScore(String.valueOf(score),today);
                    createTrees();
                }
                if (!AsrCheckBox.isChecked()) {
                    AsrcbStates = "0";
                    score--;
                    db.updateAsr(AsrcbStates,today);
                    db.updateScore(String.valueOf(score),today);
                    createTrees();
                }
                break;
            case R.id.MagribCheckBox:
                if (MagribCheckBox.isChecked()) {
                    MagribcbStates = "1";
                    check =  db.updateMagrib(MagribcbStates,today);
                    if(check==true )
                    {
                        ToasttextMashAllah();
                    }
                    score++;
                    db.updateScore(String.valueOf(score),today);
                    createTrees();
                }
                if (!MagribCheckBox.isChecked()) {
                    MagribcbStates = "0";
                    score--;
                    db.updateMagrib(MagribcbStates,today);
                    db.updateScore(String.valueOf(score),today);
                    createTrees();
                }
                break;
            case R.id.IshaaCheckBox:
                if (IshaaCheckBox.isChecked()) {
                    IshaacbStates = "1";
                    check =  db.updateIshaa(IshaacbStates,today);
                    if(check==true )
                    {
                        ToasttextMashAllah();
                    }
                    score++;
                    db.updateScore(String.valueOf(score),today);

                    createTrees();
                }
                if (!IshaaCheckBox.isChecked()) {
                    IshaacbStates = "0";
                    score--;
                    db.updateIshaa(IshaacbStates,today);
                    db.updateScore(String.valueOf(score),today);
                    createTrees();
                }
                break;
            case R.id.TahajjudCheckBox:
                if (TahajjudCheckBox.isChecked()) {
                    TahajjudcbStates = "1";
                    check =  db.updateTahajjud(TahajjudcbStates,today);
                    if(check==true )
                    {
                        ToasttextMashAllah();
                    }
                    score++;
                    db.updateScore(String.valueOf(score),today);
                    createTrees();

                }
                if (!TahajjudCheckBox.isChecked()) {
                    TahajjudcbStates = "0";
                    score--;
                    db.updateTahajjud(TahajjudcbStates,today);
                    db.updateScore(String.valueOf(score),today);
                    createTrees();
                }
                break;
        }
    }

    private void disableCB(CheckBox checkBox) {
        checkBox.setEnabled(false);
    }

    private void enableCB(CheckBox checkBox) {
        checkBox.setEnabled(true);
    }

    private void makeEveryCBFalse() {
        FajrCheckBox.setChecked(false);
        DhuhrCheckBox.setChecked(false);
        AsrCheckBox.setChecked(false);
        MagribCheckBox.setChecked(false);
        IshaaCheckBox.setChecked(false);
        TahajjudCheckBox.setChecked(false);
    }

    private void ToasttextMashAllah() {
        Toast.makeText(this, "Mashaa Allah!", Toast.LENGTH_SHORT).show();
    }

    private void ToasttextDateChanged() {
        Toast.makeText(this, "Date Changed!", Toast.LENGTH_SHORT).show();
    }


    public void everdayDates() {

        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        Intent alarmIntent1 = new Intent(MainActivity.this, AlarmReceiverClass.class);
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,3);
        calendar.set(Calendar.MINUTE,15);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY ,pendingIntent);


    }
    public String getCurrentDate() {
        Date date = new Date();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd - MM - yyyy");
        String strDate = mdformat.format(date.getTime());
        return strDate;
    }

    public String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat mdformat = new SimpleDateFormat("kk:mm");
        String strTime = mdformat.format(date.getTime());
        return strTime;
    }
}