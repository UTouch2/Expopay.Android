package com.expopay.android.adapter.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.expopay.android.R;
import com.expopay.android.model.BillUnrepaymentEntity;

import java.util.List;

/**
 * Created by NB-MIS-100002 on 2015/10/26.
 */
public class BillUnrepaymentAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<BillUnrepaymentEntity> data;
    private Context context;

    public BillUnrepaymentAdapter(Context context, List<BillUnrepaymentEntity> data) {
        this.data = data;
        //根据context上下文加载布局
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        final BillUnrepaymentEntity entity = data.get(position);
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            //根据自定义的Item布局加载布局
            view = mInflater.inflate(R.layout.view_unrepayment_item, null);
            holder.unrepaymentImg = (ImageView) view.findViewById(R.id.unrepaymentImg);
            holder.unrepaymentProductName = (TextView) view.findViewById(R.id.unrepaymentProductName);
            holder.unrepaymentPro = (TextView) view.findViewById(R.id.unrepaymentPro);
            holder.unrepaymentBillAmount = (TextView) view.findViewById(R.id.unrepaymentBillAmount);
            holder.unrepaymentTime = (TextView) view.findViewById(R.id.unrepaymentTime);
            holder.unrepaymentOrderTime = (TextView) view.findViewById(R.id.unrepaymentOrderTime);
            holder.unrepaymentPeriod = (TextView) view.findViewById(R.id.unrepaymentPeriod);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
//        holder.unrepaymentImg.setImageResource(entity.getProductImg());
        holder.unrepaymentProductName.setText(entity.getProductName());
        holder.unrepaymentPro.setText(entity.getProductName());
        holder.unrepaymentBillAmount.setText(entity.getOverdueAmount());
        holder.unrepaymentTime.setText(entity.getRepaymentTime());
        holder.unrepaymentOrderTime.setText(entity.getOrderTime());
        holder.unrepaymentPeriod.setText(entity.getOverdueDays());

        return view;
    }

    static class ViewHolder {
        public ImageView unrepaymentImg;
        public TextView unrepaymentProductName;
        public TextView unrepaymentPro;
        public TextView unrepaymentBillAmount;
        public TextView unrepaymentTime;
        public TextView unrepaymentOrderTime;
        public TextView unrepaymentPeriod;
    }
}
