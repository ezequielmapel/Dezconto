package com.dezconto.dezconto_prot.cuponclasses;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dezconto.dezconto_prot.FireData;
import com.dezconto.dezconto_prot.R;
import com.dezconto.dezconto_prot.otherclass.User;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

/**
 * Created by brhue on 07/06/2018.
 */

public class ListFavoritosAdapter extends ArrayAdapter<Item> {
    private User user;
    private Context context;
    private ArrayList<Item> lista;
    private Button btnPegar, btnConfirmar;
    private Dialog dialog;
    private ImageView btnFechar;

    public ListFavoritosAdapter(@NonNull Context context, ArrayList<Item> lista, final User user) {
        super(context, 0, lista);
        this.user = user;
        this.context = context;
        this.lista = lista;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        convertView = LayoutInflater.from(this.context).inflate(R.layout.listview_cupons_user, null);



        final Item itemPos = this.lista.get(position);


        ImageView imagem = convertView.findViewById(R.id.imgLoja);
        Glide.with(convertView.getContext()).load(itemPos.getFotoLoja()).into(imagem);

        TextView titulo = convertView.findViewById(R.id.tvTitulo);
        titulo.setText(itemPos.getNomeCupom());

        TextView tvDesconto = convertView.findViewById(R.id.tvDesconto);
        tvDesconto.setText(itemPos.getIdCupom());

        TextView desc = convertView.findViewById(R.id.tvDescricao);
        desc.setText(itemPos.getDesCupom()+" " + itemPos.getValCupom() + "%");

        TextView validade = convertView.findViewById(R.id.tvValidade);
        validade.setText(itemPos.getValidadeCupom() + " dia(s)");




        return convertView;
    }


}
