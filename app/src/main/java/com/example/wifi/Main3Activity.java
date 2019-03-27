package com.example.wifi;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard,"Done.txt");
        String text ="";
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                text+=line+"\n";
                //Log.i("baap",text);

            }

            br.close();
        }
        catch (IOException e) {
            //Log.i("baap","baap");
        }
        TextView textView = (TextView)findViewById(R.id.Display);
        textView.setText(text);
        textView.setMovementMethod(new ScrollingMovementMethod());

    }
}
