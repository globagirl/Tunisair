package com.tunisair.khawla.tunisair;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BilletActivity extends AppCompatActivity{
//
//    SharedPreferences prefs;
//    SharedPreferences.Editor editor;
//
//    Spinner de, vers, adult, enfants, bebe, jeune;
//    String[] depart = new String[62];
//    String[] ariver = new String[62];
//    String[] bb = new String[7];
//    Liste_pays adapterP;
//    Liste_pays adapterPy;
//    EditText naissance1,naissance2;
//    String Naissance1, Naissance2;
//    RadioButton rd_all,rd_eco;
//    CheckBox chek_simul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billet);
//        prefs = getSharedPreferences("Achat_biellet", MODE_PRIVATE);
//        editor = prefs.edit();
//
//        de = (Spinner) findViewById(R.id.P_depart);
//        vers = (Spinner) findViewById(R.id.p_arrive);
//        rd_all=findViewById(R.id.rd_aller);
//        naissance1=(EditText) findViewById(R.id.dat_dep);
//        naissance2=(EditText) findViewById(R.id.dat_arr);
//        rd_eco = (RadioButton) findViewById(R.id.eco);
//        chek_simul = (CheckBox) findViewById(R.id.simul);
//
//        adult = (Spinner) findViewById(R.id.N_adult);
//        enfants = (Spinner) findViewById(R.id.N_enfant);
//        bebe = (Spinner) findViewById(R.id.N_bebe);
//        jeune = (Spinner) findViewById(R.id.N_jeune);
//
//
//
//        remplirp();
//        remplirpys();
//
//        rd_all.setChecked(true);
//        editor.putString("Type_voyage", rd_all.getText().toString());
//        rd_eco.setChecked(true);
//        editor.putString("Classe_voyage", rd_eco.getText().toString());
//        editor.apply();
//
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
//
//        final List<Integer> spinerarray = new ArrayList<Integer>();
//        spinerarray.add(0);
//        spinerarray.add(1);
//        spinerarray.add(2);
//        spinerarray.add(3);
//        spinerarray.add(4);
//        spinerarray.add(5);
//        spinerarray.add(6);
//        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, spinerarray);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        adult.setAdapter(adapter);
//        enfants.setAdapter(adapter);
//        bebe.setAdapter(adapter);
//        jeune.setAdapter(adapter);


        WebView view=(WebView) findViewById(R.id.webView);
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setBuiltInZoomControls(true);
        view.loadUrl("http://www.tunisair.com/mobileWeb/booking3/booking.asp");

        view.setWebViewClient(new WebViewClient(){
            public boolean shouldOverriceUrlLoading(WebView view,String url){
                return false;
            }
        });
    }
//
//    public void onRadioButton_classe(View view) {
//        boolean checked = ((RadioButton) view).isChecked();
//        switch (view.getId()) {
//            case R.id.affaire:
//                if (checked) {
//                    RadioButton rd_aff = (RadioButton) findViewById(R.id.affaire);
//                    editor.putString("Classe_voyage", rd_aff.getText().toString());
//                    editor.apply();
//                }
//                break;
//        }
//    }
//
//    public void onRadioButtonClicked(View view) {
//        boolean checked = ((RadioButton) view).isChecked();
//        switch (view.getId()) {
//            case R.id.rd_retour:
//                if (checked) {
//                    RadioButton rd_r = (RadioButton) findViewById(R.id.rd_retour);
//                    editor.putString("Type_voyage", rd_r.getText().toString());
//                    editor.apply();
//                }
//                break;
//        }
//    }
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_profil) {
//
//            Intent intent = new Intent(this, ProfilActivity.class);
//            startActivity(intent);
//
//        } else if (id == R.id.nav_billet) {
//
//            Intent intent = new Intent(this, BilletActivity.class);
//            startActivity(intent);
//
//        } else if (id == R.id.nav_miles) {
//
//            Intent intent = new Intent(this, MilesActivity.class);
//            startActivity(intent);
//
//        } else if (id == R.id.nav_mouv) {
//
//            Intent intent = new Intent(this, MouvementActivity.class);
//            startActivity(intent);
//
//        } else if (id == R.id.nav_rec) {
//
//            Intent intent = new Intent(this, ReclamationActivity.class);
//            startActivity(intent);
//
//        } else if (id == R.id.nav_cons) {
//
//            Intent intent = new Intent(this, ConsultationActivity.class);
//            startActivity(intent);
//
//        } else if (id == R.id.nav_about) {
//
//            Intent intent = new Intent(this, AboutActivity.class);
//            startActivity(intent);
//
//        } else if (id == R.id.nav_location) {
//            Intent intent = new Intent(this, MapsActivity.class);
//            startActivity(intent);
//
//        } else if (id == R.id.nav_deconnexion) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
//
//    public void remplirp() {
//
//        remplir();
//        adapterP = new Liste_pays(this,depart);
//        de.setAdapter(adapterP);
//        de.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                de.setSelection(-1);
//            }
//        });
//
//    }
//    public void remplir() {
//
//        try {
//            InputStream inputStream = getAssets().open("depart_arriver.txt");
//            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//            int x = 0;
//            String ligne;
//            while (bufferedReader.ready()) {
//
//                ligne = bufferedReader.readLine();
//                depart[x] = ligne;
//                x++;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//    public void remplirpys() {
//
//        remplirpy();
//        adapterPy = new Liste_pays(this,ariver);
//        vers.setAdapter(adapterPy);
//        vers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//                de.setSelection(-1);
//            }
//        });
//
//    }
//    public void remplirpy() {
//
//        try {
//            InputStream inputStream = getAssets().open("depart_arriver.txt");
//            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//            int x = 0;
//            String ligne;
//            while (bufferedReader.ready()) {
//
//                ligne = bufferedReader.readLine();
//                ariver[x] = ligne;
//                x++;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//    public void get_date(View view) {
//        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
//        Calandrier_pop pop = new Calandrier_pop();
//        pop.show(manager, null);
//        InscriptionActivity.p=3;
//
//    }
//    public void setdate(String date) {
//        naissance1.setText(date);
//        naissance1.setError(null);
//    }
//    public void get_date2(View view) {
//        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
//        Calandrier_pop pop = new Calandrier_pop();
//        pop.show(manager, null);
//        InscriptionActivity.p=4;
//
//    }
//    public void setdate2(String date) {
//        naissance2.setText(date);
//        naissance2.setError(null);
//    }
//
//    public void valide(View view) {
//        Naissance1 = naissance1.getText().toString().trim();
//        Naissance2= naissance2.getText().toString().trim();
//        if (!valider()) {
//            Toast.makeText(getApplicationContext(), R.string.verifier_tout_les_champs, Toast.LENGTH_LONG).show();
//        } else {
//            remplir_champs();
//            if (chek_simul.isChecked()) {
//                Toast.makeText(getApplicationContext(), "simulation cocher", Toast.LENGTH_LONG).show();
//
//            }else{
//                Toast.makeText(getApplicationContext(),"simulation nom cocher", Toast.LENGTH_LONG).show();
//
//            }
//        }
//    }
//    public void remplir_champs() {
//        editor.putString("Date_dep", Naissance1);
//        editor.putString("Date_arr", Naissance2);
//        editor.apply();
//    }
//    private boolean valider() {
//        boolean valide = true;
//
//        if (Naissance1.isEmpty()) {
//            naissance1.setError(getString(R.string.champ_obligatoir));
//            valide = false;
//
//        }
//        if (Naissance2.isEmpty()) {
//            naissance2.setError(getString(R.string.champ_obligatoir));
//            valide = false;
//
//        }
//        return valide;
//    }
}