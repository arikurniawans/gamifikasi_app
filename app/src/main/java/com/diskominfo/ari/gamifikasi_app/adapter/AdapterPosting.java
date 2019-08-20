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
import com.diskominfo.ari.gamifikasi_app.activity.DetailShareAllActivity;
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

public class AdapterPosting extends RecyclerView.Adapter<AdapterPosting.MyViewHolder> {

    private Context mContext;
    private List<ListPostingModel> postingListAll;
    public Button btn_detailal,btnLike;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtKategoriAll,txtPelaporAll,txtAlamatAll,txtIdlaporAll,txtWaktuLaporAll;
        public ImageView imgFoto,imgStatus;;
        //public CircleImageView imgFoto;
        public CardView cv_main;
        public RelativeLayout relaList;


        public MyViewHolder(View view) {
            super(view);
            imgFoto = view.findViewById(R.id.imgFotoAll);
            imgStatus = view.findViewById(R.id.imgStatus);
            cv_main = view.findViewById(R.id.cardlist_item);
            relaList = view.findViewById(R.id.relaList);
            txtKategoriAll = view.findViewById(R.id.txtKategoriAll);
            txtPelaporAll = view.findViewById(R.id.txtPelaporAll);
            txtAlamatAll = view.findViewById(R.id.txt_alamat_pesanAll);
            txtIdlaporAll = view.findViewById(R.id.txtIdlaporAll);
            txtWaktuLaporAll = view.findViewById(R.id.txtKalendarAll);
            btn_detailal = view.findViewById(R.id.btn_detailall);
            btnLike = view.findViewById(R.id.btn_like);
        }
    }

    public AdapterPosting(Context mContext, List<ListPostingModel> riwayatList) {
        this.mContext = mContext;
        this.postingListAll = riwayatList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlist_posting, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if(postingListAll.isEmpty()){
            Toast.makeText(mContext.getApplicationContext(),"Belum ada postingan",Toast.LENGTH_LONG).show();
        }else{
            final ListPostingModel postingClass = postingListAll.get(position);

            holder.txtKategoriAll.setText(postingClass.getKategori());
            holder.txtPelaporAll.setText(postingClass.getPelapor());
            holder.txtAlamatAll.setText(postingClass.getAlamat());
            holder.txtIdlaporAll.setText(postingClass.getIdLapor());
            holder.txtWaktuLaporAll.setText(postingClass.getWaktuLapor());
            if(postingClass.getStatusLapor().equals("N")){
                holder.imgStatus.setImageResource(R.drawable.ic_clock64);
            }else if (postingClass.getStatusLapor().equals("Y")){
                holder.imgStatus.setImageResource(R.drawable.check);
            }
            Glide.with(mContext)
                    .load(postingClass.getFoto())
                    .into(holder.imgFoto);

            btn_detailal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext.getApplicationContext(), DetailShareAllActivity.class);
                    intent.putExtra("id_posting",postingClass.getIdLapor());
                    mContext.startActivity(intent);
                }
            });

            btnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(btnLike.getText().toString().equals("Like")){
                        btnLike.setText("Unlike");
                        Like(postingClass.getIdLapor(),SharedVariable.ID_PENGGUNA);
                    }else if(btnLike.getText().toString().equals("Unlike")){
                        btnLike.setText("Like");
                        UnLike(postingClass.getIdLapor(),SharedVariable.ID_PENGGUNA);
                    }

                }
            });

        }

    }

    @Override
    public int getItemCount() {
        return postingListAll.size();
    }


    public void Like(final String id_posting, final String id_user){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                List<NameValuePair> nameValuePairs = new ArrayList<>();
                nameValuePairs.add(new BasicNameValuePair("id_posting", id_posting));
                nameValuePairs.add(new BasicNameValuePair("id_user", id_user));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            JalurApi.API_LIKE);
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
                    Toast.makeText(mContext.getApplicationContext(),"Post Liked !",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(mContext.getApplicationContext(),"Gagal Simpan Data",Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(id_posting,id_user);

    }


    public void UnLike(final String id_posting, final String id_user){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                List<NameValuePair> nameValuePairs = new ArrayList<>();
                nameValuePairs.add(new BasicNameValuePair("id_posting", id_posting));
                nameValuePairs.add(new BasicNameValuePair("id_user", id_user));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            JalurApi.API_UNLIKE);
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
                    Toast.makeText(mContext.getApplicationContext(),"Post Unliked !",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(mContext.getApplicationContext(),"Gagal Simpan Data",Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(id_posting,id_user);

    }



}
