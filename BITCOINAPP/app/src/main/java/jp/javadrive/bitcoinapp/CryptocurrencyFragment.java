package jp.javadrive.bitcoinapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CryptocurrencyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CryptocurrencyFragment extends Fragment {


    public CryptocurrencyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CryptocurrencyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CryptocurrencyFragment newInstance() {
        CryptocurrencyFragment fragment = new CryptocurrencyFragment();
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
        return inflater.inflate(R.layout.fragment_cryptocurrency, container, false);
    }
}