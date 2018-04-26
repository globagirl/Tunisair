package com.tunisair.khawla.tunisair;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Inscription2Activity extends AppCompatActivity {
    EditText Tel_dom,Tel_prof,Tel_mobile,fax,sociéte,fonction,num_bielt,entet_bielt,agence,num_vole,dat_vol;
    String teldom,telprof,telmobil,faxs,num_bielts,entet_bielts,Agence,Num_vole,Dat_vol;
    boolean chek=false;
    Liste_code_payes adapter;
    Liste_nationnalité adapterN;
    Liste_pays adapterP;
    String[] codes = new String[199];
    String[] nation=new String[194];
    String[] pay=new String[199];
    Spinner SpiCode,SpiNatio,SpiPays,Spicode2,Spicode3,Spicode4;
    LinearLayout li_voyage;
    CheckBox bt_chek;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription2);

        prefs = getSharedPreferences("Inscription", MODE_PRIVATE);
        editor = prefs.edit();

        String val=prefs.getString("Nom","empty");
        String val2= prefs.getString("sexe","empty");
        String val3= prefs.getString("Prenom","empty");
        String val4=prefs.getString("Email","empty");
        String val5= prefs.getString("Password","empty");
        String val6= prefs.getString("Passport","empty");

        String val7=prefs.getString("Ville","empty");
        String val8= prefs.getString("Code_Postal","empty");
        String val9= prefs.getString("Adresse","empty");

        Toast.makeText(getApplicationContext(),val+"//"+val2+"//"+val3+"//"+val4+"//"+val5+"//"+val6+"//"+val7+"//"+val8+"//"+val9,Toast.LENGTH_LONG).show();
        Tel_dom=(EditText) findViewById(R.id.T_domic);
        Tel_prof=(EditText) findViewById(R.id.T_prof);
        Tel_mobile=(EditText) findViewById(R.id.T_mobile);
        fax=(EditText) findViewById(R.id.fax);
        sociéte=(EditText) findViewById(R.id.societe);
        fonction=(EditText) findViewById(R.id.fonction);
        SpiNatio=(Spinner) findViewById(R.id.natio);
        SpiPays = (Spinner) findViewById(R.id.pays);
        SpiCode = (Spinner) findViewById(R.id.code_pays_domic);
        Spicode2=(Spinner) findViewById(R.id.code_pays_prof);
        Spicode3= (Spinner)findViewById(R.id.code_pays_mob) ;
        Spicode4= (Spinner)findViewById(R.id.code_pays_fax) ;
        li_voyage= (LinearLayout) findViewById(R.id.linear_voyage) ;
        bt_chek= (CheckBox) findViewById(R.id.check_voyage) ;

        num_bielt=(EditText) findViewById(R.id.num_bielt2);
        entet_bielt=(EditText) findViewById(R.id.num_bielt);
        dat_vol=(EditText) findViewById(R.id.date);
        agence=(EditText) findViewById(R.id.agence);
        num_vole=(EditText) findViewById(R.id.Nvol);

        //Appel des methodes
        remplirpays();
        remplirspinir();
        remplirnatio();
    }
    public void onCheckboxClicked(View view) {
        CheckBox checkBox = (CheckBox) view;
        boolean checked = checkBox.isChecked();
        if(checked){
            agence.setError(null);
            dat_vol.setError(null);
            num_bielt.setError(null);
            entet_bielt.setError(null);
            num_vole.setError(null);
            li_voyage.setVisibility(View.GONE);
            chek=true;
        }else {
            li_voyage.setVisibility(View.VISIBLE);
            chek=false;
        }
    }
    public void remplirspinir() {

        rempli_code_pays();
        adapter = new Liste_code_payes(this, codes, Constante.imgs);
        SpiCode.setAdapter(adapter);
        SpiCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView code = (TextView) view.findViewById(R.id.code_payes);
                editor.putString("Tel_domicile",code.getText().toString());
                editor.apply();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {SpiCode.setSelection(187);
            }
        });

        adapter = new Liste_code_payes(this, codes, Constante.imgs);
        Spicode2.setAdapter(adapter);
        Spicode2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView code = (TextView) view.findViewById(R.id.code_payes);
                editor.putString("Tel_profe",code.getText().toString());
                editor.apply();
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
                TextView code = (TextView) view.findViewById(R.id.code_payes);
                editor.putString("Tel_Mobile",code.getText().toString());
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Spicode3.setSelection(-1);
            }
        });
        adapter = new Liste_code_payes(this, codes, Constante.imgs);
        Spicode4.setAdapter(adapter);
        Spicode4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                TextView code = (TextView) view.findViewById(R.id.code_payes);
                editor.putString("Tel_faxe",code.getText().toString());
                editor.apply();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Spicode4.setSelection(-1);
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
                TextView natio = (TextView) view.findViewById(R.id.code_payes);
                editor.putString("Nationalite",natio.getText().toString());
                editor.apply();
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

                TextView pays = (TextView) view.findViewById(R.id.code_payes);
                editor.putString("Pays",pays.getText().toString());
                editor.apply();
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
    public void veriff(View view) {
        teldom=Tel_dom.getText().toString().trim();
        telprof=Tel_prof.getText().toString().trim();
        telmobil=Tel_mobile.getText().toString().trim();
        faxs=fax.getText().toString().trim();

        entet_bielts=entet_bielt.getText().toString().trim();
        num_bielts=num_bielt.getText().toString().trim();
        Num_vole=num_vole.getText().toString().trim();
        Dat_vol=dat_vol.getText().toString().trim();
        Agence=agence.getText().toString().trim();

        if (valider()){
            remplir_champs();
            Intent intent = new Intent(this, AdhessionActivity.class);
            startActivity(intent);
        }
    }
    public void remplir_champs() {
        editor.putString("Societe", sociéte.getText().toString());
        editor.putString("Fonction",fonction.getText().toString());
        editor.putString("Tel_dom", teldom);
        editor.putString("Tel_prof", telprof);
        editor.putString("Tel_mobile",telmobil);
        editor.putString("Tel_fax", faxs);
        if(chek==false){
            editor.putString("Agence",Agence);
            editor.putString("Num_vol", Num_vole);
            editor.putString("Num_bielt", num_bielts);
            editor.putString("Entet_bielt",entet_bielts);
        }else{
            editor.putString("Agence","");
            editor.putString("Num_vol", "");
            editor.putString("Num_bielt", "");
            editor.putString("Entet_bielt","");
            editor.putString("Date_vol","" );
        }
        editor.apply();
    }
    // validation et messages d erreur
    private boolean valider() {
        boolean valide = true;
        if (teldom.isEmpty()) {
            Tel_dom.setError(getString(R.string.champ_obligatoir));
            valide = false;
        }
        if(chek==false){
            if (Agence.isEmpty()) {
                agence.setError(getString(R.string.champ_obligatoir));
                valide = false;
            }
            if (Num_vole.isEmpty()) {
                num_vole.setError(getString(R.string.champ_obligatoir));
                valide = false;
            }
            if (entet_bielts.isEmpty()) {
                entet_bielt.setError(getString(R.string.champ_obligatoir));
                valide = false;
            }
            if (num_bielts.isEmpty()) {
                num_bielt.setError(getString(R.string.champ_obligatoir));
                valide = false;
            }
            if (Dat_vol.isEmpty()) {
                dat_vol.setError(getString(R.string.champ_obligatoir));
                valide = false;
            }
            if (!Num_vole.isEmpty() && (Num_vole.length() != 4)) {
                num_vole.setError(getString(R.string.logure_numero_vole));
                valide = false;

            }
            if (!entet_bielts.isEmpty() && (entet_bielts.length() != 3)) {
                entet_bielt.setError(getString(R.string.logure_entet_bielts_));
                valide = false;

            }
            if (!num_bielts.isEmpty() && (num_bielts.length() != 10)) {
                num_bielt.setError(getString(R.string.logure_numero_bielts));
                valide = false;

            }
        }
        return valide;
    }
    public void get_date(View view) {
        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        Calandrier_pop pop = new Calandrier_pop();
        pop.show(manager, null);
        InscriptionActivity.p=2;

    }
    public void setdate2(String date) {
        dat_vol.setText(date);
        dat_vol.setError(null);
        editor.putString("Date_vol", dat_vol.getText().toString());
    }
}