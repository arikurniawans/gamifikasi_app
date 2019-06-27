package com.diskominfo.ari.gamifikasi_app.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class DaftarActivity extends AppCompatActivity {
DatabaseReference database;
FirebaseAuth auth;
EditText txt_email,txt_nope,txt_password1,txt_password2;
Button btn_daftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);
        FirebaseApp.initializeApp(this);
        Firebase.setAndroidContext(this);
        database = FirebaseDatabase.getInstance().getReference("tb_user");
        auth = FirebaseAuth.getInstance();

        txt_email = (EditText) findViewById(R.id.etEmail);
        txt_nope = (EditText) findViewById(R.id.etNope);
        txt_password1 = (EditText) findViewById(R.id.etPassword);
        txt_password2 = (EditText) findViewById(R.id.etPassword2);
        btn_daftar = (Button) findViewById(R.id.btnDaftar);

        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.createUserWithEmailAndPassword(txt_email.getText().toString().trim(),txt_password1.getText().toString().trim()).addOnCompleteListener(DaftarActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(getApplication(),"Registrasi Gagal : "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                        }else{
                            String id = database.push().getKey();
                            Pengguna pengguna = new Pengguna(txt_email.getText().toString().trim(),txt_nope.getText().toString().trim(),
                                    txt_password1.getText().toString().trim());
                            database.child(id).setValue(pengguna);
                            Toast.makeText(getApplication(),"Register berhasil",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

    }
}
