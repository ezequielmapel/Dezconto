package com.dezconto.dezconto_prot.otherclass;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dezconto.dezconto_prot.FireData;
import com.dezconto.dezconto_prot.LoginActivity;
import com.dezconto.dezconto_prot.R;

/**
 * Created by brhue on 28/04/2018.
 */

public class ContaFragment extends Fragment {
    private User infoUser = null;

    private TextView tvNome;
    private TextView tvEmail;
    private ImageView imgUser;
    private ProgressBar progressInfo;

    private  String nome = "nothing";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_conta, null);

        tvNome = rootView.findViewById(R.id.tvNome);
        tvEmail = rootView.findViewById(R.id.tvEmail);
        imgUser = rootView.findViewById(R.id.imgUser);
        progressInfo = rootView.findViewById(R.id.progressInfo);

        if(this.infoUser != null){
            //tvNome.setText(this.infoUser.getNome());
            FireData.getUser(this.infoUser.getEmail(), tvNome);
            tvEmail.setText(this.infoUser.getEmail());


            if(this.infoUser.getImagePerfil() != null){
                Glide.with(this).load(this.infoUser.getImagePerfil()).into(imgUser);
            }

            progressInfo.setVisibility(View.INVISIBLE);
            imgUser.setVisibility(View.VISIBLE);
            tvNome.setVisibility(View.VISIBLE);
            tvEmail.setVisibility(View.VISIBLE);
        }

        return rootView;
    }

    public ContaFragment(){
    }

    @SuppressLint("ValidFragment")
    public ContaFragment(User infoUser){
        this.infoUser = infoUser;
    }



}
