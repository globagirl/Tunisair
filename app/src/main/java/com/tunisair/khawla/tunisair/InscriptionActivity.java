package com.tunisair.khawla.tunisair;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InscriptionActivity extends AppCompatActivity {
    EditText naisence, nom, prenom, mail, password, conf_password, num_pass, ville, code_postale, adresse;
    String name, prenoms, email, pass, confirme, pasport, Ville, code_p, Adresse, Naisence;
    RadioButton rd_m;
    static int p;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        prefs = getSharedPreferences("Inscription", MODE_PRIVATE);
        editor = prefs.edit();
        // mDatabase = FirebaseDatabase.getInstance().getReference();
        nom = (EditText) findViewById(R.id.nom);
        prenom = (EditText) findViewById(R.id.prenom);
        mail = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        conf_password = (EditText) findViewById(R.id.conf_password);
        num_pass = (EditText) findViewById(R.id.num_pass);
        naisence = (EditText) findViewById(R.id.naissance);
        ville = (EditText) findViewById(R.id.ville);
        code_postale = (EditText) findViewById(R.id.code_post);
        adresse = (EditText) findViewById(R.id.adr_domic);

        rd_m = findViewById(R.id.rd_m);
        rd_m.setChecked(true);
        editor.putString("sexe", rd_m.getText().toString());
        editor.apply();


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
            //for test
            Intent intent = new Intent(this, Inscription2Activity.class);
            startActivity(intent);
        } else {
            remplir_champs();
//            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");
//
//            String userId = mDatabase.push().getKey();
//
//            User user = new User(name,prenoms,email,pass,pasport);
//
//            mDatabase.child(userId).setValue(user);
//
            Intent intent = new Intent(this, Inscription2Activity.class);
            startActivity(intent);

        }

    }
    public void remplir_champs() {
        editor.putString("Nom", name);
        editor.putString("Prenom",prenoms);
        editor.putString("Email", email);
        editor.putString("Password", pass);
        editor.putString("Passport", pasport);
        editor.putString("Ville", pasport);
        editor.putString("Code_Postal", code_p);
        editor.putString("Adresse",Adresse);
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
        if (!email.isEmpty() && (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            mail.setError(getString(R.string.mail_invalide));
            valide = false;
        }
        if (pasport.isEmpty()) {
            num_pass.setError(getString(R.string.champ_obligatoir));
            valide = false;

        }
        if (!pasport.isEmpty() && (pasport.length() != 8)) {
            num_pass.setError(getString(R.string.logure_pasport));
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
        }
    }

    public void get_date(View view) {
        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        Calandrier_pop pop = new Calandrier_pop();
        pop.show(manager, null);
        p=1;
    }

    public void setdate(String date) {
        naisence.setText(date);
        naisence.setError(null);
        editor.putString("Naissence", naisence.getText().toString());
    }


}
