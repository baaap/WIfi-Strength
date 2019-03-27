package com.example.wifi;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class Main2Activity extends AppCompatActivity {

    public void displayText(View v)
    {
        Intent myIntent = new Intent(this, Main3Activity.class);
        startActivity(myIntent);
    }

    public void save(View v) throws IOException {
        new CountDownTimer(10000, 1000) {
            String saved = "";
            TextView txt = (TextView) findViewById(R.id.textView);
//            File directory = new File(Environment.getExternalStorageDirectory() + java.io.File.separator +"Wifi");
            File file = new File(Environment.getExternalStorageDirectory() + java.io.File.separator + "Done.txt");
            Date currentTime;



            public void onTick(long millisUntilFinished) {
                currentTime = Calendar.getInstance().getTime();
                saved += currentTime+"\n"+ wifi_Info()+"\n\n";

                //mTextField.setText("seconds remaining: " + millisUntilFinished / 1000);
                //Log.i("ccc0",Long.toString(millisUntilFinished/1000));
            }

            public void onFinish(){
                //txt.setText("done!");
                if(!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (Exception e) {
                        Toast.makeText(Main2Activity.this,"Nai hua",Toast.LENGTH_SHORT).show();
                    }
                }

                try {
                    OutputStreamWriter file_writer = new OutputStreamWriter(new FileOutputStream(file,true));
                    BufferedWriter buffered_writer = new BufferedWriter(file_writer);
                    buffered_writer.write("############\n"+currentTime+"\n"+saved+"\n");
                    buffered_writer.close();
                    Toast.makeText(Main2Activity.this , "Succesful",Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                }
        }.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String output = wifi_Info();

        new CountDownTimer(30000, 1000) {
            TextView txt = (TextView)findViewById(R.id.textView);
            public void onTick(long millisUntilFinished) {
                txt.setText(wifi_Info());
            }

            public void onFinish() {
                txt.setText("done!");
            }
        }.start();
    }

    public String wifi_Info(){
        WifiManager wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        int ip = wifiInfo.getIpAddress();
        String macAdd = wifiInfo.getMacAddress();
        String bssid =wifiInfo.getBSSID();
        int rssi = wifiInfo.getRssi();
        int linkSpeed = wifiInfo.getLinkSpeed();
        String ssid = wifiInfo.getSSID();
        int network_ID = wifiInfo.getNetworkId();
        String ipAdd = Formatter.formatIpAddress(ip);
        int strength = wifiManager.calculateSignalLevel(rssi , 5);

        String output =
                "\n SSID : " +ssid+
                "\n Ip Address : " +ipAdd+
                        "\n Mac Address : " +macAdd+
                        "\n BSSID : " +bssid+
                        "\n RSSI : " +rssi+
                        "\n Link Speed : " +linkSpeed+
                        "\n Strength : " +strength+"/5"+
                        "\n Network Id : " +network_ID;
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        if(strength == 1)
            imageView.setImageResource(R.drawable.one);
        else if(strength == 2)
            imageView.setImageResource(R.drawable.two);
        else if(strength == 3)
            imageView.setImageResource(R.drawable.three);
        else if(strength == 4)
            imageView.setImageResource(R.drawable.four);
        else if(strength == 5)
            imageView.setImageResource(R.drawable.five);
        else if(strength == 0)
            imageView.setImageResource(R.drawable.zero);


        return output;
    }


}
