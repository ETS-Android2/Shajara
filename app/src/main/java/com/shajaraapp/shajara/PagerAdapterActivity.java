package com.shajaraapp.shajara;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PagerAdapterActivity extends PagerAdapter {

    private int[]layouts;
    private LayoutInflater inflator;
    private Context context;


    public PagerAdapterActivity(int[] layouts,Context context)
    {
        this.layouts=layouts;
        this.context=context;
        inflator=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {


        return layouts.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return view ==object;
    }
    public Object instantiateItem(ViewGroup container, int position) {

        View  v = inflator.inflate(layouts[position],container,false);
        container.addView(v);
        return v;
    }
    public void destroyItem(ViewGroup container,int position ,Object object)
    {
        View v = (View)object;
        container.removeView( v);
    }
}

