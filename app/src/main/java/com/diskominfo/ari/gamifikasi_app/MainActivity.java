package com.diskominfo.ari.gamifikasi_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.diskominfo.ari.gamifikasi_app.activity.BerandaActivity;
import com.diskominfo.ari.gamifikasi_app.activity.DaftarActivity;

public class MainActivity extends AppCompatActivity {
Button btn_daftar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_daftar = (Button) findViewById(R.id.btnDaftar);
        btn_daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BerandaActivity.class);
                startActivity(intent);
            }
        });
    }
}
