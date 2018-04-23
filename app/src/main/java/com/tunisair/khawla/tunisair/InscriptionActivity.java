package com.tunisair.khawla.tunisair;

import android.app.ProgressDialog;
import android.content.Intent;
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
    EditText naisence,nom,prenom,mail,password,conf_password,num_pass;
    String name,prenoms,email,pass,confirme,pasport;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

       // mDatabase = FirebaseDatabase.getInstance().getReference();
         nom = (EditText) findViewById(R.id.nom);
         prenom= (EditText) findViewById(R.id.prenom);
         mail= (EditText) findViewById(R.id.email);
         password= (EditText) findViewById(R.id.password);
         conf_password= (EditText) findViewById(R.id.conf_password);
         num_pass= (EditText) findViewById(R.id.num_pass);
         naisence= (EditText) findViewById(R.id.naissance);


    }

    public void back(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
// controle de saisi
    public void verif(View view) {
        name =nom.getText().toString().trim();
        prenoms=prenom.getText().toString().trim();
        email=mail.getText().toString().trim();
        pass=password.getText().toString().trim();
        confirme=conf_password.getText().toString().trim();
        pasport=num_pass.getText().toString().trim();


        if (!valider()){
            Toast.makeText(getApplicationContext(),"Veuillez vérifier tout les champs",Toast.LENGTH_LONG).show();

            //for test
            Intent intent = new Intent(this, Inscription2Activity.class);
            startActivity(intent);
        }
        else{
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");

            String userId = mDatabase.push().getKey();

            User user = new User(name,prenoms,email,pass,pasport);

            mDatabase.child(userId).setValue(user);

            Intent intent = new Intent(this, Inscription2Activity.class);
            startActivity(intent);
        }

    }

// validation et messages d erreur
    private boolean valider() {
            boolean valide = true;
            if (name.isEmpty() ) {
                nom.setError("Veuillez insérer votre Nom");
                valide = false;

            }
            if (prenoms.isEmpty()) {
                prenom.setError("Veuillez insérer votre Prénom");
                valide = false;

            }
            if (pass.isEmpty()) {
                password.setError("Veuillez insérer votre Mot de pass");
                valide = false;

            }
            if (confirme.isEmpty() ) {
                conf_password.setError("Veuillez vérifier votre Mot de pass");
                valide = false;

            }
            if (!confirme.equals(pass)) {
                conf_password.setError("Veuillez vérifier votre Mot de pass");
                valide = false;

            }
            if (email.isEmpty() || (!Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
                mail.setError("Veuillez réinsérer votre adresse email");
                valide = false;

            }
            if (pasport.isEmpty()||pasport.length()>8) {
                num_pass.setError("Veuillez vérifier votre N° de Passport");
                valide = false;

            }

        return valide;
    }

    public void get_date(View view) {
        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        Calandrier_pop pop = new Calandrier_pop();
        pop.show(manager, null);

    }
    public void setdate(String date) {
        naisence.setText(date);
    }


}
