package com.project.muslim_app.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.muslim_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MosqueFragment extends Fragment {


    public MosqueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mosque, container, false);
        return v;
    }

}
