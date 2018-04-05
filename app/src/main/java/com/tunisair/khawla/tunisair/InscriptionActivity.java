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


    }


//    public void openMain(View view) {
//        Intent intent=new Intent(this, LoginActivity.class);
//        startActivity(intent);
//    }
}
