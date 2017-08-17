package com.example.joshi.can.Schnittstelle_GUI;

import com.example.joshi.can.Logic.Node;

/**
 * Created by Besitzer on 16.08.2017.
 */

public class Schnittstelle_GUI {
    Node node = new Node();
    public static void main (String args[])
    {

    }

    /**
     * Der Aufruf um den Platz des Bildes in CAN zu finden für die GUI
     */
    public void placePicInCan_for_GUI(String ip, double x, double y, int fotoid)
    {
        node.placePicInCan(ip,x,y,fotoid);
    }

    /**
     * Der Aufruf für den Verbindungsaufbau bei der GUI
     */
    public void requestJoin_for_GUI()
    {
        node.requestJoin_for_GUI();
    }


}
