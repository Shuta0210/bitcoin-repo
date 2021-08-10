package jp.javadrive.bitcoinapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class SprashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sprash);

            final Handler handler = new Handler();
            Timer timer = new Timer(false);
            timer.schedule(new TimerTask() {
                               @Override
                               public void run() {
                                   handler.post(new Runnable() {
                                       @Override
                                       public void run() {
                                           // メイン画面に遷移して、現在のSplashActivityを終了
                                           Intent intent = new Intent(SprashActivity.this,MainActivity.class);
                                           startActivity(intent);//実際の画面遷移を開始
                                           finish();//現在のActivityを削除
                                       }
                                   });
                               }
                           },
                    2000);//2秒後にrun()を行う

    }
}