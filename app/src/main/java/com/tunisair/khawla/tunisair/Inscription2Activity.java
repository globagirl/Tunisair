package com.tunisair.khawla.tunisair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Inscription2Activity extends AppCompatActivity {
    EditText adresseDo,ville,codepostale,Tel_dom,Tel_prof,Tel_mobile,fax,sociéte,fonction;
    String adrDO,villes,postale,teldom,telprof,telmobil,faxs,soc,fonc,co,nat,pa;
    Liste_code_payes adapter;
    Liste_nationnalité adapterN;
    Liste_pays adapterP;
    String[] codes = new String[199];
    String[] nation=new String[175];
    String[] pay=new String[199];
    Spinner SpiCode,SpiNatio,SpiPays,Spicode2;
    Spinner Spicode3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription2);

        codepostale=(EditText) findViewById(R.id.code_post);
        Tel_dom=(EditText) findViewById(R.id.T_domic);
        adresseDo=(EditText) findViewById(R.id.adr_domic);
        ville=(EditText) findViewById(R.id.ville);
        Tel_prof=(EditText) findViewById(R.id.T_prof);
        Tel_mobile=(EditText) findViewById(R.id.T_mobile);
        fax=(EditText) findViewById(R.id.fax);
        sociéte=(EditText) findViewById(R.id.societe);
        fonction=(EditText) findViewById(R.id.fonction);
        SpiCode = (Spinner) findViewById(R.id.code_pays);
        SpiNatio=(Spinner) findViewById(R.id.natio);
        SpiPays = (Spinner) findViewById(R.id.pays);
        Spicode2=(Spinner) findViewById(R.id.code_pays_prof);
        Spicode3= (Spinner)findViewById(R.id.code_pays_mob) ;

        //Appel des methodes
        remplirspinir();
        remplirnatio();
        remplirpays();
    }

    public void remplirspinir() {

        rempli_code_pays();
        adapter = new Liste_code_payes(this, codes, Constante.imgs);
        SpiCode.setAdapter(adapter);
        SpiCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                SpiCode.setSelection(-1);
            }
        });

        adapter = new Liste_code_payes(this, codes, Constante.imgs);
        Spicode2.setAdapter(adapter);
        Spicode2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Spicode2.setSelection(-1);
            }
        });

        adapter = new Liste_code_payes(this, codes, Constante.imgs);
        Spicode3.setAdapter(adapter);
        Spicode3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Spicode3.setSelection(-1);
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
    // remplir liste nationalité
    public void remplirnatio() {

        rempli_natio();
        adapterN = new Liste_nationnalité(this,nation);
        SpiNatio.setAdapter(adapterN);
        SpiNatio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                SpiNatio.setSelection(-1);
            }
        });

    }
    //contenu de nationalité
    public void rempli_natio() {

        try {
            InputStream inputStream = getAssets().open("nationnalité.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            int x = 0;
            String ligne;
            while (bufferedReader.ready()) {

                ligne = bufferedReader.readLine();
                nation[x] = ligne;
                x++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // remplir liste pays
    public void remplirpays() {

        rempli_pays();
        adapterP = new Liste_pays(this,pay);
        SpiPays.setAdapter(adapterP);
        SpiPays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                SpiPays.setSelection(-1);
            }
        });

    }
    //contenu de pays
    public void rempli_pays() {

        try {
            InputStream inputStream = getAssets().open("payes.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            int x = 0;
            String ligne;
            while (bufferedReader.ready()) {

                ligne = bufferedReader.readLine();
                pay[x] = ligne;
                x++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void passer(View view) {
        Intent intent = new Intent(this, AdhessionActivity.class);
        startActivity(intent);
    }

    public void veriff(View view) {
        nat=Spicode2.getSelectedItem().toString();
        adrDO=adresseDo.getText().toString().trim();
        villes=ville.getText().toString().trim();
        postale=codepostale.getText().toString().trim();
        co=SpiCode.getSelectedItem().toString();
        teldom=Tel_dom.getText().toString().trim();
        telprof=Tel_prof.getText().toString().trim();
        telmobil=Tel_mobile.getText().toString().trim();
        pa=Spicode3.getSelectedItem().toString();
        faxs=fax.getText().toString().trim();
        soc=sociéte.getText().toString().trim();
        fonc=fonction.getText().toString().trim();

        if (!valider()){
            Toast.makeText(getApplicationContext(),"Veuillez vérifier tout les champs",Toast.LENGTH_LONG).show();
        }
        else{
            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");

            String userId = mDatabase.push().getKey();

            User user = new User(adrDO,villes,postale,teldom,telprof,telmobil,faxs,soc,fonc,nat,pa);

            mDatabase.child(userId).setValue(user);

            Intent intent = new Intent(this, AdhessionActivity.class);
            startActivity(intent);
        }
    }

    // validation et messages d erreur
    private boolean valider() {
        boolean valide = true;
        if (adrDO.isEmpty()) {
            adresseDo.setError("Veuillez insérer votre Adresse Domicile");
            valide = false;

        }
        if (villes.isEmpty()) {
            ville.setError("Veuillez insérer votre Ville");
            valide = false;

        }
        if (postale.isEmpty() || postale.length() <= 4 && postale.length() >= 6) {
            codepostale.setError("Veuillez vérifier votre Code Postale");
            valide = false;

        }
        if (teldom.isEmpty()) {
            Tel_dom.setError("Veuillez insérer votre N° de Téléphone Domicile");
            valide = false;

        }
        return valide;
    }

}
