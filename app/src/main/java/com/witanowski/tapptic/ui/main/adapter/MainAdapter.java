package com.witanowski.tapptic.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.witanowski.tapptic.R;
import com.witanowski.tapptic.data.model.Number;
import com.witanowski.tapptic.ui.main.MainActivity;

import java.util.ArrayList;

/**
 * Created by Michal Witanowski on 2017-11-20.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private ArrayList<Number> numbers;
    private Context context;

    public MainAdapter(ArrayList<Number> numbers, Context context){
        this.numbers = numbers;
        this.context = context;
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_main, parent, false);
        MainViewHolder viewHolder = new MainViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, final int position) {
        final Number number = numbers.get(position);

        holder.lytMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).getFragment(number);
            }
        });

        holder.tvNumber.setText(number.getName());
        Picasso.with(context).load(number.getImage()).into(holder.ivNumber);
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }

    class MainViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivNumber;
        private TextView tvNumber;
        private LinearLayout lytMain;

        public MainViewHolder(View view) {
            super(view);

            ivNumber = (ImageView) view.findViewById(R.id.ivNumber);
            tvNumber = (TextView) view.findViewById(R.id.tvNumber);
            lytMain = (LinearLayout) view.findViewById(R.id.lytMain);
        }
    }
}