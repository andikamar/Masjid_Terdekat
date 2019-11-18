package net.dika.masjid;

//Created by andika.mar

import android.widget.ProgressBar;
import android.widget.TextView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.content.Intent;
import android.widget.Toast;

import net.dika.masjid.activity.MainActivity;

import androidx.appcompat.app.AppCompatActivity;


public class Splashscreen extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView persentase;
    private int Value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = findViewById(R.id.progress);
        persentase = findViewById(R.id.persentase);
        progressBar.setProgress(0); //Set Progress Dimulai Dari O

        // Handler untuk Updating data pada latar belakang
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                // Menampung semua data yang ingin diproses oleh thread
                persentase.setText(String.valueOf(Value)+"%");
                if(Value == progressBar.getMax()){
                    Toast.makeText(getApplicationContext(), "Selamat Datang", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Splashscreen.this, MainActivity.class));
                    finish();
                }
                Value++;
            }
        };

        // Thread untuk updating progress pada ProgressBar di Latar Belakang
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    for(int w=0; w<=progressBar.getMax(); w++){
                        progressBar.setProgress(w); // Memasukan Value pada ProgressBar
                        // Mengirim pesan dari handler, untuk diproses didalam thread
                        handler.sendMessage(handler.obtainMessage());
                        Thread.sleep(100); // Waktu Pending 100ms/0.1 detik
                    }
                }catch(InterruptedException ex){
                    ex.printStackTrace();
                }
            }
        });
        thread.start();
    }
}