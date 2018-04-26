package com.tunisair.khawla.tunisair;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ReclamationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener

{

    EditText dat,identif,desc,Num_vol,Ref,Tickt,date_vol;
    String iden,type,dec,num,numvol,refe,tic,dt_vol,dat_rec;
    Spinner type_rec;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamation);

        dat=(EditText) findViewById(R.id.date_rec);
        identif=(EditText) findViewById(R.id.identifiant);
        desc=(EditText) findViewById(R.id.description);
        Num_vol=(EditText)findViewById(R.id.num_vol);
        Ref=(EditText)findViewById(R.id.billet_ref);
        Tickt=(EditText) findViewById(R.id.ticket_number);
        date_vol=(EditText) findViewById(R.id.date_vol);

        type_rec=(Spinner) findViewById(R.id.typeRec);


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

    }

    @Override
    public void onBackPressed () {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected (MenuItem item){
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

    public void get_date(View view){
        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        Calandrier_pop pop = new Calandrier_pop();
        pop.show(manager, null);
        InscriptionActivity.p=5;

    }
    public void setdate (String date){
        dat.setText(date);
    }
    public void get_date2(View view){
        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        Calandrier_pop pop = new Calandrier_pop();
        pop.show(manager, null);
        InscriptionActivity.p=6;

    }
    public void setdate2(String date){
        date_vol.setText(date);
    }

    public void verifier(View view) {
        iden=identif.getText().toString().trim();
        dec=desc.getText().toString().trim();
        numvol=Num_vol.getText().toString().trim();
        refe=Ref.getText().toString().trim();
        tic=Tickt.getText().toString().trim();
        dt_vol=date_vol.getText().toString().trim();
        dat_rec=dat.getText().toString().trim();

        if (!valider()){
            Toast.makeText(getApplicationContext(),R.string.verifier_tout_les_champs,Toast.LENGTH_LONG).show();
        }

    }
    private boolean valider() {
        boolean valide=true;

        if (iden.isEmpty() ) {
            identif.setError(getString(R.string.champ_obligatoir));
            valide = false;
        }
        if (dat_rec.isEmpty() ) {
            dat.setError(getString(R.string.champ_obligatoir));
            valide = false;
        }
        if (dec.isEmpty() ) {
            desc.setError(getString(R.string.champ_obligatoir));
            valide = false;
        }
        if (numvol.isEmpty() ) {
            Num_vol.setError(getString(R.string.champ_obligatoir));
            valide = false;
        }
        if (refe.isEmpty() ) {
            Ref.setError(getString(R.string.champ_obligatoir));
            valide = false;
        }
        if (tic.isEmpty() ) {
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

}