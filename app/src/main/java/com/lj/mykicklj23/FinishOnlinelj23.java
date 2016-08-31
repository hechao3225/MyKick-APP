package com.lj.mykicklj23;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

/**
 * Created by HC on 2016/6/23.
 */
public class FinishOnlinelj23 extends BaseActivitylj23{
    private  static MediaPlayer mplj23=null;
    private  static int isRightToUpload=0;
    private static  int curScore=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finishonlinelj23);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int cur_score=bundle.getInt("num")*10;
        curScore=cur_score;
        TextView view = (TextView) findViewById(R.id.textView1);
        view.setText("@"+LoginActivitylj23.string_username+":你的积分："+cur_score);

        if(cur_score>GameInfolj23.local_score) {
            GameInfolj23.local_score = cur_score;//刷新最佳成绩
            isRightToUpload=1;//设定标志，此时为最佳成绩，避免用户勿传非最佳成绩
            Toast.makeText(FinishOnlinelj23.this, "@"+LoginActivitylj23.string_username + "：太棒了！刷新你的最佳成绩，建议上传。", Toast.LENGTH_SHORT).show();
        }

        if(cur_score<1000)
            Toast.makeText(FinishOnlinelj23.this,"@"+ LoginActivitylj23.string_username + "：还要加油哦，我相信你！", Toast.LENGTH_SHORT).show();
        else if(cur_score>=1000&&cur_score<3000)
            Toast.makeText(FinishOnlinelj23.this,"@"+ LoginActivitylj23.string_username + "：厉害哦，潜力股！", Toast.LENGTH_SHORT).show();
        else if(cur_score>=3000&&cur_score<5000)
            Toast.makeText(FinishOnlinelj23.this,"@"+ LoginActivitylj23.string_username + "：太棒了，请收下我的膝盖！", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(FinishOnlinelj23.this,"@"+ LoginActivitylj23.string_username + "：传说中的上帝之手！", Toast.LENGTH_SHORT).show();

        Button go_on = (Button) findViewById(R.id.go_on);
        Button go_back = (Button) findViewById(R.id.go_back);
        Button upload_score = (Button) findViewById(R.id.upload_score);
        //音乐
        if(mplj23 != null){
            mplj23.release();
        }
        mplj23 = MediaPlayer.create(FinishOnlinelj23.this, R.raw.gameover);
        mplj23.start();
        go_on.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinishOnlinelj23.this,MyKickOnlinelj23.class);
                startActivity(intent);
                FinishOnlinelj23.this.finish();
            }
        });
        upload_score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1lj23();
            }
        });
        go_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinishOnlinelj23.this,MySetlj23.class);
                startActivity(intent);
                FinishOnlinelj23.this.finish();
            }
        });
    }

    protected void dialog1lj23() {
        AlertDialog.Builder builder = new Builder(FinishOnlinelj23.this);
        builder.setMessage("确定上传吗？请上传您的最佳成绩");

        builder.setTitle("温馨提示");

        builder.setPositiveButton("暂时就它了", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //待实现
                if(isRightToUpload==0) {
                    Toast.makeText(FinishOnlinelj23.this, "@" + LoginActivitylj23.string_username + "：不是您的最佳成绩哦！", Toast.LENGTH_SHORT).show();
                    dialog2lj23();//此时不是最佳成绩，弹框询问是否仍然选择上传？
                }
                else {
                    actionUploadScorelj23(GameInfolj23.local_score);//发现是最佳成绩，直接上传
                    GameInfolj23.net_score=curScore;//记录服务端分数到本地
                    Toast.makeText(FinishOnlinelj23.this, "@" +  LoginActivitylj23.string_username + "：当前你上传的分数为："+ GameInfolj23.net_score, Toast.LENGTH_SHORT).show();
                    isRightToUpload=0;
                }
            }
        });

        builder.setNegativeButton("我不服！", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    protected void dialog2lj23() {
        AlertDialog.Builder builder = new Builder(FinishOnlinelj23.this);
        builder.setMessage("仍然上传？");

        builder.setTitle("提示");

        builder.setPositiveButton("是的", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                actionUploadScorelj23(curScore);//玩家执意要上传，那就传吧
                GameInfolj23.net_score=curScore;//记录服务端分数到本地
                Toast.makeText(FinishOnlinelj23.this, "@" +  LoginActivitylj23.string_username + "：当前你上传的分数为："+ GameInfolj23.net_score, Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("不了", new OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

    private void actionUploadScorelj23(int score_upload ){
        String requestUrl = Applicationlj23.SERVERlj23 + Applicationlj23.ACTION_UPLOADSCORElj23; // 请求地址
        HashMap<String, String> requestDataMap = new HashMap<String, String>(); // 请求参数
        requestDataMap.put("username", LoginActivitylj23.string_username.trim());//待上传的用户
        String string_score = Integer.toString(score_upload);
        requestDataMap.put("score", string_score.trim());//待上传的分数
        RequestVolj23 requestVo = new RequestVolj23(requestUrl, requestDataMap);
        // 发送请求处理数据
        super.getDataFromServer(requestVo, new DataCallBack<JSONObject>() {

            @Override
            public void doInBackground(JSONObject jo) {
                try {
                    if ("error".equals(jo.getString("response"))) {
                        Toast.makeText(FinishOnlinelj23.this, jo.getJSONObject("error").getString("message"), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(FinishOnlinelj23.this, "@" + LoginActivitylj23.string_username + "：上传成功！", Toast.LENGTH_SHORT).show();
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

