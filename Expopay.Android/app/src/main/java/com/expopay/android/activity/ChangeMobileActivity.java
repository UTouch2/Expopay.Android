package com.expopay.android.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.kechong.lib.http.listener.JsonRequestListener;
import com.android.kechong.lib.listener.AbsTextWatcher;
import com.android.kechong.lib.util.PatternUtil;
import com.expopay.android.R;
import com.expopay.android.application.MyApplication;
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
    private TextView bindphone;
    private Button getVercodeBtn;

    private int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor();
        setTitle("更改手机");
        setContentView(R.layout.activity_changemobile);
        timeoutText = (TextView) findViewById(R.id.changemobile_timeout_btn);
        bindphone = (TextView) findViewById(R.id.changemobile_bindphone);
        bindphone.setText(getUser().getMobile());
        getVercodeBtn = (Button) findViewById(R.id.changemobile_vercode_btn);
        phoneNumEditText = (EditText) findViewById(R.id.changemobile_phonenum);
        vercodeEditText = (EditText) findViewById(R.id.changemobile_vercode);
        okBtn = (CustormLoadingButton) findViewById(R.id.changecardpassword_ok);

        getVercodeBtn.setEnabled(false);
        okBtn.setEnabled(false);
        okBtn.setBackgroundResource(R.drawable._button_down);
        okBtn.showNormal("确定更改");
        okBtn.setOnLoadingButtonListener(new CustormLoadingButton.OnLoadingButtonListener() {
            @Override
            public void onSuccessResult() {
                finish();
            }

            @Override
            public void onFailureResult() {
                okBtn.showNormal("确定更改");
            }
        });

        phoneNumEditText.addTextChangedListener(new AbsTextWatcher() {
            @Override
            public void onTextChanged(CharSequence str, int arg1, int arg2,
                                      int arg3) {
                // 如果输入等于11位获取按钮可用
                if (str.toString().trim().length() == 11) {
                    getVercodeBtn.setEnabled(true);
                    okBtn.setBackgroundResource(R.drawable._button);
                } else {
                    getVercodeBtn.setEnabled(false);
                    okBtn.setBackgroundResource(R.drawable._button_down);
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
                if (!PatternUtil.isMobile(mobile)) {
                    Toast.makeText(ChangeMobileActivity.this, "请输入正确的电话号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    sendVercode(mobile);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void changeMobileOnclick(View view) {
        mobile = phoneNumEditText.getText().toString().trim();
        code = vercodeEditText.getText().toString().trim();
        try {
            changeMobileRequest(getUser().getOpenId(), mobile, code);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void startTimer() {
        time = 60;
        final Timer timer = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                if (time < 0) {
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
                getVercodeBtn.setText("已 发 送");
                timeoutText.setText(time + "秒");
            } else {
                getVercodeBtn.setEnabled(true);
                getVercodeBtn.setText("重 新 获 取");
            }
        }
    };

    private void changeMobileRequest(String openId, String mobile, String code) throws JSONException {
        okBtn.showLoading("正在更改···");
        CustomerRequest request = new CustomerRequest(MyApplication.HOST + "/customer/resetmobile");
        request.setEntity(request.createChangeMobileParams(openId, mobile, code));
        request.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {
                okBtn.showResult("网络请求失败", false);
            }
            @Override
            public void onSuccess(Object o) {
                JSONObject json = (JSONObject) o;
                try {
                    if (json.getJSONObject("header").getString("code")
                            .equals("0000")) {
                        okBtn.showResult("更改成功",true);
                    }else{
                        okBtn.showResult(json.getJSONObject("header").getString("code"),false);
                    }
                } catch (JSONException e) {
                    okBtn.showResult("参数解析错误",false);
                }
            }

            @Override
            public void onProgressUpdate(int i, int i1) {

            }
        });
        request.execute();
        cancelRequest(request);
    }

    private void sendVercode(String mobile) throws JSONException {
        CustomerRequest re = new CustomerRequest(MyApplication.HOST + "/system/sendcode");
        re.setEntity(re.createGetVerCodeParams(mobile));
        re.setOutTime(10000);
        re.setIRequestListener(new JsonRequestListener() {
            @Override
            public void onFilure(Exception e) {
                System.out.print(e);
            }

            @Override
            public void onSuccess(Object o) {
                JSONObject js = (JSONObject) o;
                try {
                    if (js.getJSONObject("header").getString("code").equals("0000")) {
                        startTimer();
                    } else {
                        Toast.makeText(getApplicationContext(), js.getJSONObject("header").getString("desc"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onProgressUpdate(int i, int i1) {

            }
        });
        re.execute();
        cancelRequest(re);
    }
}
