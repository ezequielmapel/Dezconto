package com.dezconto.dezconto_prot;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dezconto.dezconto_prot.otherviews.CadastroActivity;
import com.dezconto.dezconto_prot.views.MainPage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    // FIREBASE AUTH
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener ;

    // WIDEGTS
    private Button btnCriarConta, btnEntrar;
    private EditText loginEmailEt, loginSenhaEt;
    private ProgressBar progressBarL;

    // STRINGS
    private String loginEmail;
    private String loginSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // WIDGETS
        loginEmailEt = findViewById(R.id.loginEmail);
        loginSenhaEt = findViewById(R.id.loginSenha);

        // WIDGETS > BUTTONS
        btnCriarConta = findViewById(R.id.btnCriarConta);
        btnEntrar = findViewById(R.id.btnEntrar);
        progressBarL = findViewById(R.id.progressBarL);

        // TESTE LOGIN
        if(userLogged()){
            startActivity(new Intent(LoginActivity.this, MainPage.class));
            finish();
        }
        //FIREBASE
        mAuth = FirebaseAuth.getInstance();

        btnCriarConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
            }
        });


        // LOGAR NO APP
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // STRINGS
                loginEmail = loginEmailEt.getText().toString();
                loginSenha = loginSenhaEt.getText().toString();

                if(validateform(loginEmail, loginSenha)) {
                    signIn(loginEmail, loginSenha);
                }else{
                    Toast.makeText(LoginActivity.this, "Por favor, preencha os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("LoginActivityOnState", "onAuthStateChanged:signed_in:" + user.getUid());
                    startActivity(new Intent(LoginActivity.this, MainPage.class));
                    finish();

                } else {
                    // User is signed out
                    Log.d("LoginActivityOnState", "onAuthStateChanged:signed_out");
                }


            }
        };
    }

    // Method para logar
    public void signIn(String email, String senha){
        mAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("SignIn", "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("SignIn", "signInWithEmail:failed", task.getException());
                            Toast.makeText(LoginActivity.this, "Não foi possível conectar!",
                                    Toast.LENGTH_SHORT).show();
                        }else{
                            btnEntrar.setVisibility(View.INVISIBLE);
                            btnCriarConta.setVisibility(View.INVISIBLE);
                            progressBarL.setVisibility(View.VISIBLE);

                            startActivity(new Intent(LoginActivity.this, MainPage.class));
                            finish();
                        }


                    }
                });

    }

    public boolean validateform(String loginEmail, String loginSenha){
        // VALIDAÇÃO PARA LOGIN
        return !(loginEmail.equals("") || loginSenha.equals(""));
    }


    public boolean userLogged(){
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    public void updateUI(boolean login){

    }


}
