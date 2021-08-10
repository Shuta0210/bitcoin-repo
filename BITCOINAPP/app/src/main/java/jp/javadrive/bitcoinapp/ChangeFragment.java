package jp.javadrive.bitcoinapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChangeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangeFragment extends Fragment {

    public ChangeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ChangeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChangeFragment newInstance() {
        ChangeFragment fragment = new ChangeFragment();
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
        return inflater.inflate(R.layout.fragment_change, container, false);
    }
}