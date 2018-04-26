package com.tunisair.khawla.tunisair;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ProfilActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    TextView  tx_naisenc, tx_noms, tx_prnom, tx_mail, tx_genre, tx_numtel,identif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        prefs = getSharedPreferences("Inscription", MODE_PRIVATE);
        editor = prefs.edit();

        tx_noms =  findViewById(R.id.nom);
        tx_prnom = findViewById(R.id.prenom);
        tx_mail =  findViewById(R.id.email);
        tx_genre =findViewById(R.id.gender);
        tx_naisenc =  findViewById(R.id.naisence);
        tx_numtel =  findViewById(R.id.num_tel);
        identif =  findViewById(R.id.name4);
        editor.putString("Identifiant", identif.getText().toString());
        editor.apply();

        String nom=prefs.getString("Nom","empty");
        String sexe= prefs.getString("sexe","empty");
        String prenom= prefs.getString("Prenom","empty");
        String email=prefs.getString("Email","empty");
        String naissence= prefs.getString("Naissence","empty");
        String numtel= prefs.getString("Tel_dom","empty");
        String code_numtel= prefs.getString("Tel_domicile","empty");

        tx_noms.setText(nom);
        tx_prnom.setText(prenom);
        tx_naisenc.setText(naissence);
        tx_mail.setText(email);
        tx_numtel.setText(code_numtel+" "+numtel);
        if(sexe.equals("M")){

            tx_genre.setText(R.string.homme);
        }else{
            tx_genre.setText(R.string.femme);
        }

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
