package com.expopay.android.adapter.gridview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.expopay.android.R;
import com.expopay.android.model.MallProductEntity;

import java.util.List;

/**
 * Created by NB-MIS-100002 on 2015/10/22.
 */
public class MallProductAdapter extends BaseAdapter{

    private LayoutInflater inflater = null;
    private List<MallProductEntity> data = null;

    public MallProductAdapter(Context context,List<MallProductEntity> data){
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MallProductEntity entity = data.get(position);
        ViewHolder holder = null;
        //如果缓存convertView为空，则需要创建View
        if (convertView == null) {
            holder = new ViewHolder();
            //根据自定义的Item布局加载布局
            convertView = inflater.inflate(R.layout.view_period_order_item, null);
            holder.productImg = (ImageView) convertView.findViewById(R.id.image_grid_item);
            holder.productName = (TextView) convertView.findViewById(R.id.text1_grid_item);
            holder.orderAmount = (TextView) convertView.findViewById(R.id.text2_grid_item);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
//        holder.productImg.setBackgroundResource(entity.getProductImg());
        holder.productName.setText(entity.getProductName());
        holder.orderAmount.setText(entity.getOrderAmount());

        return convertView;
    }
    //ViewHolder静态类
    static class ViewHolder {
        public ImageView productImg;
        public TextView productName;
        public TextView orderAmount;
    }
}
