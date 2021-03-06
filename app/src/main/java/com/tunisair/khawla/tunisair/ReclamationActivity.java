package com.tunisair.khawla.tunisair;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tunisair.khawla.tunisair.receiver.NetworkStateChangeReceiver;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class ReclamationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText desc, Num_vol, Ref, Tickt, date_vol;
    String type, dec, numvol, refe, tic, dt_vol, dt_rec;
    Spinner type_rec;
    int etat = 0;
    DatabaseReference reference;
    SharedPreferences prefs;
    static ACProgressFlower dialoge;
    private BroadcastReceiver mNetworkReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamation);

        LoginActivity.NB_Activity=8;
        mNetworkReceiver = new NetworkStateChangeReceiver();
        registerNetworkBroadcastForNougat();
        prefs = getSharedPreferences("Inscription", MODE_PRIVATE);

        desc = (EditText) findViewById(R.id.description);
        Num_vol = (EditText) findViewById(R.id.num_vol);
        Ref = (EditText) findViewById(R.id.billet_ref);
        Tickt = (EditText) findViewById(R.id.ticket_number);
        date_vol = (EditText) findViewById(R.id.date_vol);
        type_rec = (Spinner) findViewById(R.id.typeRec);

        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH) + 1;
        dt_rec = cal.get(Calendar.DAY_OF_MONTH) + "/" + month + "/" + cal.get(Calendar.YEAR);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        final List<String> spinerarray = new ArrayList<String>();
        spinerarray.add("Autre Reclamation");
        spinerarray.add("Bonus non alloue");
        spinerarray.add("Cloture compte justificatif");
        spinerarray.add("Demande code pin ");
        spinerarray.add("Demande de duplicata de carte ");
        spinerarray.add("Demande revision statut ");
        spinerarray.add("Regroupement famille avec 20% bonus");
        spinerarray.add("Catering ");
        spinerarray.add("Carte non parvenue");
        spinerarray.add("Voyage non compatabilse");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinerarray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_rec.setAdapter(adapter);

        type_rec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type = type_rec.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                type_rec.setSelection(-1);
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_profil) {

            Intent intent = new Intent(this, ProfilActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_billet) {
            SharedPreferences.Editor  Sprefs = getSharedPreferences("Achat_biellet", MODE_PRIVATE).edit();
            Sprefs.clear();
            Sprefs.apply();
            Intent intent = new Intent(this, BilletActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_miles) {

            Intent intent = new Intent(this, MilesActivity.class);
            startActivity(intent);

        }  else if (id == R.id.nav_rec) {

            Intent intent = new Intent(this, ReclamationActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_cons) {

            Intent intent = new Intent(this, ConsultationActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_about) {

            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_location) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_deconnexion) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            this.finishAffinity();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void get_date2(View view) {
        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        Calandrier_pop pop = new Calandrier_pop();
        pop.show(manager, null);
        InscriptionActivity.p = 6;

    }

    public void setdate2(String date) {
        date_vol.setText(date);
        date_vol.setError(null);
    }

    public void verifier(View view) {
        dec = desc.getText().toString().trim();
        numvol = Num_vol.getText().toString().trim();
        refe = Ref.getText().toString().trim();
        tic = Tickt.getText().toString().trim();
        dt_vol = date_vol.getText().toString().trim();


        if (!valider()) {
            Toast.makeText(getApplicationContext(), R.string.verifier_tout_les_champs, Toast.LENGTH_LONG).show();
        } else {
            if (isOnline()) {
                String identif = prefs.getString("Identifiant", "empty");
                reference = FirebaseDatabase.getInstance().getReference("RecEnvoi");
                String key = reference.push().getKey();
                Reclamation reclamation = new Reclamation(identif,dt_rec, type, numvol, dt_vol, refe, tic, dec, etat,key);
                reference.child(key).setValue(reclamation);
                Toast.makeText(ReclamationActivity.this, R.string.chek_reclamation, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ReclamationActivity.this, ProfilActivity.class);
                startActivity(intent);

            } else {
                Toast.makeText(this, R.string.chek_internet, Toast.LENGTH_SHORT).show();

            }
        }
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean valider() {
        boolean valide = true;
        if (dec.isEmpty()) {
            desc.setError(getString(R.string.champ_obligatoir));
            valide = false;
        }
        if (numvol.isEmpty()) {
            Num_vol.setError(getString(R.string.champ_obligatoir));
            valide = false;
        }
        if (refe.isEmpty()) {
            Ref.setError(getString(R.string.champ_obligatoir));
            valide = false;
        }
        if (dt_vol.isEmpty()) {
            date_vol.setError(getString(R.string.champ_obligatoir));
            valide = false;
        }
        if (tic.isEmpty()) {
            Tickt.setError(getString(R.string.champ_obligatoir));
            valide = false;
        }
        if (!numvol.isEmpty() && (numvol.length() != 4)) {
            Num_vol.setError(getString(R.string.logure_numero_vole));
            valide = false;
        }
        if (!refe.isEmpty() && (refe.length() != 6)) {
            Ref.setError(getString(R.string.logure_reference));
            valide = false;

        }
        if (!tic.isEmpty() && (tic.length() != 10)) {
            Tickt.setError(getString(R.string.longure_ticket));
            valide = false;

        }
        return valide;
    }
    //Methode de test connexion
    public static void dialog(boolean value, Context context) {

        if (value) {
            context=null;
            dialoge.dismiss();
        } else {
            dialoge = new ACProgressFlower.Builder(context)
                    .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                    .themeColor(Color.WHITE)
                    .text("Access Denied...").textColor(Color.WHITE)
                    .fadeColor(Color.DKGRAY).build();
            dialoge.show();
        }
    }

    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
    }
}