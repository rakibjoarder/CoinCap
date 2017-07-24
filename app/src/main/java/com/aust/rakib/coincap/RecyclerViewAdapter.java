package com.aust.rakib.coincap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Personal on 7/23/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.viewHolder> {
    ArrayList<CoinResponse> coinResponses;
    Context context;
    public RecyclerViewAdapter(Context context,ArrayList<CoinResponse> coinResponses) {

        this.coinResponses = coinResponses;
        this.context = context;
    }
    public class viewHolder extends RecyclerView.ViewHolder{

        TextView nameTv;
        TextView symbolTv;
        TextView usdTv;
        TextView lastupdateTv;

        public viewHolder(final View itemView) {
            super(itemView);

            nameTv = (TextView) itemView.findViewById(R.id.nametextview);
            symbolTv= (TextView)  itemView.findViewById(R.id.symboltextview);
            usdTv= (TextView)  itemView.findViewById(R.id.usdtextview);
            lastupdateTv= (TextView)  itemView.findViewById(R.id.lastUpdatedtextview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position=getAdapterPosition();

                    LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
                    View view=inflater.inflate(R.layout.custon_alert_dialouge,null);

                    AlertDialog.Builder builder =new AlertDialog.Builder(context);
                    builder.setView(view);

                    TextView namTextView= (TextView) view.findViewById(R.id.nameTv);
                    TextView symbolTextView= (TextView) view.findViewById(R.id.symTV);
                    TextView usdTextView= (TextView) view.findViewById(R.id.usdTV);
                    Button buttonbuy= (Button) view.findViewById(R.id.butBT);
                    Button buttonsell= (Button) view.findViewById(R.id.sellBT);

                     namTextView.setText(coinResponses.get(position).getName());
                      symbolTextView.setText(coinResponses.get(position).getSymbol());
                      usdTextView.setText(String.format("$%s",coinResponses.get(position).getPriceUsd()));

                    final AlertDialog dialog=builder.create();
                    dialog.show();


                    buttonbuy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            context.startActivity(new Intent(context,LoginActivity.class));
                            dialog.dismiss();
                        }
                    });

                    buttonsell.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            context.startActivity(new Intent(context,LoginActivity.class));
                            dialog.dismiss();
                        }
                    });


                }
            });
        }
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.custom_list_row,parent,false);
        return new viewHolder(v);

    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {

        long l= Long.valueOf(coinResponses.get(position).getLastUpdated())*1000;
        Date d = new Date(Long.valueOf(l));
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
        String date = dateFormat1.format(d);  // formatted date in string

        holder.nameTv.setText(coinResponses.get(position).getName());
        holder.symbolTv.setText(coinResponses.get(position).getSymbol());
        holder. usdTv.setText(String.format("$%s",coinResponses.get(position).getPriceUsd()));
        holder.lastupdateTv.setText(date);
    }

    @Override
    public int getItemCount() {
        return coinResponses.size();
    }



}











