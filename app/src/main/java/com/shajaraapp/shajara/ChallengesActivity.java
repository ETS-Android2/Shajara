package com.shajaraapp.shajara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class ChallengesActivity extends AppCompatActivity {


    private ImageButton challengesBackButton;
    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);
        ImageButton challengesBackButton=(ImageButton)findViewById(R.id.Back_buttonchallenges);
    }
    public  void backButton(View v)
    {
        backButtonChallege();
    }
    public  void backButtonChallege()
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
