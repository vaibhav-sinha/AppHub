package com.adobe.apphub.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.adobe.apphub.Models.AppData;
import com.adobe.apphub.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by vaisinha on 25/01/15.
 */
public class AppListAdapter extends ArrayAdapter<AppData> {

    private Context context;
    private int layoutResourceId;
    private List<AppData> appDataList;

    public AppListAdapter(Context context, int layoutResourceId, List<AppData> appDataList) {
        super(context, layoutResourceId, appDataList);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.appDataList = appDataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        AppDataHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new AppDataHolder();
            holder.alIcon = (ImageView)row.findViewById(R.id.alIcon);
            holder.alName = (TextView)row.findViewById(R.id.alName);
            holder.alStatus = (TextView)row.findViewById(R.id.alStatus);

            row.setTag(holder);
        }
        else
        {
            holder = (AppDataHolder)row.getTag();
        }

        AppData appData = appDataList.get(position);
        holder.alName.setText(appData.getName());
        Picasso.with(context).load(appData.getImage()).into(holder.alIcon);
        holder.alStatus.setText(appData.getInapp_purchase());

        return row;
    }

    static class AppDataHolder
    {
        ImageView alIcon;
        TextView alName;
        TextView alStatus;
    }
}

