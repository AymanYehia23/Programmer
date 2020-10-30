package com.programmer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Item> items;

    public GridViewAdapter(Context context, ArrayList<Item> items){
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.grid_view_item, null, false);
        ImageView websiteImage = convertView.findViewById(R.id.image_view_icon);
        TextView websiteName = convertView.findViewById(R.id.text_view_title);
        Item item = items.get(position);
        websiteImage.setImageResource(item.getImage());
        websiteName.setText(item.getName());
        return convertView;
    }
}