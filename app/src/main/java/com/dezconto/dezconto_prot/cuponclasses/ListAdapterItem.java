package com.dezconto.dezconto_prot.cuponclasses;

import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AlertDialog;
import com.bumptech.glide.Glide;
import com.dezconto.dezconto_prot.FireData;
import com.dezconto.dezconto_prot.R;
import com.dezconto.dezconto_prot.otherclass.CuponFragment;
import com.dezconto.dezconto_prot.otherclass.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by brhue on 28/04/2018.
 */

public class ListAdapterItem extends ArrayAdapter<Item>{
    private Context context;
    private ArrayList<Item> lista;
    private Button btnPegar;
    private Dialog dialog;
    private Button btnConfirmar;
    private ImageView btnFechar;
    private User user;
    private GoogleSignInAccount go;


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // MOSTRAR CUPONS
        final Item itemPos = this.lista.get(position);
        convertView = LayoutInflater.from(this.context).inflate(R.layout.listview_cupons, null);

        ImageView imagem = convertView.findViewById(R.id.imgLoja);
        Glide.with(convertView.getContext()).load(itemPos.getFotoLoja()).into(imagem);

        TextView titulo = convertView.findViewById(R.id.tvTitulo);
        titulo.setText(itemPos.getNomeCupom());

        TextView tvDesconto = convertView.findViewById(R.id.tvDesconto);
        tvDesconto.setText(itemPos.getIdCupom());

        TextView desc = convertView.findViewById(R.id.tvDescricao);
        desc.setText(itemPos.getDesCupom()+" " + itemPos.getValCupom() + "%");

        TextView qtd = convertView.findViewById(R.id.tvQuantidade);
        qtd.setText( "x "+ itemPos.getQtdCupom());

        TextView validade = convertView.findViewById(R.id.tvValidade);
        String[] venc = itemPos.getDataVenc().split("-");
        validade.setText("Vence em: " + venc[1]+'/'+venc[0]);



        // PEGAR CUPOM

        dialog = new Dialog(getContext());

        btnPegar = convertView.findViewById(R.id.btnPegar);
        btnPegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogConfirm(itemPos);
            }
        });

        return convertView;

    }

    public ListAdapterItem(@NonNull Context context, ArrayList<Item> lista, final User user) {
        super(context, 0, lista);
        this.user = user;
        this.context = context;
        this.lista = lista;
    }




    private void showDialogConfirm(final Item itemPos){
        dialog.setContentView(R.layout.dialog_confirm);
        final User u = this.user;
        btnConfirmar = dialog.findViewById(R.id.btnConfirmo);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 if(user != null){
                     // SE FOR true O CUPOM ESTÁ VENCIDO
                     if(!Cupom.cupomVencimento(itemPos)) {
                         FireData.setCupomToUser(itemPos, u.getUserId());
                     }else{
                         Toast.makeText(getContext(), "Desculpe, este cupom está vencido!", Toast.LENGTH_SHORT).show();
                     }



                 }else{
                        if(!Cupom.cupomVencimento(itemPos)) {
                            FireData.setCupomToUser(itemPos, FirebaseAuth.getInstance().getCurrentUser().getUid());
                        }else{
                            Toast.makeText(getContext(), "Desculpe, este cupom está vencido!", Toast.LENGTH_SHORT).show();
                        }

                 }

                 dialog.dismiss();
            }
        });

        btnFechar = dialog.findViewById(R.id.btnFechar);
        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();


    }
}
