package com.dezconto.dezconto_prot.views;

import android.app.TabActivity;
import android.content.Intent;

import android.support.v4.app.FragmentTabHost;
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

public class MainPage extends AppCompatActivity  {
    private Button btnSair;
    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("conta").setIndicator("Conta"),
                Conta.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("cupons").setIndicator("Cupons"),
                Cupons.class, null);

//
//
//        TabHost mTabHost = getTabHost();
//
//        mTabHost.addTab(mTabHost.newTabSpec("cupons").setIndicator("Cupons").setContent(new Intent(this  ,Cupons.class )));
//        mTabHost.addTab(mTabHost.newTabSpec("conta").setIndicator("Conta").setContent(new Intent(this , Conta.class )));
//        mTabHost.setCurrentTab(0);



        /*
        btnSair = findViewById(R.id.btnSair);

        btnSair.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainPage.this, LoginActivity.class));
                finish();
                FirebaseAuth.getInstance().signOut();

            }
        });
        */

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

    private TabHost.TabSpec getTabSpec1(FragmentTabHost tabHost) {
        return tabHost.newTabSpec("Conta")
                .setIndicator("conta");
    }

    private TabHost.TabSpec getTabSpec2(FragmentTabHost tabHost) {
        return tabHost.newTabSpec("Cupons")
                .setIndicator("cupons");
    }
}
