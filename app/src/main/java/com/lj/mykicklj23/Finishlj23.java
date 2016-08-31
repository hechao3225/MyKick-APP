package com.lj.mykicklj23;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by LJ on 2016/6/14.
 */
public class Finishlj23 extends Activity {
    private static MediaPlayer mplj23=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finishlj23);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int cur_score=bundle.getInt("num")*10;
        TextView view = (TextView) findViewById(R.id.textView1);
        view.setText("你的积分："+cur_score);
        Button go_on = (Button) findViewById(R.id.go_on);
        Button go_back = (Button) findViewById(R.id.go_back);

        if(cur_score<1000)
            Toast.makeText(Finishlj23.this,"@"+ LoginActivitylj23.string_username + "：还要加油哦，我相信你！", Toast.LENGTH_SHORT).show();
        else if(cur_score>=1000&&cur_score<3000)
            Toast.makeText(Finishlj23.this,"@"+ LoginActivitylj23.string_username + "：厉害哦，潜力股！", Toast.LENGTH_SHORT).show();
        else if(cur_score>=3000&&cur_score<5000)
            Toast.makeText(Finishlj23.this,"@"+ LoginActivitylj23.string_username + "：太棒了，请收下我的膝盖！", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(Finishlj23.this,"@"+ LoginActivitylj23.string_username + "：传说中的上帝之手！", Toast.LENGTH_SHORT).show();

        //音乐
        if(mplj23 != null){
            mplj23.release();
        }
        mplj23 = MediaPlayer.create(Finishlj23.this, R.raw.gameover);
        mplj23.start();

        go_on.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Finishlj23.this,MyKicklj23.class);
                startActivity(intent);
                finish();
            }
        });
        go_back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Finishlj23.this,Enterlj23.class);
                startActivity(intent);
                finish();
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
