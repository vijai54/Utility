package com.bluecollar.task.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bluecollar.task.activity.MainActivity;
import com.bluecollar.task.activity.R;
import com.bluecollar.task.activity.ServiceProviderListActivity;
import com.bluecollar.task.model.ServiceProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vijay.a on 1/20/2016.
 */
public class ServiceAdapter extends BaseAdapter
{

    Context context;
    private static LayoutInflater inflater=null;

    ArrayList<ServiceProvider> provider = null;

    public ServiceAdapter ( Context context,ArrayList<ServiceProvider> serviceProviderList ) {
        // TODO Auto-generated constructor stub

        context = context;

        provider = serviceProviderList;

        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return provider.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView textView_name;
        TextView textView_rating;
        TextView textView_desc;
        ImageView img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.service_provider_list_row, null);

        holder.textView_name=(TextView) rowView.findViewById(R.id.service_proider_name);
        holder.textView_desc=(TextView) rowView.findViewById(R.id.service_provider_desc_summary);
        holder.textView_rating=(TextView) rowView.findViewById(R.id.service_proider_rating);
        holder.img=(ImageView) rowView.findViewById(R.id.service_proider_image);


        holder.textView_name.setText(provider.get(position).getName());
        holder.textView_desc.setText(provider.get(position).getDescriptionSummary());
        holder.textView_rating.setText(provider.get(position).getRating());
        holder.img.setImageResource(R.drawable.dummy);//provider.get(position).getPhotoid()


       /* rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Toast.makeText(context, "You Clicked " +provider.get(position), Toast.LENGTH_LONG).show();
            }
        });*/
        return rowView;
    }
}
