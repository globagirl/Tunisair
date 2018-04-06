package com.tunisair.khawla.tunisair;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InscriptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        EditText nom = (EditText) findViewById(R.id.nom);
        EditText prenom= (EditText) findViewById(R.id.prenom);
        EditText mail= (EditText) findViewById(R.id.email);
        EditText password= (EditText) findViewById(R.id.password);
        EditText conf_password= (EditText) findViewById(R.id.conf_password);
        EditText num_pass= (EditText) findViewById(R.id.num_pass);

        protected void onclick(View v){
            if (nom.getText().toString().equals("")){


            }
            // On déclare le pattern que l’on doit vérifier
            Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
            // On déclare un matcher, qui comparera le pattern avec la
            // string passée en argument
            Matcher m = p.matcher((CharSequence) nom);
            // Si l’adresse mail saisie ne correspond au format d’une
            // adresse mail on un affiche un message à l'utilisateur
            if (!m.matches()) {
                // Toast est une classe fournie par le SDK Android
                // pour afficher les messages (indications) à l'intention de
                // l'utilisateur. Ces messages ne possédent pas d'interaction avec l'utilisateur
                // Le premier argument représente le contexte, puis
                // le message et à la fin la durée d'affichage du Toast (constante
                // LENGTH_SHORT ou LENGTH_LONG). Sans oublier d'appeler la méthode
                //show pour afficher le Toast
                return;



        }
    }

    }


//    public void openMain(View view) {
//        Intent intent=new Intent(this, LoginActivity.class);
//        startActivity(intent);
//    }
}
