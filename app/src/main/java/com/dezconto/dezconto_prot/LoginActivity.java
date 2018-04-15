package com.dezconto.dezconto_prot;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dezconto.dezconto_prot.otherviews.CadastroActivity;
import com.dezconto.dezconto_prot.views.MainPage;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {
    // FIREBASE AUTH
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static boolean status;

    // Google Auth

    private static final int REQ_CODE = 9001;
    private static GoogleApiClient googleApiClient;

    // WIDEGTS
    private Button btnCriarConta, btnEntrar, btnLoginGg;
    private EditText loginEmailEt, loginSenhaEt;
    private ProgressBar progressBarL;

    // STRINGS
    private String loginEmail;
    private String loginSenha;

    public LoginActivity() {

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // GOOGLE LOGIN
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();

        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API).build();

        // VARIABLES


        // WIDGETS
        loginEmailEt = findViewById(R.id.loginEmail);
        loginSenhaEt = findViewById(R.id.loginSenha);

        // WIDGETS > BUTTONS
        btnCriarConta = findViewById(R.id.btnCriarConta);
        btnEntrar = findViewById(R.id.btnEntrar);
        progressBarL = findViewById(R.id.progressBarL);
        btnLoginGg = findViewById(R.id.btnSignGg);


        btnLoginGg.setOnClickListener(this);
        btnCriarConta.setOnClickListener(this);
        btnEntrar.setOnClickListener(this);


        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null) {
                    // User is signed in
                    Log.d("LoginActivityOnState", "onAuthStateChanged:signed_in:" + user.getUid());
                    //startActivity(new Intent(LoginActivity.this, MainPage.class));
                    //finish();
                    updateUI(true);
                    status = true;

                } else {
                    // User is signed out
                    Log.d("LoginActivityOnState", "onAuthStateChanged:signed_out");
                }


            }
        };

    }

    // Method para logar
    public void signIn(String email, String senha) {
        mAuth.signInWithEmailAndPassword(email, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Log.d("SignIn", "signInWithEmail:onComplete:" + task.isSuccessful());

                // If sign in fails, display a message to the user. If sign in succeeds
                // the auth state listener will be notified and logic to handle the
                // signed in user can be handled in the listener.
                if (!task.isSuccessful()) {
                    Log.w("SignIn", "signInWithEmail:failed", task.getException());
                    Toast.makeText(LoginActivity.this, "Não foi possível conectar!", Toast.LENGTH_SHORT).show();
                } else {
                    btnEntrar.setVisibility(View.INVISIBLE);
                    btnCriarConta.setVisibility(View.INVISIBLE);
                    progressBarL.setVisibility(View.VISIBLE);

                    //startActivity(new Intent(LoginActivity.this, MainPage.class));
                    //finish();
                    updateUI(true);
                    status = true;
                }


            }
        });

    }

    public boolean validateform(String loginEmail, String loginSenha) {
        // VALIDAÇÃO PARA LOGIN
        return !(loginEmail.equals("") || loginSenha.equals(""));
    }


    public boolean userLogged() {
        return FirebaseAuth.getInstance().getCurrentUser() != null;
    }

    public void updateUI(boolean isLogin) {


        if (isLogin) {
            startActivity(new Intent(LoginActivity.this, MainPage.class));
            finish();
        }
        if(!status){
            signOut();
        }
    }




    // GOOGLE LOGIN



    private void signIn(){
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQ_CODE);
    }

    public void signOut(){
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status s) {
               updateUI(false);
               status = false;

            }
        });
    }

    // --- GOOGLE SIGN ---//
    private void handleResult(GoogleSignInResult result){

        if(result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            // gets informações
            String email = account.getEmail();
            String nome = account.getDisplayName();
            String id = account.getId();
            String urlphoto = String.valueOf(account.getPhotoUrl());

            btnEntrar.setVisibility(View.INVISIBLE);
            btnCriarConta.setVisibility(View.INVISIBLE);
            progressBarL.setVisibility(View.VISIBLE);



            updateUI(true);
            status = true;

        }else{
            updateUI(false);
            status = false;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleResult(result);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnEntrar:
                // STRINGS
                loginEmail = loginEmailEt.getText().toString();
                loginSenha = loginSenhaEt.getText().toString();

                if(validateform(loginEmail, loginSenha)) {
                    signIn(loginEmail, loginSenha);
                }else{
                    Toast.makeText(LoginActivity.this, "Por favor, preencha os campos", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnSignGg:
                    signIn();
                break;


            case R.id.btnCriarConta:
                startActivity(new Intent(LoginActivity.this, CadastroActivity.class));
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        @SuppressLint("RestrictedApi") GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(this);
//
//        if(acc != null){
//            Log.d("testandoo", "nao é null");
//            updateUI(true);
//        }else{
//            Log.d("testandoo", "é null");
//        }

    }


    public static void setUser(boolean isLogin){
        if(!isLogin){
            status = false;

        }
    }

}
