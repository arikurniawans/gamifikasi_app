package com.diskominfo.ari.gamifikasi_app.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.diskominfo.ari.gamifikasi_app.Kelas.JalurApi;
import com.diskominfo.ari.gamifikasi_app.Kelas.ListPostingModel;
import com.diskominfo.ari.gamifikasi_app.Kelas.SharedVariable;
import com.diskominfo.ari.gamifikasi_app.R;
import com.google.android.gms.location.DetectedActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class DetailSharemeActivity extends AppCompatActivity {
ImageButton imgDetailMe;
TextView txtKategoriDetailme,txtLaporanDetailMe,txtTanggalDetailMe,txtAlamatDetailMe;
ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_shareme);
        final Intent intent = getIntent();
        imgDetailMe = (ImageButton) findViewById(R.id.imgDetailMe);
        txtKategoriDetailme = (TextView) findViewById(R.id.txtKategoriDetailme);
        txtLaporanDetailMe = (TextView) findViewById(R.id.txtLaporanDetailMe);
        txtTanggalDetailMe = (TextView) findViewById(R.id.txtTanggalDetailMe);
        txtAlamatDetailMe = (TextView) findViewById(R.id.txtAlamatDetailMe);
        //Toast.makeText(getApplication(),"Data "+intent.getStringExtra("id_posting"),Toast.LENGTH_LONG).show();
        getData(intent.getStringExtra("id_posting"));
    }


    private void getData(final String Id) {

        loading = ProgressDialog.show(DetailSharemeActivity.this, "Please wait...", "Fetching...", false, false);

        String url = JalurApi.API_DETAIL_POSTING_ME + Id;

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                //Toast.makeText(DetailSharemeActivity.this,response,Toast.LENGTH_LONG).show();
                showdetailMe(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DetailSharemeActivity.this, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(DetailSharemeActivity.this);
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
                txtAlamatDetailMe.setText("Alamat : "+strReturnedAddress.toString());
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

    private void showdetailMe(String response){
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
            JSONArray result = jsonObject.getJSONArray("result");

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String kategori = jo.getString("kategori");
                String id_posting = jo.getString("id_posting");
                String laporan = jo.getString("laporan");
                String foto = jo.getString("foto");
                String lati = jo.getString("lati");
                String longi = jo.getString("longi");
                String tgl_posting = jo.getString("tgl_posting");

                getCompleteAddressString(Double.valueOf(lati),Double.valueOf(longi));

                txtKategoriDetailme.setText("Kategori : "+kategori);
                txtLaporanDetailMe.setText("Laporan : "+laporan);
                txtTanggalDetailMe.setText("Waktu : "+tgl_posting);
                Glide.with(DetailSharemeActivity.this)
                        .load(foto)
                        .into(imgDetailMe);

            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(DetailSharemeActivity.this
                    ,"Data Salah "+e,Toast.LENGTH_LONG).show();
        }

    }


}
