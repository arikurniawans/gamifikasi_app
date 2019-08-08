package com.diskominfo.ari.gamifikasi_app.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.diskominfo.ari.gamifikasi_app.Kelas.JalurApi;
import com.diskominfo.ari.gamifikasi_app.Kelas.ListPostingModel;
import com.diskominfo.ari.gamifikasi_app.Kelas.SharedVariable;
import com.diskominfo.ari.gamifikasi_app.MainActivity;
import com.diskominfo.ari.gamifikasi_app.R;
import com.diskominfo.ari.gamifikasi_app.activity.BerandaActivity;
import com.diskominfo.ari.gamifikasi_app.activity.DaftarActivity;
import com.diskominfo.ari.gamifikasi_app.activity.DetailSharemeActivity;
import com.diskominfo.ari.gamifikasi_app.fragment.FragmentPosting;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdapterPostingme extends RecyclerView.Adapter<AdapterPostingme.MyViewHolder> {

    private Context mContext;
    private List<ListPostingModel> postingListMe;
    public Button btn_detailme,btnShare;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtKategoriMe,txtPelaporMe,txtAlamatMe,txtIdlaporMe,txtWaktuLaporMe;
        public ImageView imgFoto;
        //public CircleImageView imgFoto;
        public CardView cv_main;
        public RelativeLayout relaList;


        public MyViewHolder(View view) {
            super(view);
            imgFoto = view.findViewById(R.id.imgFotoMe);
            cv_main = view.findViewById(R.id.cardlist_item);
            relaList = view.findViewById(R.id.relaList);
            txtKategoriMe = view.findViewById(R.id.txtKategoriMe);
            txtPelaporMe = view.findViewById(R.id.txtPelaporMe);
            txtAlamatMe = view.findViewById(R.id.txt_alamat_pesanMe);
            txtIdlaporMe = view.findViewById(R.id.txtIdlaporMe);
            txtWaktuLaporMe = view.findViewById(R.id.txtKalendarMe);
            btn_detailme = view.findViewById(R.id.btn_detailme);
            btnShare = view.findViewById(R.id.btn_share);
        }
    }

    public AdapterPostingme(Context mContext, List<ListPostingModel> riwayatList) {
        this.mContext = mContext;
        this.postingListMe = riwayatList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlist_postingme, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if(postingListMe.isEmpty()){
            Toast.makeText(mContext.getApplicationContext(),"Belum ada postingan",Toast.LENGTH_LONG).show();
        }else{
            final ListPostingModel postingClass = postingListMe.get(position);

            if(postingClass.equals("")){
                Toast.makeText(mContext.getApplicationContext(),"Data belum ada !",Toast.LENGTH_LONG).show();
            }else{
                holder.txtKategoriMe.setText(postingClass.getKategori());
                holder.txtPelaporMe.setText(postingClass.getPelapor());
                holder.txtAlamatMe.setText(postingClass.getAlamat());
                holder.txtIdlaporMe.setText(postingClass.getIdLapor());
                holder.txtWaktuLaporMe.setText(postingClass.getWaktuLapor());
                Glide.with(mContext)
                        .load(postingClass.getFoto())
                        .into(holder.imgFoto);
            }

            btn_detailme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext.getApplicationContext(), DetailSharemeActivity.class);
                    intent.putExtra("id_posting",postingClass.getIdLapor());
                    mContext.startActivity(intent);
                }
            });

            btnShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                            mContext);

                    alertDialogBuilder.setTitle("Informasi");
                    alertDialogBuilder
                            .setMessage("Apakah anda ingin share ?")
                            //.setIcon(R.mipmap.ic_launcher)
                            .setCancelable(false)
                            .setPositiveButton("Ya",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    simpanShare(postingClass.getIdLapor(), SharedVariable.ID_PENGGUNA);
                                }
                            })
                            .setNegativeButton("Tidak",new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    // membuat alert dialog dari builder
                    AlertDialog alertDialog = alertDialogBuilder.create();

                    // menampilkan alert dialog
                    alertDialog.show();
                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return postingListMe.size();
    }


    public void simpanShare(final String id_posting, final String id_user){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                List<NameValuePair> nameValuePairs = new ArrayList<>();
                nameValuePairs.add(new BasicNameValuePair("id_posting", id_posting));
                nameValuePairs.add(new BasicNameValuePair("id_user", id_user));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            JalurApi.API_SHARE);
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();

                } catch (ClientProtocolException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();

                }
                return "success";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                if(result.equalsIgnoreCase("success")){
                    Toast.makeText(mContext.getApplicationContext(),"Share berhasil",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(mContext.getApplicationContext(), BerandaActivity.class);
                    mContext.startActivity(intent);
                }else{
                    Toast.makeText(mContext.getApplicationContext(),"Gagal Simpan Data",Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(id_posting,id_user);

    }


}
