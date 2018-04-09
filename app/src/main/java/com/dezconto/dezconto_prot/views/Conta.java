package com.dezconto.dezconto_prot.views;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseAuth;

public class Conta extends Fragment{
    private FirebaseAuth mAuth;
    private Button btnSair, delAcc, changePass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View V = inflater.inflate(R.layout.activity_conta, container, false);




        btnSair = V.findViewById(R.id.btnSair);
        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Log.d("ContaSignOut", "user disconnected from app");
                startActivity(new Intent(view.getContext(), LoginActivity.class));
            }
        });


        return V;
    }




}
