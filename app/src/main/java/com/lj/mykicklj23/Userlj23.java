package com.lj.mykicklj23;

/**
 * Created by LJ on 2016/6/20.
 */
public class Userlj23 {
    public String uid;
    public String uname;
    public String upass;
    public String ucity;
    public String uphone;
    public String uqq;

    public String getUid() {return uid;}

    public String getUname() {
        return uname;
    }

    public String getUpass() {
        return upass;
    }

    public String getUcity() {
        return ucity;
    }

    public String getUphone() {
        return uphone;
    }

    public String getUqq() {
        return uqq;
    }

    public Userlj23() {
        super();
    }

    public Userlj23(String uid, String uname, String upass,String ucity,String uphone,String uqq) {
        super();
        this.uid = uid;
        this.uname = uname;
        this.upass = upass;
        this.ucity = ucity;
        this.uphone = uphone;
        this.uqq = uqq;
    }

    @Override
    public String toString() {
        return "User [uid=" + uid + ", uname=" + uname + ", upass=" + upass + "]";
    }
}
