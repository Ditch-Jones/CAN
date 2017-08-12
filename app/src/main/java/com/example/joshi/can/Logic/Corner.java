package com.example.joshi.can.Logic;

import com.example.joshi.can.Exception.XMustBeLargerThanZeroException;
import com.example.joshi.can.Exception.YMustBeLargerThanZeroException;

/**
 * Created by Joshi on 11.08.2017.
 */

public class Corner {
    private int x;
    private int y;

    public Corner(int x,int y) throws XMustBeLargerThanZeroException, YMustBeLargerThanZeroException {

        if(x < 0){
            throw new XMustBeLargerThanZeroException("X has to be larger than Zero");
        }else if(y < 0){
            throw new YMustBeLargerThanZeroException("Y has to be larger than Zero");

        }else{
            this.x = x;
            this.y = y;
        }

    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
