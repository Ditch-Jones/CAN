package com.example.joshi.can.Logic;

import java.lang.*;
/**
 * Created by Joshi on 11.08.2017.
 */

public class Node {
    private double ownX;
    private double ownY;
    private Corner topLeftCorner;
    private Corner topRightCorner;
    private Corner bottomLeftCorner;
    private Corner bottomRightCorner;
    private User user;
    private final static int maxPeers = 3;
    private int peersCount;
    private Node neighbour;
    
    public Node() {

    }

    /**
     *
     * @param topLeftCorner
     * @param topRightCorner
     * @param bottomLeftCorner
     * @param bottomRightCorner
     * @param user hat ID und IP
     * @param peersCount
     */
    public Node (Corner topLeftCorner, Corner topRightCorner, Corner bottomLeftCorner, Corner bottomRightCorner, User user, int peersCount) {
        this.topLeftCorner       = topLeftCorner;
        this.bottomLeftCorner    = bottomLeftCorner;
        this.topRightCorner      = topRightCorner;
        this.bottomRightCorner   = bottomRightCorner;
        this.user                = user;
        this.peersCount          = peersCount;
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

    /**
     * Routing-Methode
     * @param ip IP des zu routenden Knoten
     * @param x
     * @param y
     */
    private void receiveRoutingRequest(String ip, double x, double y, int id, boolean isNode) {
        if(checkIfInMyZone(x,y)){
            if(isNode){
                //// TODO: 14.08.2017  Reply to Request-Method(muss setPeers(mit sich selbst) und setNeighbours mitsenden)
                //// TODO: 14.08.2017 Muss aktuelle Peers über den neuen Knoten Informieren, sodass diese ihre Peerliste updaten. Nun update deine eigene Peerlist
                if(getPeersCount() == maxPeers){
                    //// TODO: 15.08.2017 informiere deine Peers das sie nun Splitten müssen// methode die einen Splitt aufruft
                    //// TODO: 14.08.2017 SPLITT
                }
            }else {
                //// TODO: 15.08.2017 Verbindungsaufbau zu der ip um Bild herunterzuladen und dann zu speichern
                /// TODO: 15.08.2017 verbindungsaufbau zu Peers und diesen werden die Informationen zum Bild übermittelt und nun laden sie sich das Bild von zuletzt gerouteten Node herunter
            }
        }
        //fortsetzung des routing
        routing(ip,x,y,id,isNode);
    }

    private void routing(String ip, double x ,double y, int id, boolean isNode){
        double neighbourX, neighbourY;
        double [] distances = new double[3];

        for(int i=0; i<=3 ; i++){
            // gibt getNeighbour ein Objekt wieder?
            if(getNeighbour(i) != null){
                neighbour = getNeighbour(i);

                neighbourX = neighbour.getOwnX();
                neighbourY = neighbour.getOwnY();

                distances[i] = computeDistance(x,y,neighbourX,neighbourY);
            }
        }
        int index = compareValues(distances);
        //// TODO: 14.08.2017 Verbindungsaufbau zu dem Neighbour der an Stelle == Index steht und IP und x,y-Werte übertragen so das dieser weiter routen kann
    }


    private void delPicInCan(int id, double x, double y){
        // TODO: 15.08.2017 Erst muss delPicInCan aufgerufen werden bevor das Bild auf dem eigenen Gerät gelöscht wird
        // TODO: 15.08.2017 checke deinen foreignData Table um zu sehen ob die id,x und y übereinstimmen, Falls dies der Fall ist lösche das Bild
        // TODO: 15.08.2017  
        // benötigen die Methoden getID, getX und getY auf den foreignDataTable, @somar wie löscht man ein Bild auf dem Gerät
    }
    /**
        Diese Methode berechnet die Distanz zwischen den zu Routenden Knoten und den Neighbours des aktuellen Knotens(der routet)
     */
    private double computeDistance(double x, double y, double neighbourX, double neighbourY) {
        double dis = Math.abs(x - neighbourX) + Math.abs(y - neighbourY);
        return dis;
    }

    private void informPeersAboutYourself(String ip) {
        //// TODO: 14.08.2017    user.getUid(); von DB, user.getIP von DB
        //// TODO: 14.08.2017 sende an alle deine Peers ein setPeer mit diesen Informationen

    }
    /**
     * Vergleiche alle Distanzen der Nachbarn
     * @param distances Array mit allen Distanzen der Neighbour zu dem zu routenden Knoten
     * @return den index(Neighbour) mit der geringsten Distanz
     */
    private int compareValues(double [] distances){
        int index = 0;
        double temp =  distances[0];
        for(int i= 1 ; i< distances.length; i++){
            if(temp > distances[i]){
                temp = distances[i];
                index = i;
            }
        }
        return index;
    }





    private void requestJoin(){
        //// TODO: 15.08.2017 getBootsTrapIP() Method
        //// TODO: 15.08.2017 nun Verbindung zu dieser IP herstellen und routing-Anfrage mit(eigener IP und x ,y Werten als Parameter) 
    }

    private void replyToRequest(String ip){
        //// TODO: 15.08.2017 Verbindung zu IP herstellen, und setPeer und setNeighbour aufrufen auf diesem Knoten(mit den eigenen Peers und Neighbour-Werten) 
        //// TODO: 15.08.2017 nach update der eigenen PeersDB muss überprüft werden ob die Anzahl Peers nun 3 beträgt, falls dies der Fall ist => Split 
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
        if (peersCount == maxPeers){
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

    public Corner getTopLeftCorner()
    {
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

    public Node getNeighbour(int i) {
        return neighbour;
    }

    public void setNeighbour(Node neighbour) {
        this.neighbour = neighbour;
    }



    public double getOwnX() {
        return ownX;
    }

    public double getOwnY() {
        return ownY;
    }

    public void setOwnX(double ownX) {
        this.ownX = ownX;
    }

    public void setOwnY(double ownY) {
        this.ownY = ownY;
    }


}
