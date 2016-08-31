package com.lj.mykicklj23;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by LJ on 2016/6/20.
 */
public class Enterlj23 extends Activity {
    private  static MediaPlayer mplj23=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enterlj23);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Button enter = (Button) findViewById(R.id.bt_set);
        Button login = (Button) findViewById(R.id.bt_enteronline);
        Button quit = (Button) findViewById(R.id.quit);
        //音乐
        if(mplj23 != null){
            mplj23.release();
        }
        mplj23 = MediaPlayer.create(Enterlj23.this, R.raw.mainmenu);
        mplj23.start();

        enter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Enterlj23.this, MyKicklj23.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Enterlj23.this,RegistActivitylj23.class);
                startActivity(intent);
                finish();
            }
        });

        quit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog();

            }
        });
    }

    protected void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Enterlj23.this);
        builder.setMessage("确定退出吗？");

        builder.setTitle("提示");

        builder.setPositiveButton("是的", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());//杀死进程
                System.exit(0);//安全退出程序
            }
        });

        builder.setNegativeButton("还玩会", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
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
