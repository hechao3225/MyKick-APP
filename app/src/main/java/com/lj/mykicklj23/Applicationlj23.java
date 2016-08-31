package com.lj.mykicklj23;

import android.content.Context;

/**
 * Created by LJ on 2016/6/20.
 */
public class Applicationlj23  extends android.app.Application{
    public static final String SERVERlj23 = "http://1525fg0730.iok.la/";
    public static final String ACTION_LOGINlj23 = "login";
    public static final String ACTION_REGISTlj23 = "regist";
    public static  final  String ACTION_SETlj23="set";
    public static  final String ACTION_UPLOADSCORElj23="uploadscore";
    public static  final String ACTION_SCORERANKINGlj23="scoreranking";
    public static Context contextlj23;

    @Override
    public void onCreate() {
        super.onCreate();
        Applicationlj23.contextlj23 = getApplicationContext();
    }
}
