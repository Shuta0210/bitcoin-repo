package jp.javadrive.bitcoinapp;

import android.app.Activity;
import android.app.Notification;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class APIinsert {

    public void insert(Activity act) {

        BitCoinOpenHelper helper = new BitCoinOpenHelper(act);
        SQLiteDatabase db = helper.getReadableDatabase();
        String str="";

        try {

            // ビットコインデータ
            URL url = new URL("https://api.coindesk.com/v1/bpi/historical/close.json");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            str = InputStreamToString(con.getInputStream());
            Log.d("HTTP", str);

        }catch (Exception ex) {

                System.out.println(ex);
                System.out.println("エラー発生");
            }

        try {

            JSONObject json = new JSONObject(str);
            JSONObject bpiJson=json.getJSONObject("bpi");
            System.out.println("api確認"+bpiJson.length());






            try{
                //日付を取得
                Date day = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(day);
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                day = calendar.getTime();
                SimpleDateFormat dayfmt = new SimpleDateFormat("yyyy-MM-dd");
                String date=dayfmt.format(day);

                if (!bpiJson.isNull(date)){

                    db.execSQL("delete from BitCoin");
                }

                System.out.println("ここまで");
                double bpi=bpiJson.getDouble(date);


                ContentValues insertValues = new ContentValues();
                insertValues.put("time",date);
                insertValues.put("bpi",bpi);
                //dbにinsert
                long id = db.insert("BitCoin", date, insertValues);

                calendar.add(Calendar.DAY_OF_MONTH, -31);
                day = calendar.getTime();
                dayfmt = new SimpleDateFormat("yyyy-MM-dd");
                date=dayfmt.format(day);



            }catch (JSONException e){
                System.out.println("昨日のデータがインサートできませんでした");

            }

            for(int i=-31;i<-1;i++){

                //日付を取得
                Date day = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(day);
                calendar.add(Calendar.DAY_OF_MONTH, i);
                day = calendar.getTime();
                SimpleDateFormat dayfmt = new SimpleDateFormat("yyyy-MM-dd");
                String date=dayfmt.format(day);

                System.out.println(date);
                double bpi=bpiJson.getDouble(date);
                ContentValues insertValues = new ContentValues();
                insertValues.put("time",date);
                insertValues.put("bpi",bpi);
                //dbにinsert
                long id = db.insert("BitCoin", date, insertValues);
            }



            } catch (
                    JSONException e) {
            e.printStackTrace();
        }


    }

    // InputStream -> String
    static String InputStreamToString(InputStream is) throws IOException {
        BufferedReader bufReader = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = bufReader.readLine()) != null) {
            response.append(line);
        }
        bufReader.close();


        return response.toString();
    }


}
