package com.tunisair.khawla.tunisair;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
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
import android.widget.Toast;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;
import com.tunisair.khawla.tunisair.ConfigPaypal.Config;
import com.tunisair.khawla.tunisair.receiver.NetworkStateChangeReceiver;

import org.json.JSONException;

import java.math.BigDecimal;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class MilesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RadioButton rd_type, rd_mile;
    EditText nb_miles;
    TextView total;
    String extenstion = " TND";
    double prrix_mail = 0.1, taux = 0,Total;
    static ACProgressFlower dialoge;
    private BroadcastReceiver mNetworkReceiver;

    private static final int REQUEST_CODE_PAYMENT = 1;
    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_NO_NETWORK)
            .clientId(Config.CONFIG_CLIENT_ID)
            // The following are only used in PayPalFuturePaymentActivity.
            .merchantName("Tuniser")
            .merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
            .merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"));
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miles);
        LoginActivity.NB_Activity=7;
        mNetworkReceiver = new NetworkStateChangeReceiver();
        registerNetworkBroadcastForNougat();

        nb_miles = findViewById(R.id.nb_miles3);
        total = findViewById(R.id.prix_total);

        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);

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

        nb_miles.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                total.setText("00.0" + extenstion);
                Total=0;
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
                        Total=pre_total;
                    } else {
                        double pre_total = Double.valueOf(nb_miles.getText().toString()) * prrix_mail / taux;
                        total.setText(pre_total + extenstion);
                        Total=pre_total;
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
                            Total=pre_total;
                        } else {
                            double pre_total = Double.valueOf(nb_miles.getText().toString()) * prrix_mail / taux;
                            total.setText(pre_total + extenstion);
                            Total=pre_total;
                        }
                    }
                }break;
            case R.id.typePri:
                if (checked) {
                    if (!nb_miles.getText().toString().isEmpty()) {
                        prrix_mail = 0.05;
                        if (taux == 0) {
                            double pre_total = Double.valueOf(nb_miles.getText().toString()) * prrix_mail;
                            total.setText(pre_total + extenstion);
                            Total=pre_total;
                        } else {
                            double pre_total = Double.valueOf(nb_miles.getText().toString()) * prrix_mail / taux;
                            total.setText(pre_total + extenstion);
                            Total=pre_total;
                        }
                    }
                }break;
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
                        Total=pre_total;
                    } else {
                        total.setText("00.0" + extenstion);
                        Total=0;
                    }
                }
                break;
            case R.id.dolar:
                if (checked) {
                    extenstion = " USD";
                    if (!nb_miles.getText().toString().isEmpty()) {
                        taux = 2.6;
                        double pre_total = Double.valueOf(nb_miles.getText().toString()) * prrix_mail / taux;
                        total.setText(pre_total + extenstion);
                        Total=pre_total;
                    } else {
                        total.setText("00.0" + extenstion);
                        Total=0;
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
                        Total=pre_total;
                    } else {
                        total.setText("00.0" + extenstion);
                        Total=0;
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
            SharedPreferences.Editor  Sprefs = getSharedPreferences("Achat_biellet", MODE_PRIVATE).edit();
            Sprefs.clear();
            Sprefs.apply();
            Intent intent = new Intent(this, BilletActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_miles) {

            Intent intent = new Intent(this, MilesActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_cons) {

            Intent intent = new Intent(this, ConsultationActivity.class);
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

    public void achatMile(View view) {
        switch (extenstion){
            case " USD":processPayement(Total);break;
            case " TND":processPayement(Total/2.6);break;
            case " EUR":processPayement(Total*1.16);break;
        }
    }
    private void processPayement(double valuer) {

        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(String.valueOf(valuer)),
                "USD", "Montant", PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payPalPayment);
        startActivityForResult(intent, REQUEST_CODE_PAYMENT);
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
        stopService(new Intent(this, PayPalService.class));

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                PaymentConfirmation confirm = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirm != null) {
                    try {
                        String payemmentDetail = confirm.toJSONObject().toString(4);

                        Toast.makeText(this, " solde insufise", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED)
                Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
        } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
            Toast.makeText(this, "Invalide", Toast.LENGTH_SHORT).show();

        }
    }

}
