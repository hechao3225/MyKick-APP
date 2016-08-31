package com.lj.mykicklj23;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LJ on 2016/6/20.
 */
public class LoginActivitylj23 extends BaseActivitylj23 implements OnClickListener,TextWatcher {

    private static final String TAGlj23 = "LoginActivity";
    private EditText et_pwdlj23 = null;
    private EditText et_usrlj23 = null;
    private ImageButton ib_clearlj23 = null;
    private ImageButton ib_backlj23 = null;
    private TextView tv_registlj23 = null;
    private TextView tv_titlelj23 = null;
    private SharedPreferences splj23 = null;
    private Button bt_loginlj23 = null;
    private String usernamelj23 = null;
    private String passwordlj23 = null;
    private ProgressBar progressbarlj23=null;
    private Chronometer clklj23 ;
    private TextView textlj23;
    public static String string_username=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginlj23);

        Log.i(TAGlj23, "LoginActivity.class :: onCreate()");

        splj23 = getSharedPreferences("userinfo", Context.MODE_PRIVATE);

        et_pwdlj23 = (EditText) findViewById(R.id.et_login_password);
        et_usrlj23 = (EditText) findViewById(R.id.et_login_username);
        ib_clearlj23 = (ImageButton) findViewById(R.id.ib_login_clear);
        ib_backlj23 = (ImageButton) findViewById(R.id.ib_login_back);
        tv_registlj23 = (TextView) findViewById(R.id.tv_login);
        tv_titlelj23 = (TextView) findViewById(R.id.tv_login_title);
        bt_loginlj23 = (Button) findViewById(R.id.bt_login);
        progressbarlj23=(ProgressBar) findViewById(R.id.progressBar1);
        clklj23 = (Chronometer) findViewById(R.id.chronometer2);
        textlj23 = (TextView) findViewById(R.id.login_textView);

        ib_backlj23.setOnClickListener(this);
        ib_clearlj23.setOnClickListener(this);
        tv_titlelj23.setOnClickListener(this);
        et_usrlj23.addTextChangedListener(this);
        tv_registlj23.setOnClickListener(this);
        bt_loginlj23.setOnClickListener(this);
        tv_registlj23.setText(Html.fromHtml("<u>" + getString(R.string.login_regist) + "</u>"));

        progressbarlj23.setVisibility(View.INVISIBLE);
        textlj23.setVisibility(View.INVISIBLE);
        clklj23.setVisibility(View.INVISIBLE);
        splj23.getString("username", "");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_login_clear:
                et_usrlj23.setText("");
                break;
            case R.id.ib_login_back:
                finish();
                startActivity(new Intent(this, Enterlj23.class));
                break;
            case R.id.tv_login:
                finish();
                startActivity(new Intent(this, RegistActivitylj23.class));
                break;
            case R.id.tv_login_title:
                finish();
                startActivity(new Intent(this, Enterlj23.class));
                break;
            case R.id.bt_login:
                progressbarlj23.setVisibility(View.VISIBLE);//打开登录进度条
                clklj23.setVisibility(View.VISIBLE);//打开登录计时
                actionLogin();
                break;
            default:
                break;
        }
    }

    /**
     * 用户登录
     */
    private void actionLogin() {
        usernamelj23 = et_usrlj23.getText().toString().trim();
        passwordlj23 = et_pwdlj23.getText().toString().trim();

        String requestUrl = Applicationlj23.SERVERlj23 + Applicationlj23.ACTION_LOGINlj23;
        Map<String, String> requestDataMap = new HashMap<String, String>();
        requestDataMap.put("username", usernamelj23);
        requestDataMap.put("password", passwordlj23);
        RequestVolj23 requestVo = new RequestVolj23(requestUrl, requestDataMap);
        //计时器

        clklj23.setBase(SystemClock.elapsedRealtime());
        clklj23.setFormat("正在登录... %s");
        clklj23.start();
        clklj23.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {

            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(SystemClock.elapsedRealtime() - clklj23.getBase() >= 30000){
                    textlj23.setVisibility(View.VISIBLE);//显示超时信息
                    Intent intent = new Intent(LoginActivitylj23.this,Enterlj23.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        getDataFromServer(requestVo, new DataCallBack<JSONObject>() {
            @Override
            public void doInBackground(JSONObject jo) {
                try {
                    if ("error".equals(jo.getString("response"))) {
                        // 用户名或密码错误
                        progressbarlj23.setVisibility(View.INVISIBLE);//关闭进度条
                        Toast.makeText(LoginActivitylj23.this, jo.getJSONObject("error").getString("message"), Toast.LENGTH_SHORT).show();
                    } else {
                        // 身份认证通过，转到MySetlj23
                        progressbarlj23.setVisibility(View.INVISIBLE);//关闭进度条
                        string_username= jo.getJSONObject("userinfo").getString("uname");
                        Toast.makeText(LoginActivitylj23.this, string_username+"你好！"+"欢迎回来", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivitylj23.this, MySetlj23.class));
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
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
