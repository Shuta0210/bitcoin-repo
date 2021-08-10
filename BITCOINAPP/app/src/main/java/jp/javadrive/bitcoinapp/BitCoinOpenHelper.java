package jp.javadrive.bitcoinapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BitCoinOpenHelper extends SQLiteOpenHelper {

    public BitCoinOpenHelper(Context context) {
        super(context, "BitCoinDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE BitCoin(" +
                " time text not null," +
                "bpi REAL);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
