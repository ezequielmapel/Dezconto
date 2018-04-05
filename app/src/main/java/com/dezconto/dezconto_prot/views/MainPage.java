package com.dezconto.dezconto_prot.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;

import com.dezconto.dezconto_prot.LoginActivity;
import com.dezconto.dezconto_prot.R;
import com.dezconto.dezconto_prot.otherviews.CadastroActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainPage extends AppCompatActivity{
    private Button btnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);






        btnSair = findViewById(R.id.btnSair);

        btnSair.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainPage.this, LoginActivity.class));
                finish();
                FirebaseAuth.getInstance().signOut();

            }
        });

    }
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.btnSair:
//
//                break;
//        }
//    }
}
