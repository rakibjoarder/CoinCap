package com.aust.rakib.coincap;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Personal on 7/22/2017.
 */

public class ListViewAdapter extends BaseAdapter {

    ArrayList<CoinResponse>coinResponses;

    Context context;



    public ListViewAdapter(Context context, ArrayList<CoinResponse> coinResponses) {
        this.coinResponses = coinResponses;
        this.context = context;
    }


    @Override
    public int getCount() {
        return 20;
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
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        convertView=inflater.inflate(R.layout.custom_list_row,parent,false);

        TextView nameTv= (TextView) convertView.findViewById(R.id.nametextview);
        TextView symbolTv= (TextView) convertView.findViewById(R.id.symboltextview);
        TextView usdTv= (TextView) convertView.findViewById(R.id.usdtextview);
        TextView lastupdateTv= (TextView) convertView.findViewById(R.id.lastUpdatedtextview);
        String date=" ";

          long l= Long.valueOf(coinResponses.get(position).getLastUpdated())*1000;
          Date d = new Date(Long.valueOf(l));
          SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
         date = dateFormat1.format(d);  // formatted date in string


        nameTv.setText(coinResponses.get(position).getName());
        symbolTv.setText(coinResponses.get(position).getSymbol());
        usdTv.setText(String.format("$%s",coinResponses.get(position).getPriceUsd()));
        lastupdateTv.setText(date);


        return convertView;
    }
}
