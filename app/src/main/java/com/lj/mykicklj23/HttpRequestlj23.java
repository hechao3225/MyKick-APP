package com.lj.mykicklj23;

import android.util.Log;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by LJ on 2016/6/20.
 */
public class HttpRequestlj23 {
    private static Header[] headers = new BasicHeader[1];
    private static final String TAG = "HttpRequest";

    public static JSONObject postRequest(RequestVolj23 requestVo) {
        // 创建httppost请求对象
        HttpPost request = new HttpPost(requestVo.requestUrllj23);

        Log.e(TAG, "Request Url :: " + requestVo.requestUrllj23);

        // 设置请求头
        request.setHeaders(headers);
        // 设置请求参数
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        // 从请求参数中取出用户名和密码信息
        Map<String, String> paramsMap = requestVo.requestDataMaplj23;

        Log.e(TAG, "Parameters' HashMap :: " + paramsMap.toString());
        try {
            if (requestVo.requestDataMaplj23 != null) {
                for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                    pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
            }
            // 请求参数加入裸体内容
            HttpEntity entity = new UrlEncodedFormEntity(pairs, "utf-8");
            request.setEntity(entity);

            // 获取服务器响应
            HttpResponse response = new DefaultHttpClient().execute(request);
            int statusCode = response.getStatusLine().getStatusCode();
            Log.e(TAG, "StatusCode = " + statusCode);
            if (statusCode == HttpStatus.SC_OK) {
                String json = EntityUtils.toString(response.getEntity());
                Log.e(TAG, json);
                return new JSONObject(json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static {
        headers[0] = new BasicHeader("appkey", "www.itechs.cn");
    }
}
