package com.dezconto.dezconto_prot.otherviews;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dezconto.dezconto_prot.LoginActivity;
import com.dezconto.dezconto_prot.MainActivity;
import com.dezconto.dezconto_prot.R;
import com.dezconto.dezconto_prot.otherclass.User;
import com.dezconto.dezconto_prot.views.MainPage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastroActivity extends AppCompatActivity {
    // FIREBASE
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private DatabaseReference mDatabase;



    // WIDGETS
    private EditText nomeEt, senhaEt1, senhaEt2, emailEt;
    private Button btnConfirmar;
    private ProgressBar progressBar;

    // STRIGNS

    private String nomeUser, senhaUser, senhaUser2, emailUser;
    private String textError;
    private String idUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        // Database Reference
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // FIND WIDGETS
        nomeEt = findViewById(R.id.nomeEt);
        senhaEt1 = findViewById(R.id.senhaEt1);
        senhaEt2 = findViewById(R.id.senhaEt2);
        emailEt = findViewById(R.id.emailEt);
        btnConfirmar = findViewById(R.id.btnConfimar);
        progressBar = findViewById(R.id.progressBar2);


        // CADASTRO
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // STRINGS
                nomeUser = nomeEt.getText().toString();
                senhaUser = senhaEt1.getText().toString();
                senhaUser2 = senhaEt2.getText().toString();
                emailUser = emailEt.getText().toString();

                textError = validateForm(nomeUser, senhaUser, senhaUser2, emailUser);

                if(textError.equals("")){
                    btnConfirmar.setVisibility(View.INVISIBLE);
                    progressBar.setVisibility(View.VISIBLE);

                    criarNovoUser(emailUser, senhaUser);
                }else{
                    Toast.makeText(CadastroActivity.this, textError, Toast.LENGTH_SHORT).show();
                }




            }
        });


        // FIREABSE AuthListener

        mAuth = FirebaseAuth.getInstance();


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // Ir direto para a página principal
                    Log.d("TAG", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("TAG", "onAuthStateChanged:signed_out");
                }

            }
        };
    }

    //    Method para criar um novo usuário
    public void criarNovoUser(String  email, String senha){
        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("TAG", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        if (!task.isSuccessful()) {
                            Toast.makeText(CadastroActivity.this, "Não foi possível criar o usuário.",
                                    Toast.LENGTH_SHORT).show();
                            btnConfirmar.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.INVISIBLE);
                        }else{
                            // Salvando usuário no BD


                            startActivity(new Intent(CadastroActivity.this, LoginActivity.class));
                            finish();
                            try{
                                idUser = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                User.writeNewUser(mDatabase, nomeUser, emailUser, idUser);
                            }catch (NullPointerException ignored) {
                                //User newUser = new User();

                            }

                        }

                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


    public void saveUserBd(String nome, String senha, String email){
        mDatabase = FirebaseDatabase.getInstance().getReference();


    }

    public String validateForm(String  nomeUser, String senhaUser, String senhaUser2, String emailUser) {
        if (nomeUser.equals("") || nomeUser.isEmpty()  || senhaUser.equals("") || senhaUser.isEmpty()
                || emailUser.equals("") || emailUser.isEmpty() ){
            return "Campos vazios!";
        }else if(senhaUser.length() < 8){
            return "Senha muito curta!";
        }else if(!senhaUser.equals(senhaUser2)){
            return "Senhas não conferem";
        }

        return "";
    }
}
