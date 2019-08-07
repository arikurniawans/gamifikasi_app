package com.diskominfo.ari.gamifikasi_app.fragment;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.diskominfo.ari.gamifikasi_app.Kelas.JalurApi;
import com.diskominfo.ari.gamifikasi_app.Kelas.SharedVariable;
import com.diskominfo.ari.gamifikasi_app.MainActivity;
import com.diskominfo.ari.gamifikasi_app.R;
import com.diskominfo.ari.gamifikasi_app.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProfil extends Fragment {
    ProgressDialog loading;
    TextView txtNama,txtPointShare,txtPointLike,txtBadges;
    RatingBar ratingBar;
    CardView cardBonus;
    Button btnGift;

    public FragmentProfil() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_profil, container, false);
        cardBonus = view.findViewById(R.id.cardBonus);
        txtNama = view.findViewById(R.id.txtNamaProfil);
        txtPointShare = view.findViewById(R.id.txtPointShare);
        txtPointLike = view.findViewById(R.id.txtPointLike);
        txtBadges = view.findViewById(R.id.txtBadges);
        btnGift = view.findViewById(R.id.btnGift);
        ratingBar = view.findViewById(R.id.rt_bar);
        getDataUser(MainActivity.etEmail.getText().toString().trim());
        //Toast.makeText(getActivity(),"Data "+SharedVariable.ID_PENGGUNA,Toast.LENGTH_LONG).show();
        getDataLike(SharedVariable.ID_PENGGUNA);
        getDataShare(SharedVariable.ID_PENGGUNA);
        btnGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View view;    view = LayoutInflater.from(getActivity()).inflate(R.layout.custom_layout, null);
                TextView title = (TextView) view.findViewById(R.id.title);
                ImageButton imageButton = (ImageButton) view.findViewById(R.id.image);
                title.setText("Informasi");
                imageButton.setImageResource(R.drawable.kartu_diskon);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override        public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();        }
                });
                /*builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override        public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Never Mind!", Toast.LENGTH_SHORT).show();        }
                });*/
                builder.setView(view);    builder.show();
            }
        });
        return view;
    }


    private void getDataUser(final String Id) {

        loading = ProgressDialog.show(getActivity(), "Please wait...", "Fetching...", false, false);

        String url = JalurApi.API_DETAIL_USER + Id;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                //Toast.makeText(getActivity(),"Data "+response,Toast.LENGTH_LONG).show();
               showDetailUser(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void showDetailUser(String response){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id_user = jo.getString("id_user");
                String nama = jo.getString("nama");
                txtNama.setText(nama);
            }


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity()
                    ,"Data Salah "+e,Toast.LENGTH_LONG).show();
        }

    }


    private void getDataShare(final String Id_user) {

        //loading = ProgressDialog.show(getActivity(), "Please wait...", "Fetching...", false, false);

        String url = JalurApi.API_JUMLAH_SHARE + Id_user;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJumlahShare(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    private void getDataLike(final String Id_user) {

        //loading = ProgressDialog.show(getActivity(), "Please wait...", "Fetching...", false, false);

        String url = JalurApi.API_JUMLAH_LIKE + Id_user;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJumlahLike(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }


    private void showJumlahShare(String response){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String jumlah_share = jo.getString("jumlah_share");
                txtPointShare.setText(jumlah_share);
            }


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity()
                    ,"Data Salah "+e,Toast.LENGTH_LONG).show();
        }

    }


    private void showJumlahLike(String response){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String jumlah_like = jo.getString("jumlah_like");
                txtPointLike.setText(jumlah_like);
                gamifikasi();
            }


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity()
                    ,"Data Salah "+e,Toast.LENGTH_LONG).show();
        }

    }

    void gamifikasi(){
        Float badges = Float.parseFloat(txtPointShare.getText().toString());
        Float point = Float.parseFloat(txtPointLike.getText().toString());
        Float hasil = (badges+point*5)/100;
        Integer hasil2 = Integer.parseInt(txtPointShare.getText().toString())+Integer.parseInt(txtPointLike.getText().toString());
        ratingBar.setRating(Float.valueOf(hasil));

        if(hasil2 >= 80){
            txtBadges.setText("Freak");
            cardBonus.setVisibility(View.VISIBLE);
        }else if(hasil2 >= 70){
            txtBadges.setText("Geek");
            cardBonus.setVisibility(View.VISIBLE);
        }else if(hasil2 >= 60){
            txtBadges.setText("Addict");
            cardBonus.setVisibility(View.VISIBLE);
        }else if(hasil2 >= 50){
            txtBadges.setText("Newbie");
            cardBonus.setVisibility(View.INVISIBLE);
        }else if(hasil2 < 50){
            txtBadges.setText("Newbie");
            cardBonus.setVisibility(View.INVISIBLE);
        }

    }




}
