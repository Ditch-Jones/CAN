package com.example.joshi.can.Connection;

import com.example.joshi.can.Logic.Node;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.start();

    }

    public void start() {
        try {
            Node node = new Node();
            System.out.println("Server is started");
            ServerSocket ss = new ServerSocket(9999);

            System.out.println("Server is waiting for request");
            Socket s = ss.accept();

            System.out.println("Cliet Connected");

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String str = br.readLine();

            System.out.println("Client Data: " + str);

            ss.close();
            String methodName = returnMethodName(str);
            switch(methodName){
                case "requestJoin": System.out.println("in RequestJoin\n");
                                    break;

                case "routing":     System.out.println("in routing\n");
                                    break;

                case "receiveRoutingRequest": System.out.println("in receiveRoutingRequest\n");
                                    break;
                case "hashX": System.out.println("hashX-Value:" +node.hashX(returnNewIP(str)));

            }

            //println Block zum Testen/Sehen der Ergebnisse
            System.out.println("");
            System.out.println("MethodName :" + returnMethodName(str));
            System.out.println("X-Koordinate: " + returnXCoordinate(str));
            System.out.println("Y-Koordinate: " + returnYCoordinate(str));
            System.out.println(returnInteger(str));
            System.out.println("");
            System.out.println("Beweis doubles werden mit 0,25 addiert");
            System.out.println("X_Neu : " + beweis(returnXCoordinate(str)));
            System.out.println("Y_Neu : " + beweis(returnYCoordinate(str)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Hilfsmethoden, die den uebergebenen String zurecht schneiden
     * <p>
     * der String wurde vorerst durch ',' , 'X' , 'Y' , 'I'
     * <p>
     * an den entscheidenden Stellen unterteilt
     *
     * @param longterm = der gesamte gesendete String
     * @return der jeweiligen Parameter
     */

    private static String returnMethodName(String longterm) {

        int index = longterm.indexOf(',');

        String methodName = longterm.substring(0, index);
        return methodName;
    }

    public static String giveMethodName(String str){
        return returnMethodName(str);
    }

    private static double returnXCoordinate(String longterm) {

        int indexOfKomma = longterm.indexOf('N');
        indexOfKomma += 1;
        int indexOfX = longterm.indexOf('X');


        String x = longterm.substring(indexOfKomma, indexOfX);
        double x1 = Double.parseDouble(x);
        return x1;
    }

    public static double giveXCoord(String str){
        return returnXCoordinate(str);

    }

    private static String returnNewIP(String longterm){
        int indexOfKomma = longterm.indexOf(',');
        indexOfKomma += 1;
        int indexOfNIP = longterm.indexOf('N');

        String nip = longterm.substring(indexOfKomma, indexOfNIP);
        return nip;

    }

    private static double returnYCoordinate(String longterm) {

        int indexOfKomma = longterm.indexOf('X');
        indexOfKomma += 1;
        int indexOfY = longterm.indexOf('Y');


        String y = longterm.substring(indexOfKomma, indexOfY);
        double y1 = Double.parseDouble(y);
        return y1;

    }

    public static double giveYCoord(String str){
        return returnYCoordinate(str);

    }

    private static int returnInteger(String longterm) {

        int indexOfKomma = longterm.indexOf('Y');
        indexOfKomma += 1;
        int indexOfInteger = longterm.indexOf('I');


        String i = longterm.substring(indexOfKomma, indexOfInteger);
        int i1 = Integer.parseInt(i);

        return i1;//("Integer-Wert: " + i1);
    }

    public static int giveInt(String str){
        return returnInteger(str);
    }

    private static double beweis(double wert) {
        wert += 0.25;
        return wert;
    }
}