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
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CurrentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentFragment extends Fragment {

    public View rootView;

    public static CurrentFragment newInstance() {
        CurrentFragment fragment = new CurrentFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_current, container, false);
        final Handler handler = new Handler();

        TextView bpi = (TextView)rootView.findViewById(R.id.BitCoinCurrent);

        new Thread(new Runnable() {
            @Override
            public void run() {

                String str="";
                String status="1";
                String message="";
                String bpiValue="";
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
                            bpiValue=String.valueOf(json.getDouble("mid_price"));
                        }

                        Log.d("status",status);
                        Log.d("message",message);





                        Log.d("bpi",bpiValue);


                        // Handlerを使用してメイン(UI)スレッドに処理を依頼する
                        String finalMessage = message;
                        String finalStatus = status;
                        String finalBpiValue = bpiValue;
                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                if(finalStatus.equals("1")){

                                    bpi.setText("￥"+finalBpiValue);

                                }else{

                                    Optional.ofNullable(getActivity())
                                            .filter(activity -> activity instanceof OnCurrentListener)
                                            .map(activity -> (OnCurrentListener) activity)
                                            .orElseThrow(() -> new IllegalStateException("ActivityにOnListenerを実装してください"))
                                            .onDaialog(finalMessage);
                                }


                            }
                        });


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


            }
        }).start();




        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        Button recordButton = view.findViewById(R.id.Record);
        recordButton.setOnClickListener(v -> {
            FragmentManager fragmentManager = getFragmentManager();

            if (fragmentManager != null) {
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                // BackStackを設定
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.replace(R.id.container,
                        BitCoinRecordFragment.newInstance());
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

    public interface OnCurrentListener {
        void onDaialog(String message);
    }

}