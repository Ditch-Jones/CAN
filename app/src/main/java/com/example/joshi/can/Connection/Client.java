package com.example.joshi.can.Connection;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;


public class Client {

    /**
     * Main Methode zum Testen der einzelnen Methoden
     *
     * Uebergabe-Werte bitte in der sendAll Methode
     *
     * sendAll(String, String, double, double, int)
     */
    protected static final int portNr = 9999;

    public static void main (String args[])throws NoSuchElementException{
        Client client = new Client();

        try{

            client.sendAll("localhost","Routing-Request","192.101.101.1" ,0.31, 0.78, 9);

        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * Hilfsmethode um einen Double Wert in einen String zu casten
     * @param wert = Double Wert
     * @return Double als String
     */
    private String convertDoubleToString(double wert){
        String newString = Double.toString(wert);
        return newString;
    }


    private void sendAll(String ip,String method, String newIP, double x, double y, int schlahmichtot) throws IOException{
        Socket s = new Socket(ip, portNr);

        sendMethod(s, method);
        sendNewIP(s, newIP);
        sendX(s, x);
        sendY(s,y);
        sendInt(s, schlahmichtot);
        s.close();
    }


    public void sendeAlles(String ip,String method, String newIP, double x, double y, int schlahmichtot) throws IOException {
        Client client = new Client();
        client.sendAll(ip,method, newIP,x,y, schlahmichtot);
    }

    private void sendMethod(Socket s, String method) throws UnknownHostException, IOException{

        OutputStreamWriter osw = new OutputStreamWriter(s.getOutputStream());
        PrintWriter out = new PrintWriter(osw);
        out.write(method + ",");
        osw.flush();

    }

    private void sendNewIP(Socket s, String newIP) throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(s.getOutputStream());
        PrintWriter out = new PrintWriter(osw);
        out.write(newIP + "N");
        osw.flush();

    }


    private void sendX(Socket s, double wert) throws UnknownHostException, IOException{

        OutputStreamWriter osw = new OutputStreamWriter(s.getOutputStream());
        PrintWriter out = new PrintWriter(osw);
        String wertAlsString = convertDoubleToString(wert);
        out.write(wertAlsString + "X");
        osw.flush();

    }

    private void sendY(Socket s, double wert) throws UnknownHostException, IOException{

        OutputStreamWriter osw = new OutputStreamWriter(s.getOutputStream());
        PrintWriter out = new PrintWriter(osw);
        String wertAlsString = convertDoubleToString(wert);
        out.write(wertAlsString + "Y");
        osw.flush();

    }

    private void sendInt(Socket s, int wert) throws UnknownHostException, IOException{

        OutputStreamWriter osw = new OutputStreamWriter(s.getOutputStream());
        PrintWriter out = new PrintWriter(osw);
        String wertAlsString = Integer.toString(wert);
        out.write(wertAlsString + "I");
        osw.flush();

    }


    ////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////
    //////////////////////Wird vorerst nicht verwendet!/////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////

    /**
     * Methode zum Senden einer IP-Adresse als String
     *
     * @param ip = IP des ServerSocket
     * @param ipAdr = IP-Adresse als String
     *
     * @throws UnknownHostException
     * @throws IOException
     */

    @SuppressWarnings("unused")
    private void sendIPAddress(String ip, String ipAdr) throws UnknownHostException, IOException{

        Socket s = new Socket(ip, portNr);

        OutputStreamWriter osw = new OutputStreamWriter(s.getOutputStream());
        PrintWriter out = new PrintWriter(osw);
        out.write(ipAdr);
        osw.flush();
        s.close();

    }

    /**
     * Methode zum Senden der X-Koordinate
     * Server ben�tigt aber zuerst Input Parser,
     * um zu erkennen, dass ein Double geschickt wird
     *
     * @param ip = IP-Adresse des Servers
     * @param x  = Wert der X-Koordinate
     * @throws UnknownHostException
     * @throws IOException
     */

    @SuppressWarnings("unused")
    private void sendXCoordinate(String ip, double x) throws UnknownHostException, IOException{

        Socket s = new Socket(ip, portNr);

        OutputStreamWriter osw = new OutputStreamWriter(s.getOutputStream());
        PrintWriter out = new PrintWriter(osw);
        out.write((int) x);
        osw.flush();
        s.close();

    }
}