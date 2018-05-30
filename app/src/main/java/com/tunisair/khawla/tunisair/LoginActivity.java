package com.tunisair.khawla.tunisair;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.tunisair.khawla.tunisair.receiver.NetworkStateChangeReceiver;

import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;

public class LoginActivity extends AppCompatActivity {
    //Déclaration des variables
    private BroadcastReceiver mNetworkReceiver;
    EditText login, password;
    String email, pass;
    SharedPreferences.Editor editor;
    SharedPreferences prefs;
    FirebaseAuth mAuth;
    static ConstraintLayout constraintLayout;
    static ACProgressFlower dialoge;
    public static int NB_Activity=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mNetworkReceiver = new NetworkStateChangeReceiver();
        registerNetworkBroadcastForNougat();

        prefs = getSharedPreferences("Inscription", MODE_PRIVATE);
        editor = prefs.edit();
        login = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);
        constraintLayout = (ConstraintLayout) findViewById(R.id.activity_bas);
        mAuth = FirebaseAuth.getInstance();

    }

    //Methodes
    //Action du buton inscription
    public void inscrire(View view) {
        if (isOnline()) {
            editor.clear();
            editor.commit();
            Intent intent = new Intent(this, InscriptionActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.chek_internet, Toast.LENGTH_SHORT).show();
        }
    }

    //Action du buton se connecter
    public void connecter(View view) {
        email = login.getText().toString().trim();
        pass = password.getText().toString().trim();
        if (isOnline()) {
            if (!valider()) {
                Toast.makeText(getApplicationContext(), R.string.verifier_tout_les_champs, Toast.LENGTH_LONG).show();
                //login without email and password for test
            } else {
                final ACProgressFlower dialog = new ACProgressFlower.Builder(this)
                        .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                        .themeColor(Color.WHITE)
                        .text("Loading...").textColor(Color.WHITE)
                        .fadeColor(Color.DKGRAY).build();
                dialog.show();
                mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    editor.putString("Email", email);
                                    editor.apply();
                                    Intent intent = new Intent(LoginActivity.this, ProfilActivity.class);
                                    startActivity(intent);
                                    dialog.dismiss();
                                } else {
                                    dialog.dismiss();
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        } else {
            Toast.makeText(this, R.string.chek_internet, Toast.LENGTH_SHORT).show();
        }
    }

    //contrôle de saisi
    private boolean valider() {
        boolean valide = true;
        if (email.isEmpty() || (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            login.setError(getString(R.string.champ_obligatoir));
            valide = false;
        }
        if (pass.isEmpty()) {
            password.setError(getString(R.string.champ_obligatoir));
            valide = false;
        }
        return valide;
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    //Methode de test connexion
    public static void dialog(boolean value, Context context) {

        if (value) {
            context=null;
            dialoge.dismiss();
        } else {
            dialoge = new ACProgressFlower.Builder(context)
                    .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                    .themeColor(Color.WHITE)
                    .text("Access Denied...").textColor(Color.WHITE)
                    .fadeColor(Color.DKGRAY).build();
            dialoge.show();
        }
    }

    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
    }
}

