package com.diskominfo.ari.gamifikasi_app.fragment;


import android.app.ProgressDialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.diskominfo.ari.gamifikasi_app.Kelas.JalurApi;
import com.diskominfo.ari.gamifikasi_app.Kelas.ListPostingModel;
import com.diskominfo.ari.gamifikasi_app.Kelas.SharedVariable;
import com.diskominfo.ari.gamifikasi_app.R;
import com.diskominfo.ari.gamifikasi_app.RequestHandler;
import com.diskominfo.ari.gamifikasi_app.adapter.AdapterPosting;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentPosting extends Fragment {
private RecyclerView recyclerView;
AdapterPosting adapterPosting;
List<ListPostingModel> postingModelList;
String JSON_STRING;
ProgressDialog loading;
    public FragmentPosting() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_posting, container, false);
        postingModelList = new ArrayList<ListPostingModel>();
        recyclerView = view.findViewById(R.id.recycler_viewPosting);
        adapterPosting = new AdapterPosting(getActivity(),postingModelList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterPosting);
        getJSON();
        return view;
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(getActivity(),"Mengambil Data","Mohon Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showALlposting(s);
                //Toast.makeText(getActivity(),"Data "+s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(JalurApi.API_LIST_ALL);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }


    private void showALlposting(String response){
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

                ListPostingModel posting = new ListPostingModel(
                        foto,
                        kategori,
                        "Jl. ZA. Pagar Alam No.26, Labuhan Ratu, Kec. Kedaton, Kota Bandar Lampung, Lampung 35142",
                        id_posting,
                        "Nama Pelapor : "+nama,
                        tgl_posting

                );
                postingModelList.add(posting);
                adapterPosting.notifyDataSetChanged();
            }

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getActivity()
                    ,"Data Salah "+e,Toast.LENGTH_LONG).show();
        }

    }


    /*public static void getAddressFromLocation(
            final , final Context context, final Handler handler) {
        Thread thread = new Thread() {
            @Override public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                String result = null;
                try {
                    List<Address> list = geocoder.getFromLocation(
                            location.getLatitude(), location.getLongitude(), 1);
                    if (list != null && list.size() > 0) {
                        Address address = list.get(0);
                        // sending back first address line and locality
                        result = address.getAddressLine(0) + ", " + address.getLocality();
                        SharedVariable.ALAMAT = result;
                    }
                } catch (IOException e) {
                    //Log.e(TAG, "Impossible to connect to Geocoder", e);
                } finally {
                    Message msg = Message.obtain();
                    msg.setTarget(handler);
                    if (result != null) {
                        msg.what = 1;
                        Bundle bundle = new Bundle();
                        bundle.putString("address", result);
                        msg.setData(bundle);
                    } else
                        msg.what = 0;
                    msg.sendToTarget();
                }
            }
        };
        thread.start();
    }
*/





}
