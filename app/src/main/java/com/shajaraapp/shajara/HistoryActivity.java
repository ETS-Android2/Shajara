package com.shajaraapp.shajara;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.TintableImageSourceView;
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
////
public class HistoryActivity extends Activity implements View.OnClickListener {
    RelativeLayout mainLayout;
    AnimationDrawable animationDrawable;
    private PendingIntent pendingIntent;

    de.hdodenhof.circleimageview.CircleImageView treeImage;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle DrawerToggle;
    final String[] data = {"settings", "About Us", "Challenges", "My Calender"};
    private ImageButton button;
    private DrawerLayout drawer;
    int addingDates;
    private CheckBox FajrCheckBox, DhuhrCheckBox, AsrCheckBox, MagribCheckBox, IshaaCheckBox, TahajjudCheckBox;
    String fajrcbStates = "0", DhuhrcbStates = "0", AsrcbStates = "0", MagribcbStates = "0", IshaacbStates = "0", TahajjudcbStates = "0", scoreOfTheDay = "0";

    public int score = 0;

    String today = getCurrentDate();

    Timings time =new Timings();
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (ImageButton) findViewById(R.id.but);
        button.setVisibility(View.GONE);
        db = new DatabaseHandler(this);
        SQLiteDatabase sd = db.getWritableDatabase();
        createBGAnimation();
        initiatecheckboxes();

        createTrees();

        today = getCurrentDate();

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
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        String selectedDate = intent.getExtras().getString("SelectedDate");
        time = db.getDateValuesFromRow(selectedDate);
        accordingToTimeGetVariables();//;the checkk box will be shown according to today
        createTrees();
        createCheckBox();

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

    private void accordingToTimeGetVariables() {
        fajrcbStates = time.get_fajrCBS();
        DhuhrcbStates = time.get_dhuhrCBS();
        AsrcbStates = time.get_asrCBS();
        MagribcbStates = time.get_magribCBS();
        IshaacbStates = time.get_ishaaCBS();
        TahajjudcbStates = time.get_tahajjudCBS();
        score = Integer.parseInt(time.get_scoreoftheDay());
    }

    private void accordingToTimeSetVariables() {
        time.set_date(today);
        time.set_fajrCBS(fajrcbStates);
        time.set_dhuhrCBS(DhuhrcbStates);
        time.set_asrCBS(AsrcbStates);
        time.set_magribCBS(MagribcbStates);
        time.set_ishaaCBS(IshaacbStates);
        time.set_tahajjudCBS(TahajjudcbStates);
        time.set_scoreoftheDay(String.valueOf(score));
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
        DhuhrCheckBox = (CheckBox) findViewById(R.id.DhuhrCheckBox);

        AsrCheckBox = (CheckBox) findViewById(R.id.AsrCheckBox);

        MagribCheckBox = (CheckBox) findViewById(R.id.MagribCheckBox);

        IshaaCheckBox = (CheckBox) findViewById(R.id.IshaaCheckBox);

        TahajjudCheckBox = (CheckBox) findViewById(R.id.TahajjudCheckBox);

    }


    public void createCheckBox() {
        if (fajrcbStates.equals("1")) {
            FajrCheckBox.setChecked(true);
        } else if (fajrcbStates.equals("0")) {
            FajrCheckBox.setChecked(false);
        } if (DhuhrcbStates.equals("1")) {
            DhuhrCheckBox.setChecked(true);
        } else if (DhuhrcbStates.equals("0")) {
            DhuhrCheckBox.setChecked(false);
        } if (AsrcbStates.equals("1")) {
            AsrCheckBox.setChecked(true);
        } else if (AsrcbStates.equals("0")) {
            AsrCheckBox.setChecked(false);
        } if (MagribcbStates.equals("1")) {
            MagribCheckBox.setChecked(true);
        } else if (MagribcbStates.equals("0")) {
            MagribCheckBox.setChecked(false);
        } if (IshaacbStates.equals("1")) {
            IshaaCheckBox.setChecked(true);
        } else if (IshaacbStates.equals("0")) {
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

        switch (view.getId()) {
            case R.id.FajrCheckBox:
                if (FajrCheckBox.isChecked()) {

                    fajrcbStates = "1";
                    score++;
                    accordingToTimeSetVariables();
                    db.updateDate(time);
                    ToasttextMashAllah();
                    createTrees();

                }
                if (!FajrCheckBox.isChecked()) {
                    fajrcbStates = "0";
                    score--;
                    accordingToTimeSetVariables();
                    db.updateDate(time);
                    createTrees();
                }
                break;
            case R.id.DhuhrCheckBox:
                if (DhuhrCheckBox.isChecked()) {
                    DhuhrcbStates = "1";
                    ToasttextMashAllah();
                    score++;
                    accordingToTimeSetVariables();
                    db.updateDate(time);
                    createTrees();
                }
                if (!DhuhrCheckBox.isChecked()) {
                    DhuhrcbStates = "0";
                    score--;
                    accordingToTimeSetVariables();
                    db.updateDate(time);
                    createTrees();
                }
                break;
            case R.id.AsrCheckBox:
                if (AsrCheckBox.isChecked()) {
                    AsrcbStates = "1";
                    ToasttextMashAllah();
                    score++;
                    accordingToTimeSetVariables();
                    db.updateDate(time);
                    createTrees();
                }
                if (!AsrCheckBox.isChecked()) {
                    AsrcbStates = "0";
                    score--;
                    accordingToTimeSetVariables();
                    db.updateDate(time);
                    createTrees();
                }
                break;
            case R.id.MagribCheckBox:
                if (MagribCheckBox.isChecked()) {
                    MagribcbStates = "1";
                    ToasttextMashAllah();
                    score++;
                    accordingToTimeSetVariables();
                    db.updateDate(time);
                    createTrees();
                }
                if (!MagribCheckBox.isChecked()) {
                    MagribcbStates = "0";
                    score--;
                    accordingToTimeSetVariables();
                    db.updateDate(time);
                    createTrees();
                }
                break;
            case R.id.IshaaCheckBox:
                if (IshaaCheckBox.isChecked()) {
                    IshaacbStates = "1";
                    ToasttextMashAllah();
                    score++;
                    accordingToTimeSetVariables();
                    db.updateDate(time);
                    createTrees();
                }
                if (!IshaaCheckBox.isChecked()) {
                    IshaacbStates = "0";
                    score--;
                    accordingToTimeSetVariables();
                    db.updateDate(time);
                    createTrees();
                }
                break;
            case R.id.TahajjudCheckBox:
                if (TahajjudCheckBox.isChecked()) {
                    TahajjudcbStates = "1";
                    ToasttextMashAllah();
                    score++;
                    accordingToTimeSetVariables();
                    db.updateDate(time);
                    createTrees();
                }
                if (!TahajjudCheckBox.isChecked()) {
                    TahajjudcbStates = "0";
                    score--;
                    accordingToTimeSetVariables();
                    db.updateDate(time);
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

        addingDates=1;
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY,3);
        calendar.set(Calendar.MINUTE,15);
        Toast.makeText(this,"yuss queen",Toast.LENGTH_SHORT).show();
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
