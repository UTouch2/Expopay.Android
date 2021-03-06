package com.expopay.android.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.expopay.android.R;
import com.expopay.android.model.OrderDetailsEntity;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends BaseActivity {

    private ImageView productImg;
    private TextView productName;
    private TextView productPrice;
    private TextView propertyName;
    private TextView propertyValue;
    private TextView productQutity;
    private TextView orderStatus;
    private TextView repaymentPeriod;
    private TextView serviceAmount;
    private TextView periodAmount;
    private TextView receiver;
    private TextView receiverMobile;
    private TextView receiverAddress;
    private ListView expressRecords;
    private TextView orderNumber;
    private TextView transcationCode;
    private TextView orderTime;
    private TextView expressCompany;
    private TextView expressNumber;

    private void initViews() {
        productImg = (ImageView) findViewById(R.id.orderDetail_productImg);
        productName = (TextView) findViewById(R.id.orderDetail_productName);
        productPrice = (TextView) findViewById(R.id.orderDetail_productPrice);
        propertyName = (TextView) findViewById(R.id.orderDetail_propertyName);
        propertyValue = (TextView) findViewById(R.id.orderDetail_propertyValue);
        productQutity = (TextView) findViewById(R.id.orderDetail_productQutity);
        orderStatus = (TextView) findViewById(R.id.orderDetail_orderStatus);
        repaymentPeriod = (TextView) findViewById(R.id.orderDetail_repaymentPeriod);
        serviceAmount = (TextView) findViewById(R.id.orderDetail_serviceAmount);
        periodAmount = (TextView) findViewById(R.id.orderDetail_periodAmount);
        receiver = (TextView) findViewById(R.id.orderDetail_receiver);
        receiverMobile = (TextView) findViewById(R.id.orderDetail_receiverMobile);
        receiverAddress = (TextView) findViewById(R.id.orderDetail_receiverAddress);
        expressRecords = (ListView) findViewById(R.id.orderDetail_expressRecords);
        orderNumber = (TextView) findViewById(R.id.orderDetail_orderNumber);
        transcationCode = (TextView) findViewById(R.id.orderDetail_transcationCode);
        orderTime = (TextView) findViewById(R.id.orderDetail_orderTime);
        expressCompany = (TextView) findViewById(R.id.orderDetail_expressCompany);
        expressNumber = (TextView) findViewById(R.id.orderDetail_expressNumber);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setTitle("订单详情");
        setContentView(R.layout.activity_order_detail);
        initViews();
    }

    private List<OrderDetailsEntity> testData(){
        List<OrderDetailsEntity> list = new ArrayList<OrderDetailsEntity>();
        for (int i = 0; i <10 ; i++){
            OrderDetailsEntity le = new OrderDetailsEntity();
//            le.setLogistics("【昆明市】正在配送中，李师傅13900003325  2015年10月05日   09:10:00");
            list.add(le);
        }
        return list;
    }

    //ListView嵌套到ScrollView中显示
    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

    public void cancelOrderOnclick(View view){

    }

    public void contactOnclick(View view){

    }
}
