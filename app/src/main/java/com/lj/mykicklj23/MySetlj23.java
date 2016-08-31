package com.lj.mykicklj23;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by LJ on 2016/6/20.
 */
public class MySetlj23 extends BaseActivitylj23 {
    private static MediaPlayer mplj23 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mysetlj23);

        //音乐
        if(mplj23 != null){
            mplj23.release();
        }
        mplj23 = MediaPlayer.create(MySetlj23.this, R.raw.welcome);
        mplj23.start();

        Button online = (Button) findViewById(R.id.bt_enteronline);
        Button set = (Button) findViewById(R.id.bt_setinfo);
        Button back = (Button) findViewById(R.id.bt_back);
        Button ranklist=(Button) findViewById(R.id.bt_ranklist);

        online.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MySetlj23.this, MyKickOnlinelj23.class);
                startActivity(intent);
                finish();
            }
        });

        set.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MySetlj23.this,SetActivitylj23.class);
                startActivity(intent);
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MySetlj23.this,Enterlj23.class);
                startActivity(intent);
                finish();
            }
        });

        ranklist.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //获取在线排行榜
                actionGetRankListlj23();
            }
        });
    }

    private void actionGetRankListlj23() {
        String requestUrl = Applicationlj23.SERVERlj23 + Applicationlj23.ACTION_SCORERANKINGlj23; // 请求地址
        HashMap<String, String> requestDataMap = new HashMap<String, String>(); // 请求参数
        requestDataMap.put("username", LoginActivitylj23.string_username.trim());//发起排行榜请求的玩家名
        requestDataMap.put("score", "scoreranking".trim());//请求内容提示

        RequestVolj23 requestVo = new RequestVolj23(requestUrl, requestDataMap);
        // 发送请求处理数据
        super.getDataFromServer(requestVo, new DataCallBack<JSONObject>() {

            @Override
            public void doInBackground(JSONObject jo) {
                try {
                    if ("error".equals(jo.getString("response"))) {
                        Toast.makeText(MySetlj23.this, jo.getJSONObject("error").getString("message"), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MySetlj23.this, "@" + LoginActivitylj23.string_username + "：获取排行榜成功！", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MySetlj23.this,jo.getJSONObject("userinfo").getString("uname") , Toast.LENGTH_SHORT).show();
                        Toast.makeText(MySetlj23.this,jo.getJSONObject("userinfo").getString("ucity") , Toast.LENGTH_SHORT).show();
                        Toast.makeText(MySetlj23.this,jo.getJSONObject("gameinfo").getString("score") , Toast.LENGTH_SHORT).show();
                        Toast.makeText(MySetlj23.this,jo.getJSONObject("scoreranking").toString(), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
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
