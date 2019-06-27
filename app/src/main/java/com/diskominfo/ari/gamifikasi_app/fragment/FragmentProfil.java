package com.diskominfo.ari.gamifikasi_app.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.diskominfo.ari.gamifikasi_app.R;
import com.google.firebase.database.DatabaseReference;



/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProfil extends Fragment {


    public FragmentProfil() {
        // Required empty public constructor
    }


    private DatabaseReference database;
    EditText txtUsername, txtNIk,txtEmail;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_profil, container, false);

        return view;
    }




}
