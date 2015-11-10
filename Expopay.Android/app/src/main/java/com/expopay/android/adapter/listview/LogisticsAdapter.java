package com.expopay.android.adapter.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.expopay.android.R;
import com.expopay.android.model.OrderDetailsEntity;

import java.util.List;

/**
 * Created by NB-MIS-100002 on 2015/10/28.
 */
public class LogisticsAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<OrderDetailsEntity> data;
    private Context context;

    public LogisticsAdapter(Context context, List<OrderDetailsEntity> data){
        this.data = data;
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final OrderDetailsEntity entity = data.get(i);
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            view = mInflater.inflate(R.layout.view_logistics_item,null);
            holder.logistics_img = (ImageView) view.findViewById(R.id.logistics_img);
            holder.logistics_line = (ImageView) view.findViewById(R.id.logistics_line);
            holder.logisticsStatus = (TextView) view.findViewById(R.id.logisticsStatus);
            holder.logisticsTime = (TextView) view.findViewById(R.id.logisticsTime);
            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }

        if(0 == i){
            holder.logistics_img.setImageResource(R.mipmap.logistics_unfinished);
        }
        if(data.size()-1 == i){
            holder.logistics_line.setVisibility(View.INVISIBLE);
        }
        holder.logisticsStatus.setText(entity.getLogistics());
        holder.logisticsTime.setText(entity.getOrderTime());

        return view;
    }

    static class ViewHolder{
        public ImageView logistics_img;
        public ImageView logistics_line;
        public TextView logisticsStatus;
        public TextView logisticsTime;
    }
}
