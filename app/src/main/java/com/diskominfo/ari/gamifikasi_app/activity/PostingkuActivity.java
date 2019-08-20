package com.diskominfo.ari.gamifikasi_app.activity;

import android.app.ProgressDialog;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.diskominfo.ari.gamifikasi_app.Kelas.JalurApi;
import com.diskominfo.ari.gamifikasi_app.Kelas.ListPostingModel;
import com.diskominfo.ari.gamifikasi_app.Kelas.SharedVariable;
import com.diskominfo.ari.gamifikasi_app.R;
import com.diskominfo.ari.gamifikasi_app.adapter.AdapterPosting;
import com.diskominfo.ari.gamifikasi_app.adapter.AdapterPostingme;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class PostingkuActivity extends AppCompatActivity {
    ProgressDialog loading;
    private RecyclerView recyclerView;
    AdapterPostingme adapterPosting;
    List<ListPostingModel> postingModelList;
    String JSON_STRING;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postingku);
        postingModelList = new ArrayList<ListPostingModel>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_viewPostingMe);
        adapterPosting = new AdapterPostingme(PostingkuActivity.this,postingModelList);

        recyclerView.setLayoutManager(new LinearLayoutManager(PostingkuActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterPosting);
        getData(SharedVariable.ID_PENGGUNA);
    }


    private void getData(final String Id) {

        loading = ProgressDialog.show(PostingkuActivity.this, "Please wait...", "Fetching...", false, false);

        String url = JalurApi.API_LIST_ME + Id;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                //Toast.makeText(PostingkuActivity.this,response,Toast.LENGTH_LONG).show();
                showMeposting(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PostingkuActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(PostingkuActivity.this);
        requestQueue.add(stringRequest);
    }


    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
                //Toast.makeText(getApplication(),"Data Alamat "+strReturnedAddress.toString(),Toast.LENGTH_LONG).show();
                SharedVariable.ALAMAT = strReturnedAddress.toString();
                //Log.w("My Current loction address", strReturnedAddress.toString());
            } else {
                //Log.w("My Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            //Log.w("My Current loction address", "Canont get Address!");
        }
        return strAdd;
    }


    private void showMeposting(String response){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        postingModelList.clear();
        adapterPosting.notifyDataSetChanged();
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String nama = jo.getString("nama");
                String kategori = jo.getString("kategori");
                String id_posting = jo.getString("id_posting");
                String foto = jo.getString("foto");
                String lati = jo.getString("lati");
                String longi = jo.getString("longi");
                String tgl_posting = jo.getString("tgl_posting");
                String status = jo.getString("status");

                getCompleteAddressString(Double.valueOf(lati),Double.valueOf(longi));

                ListPostingModel posting = new ListPostingModel(
                        foto,
                        kategori,
                        SharedVariable.ALAMAT,
                        id_posting,
                        "Nama Pelapor : "+nama,
                        tgl_posting,
                        status

                );
                postingModelList.add(posting);
                adapterPosting.notifyDataSetChanged();
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(PostingkuActivity.this
                    ,"Data Salah "+e,Toast.LENGTH_LONG).show();
        }

    }

}
