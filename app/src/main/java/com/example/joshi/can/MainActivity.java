package com.example.joshi.can;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joshi.can.Connection.Client;
import com.example.joshi.can.Connection.Server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;

public class MainActivity extends AppCompatActivity {
    Button startServer,startClient;
    TextView info,msg;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setContentView(R.layout.activity_main);

        startServer = (Button)findViewById(R.id.Start_Server);
        startClient = (Button)findViewById(R.id.Starte_Client);

        startServer.setOnClickListener(
                new Button.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        Thread socketServerThread = new Thread(new Server());
                        socketServerThread.start();

                    }
                }
        );

        startClient.setOnClickListener(
                new Button.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Thread socketClient = new Thread(new Client());
                        socketClient.start();
                    }
                }

        );

    }


    /*
    @Override
    protected void onStart() {
        super.onStart();
        Server server = new Server();
        server.start();



        Client client = new Client();

        try {
            client.sendeAlles("127.0.0.1","hashX","192.101.101.1",0.3,0.88766,2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        info = (TextView)findViewById(R.id.info);
        info.setText(server.getMethodName());

    }
    */



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}