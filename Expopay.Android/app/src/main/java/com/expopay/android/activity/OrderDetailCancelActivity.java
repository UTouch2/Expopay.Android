package com.expopay.android.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.expopay.android.R;

public class OrderDetailCancelActivity extends BaseActivity {

    private ImageView cancelProductImg;
    private TextView cancelProductName;
    private TextView cancelOrderAmmount;
    private TextView cancelProperties;
    private TextView cancelOrderStatus;
    private TextView cancelRepaymentPeriod;
    private TextView cancelServiceAmount;
    private TextView cancelPeriodAmount;
    private TextView cancelConsignee;
    private TextView cancelConsigneeMobile;
    private TextView cancelConsigneeAddress;
    private TextView cancelOrderNumber;
    private TextView cancelTranscationCode;
    private TextView cancelOrderTime;

    private void assignViews() {
        cancelProductImg = (ImageView) findViewById(R.id.cancelProductImg);
        cancelProductName = (TextView) findViewById(R.id.cancelProductName);
        cancelOrderAmmount = (TextView) findViewById(R.id.cancelOrderAmmount);
        cancelProperties = (TextView) findViewById(R.id.cancelProperties);
        cancelOrderStatus = (TextView) findViewById(R.id.cancelOrderStatus);
        cancelRepaymentPeriod = (TextView) findViewById(R.id.cancelRepaymentPeriod);
        cancelServiceAmount = (TextView) findViewById(R.id.cancelServiceAmount);
        cancelPeriodAmount = (TextView) findViewById(R.id.cancelPeriodAmount);
        cancelConsignee = (TextView) findViewById(R.id.cancelConsignee);
        cancelConsigneeMobile = (TextView) findViewById(R.id.cancelConsigneeMobile);
        cancelConsigneeAddress = (TextView) findViewById(R.id.cancelConsigneeAddress);
        cancelOrderNumber = (TextView) findViewById(R.id.cancelOrderNumber);
        cancelTranscationCode = (TextView) findViewById(R.id.cancelTranscationCode);
        cancelOrderTime = (TextView) findViewById(R.id.cancelOrderTime);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail_cancel);

        assignViews();
        setTextView();
    }

    private void setTextView(){
        cancelProductImg.setImageResource(R.mipmap.mall_mobile);
        cancelProductName.setText("iPhone6S");
        cancelOrderAmmount.setText("5200.00");
        cancelProperties.setText("��ɫ 32G");
        cancelOrderStatus.setText("�����");
        cancelRepaymentPeriod.setText("12��");
        cancelServiceAmount.setText("��440+30��x"+12+"="+(440+30)*12);
        cancelPeriodAmount.setText("ÿ�ڻ���"+470+"Ԫ");
        cancelConsignee.setText("����˹");
        cancelConsigneeMobile.setText("13600000000");
        cancelConsigneeAddress.setText("����ʡ�������廪����Դ��·1088�źͳɹ���A��25¥");
        cancelOrderNumber.setText("1234567890");
        cancelTranscationCode.setText("������ˮ��:678839903987484");
        cancelOrderTime.setText("����ʱ�䣺2015-09-26  00:00:00");

    }
}
