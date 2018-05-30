package com.tunisair.khawla.tunisair;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tunisair.khawla.tunisair.receiver.NetworkStateChangeReceiver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class BilletActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Spinner de, vers;
    String[] depart = new String[63];
    String[] ariver = new String[63];
    String[] bb = new String[7];
    Liste_pays adapterP, adapterPy;
    EditText naissance1, naissance2, nb_place;
    String Naissance1, Naissance2, type, Depart_vol, Arrivage_vol, err = "";
    int nb_places;
    RadioButton rd_all, rd_eco, rd_all_retour;
    RadioGroup G_type, G_class;
    LinearLayout linearLayout;
    ArrayList<Vols> list_vols = new ArrayList<>();
    Vols vol_s = new Vols();
    static ACProgressFlower dialoge;
    private BroadcastReceiver mNetworkReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billet);
        LoginActivity.NB_Activity=5;
        mNetworkReceiver = new NetworkStateChangeReceiver();
        registerNetworkBroadcastForNougat();
        getVols();
        prefs = getSharedPreferences("Achat_biellet", MODE_PRIVATE);
        editor = prefs.edit();

        de = (Spinner) findViewById(R.id.P_depart);
        vers = (Spinner) findViewById(R.id.p_arrive);
        rd_all = (RadioButton) findViewById(R.id.rd_aller);
        rd_all_retour = (RadioButton) findViewById(R.id.rd_retour);
        naissance1 = (EditText) findViewById(R.id.dat_dep);
        naissance2 = (EditText) findViewById(R.id.dat_arr);
        nb_place = (EditText) findViewById(R.id.nbr_place);
        rd_eco = (RadioButton) findViewById(R.id.eco);
        linearLayout = (LinearLayout) findViewById(R.id.linear_retour);
        G_class = (RadioGroup) findViewById(R.id.group_classe);
        G_type = (RadioGroup) findViewById(R.id.group_type);

        remplirp();
        remplirpys();
        restaurerdonner();


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

    public void restaurerdonner() {
        int restoreddepar = prefs.getInt("Id_De", 0);
        int restoredarivage = prefs.getInt("Id_Vers", 0);
        String restoreddatdep = prefs.getString("Date_dep", null);
        String restoreddatreto = prefs.getString("Date_arr", "empty");
        int restorednbplac = prefs.getInt("Nb_places", 0);
        int restoredclass = prefs.getInt("Id_Classe", 0);
        int restoredtype = prefs.getInt("Id_Type", 0);

        if (restorednbplac != 0) {
            nb_place.setText(String.valueOf(restorednbplac));
        }
        if (restoreddatdep != null) {
            naissance1.setText(String.valueOf(restoreddatdep));
        }
        if (!restoreddatreto.equals("empty")) {
            naissance2.setText(String.valueOf(restoreddatreto));
        }
        if (restoredarivage != 0) {
            vers.setSelection(restoredarivage);
        }
        if (restoreddepar != 0) {
            de.setSelection(restoreddepar);
        }
        if (restoredtype != 0) {
            G_type.check(restoredtype);
        } else {
            rd_all.setChecked(true);
            type = rd_all.getText().toString();
        }
        if (restoredclass != 0) {
            G_class.check(restoredclass);
        } else {
            rd_eco.setChecked(true);
            editor.putString("Classe_voyage", rd_eco.getText().toString());
            editor.apply();
        }
    }

    public void getVols() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Vols");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> cheldrens = dataSnapshot.getChildren();
                        for (DataSnapshot snapshot : cheldrens) {
                            Vols rep = snapshot.getValue(Vols.class);
                            list_vols.add(rep);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
            }
        }, 1000);
    }

    public void onRadioButton_classe(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.affaire:
                if (checked) {
                    RadioButton rd_aff = (RadioButton) findViewById(R.id.affaire);
                    editor.putString("Classe_voyage", rd_aff.getText().toString());
                    editor.putInt("Id_Classe", rd_aff.getId());
                    editor.apply();
                }
                break;
            case R.id.eco:
                if (checked) {
                    editor.putString("Classe_voyage", rd_eco.getText().toString());
                    editor.putInt("Id_Classe", rd_eco.getId());
                    editor.apply();
                }
                break;
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rd_retour:
                if (checked) {
                    linearLayout.setVisibility(View.VISIBLE);
                    type = rd_all_retour.getText().toString();
                    editor.putInt("Id_Type", rd_all_retour.getId());
                    editor.apply();
                }
                break;
            case R.id.rd_aller:
                if (checked) {
                    linearLayout.setVisibility(View.GONE);
                    naissance2.setError(null);
                    naissance2.setText(null);
                    type = rd_all.getText().toString();
                    editor.putInt("Id_Type", rd_all.getId());
                    editor.apply();
                }
                break;
        }
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
            finish();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void remplirp() {

        remplir();
        adapterP = new Liste_pays(this, depart);
        de.setAdapter(adapterP);
        de.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Depart_vol = adapterP.getItem(i);
                editor.putInt("Id_De", i);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
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
        adapterPy = new Liste_pays(this, ariver);
        vers.setAdapter(adapterPy);
        vers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Arrivage_vol = adapterPy.getItem(i);
                editor.putInt("Id_Vers", i);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
        InscriptionActivity.p = 3;

    }

    public void setdate(String date) {
        naissance1.setText(date);
        naissance1.setError(null);
    }

    public void get_date2(View view) {
        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        Calandrier_pop pop = new Calandrier_pop();
        pop.show(manager, null);
        InscriptionActivity.p = 4;

    }

    public void setdate2(String date) {
        naissance2.setText(date);
        naissance2.setError(null);
    }

    public void valide(View view) {
        Naissance1 = naissance1.getText().toString().trim();
        Naissance2 = naissance2.getText().toString().trim();
        try {
            nb_places = Integer.parseInt(nb_place.getText().toString());
        } catch (NumberFormatException nfe) {
            System.out.println("Could not parse " + nfe);
        }
        if (!valider()) {
            Toast.makeText(getApplicationContext(), R.string.verifier_tout_les_champs, Toast.LENGTH_LONG).show();
            if (!err.isEmpty()) {
                Toast.makeText(getApplicationContext(), err, Toast.LENGTH_LONG).show();
                err = "";
            }
        } else {
            if (Exist()) {
                if (Integer.parseInt(vol_s.getNB_places())>=nb_places) {
                    if (vol_s.getDateDeo().equals(Naissance1))//2018-05-25
                    {
                        System.out.println(vol_s.getIdV());
                        if (rd_all_retour.isChecked()) {
                            if (vol_s.getDatAre().equals(Naissance2)) //2018-05-04
                            {
                                remplir_champs();
                                Intent intent = new Intent(this, AchatBilletActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(getApplicationContext(), R.string.date_arr, Toast.LENGTH_LONG).show();
                            }
                        } else {
                            remplir_champs();
                            Intent intent = new Intent(this, AchatBilletActivity.class);
                            startActivity(intent);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.date_dep, Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), R.string.pl_ins, Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), R.string.vol_inexis, Toast.LENGTH_LONG).show();
            }
        }
    }

    public boolean Exist() {
        boolean exist = true;
        for (Vols vol : list_vols) {
            if (vol.getPaysAre().equals(Arrivage_vol) && vol.getPaysDep().equals(Depart_vol)) {
                vol_s = vol;
                exist = true;
                break;
            } else {
                exist = false;
            }
        }
        return exist;
    }

    public void remplir_champs() {
        editor.putString("Date_dep", Naissance1);
        editor.putString("Date_arr", Naissance2);
        editor.putString("Type_voyage", type);
        editor.putString("Depart", Depart_vol);
        editor.putString("Arrivage", Arrivage_vol);
        editor.putInt("Nb_places", nb_places);
        editor.putString("Identife_vol", vol_s.getIdV());
        editor.apply();
    }

    private boolean valider() {
        boolean valide = true;

        if (Naissance1.isEmpty()) {
            naissance1.setError(getString(R.string.champ_obligatoir));
            valide = false;

        }
        if (rd_all_retour.isChecked() && Naissance2.isEmpty()) {
            naissance2.setError(getString(R.string.champ_obligatoir));
            valide = false;
        }
        if (nb_place.getText().toString().isEmpty()) {
            nb_place.setError(getString(R.string.champ_obligatoir));
            valide = false;
        }
        if (!nb_place.getText().toString().isEmpty() && nb_places < 1 || nb_places > 6) {
            nb_place.setError(getString(R.string.nb_place));
            valide = false;
        }
        if (de.getSelectedItem().equals("Choisir...")) {
            err += getString(R.string.pt_dep);
            valide = false;
        }
        if (vers.getSelectedItem().equals("Choisir...")) {
            err += getString(R.string.pt_arr);

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