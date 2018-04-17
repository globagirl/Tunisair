package com.tunisair.khawla.tunisair;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProfilActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    EditText naisence,nom,prenom,mail,password,num_pass,adresseDo,ville,codepostale,Tel_dom,Tel_prof,Tel_mobile,fax,sociéte,fonction;
    String name,prenoms,email,pass,confirme,pasport,adrDO,villes,postale,teldom,telprof,telmobil,faxs,soc,fonc,co,nat,pa;
    Spinner SpiCode,SpiNatio,SpiPays,Spicode2;
    Liste_code_payes adapter;
    Liste_nationnalité adapterN;
    Liste_pays adapterP;
    String[] codes = new String[199];
    String[] nation=new String[175];
    String[] pay=new String[199];
    Spinner Spicode3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        nom = (EditText) findViewById(R.id.nom);
        prenom= (EditText) findViewById(R.id.prenom);
        mail= (EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);
        num_pass= (EditText) findViewById(R.id.num_pass);
        naisence= (EditText) findViewById(R.id.naissance);
        codepostale=(EditText) findViewById(R.id.code_post);
        Tel_dom=(EditText) findViewById(R.id.T_domic);
        adresseDo=(EditText) findViewById(R.id.adr_domic);
        ville=(EditText) findViewById(R.id.ville);
        Tel_prof=(EditText) findViewById(R.id.T_prof);
        Tel_mobile=(EditText) findViewById(R.id.T_mobile);
        fax=(EditText) findViewById(R.id.fax);
        sociéte=(EditText) findViewById(R.id.societe);
        fonction=(EditText) findViewById(R.id.fonction);
        SpiCode = (Spinner) findViewById(R.id.code_pays);
        SpiNatio=(Spinner) findViewById(R.id.natio);
        SpiPays = (Spinner) findViewById(R.id.pays);
        Spicode2=(Spinner) findViewById(R.id.code_pays_prof);
        Spicode3= (Spinner)findViewById(R.id.code_pays_mob) ;
        //Appel des methodes
        remplirspinir();
        remplirnatio();
        remplirpays();



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    public void remplirspinir() {

        rempli_code_pays();
        adapter = new Liste_code_payes(this, codes, Constante.imgs);
        SpiCode.setAdapter(adapter);
        SpiCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                SpiCode.setSelection(-1);
            }
        });

        adapter = new Liste_code_payes(this, codes, Constante.imgs);
        Spicode2.setAdapter(adapter);
        Spicode2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Spicode2.setSelection(-1);
            }
        });

        adapter = new Liste_code_payes(this, codes, Constante.imgs);
        Spicode3.setAdapter(adapter);
        Spicode3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Spicode3.setSelection(-1);
            }
        });

    }

    //contenu d indicatif pays
    public void rempli_code_pays() {

        try {
            InputStream inputStream = getAssets().open("indicatif_pays.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            int x = 0;
            String ligne;
            while (bufferedReader.ready()) {

                ligne = bufferedReader.readLine();
                codes[x] = ligne;
                x++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    // remplir liste nationalité
    public void remplirnatio() {

        rempli_natio();
        adapterN = new Liste_nationnalité(this,nation);
        SpiNatio.setAdapter(adapterN);
        SpiNatio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                SpiNatio.setSelection(-1);
            }
        });

    }
    //contenu de nationalité
    public void rempli_natio() {

        try {
            InputStream inputStream = getAssets().open("nationnalité.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            int x = 0;
            String ligne;
            while (bufferedReader.ready()) {

                ligne = bufferedReader.readLine();
                nation[x] = ligne;
                x++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // remplir liste pays
    public void remplirpays() {

        rempli_pays();
        adapterP = new Liste_pays(this,pay);
        SpiPays.setAdapter(adapterP);
        SpiPays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                SpiPays.setSelection(-1);
            }
        });

    }
    //contenu de pays
    public void rempli_pays() {

        try {
            InputStream inputStream = getAssets().open("payes.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            int x = 0;
            String ligne;
            while (bufferedReader.ready()) {

                ligne = bufferedReader.readLine();
                pay[x] = ligne;
                x++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void modif(View view){

    }

//side bar
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

        }else if (id == R.id.nav_billet) {

            Intent intent = new Intent(this, BilletActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_miles) {

            Intent intent = new Intent(this, MilesActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_mouv) {

            Intent intent = new Intent(this, MouvementActivity.class);
            startActivity(intent);

        }else if (id == R.id.nav_rec) {

            Intent intent = new Intent(this, ReclamationActivity.class);
            startActivity(intent);

        }

        else if (id == R.id.nav_about) {

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
}
