package jp.javadrive.bitcoinapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BtcHowtoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BtcHowtoFragment extends Fragment {

    public BtcHowtoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment BtcHowtoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BtcHowtoFragment newInstance() {
        BtcHowtoFragment fragment = new BtcHowtoFragment();
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
        return inflater.inflate(R.layout.fragment_btc_howto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button buyButton = view.findViewById(R.id.buy);
        buyButton.setOnClickListener(v -> {
            FragmentManager fragmentManager = getFragmentManager();

            if (fragmentManager != null) {
                FragmentTransaction fragmentTransaction =
                        getChildFragmentManager().beginTransaction();
                // BackStackを設定
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.replace(R.id.HouToFragmentContainerView,
                        BuyFragment.newInstance());
                fragmentTransaction.commit();
            }
        });

        Button investmentButton = view.findViewById(R.id.investment);
        investmentButton.setOnClickListener(v -> {
            FragmentManager fragmentManager = getFragmentManager();

            if (fragmentManager != null) {
                FragmentTransaction fragmentTransaction =
                        getChildFragmentManager().beginTransaction();
                // BackStackを設定
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.replace(R.id.HouToFragmentContainerView,
                        InvestmentFragment.newInstance());
                fragmentTransaction.commit();
            }
        });

        Button remittanceButton = view.findViewById(R.id.remittance);
        remittanceButton.setOnClickListener(v -> {
            FragmentManager fragmentManager = getFragmentManager();

            if (fragmentManager != null) {
                FragmentTransaction fragmentTransaction =
                        getChildFragmentManager().beginTransaction();
                // BackStackを設定
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.replace(R.id.HouToFragmentContainerView,
                        RemittanceFragment.newInstance());
                fragmentTransaction.commit();
            }
        });

        Button changeButton = view.findViewById(R.id.change);
        changeButton.setOnClickListener(v -> {
            FragmentManager fragmentManager = getFragmentManager();

            if (fragmentManager != null) {
                FragmentTransaction fragmentTransaction =
                        getChildFragmentManager().beginTransaction();
                // BackStackを設定
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.replace(R.id.HouToFragmentContainerView,
                        ChangeFragment.newInstance());
                fragmentTransaction.commit();
            }
        });

        Button donationButton = view.findViewById(R.id.donation);
        donationButton.setOnClickListener(v -> {
            FragmentManager fragmentManager = getFragmentManager();

            if (fragmentManager != null) {
                FragmentTransaction fragmentTransaction =
                        getChildFragmentManager().beginTransaction();
                // BackStackを設定
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.replace(R.id.HouToFragmentContainerView,
                        DonationFragment.newInstance());
                fragmentTransaction.commit();
            }
        });

        Button pointButton = view.findViewById(R.id.point);
        pointButton.setOnClickListener(v -> {
            FragmentManager fragmentManager = getFragmentManager();

            if (fragmentManager != null) {
                FragmentTransaction fragmentTransaction =
                        getChildFragmentManager().beginTransaction();
                // BackStackを設定
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.replace(R.id.HouToFragmentContainerView,
                        PointFragment.newInstance());
                fragmentTransaction.commit();
            }
        });
    }
}