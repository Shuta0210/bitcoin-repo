package jp.javadrive.bitcoinapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BitCoinMonthFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class BitCoinMonthFragment extends Fragment {

    public View rootView;

    static BitCoinMonthFragment newInstance() {
        // インスタンス生成
       BitCoinMonthFragment fragment = new BitCoinMonthFragment();

        return fragment;
    }

    // FragmentのViewを生成して返す
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        rootView= inflater.inflate(R.layout.fragment_bit_coin_month,
                container, false);

        //グラフをセット
        com.github.mikephil.charting.charts.LineChart lineChart = (LineChart)rootView.findViewById(R.id.chart);
        // メイン(UI)スレッドでHandlerのインスタンスを生成する

        final Handler handler = new Handler();
            //日付を取得
        Date day = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date day1 = calendar.getTime();
        SimpleDateFormat daft = new SimpleDateFormat("yyyy-MM-dd");
        String date=daft.format(day1);


        new Thread(new Runnable() {
            @Override
            public void run() {


                    BitCoinOpenHelper helper = new BitCoinOpenHelper(getActivity());
                    SQLiteDatabase db = helper.getReadableDatabase();

                    Cursor c= db.query("BitCoin", new String[]{"time","bpi"}, "time=?", new String[]{date}, null, null, null);
                    System.out.println("カーソル内確認"+c.getCount());
                    System.out.println("昨日の日付"+date);
                    String str="";
                    String status="1";
                    String message="";
                    ArrayList<BitCoin> list = new ArrayList<>();

                try {
                    if(c.getCount()==0){

                      APIinsert insert=new APIinsert();
                      insert.insert(getActivity());

                    }



                    for (int i=-31;i<-1;i++){

                        Date month=new Date();
                        Calendar calMon = Calendar.getInstance();
                        calMon.setTime(month);
                        calMon.add(Calendar.DAY_OF_MONTH, i);

                        month = calMon.getTime();
                        SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
                        String strMon=ff.format(month);

                        System.out.println("一ヶ月"+strMon);

                        Cursor c2= db.query("BitCoin", new String[]{"time","bpi"}, "time=?",new String[]{strMon}, null, null, null);

                        System.out.println(c2.getCount());
                        c2.moveToFirst();

                        System.out.println(c2.getString(0));
                        System.out.println(c2.getDouble(1));
                        BitCoin bitCoin=new BitCoin();
                        bitCoin.setTime(c2.getString(c2.getColumnIndex("time")));
                        bitCoin.setBpi(c2.getFloat(c2.getColumnIndex("bpi")));
                        list.add(bitCoin);

                    }


                } catch (Exception ex) {
                    System.out.println(ex);
                    System.out.println("過去のデータの取得失敗");

                    status="0";
                    message="API取得に失敗しました";

                }

                    Cursor cc= db.query("BitCoin", new String[]{"time","bpi"}, "time=?", new String[]{date}, null, null, null);
                    if (cc.getCount()==1){

                        cc.moveToFirst();
                        BitCoin bitCoin=new BitCoin();
                        bitCoin.setTime(cc.getString(cc.getColumnIndex("time")));
                        bitCoin.setBpi(cc.getFloat(cc.getColumnIndex("bpi")));
                        list.add(bitCoin);

                        System.out.println(cc.getString(0));
                        System.out.println(cc.getDouble(1));
                    }

                        // Handlerを使用してメイン(UI)スレッドに処理を依頼する
                        String finalStatus = status;
                        String finalMessage = message;

                        handler.post(new Runnable() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void run() {

                                ArrayList<Entry> entries = new ArrayList<Entry>();
                                String[] labels = new String[31];

                                float minValue=50000f;
                                if(finalStatus.equals("1")){

                                    //resultをentryにセット
                                    for (int i = 0; i < list.size(); i++) {

                                        System.out.println("確認サイズ"+list.size());

                                        if(minValue>list.get(i).getBpi()){
                                            minValue=list.get(i).getBpi();
                                        }
                                        entries.add(new Entry(list.get(i).getBpi(), i));
                                        labels[i] = list.get(i).getTime();
                                    }
                                    //データをセット
                                    LineDataSet dataSet = new LineDataSet(entries, "BTCあたりのドル");
                                    dataSet.setColor(Color.BLUE);
                                    dataSet.setLineWidth(2.5f);
                                    dataSet.setCircleColor(Color.RED);



                                    minValue-=500f;
                                    lineChart.getAxisLeft().setStartAtZero(false);
                                    lineChart.getAxisLeft().setAxisMinValue(minValue);

                                    //lineChart.getAxisRight().setStartAtZero(false);
                                    //lineChart.getAxisRight().setAxisMinValue(minValue);
                                     lineChart.getAxisRight().setEnabled(false);


                                    //LineDataインスタンス生成
                                    LineData data = new LineData(labels, dataSet);

                                    //LineDataをLineChartにセット
                                    lineChart.setData(data);

                                    //説明分
                                    lineChart.setDescription("1ヶ月の値動き");

                                    lineChart.setDescriptionTextSize(15f);

                                    //背景色
                                    lineChart.setBackgroundColor(Color.WHITE);

                                    //アニメーション
                                    lineChart.animateX(1200);

                                }else {

                                    Optional.ofNullable(getActivity())
                                            .filter(activity -> activity instanceof CurrentFragment.OnCurrentListener)
                                            .map(activity -> (CurrentFragment.OnCurrentListener) activity)
                                            .orElseThrow(() -> new IllegalStateException("ActivityにOnListenerを実装してください"))
                                            .onDaialog(finalMessage);
                                }

                            }
                        });


            }
        }).start();

        return  rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button buttonWeek = rootView.findViewById(R.id.BitCoinWeekButton);
        buttonWeek.setOnClickListener(v -> {
            FragmentManager fragmentManager = getFragmentManager();

            if (fragmentManager != null) {
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                // BackStackを設定
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.replace(R.id.container,
                        BitCoinWeekFragment.newInstance());
                fragmentTransaction.commit();
            }
    });


    }
}