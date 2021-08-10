package jp.javadrive.bitcoinapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OutlineFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OutlineFragment extends Fragment {
    public OutlineFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment OutlineFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OutlineFragment newInstance() {
        OutlineFragment fragment = new OutlineFragment();
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
        FragmentManager fragmentManager = getFragmentManager();

        return inflater.inflate(R.layout.fragment_outline, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button firstButton= view.findViewById(R.id.first);
        firstButton.setOnClickListener(v -> {
            FragmentManager fragmentManager = getFragmentManager();

            if (fragmentManager != null) {
                FragmentTransaction fragmentTransaction =
                        getChildFragmentManager().beginTransaction();
                // BackStackを設定
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.replace(R.id.fragmentContainerView,
                        OutlineFirstFragment.newInstance());
                fragmentTransaction.commit();
            }
        });

        Button constructionButton= view.findViewById(R.id.construction);
        constructionButton.setOnClickListener(v -> {

            FragmentManager fragmentManager = getFragmentManager();

            if (fragmentManager != null) {
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                // BackStackを設定
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.replace(R.id.fragmentContainerView,
                        ConstructionFragment.newInstance());
                fragmentTransaction.commit();
            }
        });
        Button settlementButton= view.findViewById(R.id.settlement);
        settlementButton.setOnClickListener(v -> {

            FragmentManager fragmentManager = getFragmentManager();

            if (fragmentManager != null) {
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                // BackStackを設定
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.replace(R.id.fragmentContainerView,
                        SettlementFragment.newInstance());
                fragmentTransaction.commit();
            }
        });

        Button miningButton= view.findViewById(R.id.mining);
        miningButton.setOnClickListener(v -> {

            FragmentManager fragmentManager = getFragmentManager();

            if (fragmentManager != null) {
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                // BackStackを設定
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.replace(R.id.fragmentContainerView,
                        MiningFragment.newInstance());
                fragmentTransaction.commit();
            }
        });

        Button blockButton= view.findViewById(R.id.blockchain);
        blockButton.setOnClickListener(v -> {

            FragmentManager fragmentManager = getFragmentManager();

            if (fragmentManager != null) {
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                // BackStackを設定
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.replace(R.id.fragmentContainerView,
                        BlockchainFragment.newInstance());
                fragmentTransaction.commit();
            }
        });

        Button virtialButton= view.findViewById(R.id.vartial);
        virtialButton.setOnClickListener(v -> {

            FragmentManager fragmentManager = getFragmentManager();

            if (fragmentManager != null) {
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                // BackStackを設定
                fragmentTransaction.addToBackStack(null);

                fragmentTransaction.replace(R.id.fragmentContainerView,
                       VartialFragment.newInstance());
                fragmentTransaction.commit();
            }
        });
    }
}