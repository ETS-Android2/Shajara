package com.shajaraapp.shajara.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.shajaraapp.shajara.model.Timings;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "timings.db";
    private static final String TABLE_NAME = "TIMINGS";
    private static final String KEY_ID = "ID";
    private static final String KEY_DATE = "DATE";
    private static final String KEY_FAJR = "FAJR";
    private static final String KEY_DHUHR = "DHUHR";
    private static final String KEY_ASR = "ASR";
    private static final String KEY_MAGRIB = "MAGRIB";
    private static final String  KEY_ISHAA = "ISHAA";
    private static final String KEY_TAHAJJUD = "TAHAJJUD";
    private static final String KEY_SCORE = "SCORE";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String CREATE_CONTACT_TABLE="CREATE TABLE "+ TABLE_NAME +"("
                + KEY_ID +" INTEGER PRIMARY KEY,"
                + KEY_DATE +" TEXT UNIQUE ,"
                + KEY_FAJR +" TEXT,"
                + KEY_DHUHR +" TEXT,"
                + KEY_ASR+" TEXT,"
                + KEY_MAGRIB+" TEXT,"
                + KEY_ISHAA +" TEXT,"
                + KEY_TAHAJJUD +" TEXT,"
                + KEY_SCORE +" TEXT,"+")";


        String CREATE_TABLE = "CREATE TABLE TIMINGS (ID INTEGER PRIMARY KEY AUTOINCREMENT" +
                ", DATE TEXT UNIQUE" +
                ", FAJR TEXT" +
                ", DHUHR TEXT" +
                ", ASR TEXT" +
                ", MAGRIB TEXT" +
                ", ISHAA TEXT" +
                ", TAHAJJUD TEXT" +
                ", SCORE TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void addnewDate(Timings timings) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();

        value.put(KEY_DATE, timings.get_date());
        value.put(KEY_FAJR, timings.get_fajrCBS());
        value.put(KEY_DHUHR, timings.get_dhuhrCBS());
        value.put(KEY_ASR, timings.get_asrCBS());
        value.put(KEY_MAGRIB, timings.get_magribCBS());
        value.put(KEY_ISHAA, timings.get_ishaaCBS());
        value.put(KEY_TAHAJJUD, timings.get_tahajjudCBS());
        value.put(KEY_SCORE,timings.get_scoreoftheDay());


        db.insert(TABLE_NAME, null,value);
        db.close();
    }

    public void updateDate(Timings timings) {
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "UPDATE TIMINGS SET " + "FAJR = "+timings.get_fajrCBS()+
                ",DHUHR = "+timings.get_dhuhrCBS()+
                ",ASR = "+timings.get_asrCBS()+
                ",MAGRIB = "+timings.get_magribCBS()+
                ",ISHAA = "+timings.get_ishaaCBS()+
                ",TAHAJJUD = "+timings.get_tahajjudCBS()+
                ",SCORE = "+timings.get_scoreoftheDay()+
                " WHERE DATE = "+timings.get_date();
        db.execSQL(query);
        db.close();
    }
    Timings time =null;
    public Timings getSingleContact(String date)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT ID,FAJR,DHUHR,ASR,MAGRIB,ISHAA,TAHAJJUD,SCORE  FROM TIMINGS WHERE DATE = "+date;
        Cursor cursor=db.rawQuery(query, null);
        if(cursor.moveToFirst())
        {
           time =new Timings(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));
        }

        return time;
    }



    public Timings getDateValuesFromRow(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE DATE='"+date+"'";
        Cursor cursor = db.rawQuery(query, null);
        Timings time = null;
        if (cursor.moveToFirst()) {
            time = new Timings(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8));
        }
        return time;
    }
    public List<String> getAllValues()
    {
        List<String> myDates= new ArrayList<String>();

        String selectquery="SELECT * FROM "+ TABLE_NAME;// where phoneno LIKE '017%'";

        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery(selectquery, null);

        if(cursor.moveToFirst())
        {
            do
            {
                String allvalues = cursor.getString(1);
                myDates.add(allvalues);
            }while(cursor.moveToNext());
        }
        return myDates;
    }
    public boolean number_update( String new_number,String pass){
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("UPDATE contact SET phoneNo=? WHERE password=?", new String[]{new_number,pass});
        return true;

    }
   public boolean updateFajr(String fajr,String date) {
       SQLiteDatabase db = this.getWritableDatabase();


       db.execSQL("UPDATE timings SET FAJR=? WHERE DATE=?", new String[]{fajr, date});
       db.close();
       return true;
   }
    public boolean updateDhuhr(String dhuhr,String date) {
        SQLiteDatabase db = this.getWritableDatabase();


        db.execSQL("UPDATE timings SET DHUHR=? WHERE DATE=?", new String[]{dhuhr, date});
        db.close();
        return true;
    }
    public boolean updateAsr(String Asr,String date) {
        SQLiteDatabase db = this.getWritableDatabase();


        db.execSQL("UPDATE timings SET ASR=? WHERE DATE=?", new String[]{Asr, date});
        db.close();
        return true;
    }
    public boolean updateMagrib(String magrib,String date) {
        SQLiteDatabase db = this.getWritableDatabase();


        db.execSQL("UPDATE timings SET MAGRIB=? WHERE DATE=?", new String[]{magrib, date});
        db.close();
        return true;
    }
    public boolean updateIshaa(String ishaa,String date) {
        SQLiteDatabase db = this.getWritableDatabase();


        db.execSQL("UPDATE timings SET ISHAA=? WHERE DATE=?", new String[]{ishaa, date});
        db.close();
        return true;
    }
    public boolean updateTahajjud(String tahajjud,String date) {
        SQLiteDatabase db = this.getWritableDatabase();


        db.execSQL("UPDATE timings SET TAHAJJUD=? WHERE DATE=?", new String[]{tahajjud, date});
        db.close();
        return true;
    }
    public boolean updateScore(String score,String date) {
        SQLiteDatabase db = this.getWritableDatabase();


        db.execSQL("UPDATE timings SET SCORE=? WHERE DATE=?", new String[]{score, date});
        db.close();
        return true;
    }



}