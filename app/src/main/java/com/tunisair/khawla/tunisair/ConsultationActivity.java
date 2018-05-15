package com.tunisair.khawla.tunisair;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ConsultationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    ListView listView;
    ArrayList<String> listrec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);

        prefs = getSharedPreferences("Inscription", MODE_PRIVATE);
        editor = prefs.edit();
        listView = (ListView) findViewById(R.id.listview);
        listrec=new ArrayList<>();

        //getReclamation();
        String identif = prefs.getString("Identifiant", "empty");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("RecEnvoi");
        Query query = reference.orderByChild("id_User").equalTo(identif);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Reclamation rec=snapshot.getValue(Reclamation.class);
                        String item=rec.getId_recenvoi();
                        listrec.add(new String(item));

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1, listrec);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listrec);
//        listView.setAdapter(adapter);


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
    private void getReclamation(){
//        String identif = prefs.getString("Identifiant", "empty");
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("RecEnvoi");
//        Query query = reference.orderByChild("id_User").equalTo(identif);
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        Reclamation rec=snapshot.getValue(Reclamation.class);
//                        Toast.makeText(getApplicationContext(),rec.getTyperec(),Toast.LENGTH_LONG).show();
//                        String item=rec.getTyperec();
//                        listrec.add(new String(item));
//
//                    }
//                    System.out.println(listrec.size());
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
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
