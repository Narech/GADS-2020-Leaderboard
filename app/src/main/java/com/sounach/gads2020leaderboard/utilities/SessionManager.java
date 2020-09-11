package com.sounach.gads2020leaderboard.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SessionManager {
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private final static String PREFS_NAME="gads_leaderboard";
    private  final static int PRIVATE_MODE=0;

    Context context;
    public SessionManager(Context context){
        this.context=context;
        prefs=context.getSharedPreferences(PREFS_NAME,PRIVATE_MODE);
        editor=prefs.edit();
    }


    public void setData(String name, String value)
    {
        editor.putString(name,value);
        editor.commit();
    }
    public String getData(String name) {

        return prefs.getString(name,null);
    }




}
