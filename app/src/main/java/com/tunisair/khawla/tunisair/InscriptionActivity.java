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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InscriptionActivity extends AppCompatActivity {
    EditText naisence,nom,prenom,mail,password,conf_password,num_pass,adresseDo,ville,codepostale,Tel_dom,Tel_prof,Tel_mobile,fax,sociéte,fonction;
    String name,prenoms,email,pass,confirme,pasport,adrDO,villes,postale,teldom,telprof,telmobil,faxs,soc,fonc;
    String pays;
    CheckBox case1,case2;
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



    }
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
            Toast.makeText(getApplicationContext(),"verifier tout les champs",Toast.LENGTH_LONG).show();
        }else{

        }
    }


    private boolean valider() {
        boolean valide = true;
        if (name.isEmpty() ) {
            nom.setError("insirer votre nom");
            valide = false;

        }
        if (prenoms.isEmpty()) {
            prenom.setError("insirer votre prenom");
            valide = false;

        }
        if (pass.isEmpty()) {
            password.setError("insirer votre password");
            valide = false;

        }
        if (confirme.isEmpty()&& confirme!=pass) {
            conf_password.setError("vérifier votre password");
            valide = false;

        }
        if (email.isEmpty() || (!Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            mail.setError("insirer votre mail");
            valide = false;

        }
        if (pasport.isEmpty()||pasport.length()>8) {
            num_pass.setError("insirer votre numéro pasport");
            valide = false;

        }
        if (adrDO.isEmpty()) {
            adresseDo.setError("insirer votre adresse domicile");
            valide = false;

        }
        if (villes.isEmpty()) {
            ville.setError("insirer votre ville");
            valide = false;

        }
        if (postale.isEmpty()&& postale.length()<=4 && postale.length()>=6) {
            codepostale.setError("insirer votre code postale");
            valide = false;

        }
        if (teldom.isEmpty()) {
            Tel_dom.setError("insirer votre numéro de téléphone domicile");
            valide = false;

        }
        if (telprof.isEmpty()) {
            Tel_prof.setError("insirer votre numéro de téléphone profitionel");
            valide = false;

        }
        if (telmobil.isEmpty()) {
            Tel_mobile.setError("insirer votre numéro de télephone mobile ");
            valide = false;

        }
        if (soc.isEmpty()) {
            sociéte.setError("insirer votre sociéte au tu travail");
            valide = false;

        }
        if (fonc.isEmpty()) {
            fonction.setError("insirer votre fonctionalité dans la société");
            valide = false;

        }
        if (case1.isChecked()&& case2.isChecked()){
            Toast.makeText(InscriptionActivity.this,"il faut cocher une seule case",Toast.LENGTH_SHORT).show();

        }
        else if (!(case1.isChecked())&&!(case2.isChecked())){
            Toast.makeText(InscriptionActivity.this,"sélectioner une case",Toast.LENGTH_SHORT).show();

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
