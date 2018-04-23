package com.tunisair.khawla.tunisair;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BilletActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Spinner de, vers, adult, enfants, bébé, jeune,classe;
    String[] depart = new String[60];
    String[] ariver = new String[60];
    String[] adl = new String[7];
    String[] enf = new String[7];
    String[] bb = new String[7];
    String[] jne = new String[7];
    Liste_pays adapterP;
    Liste_pays adapterPy;
    EditText naissance1,naissance2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billet);
        de = (Spinner) findViewById(R.id.P_depart);
        vers = (Spinner) findViewById(R.id.p_arrive);
        adult = (Spinner) findViewById(R.id.N_adult);
        enfants = (Spinner) findViewById(R.id.N_enfant);
        bébé = (Spinner) findViewById(R.id.N_bebe);
        jeune = (Spinner) findViewById(R.id.N_jeune);
      naissance1=(EditText) findViewById(R.id.dat_dep);
      naissance2=(EditText) findViewById(R.id.dat_arr);
      classe= (Spinner) findViewById(R.id.classe);

        remplirp();
        remplirpys();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        final List<Integer> spinerarray = new ArrayList<Integer>();
        spinerarray.add(0);
        spinerarray.add(1);
        spinerarray.add(2);
        spinerarray.add(3);
        spinerarray.add(4);
        spinerarray.add(5);
        spinerarray.add(6);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, spinerarray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adult.setAdapter(adapter);
        enfants.setAdapter(adapter);
        bébé.setAdapter(adapter);
        jeune.setAdapter(adapter);

        final List<String> spinerarra = new ArrayList<String>();
        spinerarra.add("affaire");
        spinerarra.add("Economique");

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinerarra);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        classe.setAdapter(adapter1);



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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profil) {

            Intent intent = new Intent(this, ProfilActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_billet) {

            Intent intent = new Intent(this, BilletActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_miles) {

            Intent intent = new Intent(this, MilesActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_mouv) {

            Intent intent = new Intent(this, MouvementActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_rec) {

            Intent intent = new Intent(this, ReclamationActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_about) {

            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_location) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_deconnexion) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void remplirp() {

        remplir();
        adapterP = new Liste_pays(this,depart);
        de.setAdapter(adapterP);
        de.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                de.setSelection(-1);
            }
        });

    }



    public void remplir() {

        try {
            InputStream inputStream = getAssets().open("depart_arriver.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            int x = 0;
            String ligne;
            while (bufferedReader.ready()) {

                ligne = bufferedReader.readLine();
                depart[x] = ligne;
                x++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void remplirpys() {

        remplirpy();
        adapterPy = new Liste_pays(this,ariver);
       vers.setAdapter(adapterPy);
        vers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                de.setSelection(-1);
            }
        });

    }


    public void remplirpy() {

        try {
            InputStream inputStream = getAssets().open("depart_arriver.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            int x = 0;
            String ligne;
            while (bufferedReader.ready()) {

                ligne = bufferedReader.readLine();
               ariver[x] = ligne;
                x++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void get_date(View view) {
        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        Calandrier_pop pop = new Calandrier_pop();
        pop.show(manager, null);

    }
    public void setdate(String date) {
        naissance1.setText(date);
        naissance2.setText(date);
    }


}
