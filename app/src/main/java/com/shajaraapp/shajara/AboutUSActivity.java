package com.shajaraapp.shajara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AboutUSActivity extends AppCompatActivity {
    private ImageButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ImageButton Backbutton = (ImageButton)findViewById(R.id.Back_button);
    }
    public  void backButton(View v)
    {
             back();
    }
    public  void back()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }




}
