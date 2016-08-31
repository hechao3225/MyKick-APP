package com.lj.mykicklj23;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


/**
 * Created by HC on 2016/6/23.
 */
public class SetActivitylj23 extends BaseActivitylj23 implements OnClickListener,TextWatcher {
    private static final String TAGlj23 = "SetActivity";
    private EditText et_pwd1lj23 = null;
    private EditText et_pwd2lj23 = null;
    private EditText et_usrlj23 = null;
    private EditText et_citylj23 = null;
    private EditText et_phonelj23 = null;
    private EditText et_qqlj23 = null;
    private ImageButton ib_clearlj23 = null;
    private ImageButton ib_backlj23 = null;
    private TextView tv_titlelj23 = null;
    private Button bt_setlj23 = null;
    private ProgressBar progressbarlj23=null;
    private  static MediaPlayer mplj23=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setlj23);

        Log.i(TAGlj23, "SetActivity.class :: onCreate()");
        //音乐
        if(mplj23 != null){
            mplj23.release();
        }
        mplj23 = MediaPlayer.create(SetActivitylj23.this, R.raw.welcome);
        mplj23.start();

        et_pwd1lj23 = (EditText) findViewById(R.id.et_set_password1);
        et_usrlj23 = (EditText) findViewById(R.id.et_set_username);
        et_pwd2lj23 = (EditText) findViewById(R.id.et_set_password2);
        et_citylj23 = (EditText) findViewById(R.id.et_set_usercity);
        et_phonelj23 = (EditText) findViewById(R.id.et_set_userphone);
        et_qqlj23 = (EditText) findViewById(R.id.et_set_userqq);

        ib_clearlj23 = (ImageButton) findViewById(R.id.ib_set_clear);
        ib_backlj23 = (ImageButton) findViewById(R.id.ib_set_back);
        tv_titlelj23 = (TextView) findViewById(R.id.tv_set_title);
        bt_setlj23 = (Button) findViewById(R.id.bt_set);
        progressbarlj23=(ProgressBar) findViewById(R.id.progressBar3);

        ib_backlj23.setOnClickListener(this);
        ib_clearlj23.setOnClickListener(this);
        tv_titlelj23.setOnClickListener(this);
        et_usrlj23.addTextChangedListener(this);
        bt_setlj23.setOnClickListener(this);

        progressbarlj23.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_set_clear:
                et_usrlj23.setText("");
                break;
            case R.id.ib_set_back:
                finish();
                startActivity(new Intent(this, MySetlj23.class));
                break;
            case R.id.tv_login_title:
                finish();
                startActivity(new Intent(this, MySetlj23.class));
                break;
            case R.id.bt_set:
                progressbarlj23.setVisibility(View.VISIBLE);//打开登录进度条
                actionSet();
                break;
            default:
                break;
        }
    }

    /**
     * 用户登录
     */
    private void actionSet() {
        username = et_usrlj23.getText().toString().trim();
        password = et_pwd1lj23.getText().toString().trim();
        usercity = et_citylj23.getText().toString().trim();
        userphone = et_phonelj23.getText().toString().trim();
        userqq = et_qqlj23.getText().toString().trim();
        String password2 = et_pwd2lj23.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(password2)) {
            Toast.makeText(this, "必要信息填写不完整", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (!password.equals(password2)) {
                Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                return;
            } else {
                // TODO 信息校验通过，提交到服务器进行修改
                String requestUrl = Applicationlj23.SERVERlj23 + Applicationlj23.ACTION_SETlj23; // 请求地址
                HashMap<String, String> requestDataMap = new HashMap<String, String>(); // 请求参数
                requestDataMap.put("username", username);
                requestDataMap.put("password", password);
                requestDataMap.put("usercity", usercity);
                requestDataMap.put("userphone", userphone);
                requestDataMap.put("userqq", userqq);
                RequestVolj23 requestVo = new RequestVolj23(requestUrl, requestDataMap);

                // 发送请求处理数据
                super.getDataFromServer(requestVo, new DataCallBack<JSONObject>() {

                    @Override
                    public void doInBackground(JSONObject jo) {
                        try {
                            if ("error".equals(jo.getString("response"))) {
                                progressbarlj23.setVisibility(View.INVISIBLE);//关闭修改进度条
                                Toast.makeText(SetActivitylj23.this, jo.getJSONObject("error").getString("message"), Toast.LENGTH_SHORT).show();
                            } else {
                                progressbarlj23.setVisibility(View.INVISIBLE);//关闭修改进度条
                                String uname = jo.getJSONObject("userinfo").getString("uname");
                                Toast.makeText(SetActivitylj23.this, uname + "修改成功", Toast.LENGTH_LONG).show();
                                finish();
                                startActivity(new Intent(SetActivitylj23.this,MySetlj23.class));
                                SetActivitylj23.this.finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }
    private String username;
    private String password;
    private String usercity;
    private String userphone;
    private String userqq;

    @Override
    public void afterTextChanged(Editable s) {
        if (et_usrlj23.getText().toString().trim().length() > 0) {
            ib_clearlj23.setVisibility(View.VISIBLE);
        } else {
            ib_clearlj23.setVisibility(View.GONE);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    protected void onDestroy() {
        if(mplj23 != null){
            mplj23.stop();
            mplj23.release();
            mplj23=null;
        }
        super.onDestroy();
    }
}

