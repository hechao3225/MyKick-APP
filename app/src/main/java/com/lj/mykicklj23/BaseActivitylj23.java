package com.lj.mykicklj23;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONObject;

/**
 * Created by LJ on 2016/6/20.
 */
public class BaseActivitylj23 extends Activity {
    private static final String TAGlj23 = "BaseActivity";
    private static final int NONETWORKlj23 = 0;
    private static final int COMPLETElj23 = 1;
    private static final int NOCONTENTlj23 = 2;
    protected ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAGlj23, "BaseActivitylj23.class :: onCreate()");

    }

    protected void getDataFromServer(RequestVolj23 requestVo, DataCallBack<JSONObject> callBack) {
        BaseHandler handler = new BaseHandler(callBack);
        BaseTask task = new BaseTask(requestVo, handler);
        ThreadPoolManagerlj23.getInstancelj23().executelj23(task);
    }

    @SuppressLint("HandlerLeak")
    public class BaseHandler extends Handler {

        private DataCallBack<JSONObject> callback;

        public BaseHandler(DataCallBack<JSONObject> callBack) {
            this.callback = callBack;
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NONETWORKlj23:
                    Log.e(TAGlj23, "Request failed due to no available network");
                    break;
                case NOCONTENTlj23:
                    Log.e(TAGlj23, "Request complete with no data");
                    break;
                case COMPLETElj23:
                    Log.e(TAGlj23, "Request complete successfully");
                    // TODO 返回json数据进行解析
                    JSONObject obj = (JSONObject) msg.obj;
                    callback.doInBackground(obj);
                    break;

                default:
                    break;
            }
        }
    }

    public class BaseTask implements Runnable {

        private RequestVolj23 requestVolj23;
        private Handler handlerlj23;

        public BaseTask(RequestVolj23 requestVo, Handler handler) {
            this.requestVolj23 = requestVo;
            this.handlerlj23 = handler;
        }

        @Override
        public void run() {
            // Looper.prepare();
            if (Toolslj23.hasNoNetworklj23(BaseActivitylj23.this)) {
                handlerlj23.sendEmptyMessage(NONETWORKlj23);
            } else {
                JSONObject jo = HttpRequestlj23.postRequest(requestVolj23);
                if (jo != null) {
                    Message msg = Message.obtain();
                    msg.what = COMPLETElj23;
                    msg.obj = jo;
                    handlerlj23.sendMessage(msg);
                } else {
                    handlerlj23.sendEmptyMessage(NOCONTENTlj23);
                }
            }
            // Looper.loop();
        }
    }

    public abstract interface DataCallBack<T> {
        public abstract void doInBackground(T jo);
    }

}
