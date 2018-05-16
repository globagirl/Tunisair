package com.tunisair.khawla.tunisair;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class InscriptionActivity extends AppCompatActivity {
    EditText naisence, nom, prenom, mail, password, conf_password, num_pass, ville, code_postale, adresse;
    String name, prenoms, email, pass, confirme, pasport, Ville, code_p, Adresse, Naisence;
    RadioButton rd_m;
    static int p;
    boolean use;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        prefs = getSharedPreferences("Inscription", MODE_PRIVATE);
        editor = prefs.edit();
        nom =  findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        mail =  findViewById(R.id.email);
        password =findViewById(R.id.password);
        conf_password =  findViewById(R.id.conf_password);
        num_pass =  findViewById(R.id.num_pass);
        naisence = findViewById(R.id.naissance);
        ville = findViewById(R.id.ville);
        code_postale =  findViewById(R.id.code_post);
        adresse =  findViewById(R.id.adr_domic);

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
            //Toast.makeText(getApplicationContext(), R.string.verifier_tout_les_champs, Toast.LENGTH_LONG).show();
        } else {
            remplir_champs();
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

        //Toast.makeText(getApplicationContext(),use+"",Toast.LENGTH_LONG).show();
//         if(use==true){
//             mail.setError("Votre email es deja utiliser");
//            use=false;
//         }
        if (getUser()) {
            mail.setError("Votre email es deja utiliser");
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
    private boolean getUser() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query query = reference.orderByChild("email").equalTo(email);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    use=true;
                }else {
                    use=false;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return use;
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
        p=1;
    }

    public void setdate(String date) {
        naisence.setText(date);
        naisence.setError(null);
        editor.putString("Naissence", naisence.getText().toString());
    }
}