package jp.javadrive.bitcoinapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BitCoinMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BitCoinMenuFragment extends Fragment {

    static BitCoinMenuFragment newInstance() {
        // インスタンス生成
        BitCoinMenuFragment mainFragment = new BitCoinMenuFragment();


        return mainFragment;
    }

    // FragmentのViewを生成して返す
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {




        return inflater.inflate(R.layout.fragment_bit_coin_menu,
                container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button buttonDay = view.findViewById(R.id.BitCoinCurrentButton);
        buttonDay.setOnClickListener(v -> {

                buttonDay.setEnabled(false);
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

        Button buttonWeek = view.findViewById(R.id.BitCoinMonthButton);
        buttonWeek.setOnClickListener(v -> {
            FragmentManager fragmentManager = getFragmentManager();

            if (fragmentManager != null) {
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                // BackStackを設定
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.replace(R.id.container,
                        BitCoinMonthFragment.newInstance());
                fragmentTransaction.commit();
            }
        });

        Button buttonOutline = view.findViewById(R.id.OutlineButton1);
        buttonOutline.setOnClickListener(v -> {
            FragmentManager fragmentManager = getFragmentManager();

            if (fragmentManager != null) {
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                // BackStackを設定
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.replace(R.id.container,
                       OutlineFragment.newInstance());
                fragmentTransaction.commit();
            }
        });
        Button buttonExchage = view.findViewById(R.id.exchange);
        buttonExchage.setOnClickListener(v -> {
            FragmentManager fragmentManager = getFragmentManager();

            if (fragmentManager != null) {
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                // BackStackを設定
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.replace(R.id.container,
                        ExchageFragment.newInstance());
                fragmentTransaction.commit();
            }
        });
        Button buttonHouTo = view.findViewById(R.id.BtcHowto);
        buttonHouTo.setOnClickListener(v -> {
            FragmentManager fragmentManager = getFragmentManager();

            if (fragmentManager != null) {
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                // BackStackを設定
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.replace(R.id.container,
                        BtcHowtoFragment.newInstance());
                fragmentTransaction.commit();
            }
        });
        Button Cryptocurrencybutton = view.findViewById(R.id.Cryptocurrency);
        Cryptocurrencybutton.setOnClickListener(v -> {
            FragmentManager fragmentManager = getFragmentManager();

            if (fragmentManager != null) {
                FragmentTransaction fragmentTransaction =
                        fragmentManager.beginTransaction();
                // BackStackを設定
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.replace(R.id.container,
                        CryptocurrencyFragment.newInstance());
                fragmentTransaction.commit();
            }
        });


        //ボタン
        Runnable updateText = new Runnable() {
            public void run() {
                buttonDay.setEnabled(true);
            }
        };
        new Handler().postDelayed(updateText,5000);
    }
}