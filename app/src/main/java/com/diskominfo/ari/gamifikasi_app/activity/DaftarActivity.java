package com.diskominfo.ari.gamifikasi_app.activity;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.diskominfo.ari.gamifikasi_app.Kelas.JalurApi;
import com.diskominfo.ari.gamifikasi_app.Kelas.Pengguna;
import com.diskominfo.ari.gamifikasi_app.R;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

public class DaftarActivity extends AppCompatActivity {

EditText etNik,etNama,etEmail,etNohp,etPass;
Spinner spKelamin;
Button btn_daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        etNik = (EditText) findViewById(R.id.etNik);
        etNama = (EditText) findViewById(R.id.etNama);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etNohp = (EditText) findViewById(R.id.etNope);
        etPass = (EditText) findViewById(R.id.etPassword);
        spKelamin = (Spinner) findViewById(R.id.spKelamin);
        btn_daftar = (Button) findViewById(R.id.btnDaftar);

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpanData(etNik.getText().toString().trim(),etNama.getText().toString().trim(),
                        spKelamin.getSelectedItem().toString().trim(),etEmail.getText().toString().trim(),
                        etNohp.getText().toString().trim(),etPass.getText().toString().trim());
            }
        });


    }


    public void simpanData(final String nik, final String nama, final String jenkel, final String email, final String no_hp, final String password){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                List<NameValuePair> nameValuePairs = new ArrayList<>();
                nameValuePairs.add(new BasicNameValuePair("nik", nik));
                nameValuePairs.add(new BasicNameValuePair("nama", nama));
                nameValuePairs.add(new BasicNameValuePair("jenkel", jenkel));
                nameValuePairs.add(new BasicNameValuePair("email", email));
                nameValuePairs.add(new BasicNameValuePair("no_hp", no_hp));
                nameValuePairs.add(new BasicNameValuePair("pass", password));

                try {
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(
                            JalurApi.API_REGIS);
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
                    Toast.makeText(getApplication(),"Register berhasil",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplication(),"Gagal Simpan Data",Toast.LENGTH_LONG).show();
                }
            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(nik, nama, jenkel, email, no_hp, password);

    }


}
