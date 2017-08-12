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
    private Node neighbourOne;
    private Node neighbourTwo;
    private Node neighbourThree;
    private Node neighbourFour;
    
    public Node() {

    }

    public Node (Corner topLeftCorner, Corner topRightCorner, Corner bottomLeftCorner, Corner bottomRightCorner, User user, int peersCount) {

    }


    public double hashX(String ip) {
        double x = ip.hashCode();
        if(x < 0){
            x = x/(-2552552552l);
            return x;
        }else{
            x = x/2552552552l;
            return x;
        }
    }



    public double hashY(String ip){
        String hash2 = umkehren(ip);
        double y = hash2.hashCode();
        if(y < 0){
            y = y/(-2552552552l);
            return y;
        }else{
            y = y/2552552552l;
            return y;
        }
    }


    public static String umkehren( String str )
    {
        String umgekehrt = new String();

        for ( int j = str.length()-1; j >= 0; j-- )
            umgekehrt += str.charAt(j);

        return umgekehrt;
    }

    public boolean checkIfInMyZone(double x, double y) {
        if(x > topLeftCorner.getX() && x <= topRightCorner.getX())
        {
            if (y > bottomLeftCorner.getY() && y <= topLeftCorner.getY() ) {
                return true;
            }
        }
        return false;
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

    public Node getNeighbourFour() {
        return neighbourFour;
    }

    public Node getNeighbourOne() {
        return neighbourOne;
    }

    public Node getNeighbourThree() {
        return neighbourThree;
    }

    public Node getNeighbourTwo() {
        return neighbourTwo;
    }


    public void setNeighbourFour(Node neighbourFour) {
        this.neighbourFour = neighbourFour;
    }

    public void setNeighbourOne(Node neighbourOne) {
        this.neighbourOne = neighbourOne;
    }

    public void setNeighbourThree(Node neighbourThree) {
        this.neighbourThree = neighbourThree;
    }

    public void setNeighbourTwo(Node neighbourTwo) {
        this.neighbourTwo = neighbourTwo;
    }
}
