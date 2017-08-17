package com.example.joshi.can.Connection;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;


public class Client extends Thread {

    /**
     * Main Methode zum Testen der einzelnen Methoden
     *
     * Uebergabe-Werte bitte hier in Konstruktoren geben
     */

    protected static final int portNr = 9999;

    public static void main (String args[])throws NoSuchElementException{
       Client client = new Client();
        client.run();
    }

    public void run(){
        Client client = new Client();

        try{
            client.sendIPAddress("10.234.201.158", "Dooooo Hont!");
            // client.sendXCoordinate("192.168.2.110", 0.213);

        }catch (UnknownHostException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * Methode zum Senden einer IP-Adresse als String
     *
     * @param ip = IP des ServerSocket
     * @param ipAdr = IP-Adresse als String
     *
     * @throws UnknownHostException
     * @throws IOException
     */

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

    private void sendXCoordinate(String ip, double x) throws UnknownHostException, IOException{

        Socket s = new Socket(ip, portNr);

        OutputStreamWriter osw = new OutputStreamWriter(s.getOutputStream());
        PrintWriter out = new PrintWriter(osw);
        out.write((int) x);
        osw.flush();
        s.close();

    }

    public void sendString(String ip, String ipAdr) throws IOException {
        sendIPAddress(ip,ipAdr);
    }
}
