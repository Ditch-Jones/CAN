package com.example.joshi.can.Logic;

import com.example.joshi.can.Exception.IpMustBeAStringException;
import com.example.joshi.can.Exception.UidMustBeLargerThanZeroException;

/**
 * Created by Joshi on 11.08.2017.
 */

public class User {
    private int uid;
    private String ip;

    public User () {

    }

    public User(int uid, String ip) throws UidMustBeLargerThanZeroException, IpMustBeAStringException {
        if(uid < 0){
            throw new UidMustBeLargerThanZeroException("UID hast to be larger than Zero");
        }else if(!(ip instanceof String)){
            throw new IpMustBeAStringException("IP must be a String");
        } else{
            this.ip = ip;
            this.uid= uid;
        }

    }

    public int getUid() {
        return uid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
