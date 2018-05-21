package com.tunisair.khawla.tunisair;

import android.content.Intent;
import android.graphics.Color;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ReponseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    TextView datenvoi,type_rec,description,datreponse,reponse;
    String id_recenvoi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reponse);

        Intent intent = getIntent();
        id_recenvoi = intent.getStringExtra("Id_recenvoi");
        datenvoi = (TextView) findViewById(R.id.txt_dat);
        type_rec = (TextView) findViewById(R.id.txt_type);
        description = (TextView) findViewById(R.id.txt_desc);
        datreponse = (TextView) findViewById(R.id.txt_datrep);
        reponse = (TextView) findViewById(R.id.txt_rep);

        getReclamation_envoyer();
        getReclamation_reponse();
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

    private void getReclamation_envoyer() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("RecEnvoi");
        Query query = reference.orderByChild("id_recenvoi").equalTo(id_recenvoi);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Reclamation rec = snapshot.getValue(Reclamation.class);
                        if (snapshot.getKey().equals(rec.getId_recenvoi())) {
                            datenvoi.setText(rec.getDatrec());
                            type_rec.setText(rec.getTyperec());
                            description.setText(rec.getDescription());
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void getReclamation_reponse() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("RecRep");
        Query query = reference.orderByChild("idEnvoi").equalTo(id_recenvoi);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Reponse rep = snapshot.getValue(Reponse.class);
                            datreponse.setText(rep.getDataRep());
                            datreponse.setTextColor(Color.DKGRAY);
                            reponse.setText(rep.getReponce());
                            reponse.setTextColor(Color.DKGRAY);
                }
                }else{
                    datreponse.setText(R.string.en_cours);
                    datreponse.setTextColor(Color.RED);
                    reponse.setText(R.string.en_cours);
                    reponse.setTextColor(Color.RED);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
