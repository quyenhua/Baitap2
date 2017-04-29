package com.tqt.btsoxo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Quyen Hua on 4/28/2017.
 */

public class Adapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<DataKetQua> listData = new ArrayList<>();

    public Adapter(Context context, int resource, ArrayList<DataKetQua> listData) {
        super(context, resource, listData);
        this.context = context;
        this.resource = resource;
        this.listData = listData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        ViewHolder viewHolder;
        if(rowView == null){
            //LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //rowView = inflater.inflate(R.layout.item_list_song, null);
            rowView = LayoutInflater.from(context).inflate(resource, null);
            viewHolder = new ViewHolder();
            viewHolder.tvLevel = (TextView) rowView.findViewById(R.id.tvTengiai);
            viewHolder.tvValue = (TextView)rowView.findViewById(R.id.tvDaySo);
            rowView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        String level = "Giải " + listData.get(position).getLevel();
        viewHolder.tvLevel.setText(level);
        viewHolder.tvValue.setText(listData.get(position).getGiatri());
        return rowView;
    }

    static class ViewHolder{
        TextView tvLevel, tvValue;
    }
}
