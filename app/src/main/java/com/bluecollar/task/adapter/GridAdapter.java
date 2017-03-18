package com.bluecollar.task.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluecollar.task.activity.R;

/**
 * Created by vijay.a on 1/7/2016.
 */

public class GridAdapter extends BaseAdapter
{
    private Context mContext;
    private final String[] web;
    private final int[] Imageid;

    public GridAdapter(Context c,String[] web,int[] Imageid ) {
        mContext = c;
        this.Imageid = Imageid;
        this.web = web;
    }

    @Override
    public int getCount() {

        return web.length;
    }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

           // grid = new View(mContext);
            grid = inflater.inflate(R.layout.cardview_layout, null);
            TextView textView = (TextView) grid.findViewById(R.id.title);
            ImageView imageView = (ImageView)grid.findViewById(R.id.thumbnail);
            textView.setText(web[position]);
            imageView.setImageResource(Imageid[position]);
        } else {
            grid = (View) convertView;
        }

        return grid;
    }
}
