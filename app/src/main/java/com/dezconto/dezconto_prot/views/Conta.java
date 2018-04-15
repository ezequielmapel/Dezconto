package com.dezconto.dezconto_prot.views;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.dezconto.dezconto_prot.LoginActivity;
import com.dezconto.dezconto_prot.MainActivity;
import com.dezconto.dezconto_prot.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;

public class Conta extends Fragment{
    private FirebaseAuth mAuth;
    private Button btnSair, delAcc, changePass;




    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View V = inflater.inflate(R.layout.activity_conta, container, false);



        btnSair = V.findViewById(R.id.btnSair);
        btnSair .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                FirebaseAuth.getInstance().signOut();
                LoginActivity.setUser(false);
                startActivity(new Intent(v.getContext(), LoginActivity.class));
            }
        });



        return V;
    }



}
