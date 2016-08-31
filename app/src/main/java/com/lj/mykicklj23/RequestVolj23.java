package com.lj.mykicklj23;

import java.util.Map;

/**
 * Created by LJ on 2016/6/20.
 */
public class RequestVolj23  {
    public String requestUrllj23;
    public Map<String, String> requestDataMaplj23;

    public RequestVolj23() {
        super();
    }

    public RequestVolj23(String requestUrl, Map<String, String> requestDataMap) {
        super();
        this.requestUrllj23= requestUrl;
        this.requestDataMaplj23 = requestDataMap;
    }

    @Override
    public String toString() {
        return "RequestVo [requestUrl=" + requestUrllj23 + ", requestDataMap=" + requestDataMaplj23 + "]";
    }
}
