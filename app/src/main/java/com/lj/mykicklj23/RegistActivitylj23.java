package com.lj.mykicklj23;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
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

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by LJ on 2016/6/20.
 */
public class RegistActivitylj23  extends BaseActivitylj23 implements OnClickListener, TextWatcher {
    private static final String TAGlj23 = "RegistActivitylj23";
    private TextView tv_titlelj23 = null;
    private EditText et_usrlj23 = null;
    private EditText et_pw2lj23 = null;
    private EditText et_pw1lj23 = null;
    private ImageButton ib_clearlj23 = null;
    private ImageButton ib_backlj23 = null;
    private TextView tv_registlj23;
    private Button bt_registlj23;
    private ProgressBar progressbarlj23=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registlj23);

        Log.e(TAGlj23, "RegistActivity.class :: onCreate()");

        et_usrlj23 = (EditText) findViewById(R.id.et_regist_username);
        et_pw1lj23 = (EditText) findViewById(R.id.et_regist_password1);
        et_pw2lj23 = (EditText) findViewById(R.id.et_regist_password2);
        ib_clearlj23 = (ImageButton) findViewById(R.id.ib_regist_clear);
        ib_backlj23 = (ImageButton) findViewById(R.id.ib_regist_back);
        tv_titlelj23 = (TextView) findViewById(R.id.tv_regist_title);
        tv_registlj23 = (TextView) findViewById(R.id.tv_regist);
        bt_registlj23 = (Button) findViewById(R.id.bt_regist);
        progressbarlj23=(ProgressBar) findViewById(R.id.progressBar2);

        ib_clearlj23.setOnClickListener(this);
        ib_backlj23.setOnClickListener(this);
        tv_titlelj23.setOnClickListener(this);
        et_usrlj23.addTextChangedListener(this);
        tv_registlj23.setOnClickListener(this);
        bt_registlj23.setOnClickListener(this);
        tv_registlj23.setText(Html.fromHtml("<u>" + getString(R.string.regist_login) + "</u>"));
        progressbarlj23.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_regist_back:
                finish();
                startActivity(new Intent(this, Enterlj23.class));
                break;
            case R.id.ib_regist_clear:
                et_usrlj23.setText("");
                break;
            case R.id.tv_regist_title:
                finish();
                startActivity(new Intent(this, Enterlj23.class));
                break;
            case R.id.tv_regist:
                finish();
                startActivity(new Intent(this, LoginActivitylj23.class));
                break;
            case R.id.bt_regist:
                progressbarlj23.setVisibility(View.VISIBLE);
                vilidate();
                break;
            default:
                break;
        }
    }

    /**
     * 检查用户数据的合法性
     */
    private void vilidate() {
        username = et_usrlj23.getText().toString().trim();
        password = et_pw1lj23.getText().toString().trim();
        String password2 = et_pw2lj23.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(password2)) {
            Toast.makeText(this, "注册信息填写不完整", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (!password.equals(password2)) {
                Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                return;
            } else {
                // TODO 信息校验通过，提交到服务器进行注册
                String requestUrl = Applicationlj23.SERVERlj23 + Applicationlj23.ACTION_REGISTlj23; // 请求地址
                HashMap<String, String> requestDataMap = new HashMap<String, String>(); // 请求参数
                requestDataMap.put("username", username);
                requestDataMap.put("password", password);
                RequestVolj23 requestVo = new RequestVolj23(requestUrl, requestDataMap);

                // 发送请求处理数据
                super.getDataFromServer(requestVo, new DataCallBack<JSONObject>() {

                    @Override
                    public void doInBackground(JSONObject jo) {
                        try {
                            if ("error".equals(jo.getString("response"))) {
                                progressbarlj23.setVisibility(View.INVISIBLE);//关闭注册进度条
                                Toast.makeText(RegistActivitylj23.this, jo.getJSONObject("error").getString("message"), Toast.LENGTH_SHORT).show();
                            } else {
                                progressbarlj23.setVisibility(View.INVISIBLE);//关闭注册进度条
                                String uname = jo.getJSONObject("userinfo").getString("uname");
                                Toast.makeText(RegistActivitylj23.this, uname + "注册成功", Toast.LENGTH_LONG).show();
                                // 跳转到登录页面
                                finish();
                                startActivity(new Intent(RegistActivitylj23.this, LoginActivitylj23.class));
                                RegistActivitylj23.this.finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    private static Header[] headers = new BasicHeader[10];
    private String username;
    private String password;
    static {
        headers = new BasicHeader[10];
        headers[0] = new BasicHeader("Appkey", "12343");
        headers[1] = new BasicHeader("Udid", "");// 手机串号
        headers[2] = new BasicHeader("Os", "android");//
        headers[3] = new BasicHeader("Osversion", "");//
        headers[4] = new BasicHeader("Appversion", "");// 1.0
        headers[5] = new BasicHeader("Sourceid", "");//
        headers[6] = new BasicHeader("Ver", "");
        headers[7] = new BasicHeader("Userid", "");
        headers[8] = new BasicHeader("Usersession", "");
        headers[9] = new BasicHeader("Unique", "");
    }

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

}
