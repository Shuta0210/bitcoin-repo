package jp.javadrive.bitcoinapp;

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
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BitCoinRecordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BitCoinRecordFragment extends Fragment {

    public View rootView;

    public static BitCoinRecordFragment newInstance() {
        BitCoinRecordFragment fragment = new BitCoinRecordFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_bit_coin_record, container, false);

        final Handler handler = new Handler();
        RecordAdapter bidsAdapter=new RecordAdapter(getActivity());
        RecordAdapter asksAdapter=new RecordAdapter(getActivity());
        ListView bidsView=(ListView)rootView.findViewById(R.id.bidsListView);
        ListView askView=(ListView)rootView.findViewById(R.id.askListView);
        bidsView.setAdapter(bidsAdapter);
        askView.setAdapter(asksAdapter);

        new Thread(new Runnable() {
            @Override
            public void run() {

                String str="";
                String status="1";
                String message="";
                JSONArray bidsArray=new JSONArray();
                JSONArray askArray=new JSONArray();
                try {

                    // サーバへアクセス
                    URL url = new URL("https://api.bitflyer.com/v1/getboard");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    str = InputStreamToString(con.getInputStream());
                    Log.d("HTTP", str);

                } catch (Exception ex) {
                    System.out.println(ex);
                    System.out.println("API取得失敗");
                    status="0";
                    message="API取得に失敗しました";
                }
                    try {

                        if(status.equals("1")){
                            JSONObject json = new JSONObject(str);
                            Log.v("確認",json.toString());





                            bidsArray=json.getJSONArray("bids");
                            askArray=json.getJSONArray("asks");

                            double s=bidsArray.getJSONObject(0).getDouble("price");
                            Log.v("確認",String.valueOf(s));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println(e);
                        System.out.println("API読み込み失敗");
                        status="0";
                        message="API取得に失敗しました";
                    }



                        // Handlerを使用してメイン(UI)スレッドに処理を依頼する
                        String finalMessage = message;
                        String finalStatus = status;
                        JSONArray finalBidsArray = bidsArray;
                        JSONArray finalAskArray = askArray;
                        handler.post(new Runnable() {
                            @RequiresApi(api = Build.VERSION_CODES.N)
                            @Override
                            public void run() {

                                if(finalStatus.equals("1")){
                                    //サーバ側からの情報をセットする
                                    for (int i = 0; i< 7; i++){

                                        try {
                                            float bids=(float)finalBidsArray.getJSONObject(i).getDouble("price");
                                            double bidsSize=(double)finalBidsArray.getJSONObject(i).getDouble("size");
                                            float ask=(float)finalAskArray.getJSONObject(i).getDouble("price");
                                            double askSize=(double)finalAskArray.getJSONObject(i).getDouble("size");

                                            bidsAdapter.add(bids,bidsSize);
                                            asksAdapter.add(ask,askSize);



                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            Optional.ofNullable(getActivity())
                                                    .filter(activity -> activity instanceof CurrentFragment.OnCurrentListener)
                                                    .map(activity -> (CurrentFragment.OnCurrentListener) activity)
                                                    .orElseThrow(() -> new IllegalStateException("ActivityにOnListenerを実装してください"))
                                                    .onDaialog(finalMessage);
                                        }
                                    }



                                }else{

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




        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button buttonRecord = view.findViewById(R.id.Current);
        buttonRecord.setOnClickListener(v -> {
            FragmentManager fragmentManager = getFragmentManager();

            if (fragmentManager != null) {
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                // BackStackを設定
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.replace(R.id.container,
                        CurrentFragment.newInstance());
                fragmentTransaction.commit();
            }
        });
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