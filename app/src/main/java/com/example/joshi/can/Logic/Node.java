package com.example.joshi.can.Logic;

/**
 * Created by Joshi on 11.08.2017.
 */

public class Node {
    private Corner topLeftCorner;
    private Corner topRightCorner;
    private Corner bottomLeftCorner;
    private Corner bottomRightCorner;
    private User user;
    private final static int maxPeers = 4;
    private int peersCount;

    public Node() {

    }

    public Node (Corner topLeftCorner, Corner topRightCorner, Corner bottomLeftCorner, Corner bottomRightCorner, User user, int peersCount) {

    }

    public void increasePeersCount(){
        if(checkIfMaxPeersCount()){

        }else{
            peersCount++;
        }
    }
    public void decreasePeersCount(){
        if(peersCount < 1){

        }else{
            peersCount--;
        }
    }

    private boolean checkIfMaxPeersCount(){
        if (peersCount == 4){
            return true;
        }else{
            return false;
        }
    }
    //getter and setter
    public void setBottomLeftCorner(Corner bottomLeftCorner) {
        this.bottomLeftCorner = bottomLeftCorner;
    }

    public void setBottomRightCorner(Corner bottomRightCorner) {
        this.bottomRightCorner = bottomRightCorner;
    }

    public void setPeersCount(int peersCount) {
        this.peersCount = peersCount;
    }

    public void setTopLeftCorner(Corner topLeftCorner) {
        this.topLeftCorner = topLeftCorner;
    }

    public void setTopRightCorner(Corner topRightCorner) {
        this.topRightCorner = topRightCorner;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Corner getBottomLeftCorner() {
        return bottomLeftCorner;
    }

    public Corner getBottomRightCorner() {
        return bottomRightCorner;
    }

    public Corner getTopRightCorner() {
        return topRightCorner;
    }

    public Corner getTopLeftCorner() {
        return topLeftCorner;
    }

    public static int getMaxPeers() {
        return maxPeers;
    }

    public int getPeersCount() {
        return peersCount;
    }

    public User getUser() {
        return user;
    }

}
