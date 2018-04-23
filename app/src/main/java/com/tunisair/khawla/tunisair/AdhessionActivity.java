package com.tunisair.khawla.tunisair;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdhessionActivity extends AppCompatActivity {
    CheckBox case1,case2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adhession);

        case1=(CheckBox) findViewById(R.id.checkBox);
        case2=(CheckBox) findViewById(R.id.checkBox2);

    }
    public void profil(View view) {
        Intent intent = new Intent(this, ProfilActivity.class);
        startActivity(intent);
    }
    public void terminer(View view) {


        if (!valider()){
            Toast.makeText(getApplicationContext(),"Veuillez vérifier tout les champs",Toast.LENGTH_LONG).show();
        }
        else{
            Intent intent = new Intent(this, ProfilActivity.class);
            startActivity(intent);
        }

    }
    private boolean valider() {
        boolean valide = true;

        if (! case2.isChecked()){
            Toast.makeText(AdhessionActivity.this,"Veuillez accepter les Conditions Générales du Programme",Toast.LENGTH_SHORT).show();
            valide = false;
        }

        return valide;
    }
}
