package com.example.joshi.can.Logic;


import java.lang.*;
/**
 * Created by Joshi on 11.08.2017.
 * Diese Klasse Node stellt einen Knotem im CAN dar
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
    private double rtt = 0;

    /**
     * Standardkonstruktor für Node
     */
    public Node() {

    }

    /**
     * Konstruktor für Node mit den folgenden Parametern
     * @param topLeftCorner Ecke links oben des Knotens
     * @param topRightCorner Ecke rechts oben des Knotens
     * @param bottomLeftCorner Ecke links unten des Knotens
     * @param bottomRightCorner Ecke rechts unten des Knotens
     * @param user Hat ID und IP
     * @param peersCount Anzahl der aktuellen Peers
     */
    public Node (Corner topLeftCorner, Corner topRightCorner, Corner bottomLeftCorner, Corner bottomRightCorner, User user, int peersCount) {
        this.topLeftCorner       = topLeftCorner;
        this.bottomLeftCorner    = bottomLeftCorner;
        this.topRightCorner      = topRightCorner;
        this.bottomRightCorner   = bottomRightCorner;
        this.user                = user;
        this.peersCount          = peersCount;
    }

    /**
     * Diese Methode liefert einen x-Wert der zwischen 0 und 1 liegt
     * Es wird durch 2552552552lgeteilt, da so Werte zwischen 0 und 1 liegt
     * @param ip Anhand der IP wird ein x-Wert berechnet
     * @return Gebe einen double X-Wert zurück
     */
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

    /**
     * Diese Methode liefert einen Y-Wert der zwischen 0 und 1 liegt
     * Es wird durch 2552552552l geteilt, da so Werte zwischen 0 und 1 liegen und die IP wird von hinten nach vorne gelesen durch Methode-Umkehren
     * @param ip Anhand der IP wird ein Y-Wert berechnet
     * @return Gebe einen double Y-Wert zurück
     */
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

    /**
     * Methode zum umkehren von einer IP-Adresse
     * @param ip Eine IP-Adresse
     * @return Gibt die IP-Adresse umgekehrt zurück
     */
    public static String umkehren( String ip )
    {
        String umgekehrt = new String();

        for ( int j = ip.length()-1; j >= 0; j-- )
            umgekehrt += ip.charAt(j);

        return umgekehrt;
    }

    /**
     * Methode zum Testen ob ein Knoten/Bild in der eigenen Zone liegt
     * @param x
     * @param y
     * @return True falls Knoten/Bild in der Zone liegt, false falls Knoten/bild nicht in der Zone liegt
     */
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
     * Methode die den Routing-Vorgang weiterleitet falls das Ziel noch nicht erreicht wurde
     * @param ip IP des zu routenden Knoten
     * @param x X-Wert des zu routenden Knoten
     * @param y Y-Wert des zu routenden Knoten
     * @param id kann jeweils FotoID oder UID sein, wird benötigt sodass man seinen Peers die nötigen Informationen zu dem neuen Knoten geben kann
     * @param isNode Dient zur unterscheidung von Knoten und Bildern
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

    /**
     * Routing Methode: In der Routing-Methode wird die Distanz zu allen Nachbarn berechnet und zu dem routet zu dem die Distanz am geringsten ist
     * @param ip Des zu routenden Knoten/Bild
     * @param x Des zu routenden Knoten/Bild
     * @param y Des zu routenden Knoten/Bild
     * @param id Des zu routenden Knoten/Bild
     * @param isNode Ist es ein Knoten? Wenn nicht dann ist es ein Bild
     */
    private void routing(String ip, double x ,double y, int id, boolean isNode){
        double neighbourX, neighbourY;
        double [] distances = new double[4];

        for(int i=0; i<=distances.length ; i++){
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

    /**
     * Methode um ein Bild auf dem Gerät und dann auch im CAN zu löschen
     * @param id Des zu löschenden Bildes
     * @param x Des Bildes
     * @param y Des Bildes
     */
    private void delPicInCan(int id, double x, double y){
        // TODO: 15.08.2017 Erst muss delPicInCan aufgerufen werden bevor das Bild auf dem eigenen Gerät gelöscht wird
        // TODO: 15.08.2017 checke deinen foreignData Table um zu sehen ob die id,x und y übereinstimmen, Falls dies der Fall ist lösche das Bild
        // TODO: 15.08.2017  
        // benötigen die Methoden getID, getX und getY auf den foreignDataTable, @somar wie löscht man ein Bild auf dem Gerät
    }

    /**
     * Diese Methode berechnet die Distanz zwischen den zu Routenden Knoten und den Neighbours des aktuellen Knotens(der routet)
     * @param x Des zu routenden Knoten
     * @param y Des zu routenden Knoten
     * @param neighbourX
     * @param neighbourY
     * @return Die Distanz zwischen den 2 Punkten
     */
    public double computeDistance(double x, double y, double neighbourX, double neighbourY) {
        double dis = Math.abs(x - neighbourX) + Math.abs(y - neighbourY);
        return dis;
    }

    /**
     * Methode mit der ein neuer Knoten seinen Peers seine ID und IP (zweck=zum Eintragen in PeersDB) mitteilen kann
     * @param ip
     */
    private void informPeersAboutYourself(String ip) {
        //// TODO: 14.08.2017    user.getUid(); von DB, user.getIP von DB
        //// TODO: 14.08.2017 sende an alle deine Peers ein setPeer mit diesen Informationen

    }
    /**
     * Vergleiche alle Distanzen der Nachbarn
     * @param distances Array mit allen Distanzen der Neighbour zu dem zu routenden Knoten
     * @return den index(in NeighbourDB) mit der geringsten Distanz
     */
    public int compareValues(double [] distances){
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


    /**
     * Mit dieser Methode findet ein neuer Knoten einen Einstiegspunkt in das CAN, indem er den Bootstrapserver nach einer IP anfragt
     */
    private void requestJoin(){
        //// TODO: 15.08.2017 getBootsTrapIP() Method
        //// TODO: 15.08.2017 nun Verbindung zu dieser IP herstellen und routing-Anfrage mit(eigener IP und x ,y Werten, id und isNode als Parameter)
    }

    /**
     * Methode die aufgerufen wird wenn das routing beendet ist und die DB's des neuen Knoten updaten muss
     * @param ip Des neuen Knotens
     */
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

    // TODO: 15.08.2017 Vielleicht so implementieren das hier auch noch gecheckt wird ob gesplittet wird 
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

    public double getRtt() {
        return rtt;
    }

    public void setRtt(double rtt) {
        this.rtt = rtt;
    }

    public Node getNeighbour() {
        return neighbour;
    }
}
