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
import android.widget.EditText;
import android.widget.Toast;

public class ReclamationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener

    {

EditText naisence,id,type_rec,desc,Num_vol,Ref,Tickt,date_vol;
String iden,type,dec,num,numvol,refe,tic;
        @Override
        protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamation);
        naisence=(EditText) findViewById(R.id.date_rec);
        id=(EditText) findViewById(R.id.identifiant);
        type_rec=(EditText) findViewById(R.id.type_rec);
        desc=(EditText) findViewById(R.id.description);
        Num_vol=(EditText)findViewById(R.id.num_vol);
        Ref=(EditText)findViewById(R.id.billet_ref);
        Tickt=(EditText) findViewById(R.id.ticket_number);


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

        @Override
        public void onBackPressed () {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

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
        public void get_date (View view){
        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        Calandrier_pop pop = new Calandrier_pop();
        pop.show(manager, null);

    }
        public void setdate (String date){
        naisence.setText(date);
    }

        public void verifier(View view) {
            iden=id.getText().toString().trim();
            type= type_rec.getText().toString().trim();
            dec=desc.getText().toString().trim();
            num=Num_vol.getText().toString().trim();
            refe=Ref.getText().toString().trim();
            tic=Tickt.getText().toString().trim();

            if (!valider()){
                Toast.makeText(getApplicationContext(),"Veuillez vérifier tout les champs",Toast.LENGTH_LONG).show();
            }

        }

        private boolean valider() {
            boolean valide=true;

            if (iden.isEmpty() ) {
                id.setError("Veuillez insérer votre identifiant ");
                valide = false;

            }
            if (type.isEmpty() ) {
                type_rec.setError("Veuillez ajoutée votre type de réclamation");
                valide = false;

            }
            if (dec.isEmpty() ) {
                desc.setError("Veuillez ajoutée une description ");
                valide = false;

            }
            if (numvol.isEmpty() ) {
                Num_vol.setError("Veuillez ajoutée votre numéro de vol");
                valide = false;

            }
            if (refe.isEmpty() ) {
                Ref.setError("Veuillez ajoutée votre réferance de billet");
                valide = false;

            }
            if (tic.isEmpty() ) {
                Tickt.setError("Veuillez ajoutée numéro de ticket");
                valide = false;

            }
            return valide;
        }
    }

