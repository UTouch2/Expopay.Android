package com.expopay.android.adapter.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.expopay.android.R;
import com.expopay.android.model.PeriodOrderEntity;

import java.util.List;

/**
 * Created by NB-MIS-100002 on 2015/10/21.
 */
public class PeriodOrderAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<PeriodOrderEntity> data;
    private Context context;

    public PeriodOrderAdapter(Context context, List<PeriodOrderEntity> data) {
        this.data = data;
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        //在此适配器中所代表的数据集中的条目数
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        //获取数据集中与指定索引对应的数据项
        return i;
    }

    @Override
    public long getItemId(int position) {
        //获取在列表中与指定索引对应的行id
        return position;
    }

    //获取一个在数据集中指定索引的视图来显示数据
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final PeriodOrderEntity entity = data.get(position);
        ViewHolder holder = null;
        //如果缓存convertView为空，则需要创建View
        if (convertView == null) {
            holder = new ViewHolder();
            //根据自定义的Item布局加载布局
            convertView = mInflater.inflate(R.layout.view_period_order_item, null);
            holder.productImg = (ImageView) convertView.findViewById(R.id.imgInstallmentOrder);
            holder.productName = (TextView) convertView.findViewById(R.id.titleInstallmentOrder);
            holder.orderAmount = (TextView) convertView.findViewById(R.id.priceInstallmentOrder);
            holder.properties = (TextView) convertView.findViewById(R.id.colourInstallmentOrder);
            holder.orderTime = (TextView) convertView.findViewById(R.id.dateInstallmentOrder);
            holder.repaymentPeriod = (TextView) convertView.findViewById(R.id.installmentOrder);
            holder.orderStatus = (TextView) convertView.findViewById(R.id.stateInstallmentOrder);
            //将设置好的布局保存到缓存中，并将其设置在Tag里，以便后面方便取出Tag
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //holder.productImg.setBackgroundResource((Integer) data);
        holder.productName.setText(entity.getProductName());
        holder.orderAmount.setText(entity.getOrderAmount());
        holder.properties.setText(entity.getProperties());
        holder.repaymentPeriod.setText(entity.getRepaymentPeriod());
        holder.orderTime.setText(entity.getOrderTime());
        holder.orderStatus.setText(entity.getOrderStatus());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, OrderDetailItemActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);
                Toast.makeText(context, "分期订单", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
    //ViewHolder静态类
    static class ViewHolder {
        public ImageView productImg;
        public TextView productName;
        public TextView orderAmount;
        public TextView properties;
        public TextView repaymentPeriod;
        public TextView orderTime;
        public TextView orderStatus;
    }

}