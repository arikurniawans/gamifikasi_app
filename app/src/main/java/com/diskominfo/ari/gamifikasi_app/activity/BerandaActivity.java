package com.diskominfo.ari.gamifikasi_app.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.diskominfo.ari.gamifikasi_app.MainActivity;
import com.diskominfo.ari.gamifikasi_app.R;
import com.diskominfo.ari.gamifikasi_app.fragment.FragmentHome;
import com.diskominfo.ari.gamifikasi_app.fragment.FragmentPosting;
import com.diskominfo.ari.gamifikasi_app.fragment.FragmentProfil;


public class BerandaActivity extends AppCompatActivity {
FragmentHome fragmentHome;
FragmentPosting fragmentPosting;
FragmentProfil fragmentProfil;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    goToFragment(fragmentHome,true);
                    return true;
                case R.id.navigation_posting:
                    goToFragment(fragmentPosting,true);
                    return true;
                case R.id.navigation_profil:
                    goToFragment(fragmentProfil,true);
                    return true;
            }
            return false;
        }
    };

    void goToFragment(Fragment fragment, boolean isTop) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_admin_container, fragment);
        if (!isTop)
            fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragmentHome = new FragmentHome();
        fragmentPosting = new FragmentPosting();
        fragmentProfil = new FragmentProfil();
        goToFragment(fragmentHome,true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pojok, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {


            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
