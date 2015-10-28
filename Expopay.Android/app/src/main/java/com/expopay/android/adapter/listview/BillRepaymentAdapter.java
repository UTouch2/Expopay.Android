package com.expopay.android.adapter.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.expopay.android.R;
import com.expopay.android.model.BillRepaymentEntity;

import java.util.List;

/**
 * Created by NB-MIS-100002 on 2015/10/26.
 */
public class BillRepaymentAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<BillRepaymentEntity> data ;
    private Context context;

    public BillRepaymentAdapter(Context context,List<BillRepaymentEntity> data) {
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
        final BillRepaymentEntity entity = data.get(position);
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            //根据自定义的Item布局加载布局
            view = mInflater.inflate(R.layout.view_repayment_item, null);
            holder.repaymentImg = (ImageView) view.findViewById(R.id.repaymentImg);
            holder.remainingDays = (TextView) view.findViewById(R.id.remainingDays);
            holder.repaymentProductName = (TextView) view.findViewById(R.id.repaymentProductName);
            holder.repaymentPro = (TextView) view.findViewById(R.id.repaymentPro);
            holder.repaymentBillAmount = (TextView) view.findViewById(R.id.repaymentBillAmount);
            holder.repaymentTime = (TextView) view.findViewById(R.id.repaymentTime);
            holder.repaymentOrderTime = (TextView) view.findViewById(R.id.repaymentOrderTime);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
//        holder.repaymentImg.setImageResource(entity.getProductImg());
        holder.remainingDays.setText(entity.getRemainingDays());
        holder.repaymentProductName.setText(entity.getProductName());
        holder.repaymentPro.setText(entity.getProductName());
        holder.repaymentBillAmount.setText(entity.getOverdueAmount());
        holder.repaymentTime.setText(entity.getRepaymentTime());
        holder.repaymentOrderTime.setText(entity.getOrderTime());

        return view;
    }

    static class ViewHolder {
        public ImageView repaymentImg;
        public TextView remainingDays;
        public TextView repaymentProductName;
        public TextView repaymentPro;
        public TextView repaymentBillAmount;
        public TextView repaymentTime;
        public TextView repaymentOrderTime;
    }
}
