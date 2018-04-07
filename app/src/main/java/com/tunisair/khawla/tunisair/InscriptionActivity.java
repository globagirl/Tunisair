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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InscriptionActivity extends AppCompatActivity {
    EditText naisence,nom,prenom,mail,password,conf_password,num_pass,adresseDo,ville,codepostale,Tel_dom,Tel_prof,Tel_mobile,fax,sociéte,fonction;
    String name,prenoms,email,pass,confirme,pasport,adrDO,villes,postale,teldom,telprof,telmobil,faxs,soc,fonc;
    String pays;
    Spinner spinner;
    CheckBox case1,case2;
    Liste_code_payes adapter;
    String[] codes = new String[199];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

         nom = (EditText) findViewById(R.id.nom);
         prenom= (EditText) findViewById(R.id.prenom);
         mail= (EditText) findViewById(R.id.email);
         password= (EditText) findViewById(R.id.password);
         conf_password= (EditText) findViewById(R.id.conf_password);
         num_pass= (EditText) findViewById(R.id.num_pass);
         naisence= (EditText) findViewById(R.id.naissance);
         codepostale=(EditText) findViewById(R.id.code_post);
         Tel_dom=(EditText) findViewById(R.id.T_domic);
         adresseDo=(EditText) findViewById(R.id.adr_domic);
         ville=(EditText) findViewById(R.id.ville);
         Tel_prof=(EditText) findViewById(R.id.T_prof);
         Tel_mobile=(EditText) findViewById(R.id.T_mobile);
         fax=(EditText) findViewById(R.id.fax);
         sociéte=(EditText) findViewById(R.id.societe);
         fonction=(EditText) findViewById(R.id.fonction);
        case1=(CheckBox) findViewById(R.id.checkBox);
         case2=(CheckBox) findViewById(R.id.checkBox2);
        spinner = (Spinner) findViewById(R.id.code_pays);

    //Appel des methodes
        remplirspinir();
    }
    public void remplirspinir() {

        rempli_code_pays();
        adapter = new Liste_code_payes(this, codes, Constante.imgs);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinner.setSelection(-1);
            }
        });

    }
    //contenu d indicatif pays
    public void rempli_code_pays() {

        try {
            InputStream inputStream = getAssets().open("indicatif_pays.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            int x = 0;
            String ligne;
            while (bufferedReader.ready()) {

                ligne = bufferedReader.readLine();
                codes[x] = ligne;
                x++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

// controle de saisi
    public void verif(View view) {
        name =nom.getText().toString().trim();
        prenoms=prenom.getText().toString().trim();
        email=mail.getText().toString().trim();
        pass=password.getText().toString().trim();
        confirme=conf_password.getText().toString().trim();
        pasport=num_pass.getText().toString().trim();
        adrDO=adresseDo.getText().toString().trim();
        villes=ville.getText().toString().trim();
        postale=codepostale.getText().toString().trim();
        teldom=Tel_dom.getText().toString().trim();
        telprof=Tel_prof.getText().toString().trim();
        telmobil=Tel_mobile.getText().toString().trim();
        faxs=fax.getText().toString().trim();
        soc=sociéte.getText().toString().trim();
        fonc=fonction.getText().toString().trim();

        if (!valider()){
            Toast.makeText(getApplicationContext(),"Veuillez vérifier tout les champs",Toast.LENGTH_LONG).show();
        }
        else{

        }
        Intent intent = new Intent(this, ProfilActivity.class);
        startActivity(intent);
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
        if (confirme.isEmpty()&& confirme!=pass) {
            conf_password.setError("Veuillez vérifier votre Mot de pass");
            valide = false;

        }
        if (email.isEmpty() || (!Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            mail.setError("Veuillez réinsérer votre adresse email");
            valide = false;

        }
        if (pasport.isEmpty()||pasport.length()>8) {
            num_pass.setError("Numéro de passport incorrect");
            valide = false;

        }
        if (adrDO.isEmpty()) {
            adresseDo.setError("Veuillez insérer votre Adresse Domicile");
            valide = false;

        }
        if (villes.isEmpty()) {
            ville.setError("Veuillez insérer votre Ville");
            valide = false;

        }
        if (postale.isEmpty()&& postale.length()<=4 && postale.length()>=6) {
            codepostale.setError("Veuillez insérer votre Code Postale");
            valide = false;

        }
        if (teldom.isEmpty()) {
            Tel_dom.setError("Veuillez insérer votre N° de Téléphone Domicile");
            valide = false;

        }
//        if (telprof.isEmpty()) {
//            Tel_prof.setError("insirer votre numéro de téléphone profitionel");
//            valide = false;
//
//        }
//        if (telmobil.isEmpty()) {
//            Tel_mobile.setError("insirer votre numéro de télephone mobile ");
//            valide = false;
//
//        }
//        if (soc.isEmpty()) {
//            sociéte.setError("insirer votre sociéte au tu travail");
//            valide = false;
//
//        }
//        if (fonc.isEmpty()) {
//            fonction.setError("insirer votre fonctionalité dans la société");
//            valide = false;
//
//        }
        if (! case2.isChecked()){
            Toast.makeText(InscriptionActivity.this,"Veuillez accepter les Conditions Générales du Programme",Toast.LENGTH_SHORT).show();

        }
        return valide;
    }

    public void get_age(View view) {
    FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
    Calandrier_pop pop = new Calandrier_pop();
        pop.show(manager, null);

}
    public void setage(String age) {
        naisence.setText(age);
    }


//    public void openMain(View view) {
//        Intent intent=new Intent(this, LoginActivity.class);
//        startActivity(intent);
//    }
}
