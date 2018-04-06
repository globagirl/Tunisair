package com.tunisair.khawla.tunisair;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
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
    EditText naisence;
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
         naisence= (EditText) findViewById(R.id.naissance);




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
