package com.diskominfo.ari.gamifikasi_app.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentProfil extends Fragment {
    ProgressDialog loading;
    TextView txtNama,txtPointShare,txtPointLike;

    public FragmentProfil() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_profil, container, false);
        txtNama = view.findViewById(R.id.txtNamaProfil);
        txtPointShare = view.findViewById(R.id.txtPointShare);
        txtPointLike = view.findViewById(R.id.txtPointLike);
        //getDataUser(MainActivity.etEmail.getText().toString().trim());
        //Toast.makeText(getActivity(),"Data "+SharedVariable.ID_PENGGUNA,Toast.LENGTH_LONG).show();
        getDataLike(SharedVariable.ID_PENGGUNA);
        getDataShare(SharedVariable.ID_PENGGUNA);
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

        loading = ProgressDialog.show(getActivity(), "Please wait...", "Fetching...", false, false);

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

        loading = ProgressDialog.show(getActivity(), "Please wait...", "Fetching...", false, false);

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
            }


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity()
                    ,"Data Salah "+e,Toast.LENGTH_LONG).show();
        }

    }




}
