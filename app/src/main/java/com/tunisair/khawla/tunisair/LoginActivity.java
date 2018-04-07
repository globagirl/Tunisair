package com.tunisair.khawla.tunisair;

import android.content.Intent;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;

public class LoginActivity extends AppCompatActivity {

    EditText login,password;
    FirebaseAuth mAuth;
    String email,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login=(EditText)findViewById(R.id.login);
        password=(EditText)findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();

    }
    public void inscrire(View view) {
        Intent intent = new Intent(this, InscriptionActivity.class);
        startActivity(intent);
    }
    public void connecter(View view) {
         email=login.getText().toString().trim();
         pass=password.getText().toString().trim();
        if (!valider()){
            Toast.makeText(getApplicationContext(),"verifier tout les champs",Toast.LENGTH_LONG).show();
            //login without email and password for test
            Intent intent = new Intent(LoginActivity.this, AccountActivity.class);
            startActivity(intent);
        }else{
            mAuth.signInWithEmailAndPassword(email,pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Intent intent = new Intent(LoginActivity.this, AccountActivity.class);
                                startActivity(intent);

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(LoginActivity.this, task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }

    }
    private boolean valider() {
        boolean valide = true;
        if (email.isEmpty() || (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())) {
            login.setError("remplir votre email");
            valide = false;

        }
        if (pass.isEmpty()) {
            password.setError("remplir votre password");
            valide = false;

        }
        return valide;
    }


}

