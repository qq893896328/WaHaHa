package com.jiyun.wahaha;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/31 0031.
 */

public class GridBasAdapter extends BaseAdapter {
    private ArrayList<Car> mlist;
    private Context context;

    public GridBasAdapter(ArrayList<Car> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Holder holder = null;
        if (view == null){
            holder = new Holder();
            view = LayoutInflater.from(context).inflate(R.layout.gird_item,null);
            holder.carImg = view.findViewById(R.id.grid_item_carImg);
            holder.carName = view.findViewById(R.id.grid_item_carName);
            view.setTag(holder);
        }else {
             holder = (Holder) view.getTag();
            holder.carName.setText(mlist.get(position).getName());
            holder.carImg.setImageResource(mlist.get(position).getImg());
        }
        return view;
    }

    class Holder{
        private TextView carName;
        private ImageView carImg;
    }
}
