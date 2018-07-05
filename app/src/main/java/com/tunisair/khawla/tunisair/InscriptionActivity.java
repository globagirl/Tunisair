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
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tunisair.khawla.tunisair.receiver.NetworkStateChangeReceiver;

import java.util.ArrayList;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class InscriptionActivity extends AppCompatActivity {
    EditText naisence, nom, prenom, mail, password, conf_password, num_pass, ville, code_postale, adresse;
    String name, prenoms, email, pass, confirme, pasport, Ville, code_p, Adresse, Naisence;
    RadioButton rd_m;
    static int p;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    ArrayList<User> list_users = new ArrayList<>();

    static ACProgressFlower dialoge;
    private BroadcastReceiver mNetworkReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        LoginActivity.NB_Activity=1;
        mNetworkReceiver = new NetworkStateChangeReceiver();
        registerNetworkBroadcastForNougat();
        prefs = getSharedPreferences("Inscription", MODE_PRIVATE);
        editor = prefs.edit();


        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        mail = findViewById(R.id.email);
        password = findViewById(R.id.password);
        conf_password = findViewById(R.id.conf_password);
        num_pass = findViewById(R.id.num_pass);
        naisence = findViewById(R.id.naissance);
        ville = findViewById(R.id.ville);
        code_postale = findViewById(R.id.code_post);
        adresse = findViewById(R.id.adr_domic);

        rd_m = findViewById(R.id.rd_m);
        rd_m.setChecked(true);
        editor.putString("sexe", rd_m.getText().toString());
        editor.apply();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Iterable<DataSnapshot> cheldren = dataSnapshot.getChildren();
                        for (DataSnapshot snapshot : cheldren) {
                            User rep = snapshot.getValue(User.class);
                            list_users.add(rep);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }

                });
            }
        }, 1000);
    }

    // controle de saisi
    public void verif(View view) {
        name = nom.getText().toString().trim();
        prenoms = prenom.getText().toString().trim();
        email = mail.getText().toString().trim();
        pass = password.getText().toString().trim();
        confirme = conf_password.getText().toString().trim();
        pasport = num_pass.getText().toString().trim();
        Ville = ville.getText().toString().trim();
        Adresse = adresse.getText().toString().trim();
        code_p = code_postale.getText().toString().trim();
        Naisence = naisence.getText().toString().trim();
        if (!valider()) {
            Toast.makeText(getApplicationContext(), R.string.verifier_tout_les_champs, Toast.LENGTH_LONG).show();
        } else {
            remplir_champs();
            Intent intent = new Intent(this, Inscription2Activity.class);
            startActivity(intent);
        }
    }

    public void remplir_champs() {
        editor.putString("Nom", name);
        editor.putString("Prenom", prenoms);
        editor.putString("Email", email);
        editor.putString("Password", pass);
        editor.putString("Passport", pasport);
        editor.putString("Ville", pasport);
        editor.putString("Code_Postal", code_p);
        editor.putString("Adresse", Adresse);
        editor.apply();
    }

    // validation et messages d erreur
    private boolean valider() {
        boolean valide = true;

        if (name.isEmpty()) {
            nom.setError(getString(R.string.champ_obligatoir));
            valide = false;

        }
        if (prenoms.isEmpty()) {
            prenom.setError(getString(R.string.champ_obligatoir));
            valide = false;

        }
        if (pass.isEmpty()) {
            password.setError(getString(R.string.champ_obligatoir));
            valide = false;

        }
        if (confirme.isEmpty()) {
            conf_password.setError(getString(R.string.champ_obligatoir));
            valide = false;

        }
        if (!confirme.isEmpty() && (!confirme.contentEquals(pass))) {
            conf_password.setError(getString(R.string.mot_de_passe_identique));
            valide = false;
        }
        if (!pass.isEmpty() && (pass.length() < 6)) {
            password.setError(getString(R.string.longure_mot_de_passe));
            valide = false;
        }
        if (email.isEmpty()) {
            mail.setError(getString(R.string.champ_obligatoir));
            valide = false;

        }
        if (mailexiest(email)) {
            mail.setError(getString(R.string.mail_existe_db));
            valide = false;
        }
        if (!email.isEmpty() && (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            mail.setError(getString(R.string.mail_invalide));
            valide = false;
        }
        if (Ville.isEmpty()) {
            ville.setError(getString(R.string.champ_obligatoir));
            valide = false;

        }
        if (Adresse.isEmpty()) {
            adresse.setError(getString(R.string.champ_obligatoir));
            valide = false;

        }
        if (code_p.isEmpty()) {
            code_postale.setError(getString(R.string.champ_obligatoir));
            valide = false;

        }
        if (Naisence.isEmpty()) {
            naisence.setError(getString(R.string.champ_obligatoir));
            valide = false;

        }
        return valide;
    }

    public boolean mailexiest(String mail) {
        for (User users : list_users) {
            if (users.getEmail().equals(mail)) {
                return true;
            }
        }
        return false;
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rd_mme:
                if (checked) {
                    RadioButton rd_mme = (RadioButton) findViewById(R.id.rd_mme);
                    editor.putString("sexe", rd_mme.getText().toString());
                    editor.apply();
                }
                break;
            case R.id.rd_mle:
                if (checked) {
                    RadioButton rd_mle = (RadioButton) findViewById(R.id.rd_mle);
                    editor.putString("sexe", rd_mle.getText().toString());
                    editor.apply();
                }
                break;
            case R.id.rd_m:
                if (checked) {
                    RadioButton rd_mle = (RadioButton) findViewById(R.id.rd_m);
                    editor.putString("sexe", rd_mle.getText().toString());
                    editor.apply();
                }
                break;
        }
    }

    public void get_date(View view) {
        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        Calandrier_pop pop = new Calandrier_pop();
        pop.show(manager, null);
        p = 1;
    }

    public void setdate(String date) {
        naisence.setText(date);
        naisence.setError(null);
        editor.putString("Naissence", naisence.getText().toString());
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
                    .text("Access Denied..").textColor(Color.WHITE)
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