package com.shajaraapp.shajara;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager viewPager;
    private TextView[]dotstv;
    private int[]layouts={R.layout.slider_1 , R.layout.slider_2,R.layout.slider_3,R.layout.slider_4};
    private PagerAdapterActivity pagerAdapter;//Created a PagerAdapter for the ViewPager
    private LinearLayout layoutDot;
    private ImageView[] dots;
    private Button btnSkip;
    private Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        if(new PreferanceManager(this).checkPreferance())
        {
            loadHome();
        }
        if(Build.VERSION.SDK_INT>=22)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }// Made the notification bar transparent, so that the view background color can be seen through.
        else
        {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }
        setContentView(R.layout.activity_welcome);
        viewPager=(ViewPager) findViewById(R.id.view_pager);
        pagerAdapter =  new  PagerAdapterActivity(layouts,this );
        viewPager.setAdapter(pagerAdapter);
        layoutDot=(LinearLayout)findViewById(R.id.dotLayout);
        btnNext=(Button) findViewById(R.id.btn_next);
        btnSkip=(Button)findViewById(R.id.btn_skip);
        btnNext.setOnClickListener(this);
        btnSkip.setOnClickListener(this);

        createDots(0);


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {//helps to swipe as viepager has inbuilt sipw options
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position)
            {
                /*on reaching last slide check the condtion so the start button CAN COME*/
                if(position== layouts.length-1)
                {
                    btnNext.setText("START");
                    btnSkip.setVisibility(View.INVISIBLE);
                }
                else
                {
                    btnNext.setText("NEXT");
                    btnSkip.setVisibility(View.VISIBLE);
                }
                createDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void createDots(int current_position)
    {
        if(layoutDot!=null)
        {
            layoutDot.removeAllViews();//dynamically inflating linear layout
        }
        dots=new ImageView[layouts.length];
        for(int i=0;i<layouts.length;i++)
        {
            dots[i]=new ImageView(this);
            if(i==current_position)
            {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.active_dots));
            }
            else
            {
                dots[i].setImageDrawable(ContextCompat.getDrawable(this,R.drawable.default_dots));
            }
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(4,0,4,0);//how the dots want to be laid out
            layoutDot.addView(dots[i],params);
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn_next:
            {
                load_next_slide();
                break;
            }
            case R.id.btn_skip:
            {
                loadHome();
                new PreferanceManager(this).writePreferance();
                break;
            }


        }

    }
    private  void loadHome()
    {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
    private  void load_next_slide()
    {
        int next_slide=viewPager.getCurrentItem()+1;
        if(next_slide<layouts.length)/*still slides are left */
        {
            viewPager.setCurrentItem(next_slide);

        }
        else
        {
            loadHome();
            new PreferanceManager(this).writePreferance();
        }
    }
}



