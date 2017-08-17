package com.example.joshi.can.Connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

<<<<<<< HEAD
public class Server extends Thread{
    String str;
    public static void main (String [] args) throws Exception{
        Server server = new Server();
        server.run();
    }
    @Override
    public void run(){
=======
public class Server {


    public static void main (String [] args) throws Exception{
        Server server = new Server();
        server.start();

    }

    public void start() {
>>>>>>> 521357607f2116657b79405073a6f04716439b6a
        try{
            System.out.println("Server is started");
            ServerSocket ss = new ServerSocket(9999);

            System.out.println("Server is waiting for request");
            Socket s = ss.accept();

            System.out.println("Cliet Connected");

            BufferedReader br = new BufferedReader (new InputStreamReader(s.getInputStream()));
            str = br.readLine();

            System.out.println("Client Data: " + str);

            ss.close();

            //println Block zum Testen/Sehen der Ergebnisse
            System.out.println("");
            System.out.println(returnMethodName(str));
            System.out.println("X-Koordinate: " + returnXCoordinate(str));
            System.out.println("Y-Koordinate: " + returnYCoordinate(str));
            System.out.println(returnInteger(str));
            System.out.println("");
            System.out.println("Beweis (doubles werden mit 0,25 addiert");
            System.out.println("X_Neu : " + beweis(returnXCoordinate(str)));
            System.out.println("Y_Neu : " + beweis(returnYCoordinate(str)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
<<<<<<< HEAD

    public String getStr(){
        return str;
=======

    /**
     * Hilfsmethoden, die den uebergebenen String zurecht schneiden
     *
     * der String wurde vorerst durch ',' , 'X' , 'Y' , 'I'
     *
     * an den entscheidenden Stellen unterteilt
     *
     * @param longterm = der gesamte gesendete String
     * @return der jeweiligen Parameter
     */

    private static String returnMethodName(String longterm){

        int index = longterm.indexOf(',');

        String methodName = longterm.substring(0,index);
        return ("MethodName: " + methodName);
    }

    private static double returnXCoordinate(String longterm){

        int indexOfKomma 	 = longterm.indexOf(',');
        indexOfKomma 	+= 1;
        int indexOfX 		 = longterm.indexOf('X');


        String x = longterm.substring(indexOfKomma, indexOfX);
        double x1 = Double.parseDouble(x);
        return x1;
    }

    private static double returnYCoordinate(String longterm){

        int indexOfKomma 	 = longterm.indexOf('X');
        indexOfKomma 	+= 1;
        int indexOfY 		 = longterm.indexOf('Y');


        String y = longterm.substring(indexOfKomma, indexOfY);
        double y1 = Double.parseDouble(y);
        return y1;

    }

    private static String returnInteger(String longterm){

        int indexOfKomma 	 = longterm.indexOf('Y');
        indexOfKomma 	+= 1;
        int indexOfInteger   = longterm.indexOf('I');


        String i = longterm.substring(indexOfKomma, indexOfInteger);
        int i1   = Integer.parseInt(i);

        return ("Integer-Wert: " + i1);
    }

    private static double beweis (double wert){
        wert += 0.25;
        return wert;
>>>>>>> 521357607f2116657b79405073a6f04716439b6a
    }
}
