package com.tunisair.khawla.tunisair;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MilesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RadioButton rd_type, rd_mile;
    EditText nb_miles;
    TextView total;
    String extenstion = " TND";
    double prrix_mail = 0.1, taux = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miles);

        nb_miles = findViewById(R.id.nb_miles3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        rd_type = findViewById(R.id.dinar);
        rd_type.setChecked(true);

        rd_mile = findViewById(R.id.typeQua);
        rd_mile.setChecked(true);
        total = findViewById(R.id.prix_total);

        nb_miles.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                total.setText("00.0" + extenstion);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!nb_miles.getText().toString().isEmpty()) {
                    if (taux == 0) {
                        double pre_total = Double.valueOf(nb_miles.getText().toString()) * 0.1;
                        total.setText(pre_total + extenstion);
                    } else {
                        double pre_total = Double.valueOf(nb_miles.getText().toString()) * prrix_mail / taux;
                        total.setText(pre_total + extenstion);
                    }
                }
            }
        });

    }

    public void onRadioButton_miles(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.typeQua:
                if (checked) {
                    if (!nb_miles.getText().toString().isEmpty()) {
                        prrix_mail = 0.1;
                        if (taux == 0) {
                            double pre_total = Double.valueOf(nb_miles.getText().toString()) * prrix_mail;
                            total.setText(pre_total + extenstion);
                        } else {
                            double pre_total = Double.valueOf(nb_miles.getText().toString()) * prrix_mail / taux;
                            total.setText(pre_total + extenstion);
                        }
                    }
                }
                break;
            case R.id.typePri:
                if (checked) {
                    if (!nb_miles.getText().toString().isEmpty()) {
                        prrix_mail = 0.05;
                        if (taux == 0) {
                            double pre_total = Double.valueOf(nb_miles.getText().toString()) * prrix_mail;
                            total.setText(pre_total + extenstion);
                        } else {
                            double pre_total = Double.valueOf(nb_miles.getText().toString()) * prrix_mail / taux;
                            total.setText(pre_total + extenstion);
                        }
                    }
                }
                break;
        }
    }

    public void onRadioButton_type(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.eruo:
                if (checked) {
                    extenstion = " EUR";
                    if (!nb_miles.getText().toString().isEmpty()) {
                        taux = 3.00;
                        double pre_total = Double.valueOf(nb_miles.getText().toString()) * prrix_mail / taux;
                        total.setText(pre_total + extenstion);
                    }else {
                        total.setText("00.0" + extenstion);
                    }
                }
                break;
            case R.id.dolar:
                if (checked) {
                    extenstion = " USD";
                    if (!nb_miles.getText().toString().isEmpty()) {
                        taux = 2.47;
                        double pre_total = Double.valueOf(nb_miles.getText().toString()) * prrix_mail / taux;
                        total.setText(pre_total + extenstion);
                    }else {
                        total.setText("00.0" + extenstion);
                    }
                }
                break;
            case R.id.dinar:
                if (checked) {
                    extenstion = " TND";
                    if (!nb_miles.getText().toString().isEmpty()) {
                        taux = 0;
                        double pre_total = Double.valueOf(nb_miles.getText().toString()) * prrix_mail;
                        total.setText(pre_total + extenstion);
                    }else {
                        total.setText("00.0" + extenstion);
                    }
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

    public void acheter(View view) {
    }
}
