package com.shajaraapp.shajara.model;


public class Timings{

    private int _id = 1;
    private String _date = "";
    private String _fajrCBS = "0";
    private String _dhuhrCBS = "0";
    private String _asrCBS = "0";
    private String _magribCBS = "0";
    private String _ishaaCBS = "0";
    private String _tahajjudCBS = "0";
    private String _scoreoftheDay = "0";


    public Timings() {

    }

    public Timings(String _date )
    {
        this._date=_date;
    }
    public Timings(int _id, String _date, String _fajrCBS, String _dhuhrCBS, String _asrCBS, String _magribCBS, String _ishaaCBS, String _tahajjudCBS, String _scoreoftheDay) {
        this._date = _date;
        this._fajrCBS = _fajrCBS;
        this._dhuhrCBS = _dhuhrCBS;
        this._asrCBS = _asrCBS;
        this._magribCBS = _magribCBS;
        this._ishaaCBS = _ishaaCBS;
        this._tahajjudCBS = _tahajjudCBS;
        this._scoreoftheDay = _scoreoftheDay;

    }

    public Timings(String _date,String _fajrCBS, String _dhuhrCBS, String _asrCBS, String _magribCBS, String _ishaaCBS, String _tahajjudCBS, String _scoreoftheDay) {
        this._date = _date;
        this._fajrCBS = _fajrCBS;
        this._dhuhrCBS = _dhuhrCBS;
        this._asrCBS = _asrCBS;
        this._magribCBS = _magribCBS;
        this._ishaaCBS = _ishaaCBS;
        this._tahajjudCBS = _tahajjudCBS;
        this._scoreoftheDay = _scoreoftheDay;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }


    public String get_fajrCBS() {
        return _fajrCBS;
    }

    public void set_fajrCBS(String _fajrCBS) {
        this._fajrCBS = _fajrCBS;
    }

    public String get_dhuhrCBS() {
        return _dhuhrCBS;
    }

    public void set_dhuhrCBS(String _dhuhrCBS) {
        this._dhuhrCBS = _dhuhrCBS;
    }

    public String get_asrCBS() {
        return _asrCBS;
    }

    public void set_asrCBS(String _asrCBS) {
        this._asrCBS = _asrCBS;
    }

    public String get_magribCBS() {
        return _magribCBS;
    }

    public void set_magribCBS(String _magribCBS) {
        this._magribCBS = _magribCBS;
    }

    public String get_ishaaCBS() {
        return _ishaaCBS;
    }

    public void set_ishaaCBS(String _ishaaCBS) {
        this._ishaaCBS = _ishaaCBS;
    }

    public String get_tahajjudCBS() {
        return _tahajjudCBS;
    }

    public void set_tahajjudCBS(String _tahajjudCBS) {
        this._tahajjudCBS = _tahajjudCBS;
    }

    public String get_scoreoftheDay() {
        return _scoreoftheDay;
    }

    public void set_scoreoftheDay(String _scoreoftheDay) {
        this._scoreoftheDay = _scoreoftheDay;
    }

    public String toString(){
        return _date + get_fajrCBS() + get_dhuhrCBS() + get_asrCBS() + get_magribCBS() + get_ishaaCBS() + get_scoreoftheDay();
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }
}