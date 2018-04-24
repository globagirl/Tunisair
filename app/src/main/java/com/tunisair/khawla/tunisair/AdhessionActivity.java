package com.tunisair.khawla.tunisair;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdhessionActivity extends AppCompatActivity {
    CheckBox case1, case2;
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    EditText autre, site, pointe, agencee;
    String Autre,Site,Pointe,Agencee;
    RadioButton rd_indiv, rd_lang, rd_hub, rd_eco, rd_cache, rd_seul, rd_assis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adhession);
        prefs = getSharedPreferences("Inscription", MODE_PRIVATE);
        editor = prefs.edit();

        String val=prefs.getString("Agence","empty");
        String val2= prefs.getString("Num_vol","empty");
        String val3= prefs.getString("Num_bielt","empty");
        String val4=prefs.getString("Tel_prof","empty");
        String val5= prefs.getString("Entet_bielt","empty");
        String val6= prefs.getString("Date_vol","empty");

//        String val7=prefs.getString("Tel_Mobile","empty");
//        String val8= prefs.getString("Tel_domicile","empty");
//        String val9= prefs.getString("Tel_faxe","empty");
//        String val10= prefs.getString("Tel_profe","empty");

        Toast.makeText(getApplicationContext(),val+"//"+val2+"//"+val3+"//"+val4+"//"+val5+"//"+val6,Toast.LENGTH_LONG).show();

        autre = (EditText) findViewById(R.id.autre);
        agencee = (EditText) findViewById(R.id.agence_habit);
        pointe = (EditText) findViewById(R.id.point);
        site = (EditText) findViewById(R.id.site);
        rd_indiv = (RadioButton) findViewById(R.id.indiv);
        rd_indiv.setChecked(true);
        editor.putString("Type_adhession", rd_indiv.getText().toString());

        rd_lang = (RadioButton) findViewById(R.id.fr);
        rd_lang.setChecked(true);
        editor.putString("Langue", rd_lang.getText().toString());

        rd_hub = (RadioButton) findViewById(R.id.hublot);
        rd_hub.setChecked(true);
        editor.putString("Preference", rd_hub.getText().toString());

        rd_eco = (RadioButton) findViewById(R.id.eco);
        rd_eco.setChecked(true);
        editor.putString("Classe_voyage", rd_eco.getText().toString());

        rd_cache = (RadioButton) findViewById(R.id.cashe);
        rd_cache.setChecked(true);
        editor.putString("Mode_pays", rd_cache.getText().toString());

        rd_seul = (RadioButton) findViewById(R.id.seul);
        rd_seul.setChecked(true);
        editor.putString("Habitude", rd_seul.getText().toString());

        rd_assis = (RadioButton) findViewById(R.id.assis);
        rd_assis.setChecked(true);
        editor.putString("Assistance", rd_assis.getText().toString());

        editor.apply();
        case1 = (CheckBox) findViewById(R.id.checkBox);
        case2 = (CheckBox) findViewById(R.id.checkBox2);

    }

    public void onRadioButton_type(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.fami:
                if (checked) {
                    RadioButton rd_fami = (RadioButton) findViewById(R.id.fami);
                    editor.putString("Type_adhession", rd_fami.getText().toString());
                    editor.apply();
                }
                break;
        }
    }

    public void onRadioButton_langue(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.en:
                if (checked) {
                    RadioButton rd_l = (RadioButton) findViewById(R.id.en);
                    editor.putString("Langue", rd_l.getText().toString());
                    editor.apply();
                }
                break;
            case R.id.ar:
                if (checked) {
                    RadioButton rd_l = (RadioButton) findViewById(R.id.ar);
                    editor.putString("Langue", rd_l.getText().toString());
                    editor.apply();
                }
                break;
        }
    }

    public void onRadioButton_prefe(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.couloir:
                if (checked) {
                    RadioButton rd_l = (RadioButton) findViewById(R.id.couloir);
                    editor.putString("Preference", rd_l.getText().toString());
                    editor.apply();
                }
                break;
            case R.id.indif:
                if (checked) {
                    RadioButton rd_l = (RadioButton) findViewById(R.id.indif);
                    editor.putString("Preference", rd_l.getText().toString());
                    editor.apply();
                }
                break;
        }
    }

    public void onRadioButton_classe(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.affaire:
                if (checked) {
                    RadioButton rd_aff = (RadioButton) findViewById(R.id.affaire);
                    editor.putString("Classe_voyage", rd_aff.getText().toString());
                    editor.apply();
                }
                break;
        }
    }

    public void onRadioButton_mode(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.cheque:
                if (checked) {
                    RadioButton rd_mode = (RadioButton) findViewById(R.id.cheque);
                    editor.putString("Mode_pays", rd_mode.getText().toString());
                    editor.apply();
                }
                break;
            case R.id.carte:
                if (checked) {
                    RadioButton rd_mode = (RadioButton) findViewById(R.id.carte);
                    editor.putString("Mode_pays", rd_mode.getText().toString());
                    editor.apply();
                }
                break;
        }
    }

    public void onRadioButton_habi(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.accomp:
                if (checked) {
                    RadioButton rd_hab = (RadioButton) findViewById(R.id.accomp);
                    editor.putString("Habitude", rd_hab.getText().toString());
                    editor.apply();
                }
                break;
        }
    }

    public void onRadioButton_assistance(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.noAssis:
                if (checked) {
                    RadioButton rd_no = (RadioButton) findViewById(R.id.noAssis);
                    editor.putString("Assistance", rd_no.getText().toString());
                    editor.apply();
                }
                break;
        }
    }

    public void terminer(View view) {
        Agencee=agencee.getText().toString().trim();
        Site=site.getText().toString().trim();
        Autre=autre.getText().toString().trim();
        Pointe=pointe.getText().toString().trim();
        if (!valider()) {
            Toast.makeText(getApplicationContext(), "Accepter les conditions", Toast.LENGTH_LONG).show();
        } else {
            remplir_champs();
            Intent intent = new Intent(this, ProfilActivity.class);
            startActivity(intent);
        }

    }
    public void remplir_champs() {
        editor.putString("Agence_habit", Agencee);
        editor.putString("Site",Site);
        editor.putString("Autre_prog", Autre);
        editor.putString("Pointe", Pointe);
        if(case1.isChecked()){
            editor.putBoolean("Accepter_mail",true);
        }else {
            editor.putBoolean("Accepter_mail",false);
        }
        editor.apply();
    }

    private boolean valider() {
        boolean valide = true;

        if (!case2.isChecked()) {
            Toast.makeText(AdhessionActivity.this, R.string.accepter_les_conditions, Toast.LENGTH_SHORT).show();
            valide = false;
        }

        return valide;
    }
}
