package com.diskominfo.ari.gamifikasi_app.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.diskominfo.ari.gamifikasi_app.activity.MapsActivity;

import com.diskominfo.ari.gamifikasi_app.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment{
Button btnmenu1;
    public FragmentHome() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_beranda, container, false);
        btnmenu1 = view.findViewById(R.id.btnmenu1);

        btnmenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });

        return  view;
    }

}