package com.tunisair.khawla.tunisair;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.tunisair.khawla.tunisair.receiver.NetworkStateChangeReceiver;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class AchatBilletActivity extends AppCompatActivity {

    static final Double POURCENTAGE_AFFAIRE = 0.25;
    SharedPreferences prefs;
    TextView Depart, Arrivage, Date_Depart, Date_Arrivage, Classe, Nb_places;
    TextView PrixDepart, PrixArrivage, PrixClasse, PrixTotale, PrixTotale_P;
    int restorednbplac;
    String restoreddatreto, restoredclass;
    ACProgressFlower dialog;
    static ACProgressFlower dialoge;
    private BroadcastReceiver mNetworkReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achat_billet);

        LoginActivity.NB_Activity=6;
        mNetworkReceiver = new NetworkStateChangeReceiver();
        registerNetworkBroadcastForNougat();
        prefs = getSharedPreferences("Achat_biellet", MODE_PRIVATE);

        Depart = (TextView) findViewById(R.id.txt_depart);
        Arrivage = (TextView) findViewById(R.id.txt_arriv);
        Date_Depart = (TextView) findViewById(R.id.txt_dat_dep);
        Date_Arrivage = (TextView) findViewById(R.id.txt_dat_retour);
        Classe = (TextView) findViewById(R.id.txt_classe);
        Nb_places = (TextView) findViewById(R.id.txt_nb_place);

        PrixDepart = (TextView) findViewById(R.id.prix_dep);
        PrixArrivage = (TextView) findViewById(R.id.prix_retour);
        PrixClasse = (TextView) findViewById(R.id.prix_class);
        PrixTotale_P = (TextView) findViewById(R.id.txt_total1);
        PrixTotale = (TextView) findViewById(R.id.txt_total);
        dialog = new ACProgressFlower.Builder(this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .text("Loading...").textColor(Color.WHITE)
                .fadeColor(Color.DKGRAY).build();
        dialog.show();
        restorde_donner();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String ident = prefs.getString("Identife_vol", "empty");
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Vols");
                Query query = reference.orderByChild("IdV").equalTo(ident);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Vols vol = snapshot.getValue(Vols.class);
                                double Prixvole = Double.parseDouble(vol.getPrix_A());
                                double Prixretoure = Double.parseDouble(vol.getPrix_A_R());
                                double PrixclassA = Double.parseDouble(vol.getPrix_Cal_A());
                                double PrixclassAR = Double.parseDouble(vol.getPrix_Cal_A_R());
                                double Prixsoupliment=0;
                                double Prixtotale = 0;

                                PrixDepart.setText("+" + Prixvole);
                                if (restoreddatreto.equals("")) {
                                    Prixretoure = 0;
                                    PrixArrivage.setText("+" + Prixretoure);
                                } else {
                                    PrixArrivage.setText("+" + Prixretoure);
                                }
                                if (restoredclass.equals(getString(R.string.economique))) {
                                        PrixClasse.setText("+" + Prixsoupliment);
                                } else {
                                    Prixsoupliment = Prixvole * POURCENTAGE_AFFAIRE;
                                    PrixClasse.setText("+" + Prixsoupliment);
                                }

                                if (restoreddatreto.equals("")) {
                                    Prixtotale = Prixretoure + Prixvole;
                                    PrixTotale_P.setText( String.valueOf(Prixtotale));
                                } else {
                                    Prixtotale = Prixsoupliment + Prixretoure + Prixvole;
                                    PrixTotale_P.setText(String.valueOf(Prixtotale));
                                }
                                if (restorednbplac != 0) {
                                    PrixTotale.setText(String.valueOf( Prixtotale * restorednbplac));
                                }
                            }
                            dialog.dismiss();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }, 1000);

    }

    public void restorde_donner() {
        String restoreddepar = prefs.getString("Depart", null);
        String restoredarivage = prefs.getString("Arrivage", null);
        String restoreddatdep = prefs.getString("Date_dep", null);
        restoreddatreto = prefs.getString("Date_arr", "empty");
        restoredclass = prefs.getString("Classe_voyage", null);
        restorednbplac = prefs.getInt("Nb_places", 0);

        if (restoreddepar != null) {
            Depart.setText(restoreddepar);
        }
        if (restoredarivage != null) {
            Arrivage.setText(restoredarivage);
        }
        if (restoreddatdep != null) {
            Date_Depart.setText(restoreddatdep);
        }
        if (!restoreddatreto.equals("")) {
            Date_Arrivage.setText(restoreddatreto);
        } else {
            String text = "<font color='#960000'><b>Sans Retoure</b></font>";
            Date_Arrivage.setText(Html.fromHtml(text));
        }
        if (restoredclass != null) {
            Classe.setText(restoredclass);
        }
        if (restorednbplac != 0) {
            Nb_places.setText(String.valueOf(restorednbplac));
        }
    }

    public void retour(View view) {
        Intent intent = new Intent(this, BilletActivity.class);
        startActivity(intent);
    }

    public void acheter(View view) {
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
