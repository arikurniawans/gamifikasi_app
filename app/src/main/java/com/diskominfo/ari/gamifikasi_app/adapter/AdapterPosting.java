package com.diskominfo.ari.gamifikasi_app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.diskominfo.ari.gamifikasi_app.Kelas.ListPostingModel;
import com.diskominfo.ari.gamifikasi_app.R;

import java.util.List;

public class AdapterPosting extends RecyclerView.Adapter<AdapterPosting.MyViewHolder> {

    private Context mContext;
    private List<ListPostingModel> postingList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtKategori,txtPelapor,txtAlamat,txtIdlapor,txtWaktuLapor;
        public ImageView imgFoto;
        //public CircleImageView imgFoto;
        public CardView cv_main;
        public RelativeLayout relaList;

        public MyViewHolder(View view) {
            super(view);
            imgFoto = view.findViewById(R.id.imgFoto);
            cv_main = view.findViewById(R.id.cardlist_item);
            relaList = view.findViewById(R.id.relaList);
            txtKategori = view.findViewById(R.id.txtKategori);
            txtPelapor = view.findViewById(R.id.txtPelapor);
            txtAlamat = view.findViewById(R.id.txt_alamat_pesan);
            txtIdlapor = view.findViewById(R.id.txtIdlapor);
            txtWaktuLapor = view.findViewById(R.id.txtWaktuLapor);
        }
    }

    public AdapterPosting(Context mContext, List<ListPostingModel> riwayatList) {
        this.mContext = mContext;
        this.postingList = riwayatList;
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

        if(postingList.isEmpty()){
            Toast.makeText(mContext.getApplicationContext(),"Belum ada postingan",Toast.LENGTH_LONG).show();
        }else{
            final ListPostingModel postingClass = postingList.get(position);

            holder.txtKategori.setText(postingClass.getKategori());
            holder.txtPelapor.setText(postingClass.getPelapor());
            holder.txtAlamat.setText(postingClass.getAlamat());
            holder.txtIdlapor.setText(postingClass.getIdLapor());
            holder.txtWaktuLapor.setText(postingClass.getWaktuLapor());
            Glide.with(mContext)
                    .load(postingClass.getFoto())
                    .into(holder.imgFoto);
        }

    }

    @Override
    public int getItemCount() {
        return postingList.size();
    }

}
