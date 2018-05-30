package com.tunisair.khawla.tunisair;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tunisair.khawla.tunisair.receiver.NetworkStateChangeReceiver;

import java.util.ArrayList;
import java.util.List;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class AdhessionActivity extends AppCompatActivity {
    //Déclaration
    CheckBox case1, case2;
    EditText autre, site, pointe, agencee;
    String Autre, Site, Pointe, Agencee, nom, sexe, prenom, email, naissence, habitude, besoin, repas;
    String payement, type_adh, classe, lan, pref, date_vol, entet_bielt, num_bielt, pass, num_vol, agence, fonction;
    String societe, cod_tel_fax, tel_fax, cod_tel_mobile, passport, tel_mobile, cod_tel_prof, ville, code_p, tel_prof, adr_dom, natio, pays, tel_dom, cod_tel_dom;
    RadioButton rd_indiv, rd_lang, rd_hub, rd_eco, rd_cache, rd_seul, rd_assis;
    Boolean accepte_mail;
    int NB_miles = 600;
    Spinner Repa;
    User user;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    static ACProgressFlower dialoge;
    private BroadcastReceiver mNetworkReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adhession);

        LoginActivity.NB_Activity=3;
        mNetworkReceiver = new NetworkStateChangeReceiver();
        registerNetworkBroadcastForNougat();
        prefs = getSharedPreferences("Inscription", MODE_PRIVATE);
        editor = prefs.edit();

        reference = FirebaseDatabase.getInstance().getReference("users");
        mAuth = FirebaseAuth.getInstance();
        autre = (EditText) findViewById(R.id.autre);
        agencee = (EditText) findViewById(R.id.agence_habit);
        pointe = (EditText) findViewById(R.id.point);
        site = (EditText) findViewById(R.id.site);
        Repa = (Spinner) findViewById(R.id.spinner);

        rd_indiv = (RadioButton) findViewById(R.id.indiv);
        rd_indiv.setChecked(true);
        editor.putString("Type_adhession", rd_indiv.getText().toString());

        rd_lang = (RadioButton) findViewById(R.id.fr);
        rd_lang.setChecked(true);
        editor.putString("Langue", rd_lang.getText().toString());

        rd_hub = (RadioButton) findViewById(R.id.hublot);
        rd_hub.setChecked(true);
        editor.putString("Preference", rd_hub.getText().toString());

        rd_eco = (RadioButton) findViewById(R.id.eco);
        rd_eco.setChecked(true);
        editor.putString("Classe_voyage", rd_eco.getText().toString());

        rd_cache = (RadioButton) findViewById(R.id.cashe);
        rd_cache.setChecked(true);
        editor.putString("Mode_pays", rd_cache.getText().toString());

        rd_seul = (RadioButton) findViewById(R.id.seul);
        rd_seul.setChecked(true);
        editor.putString("Habitude", rd_seul.getText().toString());

        rd_assis = (RadioButton) findViewById(R.id.assis);
        rd_assis.setChecked(true);
        editor.putString("Assistance", rd_assis.getText().toString());

        editor.apply();
        case1 = (CheckBox) findViewById(R.id.checkBox);
        case2 = (CheckBox) findViewById(R.id.checkBox2);

        // Remplissage et récupération de valeur du spiner "repas"
        final List<String> spinerarray = new ArrayList<String>();
        spinerarray.add("Bébé");
        spinerarray.add("Sans sel");
        spinerarray.add("Diabète");
        spinerarray.add("Cachére");
        ArrayAdapter<String> adapterR = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinerarray);
        adapterR.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Repa.setAdapter(adapterR);
        Repa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                repas = Repa.getSelectedItem().toString();
                editor.putString("Repas", repas);
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Repa.setSelection(187);
            }
        });
    }

    public void onRadioButton_type(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.fami:
                if (checked) {
                    RadioButton rd_fami = (RadioButton) findViewById(R.id.fami);
                    editor.putString("Type_adhession", rd_fami.getText().toString());
                    editor.apply();
                }
                break;
            case R.id.indiv:
                if (checked) {
                    RadioButton rd_fami = (RadioButton) findViewById(R.id.indiv);
                    editor.putString("Type_adhession", rd_fami.getText().toString());
                    editor.apply();
                }
                break;
        }
    }

    public void onRadioButton_langue(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.en:
                if (checked) {
                    RadioButton rd_l = (RadioButton) findViewById(R.id.en);
                    editor.putString("Langue", rd_l.getText().toString());
                    editor.apply();
                }
                break;
            case R.id.ar:
                if (checked) {
                    RadioButton rd_l = (RadioButton) findViewById(R.id.ar);
                    editor.putString("Langue", rd_l.getText().toString());
                    editor.apply();
                }
                break;
            case R.id.fr:
                if (checked) {
                    RadioButton rd_l = (RadioButton) findViewById(R.id.fr);
                    editor.putString("Langue", rd_l.getText().toString());
                    editor.apply();
                }
                break;
        }
    }

    public void onRadioButton_prefe(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.couloir:
                if (checked) {
                    RadioButton rd_l = (RadioButton) findViewById(R.id.couloir);
                    editor.putString("Preference", rd_l.getText().toString());
                    editor.apply();
                }
                break;
            case R.id.indif:
                if (checked) {
                    RadioButton rd_l = (RadioButton) findViewById(R.id.indif);
                    editor.putString("Preference", rd_l.getText().toString());
                    editor.apply();
                }
                break;
            case R.id.hublot:
                if (checked) {
                    RadioButton rd_l = (RadioButton) findViewById(R.id.hublot);
                    editor.putString("Preference", rd_l.getText().toString());
                    editor.apply();
                }
                break;
        }
    }

    public void onRadioButton_classe(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.affaire:
                if (checked) {
                    RadioButton rd_aff = (RadioButton) findViewById(R.id.affaire);
                    editor.putString("Classe_voyage", rd_aff.getText().toString());
                    editor.apply();
                }
                break;
            case R.id.eco:
                if (checked) {
                    RadioButton rd_aff = (RadioButton) findViewById(R.id.eco);
                    editor.putString("Classe_voyage", rd_aff.getText().toString());
                    editor.apply();
                }
                break;
        }
    }

    public void onRadioButton_mode(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.cheque:
                if (checked) {
                    RadioButton rd_mode = (RadioButton) findViewById(R.id.cheque);
                    editor.putString("Mode_pays", rd_mode.getText().toString());
                    editor.apply();
                }
                break;
            case R.id.carte:
                if (checked) {
                    RadioButton rd_mode = (RadioButton) findViewById(R.id.carte);
                    editor.putString("Mode_pays", rd_mode.getText().toString());
                    editor.apply();
                }
                break;
            case R.id.cashe:
                if (checked) {
                    RadioButton rd_mode = (RadioButton) findViewById(R.id.cashe);
                    editor.putString("Mode_pays", rd_mode.getText().toString());
                    editor.apply();
                }
                break;
        }
    }

    public void onRadioButton_habi(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.accomp:
                if (checked) {
                    RadioButton rd_hab = (RadioButton) findViewById(R.id.accomp);
                    editor.putString("Habitude", rd_hab.getText().toString());
                    editor.apply();
                }
                break;
            case R.id.seul:
                if (checked) {
                    RadioButton rd_hab = (RadioButton) findViewById(R.id.seul);
                    editor.putString("Habitude", rd_hab.getText().toString());
                    editor.apply();
                }
                break;
        }
    }

    public void onRadioButton_assistance(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.noAssis:
                if (checked) {
                    RadioButton rd_no = findViewById(R.id.noAssis);
                    editor.putString("Assistance", rd_no.getText().toString());
                    editor.apply();
                }
                break;
            case R.id.assis:
                if (checked) {
                    RadioButton rd_no = findViewById(R.id.assis);
                    editor.putString("Assistance", rd_no.getText().toString());
                    editor.apply();
                }
                break;
        }
    }

    public void terminer(View view) {
        Agencee = agencee.getText().toString().trim();
        Site = site.getText().toString().trim();
        Autre = autre.getText().toString().trim();
        Pointe = pointe.getText().toString().trim();
        recupechmps();
        String tel_dom_final = cod_tel_dom + " " + tel_dom;
        String tel_pro_final = cod_tel_prof + " " + tel_prof;
        String tel_mobil_final = cod_tel_mobile + " " + tel_mobile;
        String tel_fax_final = cod_tel_fax + " " + tel_fax;
        String num_biellt_final = entet_bielt + "/" + num_bielt;

        if (valider()) {
            user = new User(nom, prenom, sexe, naissence, email, pass, passport, ville, code_p, adr_dom, natio,
                    pays, tel_dom_final, tel_pro_final, tel_mobil_final, tel_fax_final, societe, fonction, num_vol, date_vol,
                    num_biellt_final, agence, type_adh, Autre, lan, Agencee, Pointe, Site, pref, classe, payement, habitude, besoin, repas, NB_miles, accepte_mail);
            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                remplir_champs();
                                reference.push().setValue(user);
                                Intent intent = new Intent(getApplicationContext(), ProfilActivity.class);
                                startActivity(intent);
                            }
                        }
                    });
        }

    }

    public void remplir_champs() {
        if (!passport.isEmpty()) {
            NB_miles += 100;
        }
        if (!tel_mobile.isEmpty()) {
            NB_miles += 100;
        }
        if (!natio.equals("Choisire votre payés..")) {
            NB_miles += 100;
        }
        editor.putInt("Num_miles", NB_miles);
        editor.putString("Agence_habit", Agencee);
        editor.putString("Site", Site);
        editor.putString("Autre_prog", Autre);
        editor.putString("Pointe", Pointe);
        if (case1.isChecked()) {
            editor.putBoolean("Accepter_mail", true);
        } else {
            editor.putBoolean("Accepter_mail", false);
        }
        editor.apply();
    }

    private boolean valider() {
        boolean valide = true;

        if (!case2.isChecked()) {
            Toast.makeText(AdhessionActivity.this, R.string.accepter_les_conditions, Toast.LENGTH_SHORT).show();
            valide = false;
        }

        return valide;
    }

    private void recupechmps() {
        nom = prefs.getString("Nom", "empty");
        sexe = prefs.getString("sexe", "empty");
        prenom = prefs.getString("Prenom", "empty");
        email = prefs.getString("Email", "empty");
        naissence = prefs.getString("Naissence", "empty");
        pass = prefs.getString("Password", "empty");
        passport = prefs.getString("Passport", "empty");
        ville = prefs.getString("Ville", "empty");
        code_p = prefs.getString("Code_Postal", "empty");
        adr_dom = prefs.getString("Adresse", "empty");
        natio = prefs.getString("Nationalite", "");
        pays = prefs.getString("Pays", "");
        tel_dom = prefs.getString("Tel_dom", "empty");
        cod_tel_dom = prefs.getString("Tel_domicile", "empty");
        tel_prof = prefs.getString("Tel_prof", "");
        cod_tel_prof = prefs.getString("Tel_profe", "");
        tel_mobile = prefs.getString("Tel_mobile", "");
        cod_tel_mobile = prefs.getString("Tel_Mobile", "");
        tel_fax = prefs.getString("Tel_fax", "");
        cod_tel_fax = prefs.getString("Tel_faxe", "");
        societe = prefs.getString("Societe", "");
        fonction = prefs.getString("Fonction", "");
        agence = prefs.getString("Agence", "");
        num_vol = prefs.getString("Num_vol", "");
        num_bielt = prefs.getString("Num_bielt", "");
        entet_bielt = prefs.getString("Entet_bielt", "");
        date_vol = prefs.getString("Date_vol", "");
        type_adh = prefs.getString("Type_adhession", "");
        lan = prefs.getString("Langue", "");
        pref = prefs.getString("Preference", "");
        classe = prefs.getString("Classe_voyage", "");
        payement = prefs.getString("Mode_pays", "");
        habitude = prefs.getString("Habitude", "");
        besoin = prefs.getString("Assistance", "");
        accepte_mail = prefs.getBoolean("Accepter_mail", false);
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
