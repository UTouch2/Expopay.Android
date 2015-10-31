package com.expopay.android.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.android.kechong.lib.listener.AbsTextWatcher;
import com.expopay.android.R;
import com.expopay.android.request.CustomerRequest;
import com.expopay.android.view.CustormLoadingButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by misxu012 on 2015/10/23.
 */
public class ChangeMobileActivity extends BaseActivity {
    private String openId, mobile, code;

    private EditText phoneNumEditText, vercodeEditText;

    private CustormLoadingButton okBtn;
    private TextView timeoutText;
    private Button getVercodeBtn;

    private int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setContentView(R.layout.activity_changemobile);
        timeoutText = (TextView) findViewById(R.id.changemobile_timeout_btn);
        getVercodeBtn = (Button) findViewById(R.id.changemobile_vercode_btn);
        phoneNumEditText = (EditText) findViewById(R.id.changemobile_phonenum);
        vercodeEditText = (EditText) findViewById(R.id.changemobile_vercode);
        okBtn = (CustormLoadingButton) findViewById(R.id.changecardpassword_ok);

        getVercodeBtn.setEnabled(false);
        timeoutText.setVisibility(View.GONE);
        okBtn.setEnabled(false);
        getVercodeBtn.setVisibility(View.GONE);
        okBtn.setBackgroundResource(R.drawable._button_down);

        phoneNumEditText.addTextChangedListener(new AbsTextWatcher() {
            @Override
            public void onTextChanged(CharSequence str, int arg1, int arg2,
                                      int arg3) {
                // 如果输入等于11位获取按钮可用
                if (str.toString().trim().length() == 11) {
                    getVercodeBtn.setEnabled(true);
                    getVercodeBtn.setVisibility(View.VISIBLE);
                } else {
                    getVercodeBtn.setEnabled(false);
                    getVercodeBtn.setVisibility(View.GONE);
                }
            }
        });
        vercodeEditText.addTextChangedListener(new AbsTextWatcher() {
            @Override
            public void onTextChanged(CharSequence str, int arg1, int arg2,
                                      int arg3) {
                // 如果输入等于6位验证按钮可用
                if (str.toString().trim().length() == 6) {
                    okBtn.setEnabled(true);
                    okBtn.setBackgroundResource(R.drawable._button);
                } else {
                    okBtn.setEnabled(false);
                    okBtn.setBackgroundResource(R.drawable._button_down);
                }
            }
        });
        getVercodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mobile = phoneNumEditText.getText().toString().trim();
                try {
                    setgetVercodeBtn(mobile);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void changeMobileOnclick(View view) {
        openId = "";
        try {
            code = vercodeEditText.getText().toString().trim();
            changeMobileRequest(openId, mobile, code);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void changeMobileRequest(String openId, String mobile, String code) throws JSONException {
        CustomerRequest request = new CustomerRequest("");
        request.setEntity(request.createChangeMobileParams(openId, mobile, code));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {

            }

            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onProgressUpdate(int i, int i1) {

            }
        });
        request.execute();
        cancelRequest(request);
    }

    private void setgetVercodeBtn(String mobile) throws JSONException {
        CustomerRequest request = new CustomerRequest("");
        request.setEntity(request.createGetVerCodeParams(""));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {

            }

            @Override
            public void onSuccess(Object o) {
                JSONObject json = (JSONObject) o;
                try {
                    if (json.getJSONObject("header").getString("code")
                            .equals("0000")) {
                        // 获取成功则启动倒计时
                        startTimer();
                    } else {

                    }
                } catch (JSONException e) {

                }
            }

            @Override
            public void onProgressUpdate(int i, int i1) {

            }
        });
        request.execute();
        cancelRequest(request);
    }

    public void startTimer() {
        time = 60;
        final Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                if (time <= 0) {
                    Message msg = handler.obtainMessage();
                    msg.what = 1;
                    handler.sendMessage(msg);
                    timer.cancel();
                } else {
                    Message msg = handler.obtainMessage();
                    msg.what = 0;
                    msg.arg1 = time;
                    handler.sendMessage(msg);
                }
                time--;
            }
        };
        timer.schedule(tt, 0, 1000);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                int time = msg.arg1;
                getVercodeBtn.setEnabled(false);
                timeoutText.setText(time + "秒");
            } else {
                getVercodeBtn.setEnabled(true);
                getVercodeBtn.setText("重 新 获 取");
            }
        }
    };
}
