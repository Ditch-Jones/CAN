package com.example.joshi.can.Logic;

/**
 * Created by Joshi on 11.08.2017.
 */


import com.example.joshi.can.Exception.OwnerIdMustBeLargerThanZeroException;
import com.example.joshi.can.Exception.PicIdMustBeLargerThanZeroException;
import com.example.joshi.can.Exception.XMustBeLargerThanZeroException;
import com.example.joshi.can.Exception.YMustBeLargerThanZeroException;

/**
 * Created by Joshi on 11.08.2017.
 * This DataItem Object represents a Picture in CAN. It is mapped into CAN through
 * a Hashing-method and is then routed through CAN.
 */

public class DataItem {
    private int picID;

    private double x;
    private double y;

    public DataItem() {

    }

    public DataItem(int picID, int ownerID, double x, double y) throws PicIdMustBeLargerThanZeroException, OwnerIdMustBeLargerThanZeroException,
            XMustBeLargerThanZeroException, YMustBeLargerThanZeroException {
        if (picID < 0) {
            throw new PicIdMustBeLargerThanZeroException("Picture ID has to be larger than Zero");
        } else if (x < 0) {
            throw new XMustBeLargerThanZeroException("X has to be larger than Zero");
        } else if (y < 0) {
            throw new YMustBeLargerThanZeroException("Y has to be larger than Zero");
        } else {
            this.x          = x;
            this.y          = y;
            this.picID      = picID;
        }

    }



    //getter and Setter

    public double getPicID() {
        return picID;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setPicID(int picID) {
        this.picID = picID;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}

