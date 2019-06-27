package com.diskominfo.ari.gamifikasi_app.fragment;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diskominfo.ari.gamifikasi_app.R;
import com.google.firebase.database.DatabaseReference;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPosting extends Fragment {


    public FragmentPosting() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    //AdapterKerabat adapterKerabat;
   // private List<Kerabat> listKerabat;
    FloatingActionButton btnCreate;
    private DatabaseReference database;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_posting, container, false);
        /*Firebase.setAndroidContext(getActivity());
        FirebaseApp.initializeApp(getContext());


        database = FirebaseDatabase.getInstance().getReference("tb_kerabat");
        recyclerView = view.findViewById(R.id.rvFeed);
        btnCreate = view.findViewById(R.id.btnCreate);

        listKerabat = new ArrayList<>();
        adapterKerabat = new AdapterKerabat(getActivity(),listKerabat);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterKerabat);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),TambahKerabatActivity.class);
                startActivity(i);
            }
        });

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                listKerabat.clear();
                adapterKerabat.notifyDataSetChanged();

                for (DataSnapshot child : dataSnapshot.getChildren()){
                    String nama_kerabat = child.child("nama").getValue().toString();
                    String telp_kerabat = child.child("nope").getValue().toString();

                    Kerabat kerabatModel = new Kerabat(
                            nama_kerabat,
                            telp_kerabat
                    );
                    listKerabat.add(kerabatModel);
                    adapterKerabat.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Aku ",databaseError.getMessage());

            }
        });
        */

        return view;
    }




}
