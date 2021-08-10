package jp.javadrive.bitcoinapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InvestmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InvestmentFragment extends Fragment {

    public InvestmentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InvestmentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InvestmentFragment newInstance() {
        InvestmentFragment fragment = new InvestmentFragment();
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
        return inflater.inflate(R.layout.fragment_investment, container, false);
    }
}