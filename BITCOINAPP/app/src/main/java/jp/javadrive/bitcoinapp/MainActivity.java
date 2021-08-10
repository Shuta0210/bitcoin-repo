package jp.javadrive.bitcoinapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements CurrentFragment.OnCurrentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    @Override
    public void onDaialog(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("時間を開けて再度、取得してください")
                .setTitle(message)
                .setPositiveButton("閉じる", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();

                    }
                });
        builder.show();
    }
}