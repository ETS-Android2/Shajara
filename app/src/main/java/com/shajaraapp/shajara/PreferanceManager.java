package com.shajaraapp.shajara;

import android.content.Context;
import android.content.SharedPreferences;
//we use SharedPreferences to store a boolean value indicating first time launch.
public class PreferanceManager {
    private Context context;
    private SharedPreferences sharedPreferances;

    public PreferanceManager(Context context)
    {
        this.context = context;
        getSharedPreferance();
    }

    private void getSharedPreferance()
    {
        sharedPreferances = context.getSharedPreferences(context.getString(R.string.my_preferance), Context.MODE_PRIVATE);
    }

    public void writePreferance()
    {
        SharedPreferences.Editor editor = sharedPreferances.edit();
        editor.putString(context.getString(R.string.my_preferance_key), "abc");
        editor.commit();
    }

    public boolean checkPreferance()  /* first time app check*/
    {
        boolean status = false;
        if (sharedPreferances.getString(context.getString(R.string.my_preferance_key), "null").equals("null"))
        {
            status = false;
        }//returns false for first time
        else
            status = true;

        return status;

    }

}
