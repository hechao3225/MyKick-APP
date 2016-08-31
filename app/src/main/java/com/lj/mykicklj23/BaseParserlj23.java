package com.lj.mykicklj23;

import android.text.TextUtils;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by LJ on 2016/6/20.
 */
public abstract class BaseParserlj23<T> {
    public String filterlj23(String json) {
        String result = null;
        try {
            if (!TextUtils.isEmpty(json)) {
                JSONObject jo = new JSONObject(json);
                if ("error".equals(jo.getString("response"))) {
                    result = jo.getJSONObject("error").getString("message");
                    Toast.makeText(Applicationlj23.contextlj23, result, Toast.LENGTH_LONG).show();
                } else {
                    parseJsonlj23(json);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public abstract T parseJsonlj23(String json) throws JSONException;

}
