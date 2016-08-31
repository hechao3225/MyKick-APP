package com.lj.mykicklj23;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by HC on 2016/6/23.
 */
public class MyKickOnlinelj23 extends Activity {
    private int i = 0;
    private Handler handler1lj23;
    private Handler handler2lj23;
    private Handler handler3lj23;
    private Handler handler_bosslj23;
    private Handler handler_notlj23;
    private ImageView mouse1lj23;
    private ImageView mouse2lj23;
    private ImageView mouse3lj23;
    private ImageView mouse_bosslj23;
    private ImageView mouse_notlj23;

    private TextView textViewlj23 ;
    private ImageView integral_add10lj23;
    private ImageView integral_add20lj23;
    private ImageView integral_add30lj23;
    private ImageView integral_add50lj23;
    private ImageView integral_sub50lj23;
    private ImageView integral_3s;
    private ImageView integral_2s;
    private ImageView integral_1s;
    private ImageView ready_go;

    private Chronometer chlj23 ;
    private Chronometer ch_readylj23;
    private SoundPool poollj23;
    private TextView viewlj23;
    private static MediaPlayer mplj23 = null;
    private HashMap<Integer, Integer> soundmaplj23 = new HashMap<Integer, Integer>();
    public int[][] positionlj23 = new int[][] { { 150, 100 }, { 250, 100 },
            { 350, 100 }, { 450, 100 }, { 550, 100 }, { 650, 100 },
            { 50, 100 }, { 150, 220 }, { 250, 220 }, { 350, 510 },
            { 450, 220 }, { 550, 220 }, { 650, 220 }, { 50, 220 } };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mykickonlinelj23);

        viewlj23 = (TextView) findViewById(R.id.textView1);
        viewlj23.setText("亲爱的玩家 "+LoginActivitylj23.string_username+"： 祝您游戏愉快！");

        //音乐
        if(mplj23 != null){
            mplj23.release();
        }
        mplj23 = MediaPlayer.create(MyKickOnlinelj23.this, R.raw.dalaoshu);
        mplj23.start();
        //计时器
        chlj23 = (Chronometer) findViewById(R.id.chronometer1);
        chlj23.setBase(SystemClock.elapsedRealtime());
        chlj23.setFormat("已用时间：%s");
        //倒计时
        ch_readylj23 = (Chronometer) findViewById(R.id.chronometer2);

        //       integral_3s=(ImageView)findViewById(R.id.integral_3s);
        //       integral_2s=(ImageView)findViewById(R.id.integral_2s);
        //       integral_1s=(ImageView)findViewById(R.id.integral_1s);
        //       ready_go=(ImageView)findViewById(R.id.ready_go);
        //         ch_readylj23.setVisibility(View.INVISIBLE);

        //       integral_3s.setVisibility(View.VISIBLE);
        //       integral_2s.setVisibility(View.INVISIBLE);
        //       integral_1s.setVisibility(View.INVISIBLE);
        //       ready_go.setVisibility(View.INVISIBLE);

        ch_readylj23.setBase(SystemClock.elapsedRealtime());
        ch_readylj23.start();
        ch_readylj23.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(SystemClock.elapsedRealtime() - ch_readylj23.getBase() >= 1000 && SystemClock.elapsedRealtime() - ch_readylj23.getBase() < 2000){
                    //                  integral_3s.startAnimation(translatelj23);
                    //                  integral_3s.setVisibility(View.INVISIBLE);
                    //                  integral_2s.setVisibility(View.VISIBLE);

                }else if(SystemClock.elapsedRealtime() - ch_readylj23.getBase() >= 2000 && SystemClock.elapsedRealtime() - ch_readylj23.getBase() < 3000){
                    //                  integral_2s.startAnimation(translatelj23);
                    //                  integral_2s.setVisibility(View.INVISIBLE);
                    //                  integral_1s.setVisibility(View.VISIBLE);
                }else if(SystemClock.elapsedRealtime() - ch_readylj23.getBase() >= 3000){
                    //                  integral_1s.startAnimation(translatelj23);
                    //                  integral_1s.setVisibility(View.INVISIBLE);
                    //                  ready_go.setVisibility(View.VISIBLE);
                    //                  ready_go.startAnimation(translatelj23);
                    //                  ready_go.setVisibility(View.INVISIBLE);
                    chlj23.start();
                    ch_readylj23.stop();
                }
            }
        });

        chlj23.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {

            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(SystemClock.elapsedRealtime() - chlj23.getBase() >= 30000){
                    Intent intent = new Intent(MyKickOnlinelj23.this,FinishOnlinelj23.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("num", i);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    MyKickOnlinelj23.this.finish();
                }
            }
        });

        textViewlj23 = (TextView) findViewById(R.id.textView1);
        mouse1lj23 = (ImageView) findViewById(R.id.mouse1);
        mouse2lj23 = (ImageView) findViewById(R.id.mouse2);
        mouse3lj23 = (ImageView) findViewById(R.id.mouse3);
        mouse_bosslj23=(ImageView) findViewById(R.id.mouse_boss);
        mouse_notlj23=(ImageView) findViewById(R.id.mouse_not);

        integral_add10lj23=(ImageView) findViewById(R.id.add10);
        integral_add20lj23=(ImageView) findViewById(R.id.add20);
        integral_add30lj23=(ImageView) findViewById(R.id.add30);
        integral_add50lj23=(ImageView) findViewById(R.id.add50);
        integral_sub50lj23=(ImageView) findViewById(R.id.sub50);

        integral_add10lj23.setVisibility(View.INVISIBLE);
        integral_add20lj23.setVisibility(View.INVISIBLE);
        integral_add30lj23.setVisibility(View.INVISIBLE);
        integral_add50lj23.setVisibility(View.INVISIBLE);
        integral_sub50lj23.setVisibility(View.INVISIBLE);

        mouse1lj23.setVisibility(View.INVISIBLE);
        mouse2lj23.setVisibility(View.INVISIBLE);
        mouse3lj23.setVisibility(View.INVISIBLE);
        mouse_bosslj23.setVisibility(View.INVISIBLE);
        mouse_notlj23.setVisibility(View.INVISIBLE);

        poollj23 = new SoundPool(2, AudioManager.STREAM_SYSTEM, 0);
        soundmaplj23.put(1, poollj23.load(MyKickOnlinelj23.this, R.raw.dalaoshu,1));
        soundmaplj23.put(2, poollj23.load(MyKickOnlinelj23.this, R.raw.enter,1));
        poollj23.play(soundmaplj23.get(1), 1, 1, 0, -1, 1);
        final Animation translatelj23 = AnimationUtils.loadAnimation(MyKickOnlinelj23.this, R.anim.anim_translate);
        //mouse1
        mouse1lj23.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public  boolean onTouch(View v, MotionEvent event) {
                v.setVisibility(View.INVISIBLE);// 设置地老鼠不显示
                i++;
                poollj23.play(soundmaplj23.get(2), 1, 1, 0, 0, 1);
                integral_add10lj23.startAnimation(translatelj23);
                integral_add10lj23.setVisibility(View.INVISIBLE);
                textViewlj23.setText("积分："+i+"0");
                return false;
            }
        });
        handler1lj23 = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                int index = 0;
                if(msg.what == 0x111){
                    index = msg.arg1;
                    mouse1lj23.setX(positionlj23[index][0]);
                    mouse1lj23.setY(positionlj23[index][1]);
                    mouse1lj23.setVisibility(View.VISIBLE);
                }
                super.handleMessage(msg);
            }
        };
        //mouse2
        mouse2lj23.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public  boolean onTouch(View v, MotionEvent event) {
                v.setVisibility(View.INVISIBLE);// 设置地老鼠不显示
                i+=2;
                poollj23.play(soundmaplj23.get(2), 1, 1, 0, 0, 1);
                integral_add20lj23.startAnimation(translatelj23);
                integral_add20lj23.setVisibility(View.INVISIBLE);
                textViewlj23.setText("积分："+i+"0");
                return false;
            }
        });
        handler2lj23 = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                int index = 0;
                if(msg.what == 0x222){
                    index = msg.arg1;
                    mouse2lj23.setX(positionlj23[index][0]);
                    mouse2lj23.setY(positionlj23[index][1]);
                    mouse2lj23.setVisibility(View.VISIBLE);
                }
                super.handleMessage(msg);
            }
        };
        //mouse3
        mouse3lj23.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public  boolean onTouch(View v, MotionEvent event) {
                v.setVisibility(View.INVISIBLE);// 设置地老鼠不显示
                i+=3;
                poollj23.play(soundmaplj23.get(2), 1, 1, 0, 0, 1);
                integral_add30lj23.startAnimation(translatelj23);
                integral_add30lj23.setVisibility(View.INVISIBLE);
                textViewlj23.setText("积分："+i+"0");
                return false;
            }
        });
        handler3lj23 = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                int index = 0;
                if(msg.what == 0x333){
                    index = msg.arg1;
                    mouse3lj23.setX(positionlj23[index][0]);
                    mouse3lj23.setY(positionlj23[index][1]);
                    mouse3lj23.setVisibility(View.VISIBLE);
                }
                super.handleMessage(msg);
            }
        };
        //mouse_boss
        mouse_bosslj23.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public  boolean onTouch(View v, MotionEvent event) {
                v.setVisibility(View.INVISIBLE);// 设置地老鼠不显示
                i+=5;
                poollj23.play(soundmaplj23.get(2), 1, 1, 0, 0, 1);
                integral_add50lj23.startAnimation(translatelj23);
                integral_add50lj23.setVisibility(View.INVISIBLE);
                textViewlj23.setText("积分："+i+"0");
                return false;
            }
        });
        handler_bosslj23 = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                int index = 0;
                if(msg.what == 0x444){
                    index = msg.arg1;
                    mouse_bosslj23.setX(positionlj23[index][0]);
                    mouse_bosslj23.setY(positionlj23[index][1]);
                    mouse_bosslj23.setVisibility(View.VISIBLE);
                }
                super.handleMessage(msg);
            }
        };
        //mouse_not
        mouse_notlj23.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public  boolean onTouch(View v, MotionEvent event) {
                v.setVisibility(View.INVISIBLE);// 设置地老鼠不显示
                i-=5;
                poollj23.play(soundmaplj23.get(2), 1, 1, 0, 0, 1);
                integral_sub50lj23.startAnimation(translatelj23);
                integral_sub50lj23.setVisibility(View.INVISIBLE);
                textViewlj23.setText("积分："+i+"0");
                return false;
            }
        });
        handler_notlj23 = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                int index = 0;
                if(msg.what == 0x555){
                    index = msg.arg1;
                    mouse_notlj23.setX(positionlj23[index][0]);
                    mouse_notlj23.setY(positionlj23[index][1]);
                    mouse_notlj23.setVisibility(View.VISIBLE);
                }
                super.handleMessage(msg);
            }
        };

        Thread t = new Thread(new Runnable() {
            int mouse_flag=0;
            @Override
            public void run() {
                int indexlj23 = 0;
                try {
                    Thread.sleep(3500);//睡3s再开始游戏,留少量余量
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                Toast.makeText(MyKicklj23.this, "mouse_flag:"+mouse_flag, Toast.LENGTH_SHORT).show();
                while(!Thread.currentThread().isInterrupted()){
                    indexlj23 = new Random().nextInt(positionlj23.length);
                    mouse_flag=(int)(1+Math.random()*(5-1+1));
                    if(mouse_flag==1) {
                        Message mlj23 = handler1lj23.obtainMessage();
                        mlj23.what = 0x111;
                        mlj23.arg1 = indexlj23;
                        handler1lj23.sendMessage(mlj23);
                    }else if(mouse_flag==2){
                        Message mlj23 = handler2lj23.obtainMessage();
                        mlj23.what = 0x222;
                        mlj23.arg1 = indexlj23;
                        handler2lj23.sendMessage(mlj23);
                    }else if(mouse_flag==3){
                        Message mlj23 = handler3lj23.obtainMessage();
                        mlj23.what = 0x333;
                        mlj23.arg1 = indexlj23;
                        handler3lj23.sendMessage(mlj23);
                    }else if(mouse_flag==4){
                        Message mlj23 = handler_bosslj23.obtainMessage();
                        mlj23.what = 0x444;
                        mlj23.arg1 = indexlj23;
                        handler_bosslj23.sendMessage(mlj23);
                    }else{
                        Message mlj23 = handler_notlj23.obtainMessage();
                        mlj23.what = 0x555;
                        mlj23.arg1 = indexlj23;
                        handler_notlj23.sendMessage(mlj23);
                    }

                    try {
                        Thread.sleep(new Random().nextInt(240)+200);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        t.start();
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

