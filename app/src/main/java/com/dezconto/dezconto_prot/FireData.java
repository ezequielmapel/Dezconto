package com.dezconto.dezconto_prot;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.signature.StringSignature;
import com.dezconto.dezconto_prot.cuponclasses.Cupom;
import com.dezconto.dezconto_prot.cuponclasses.Item;
import com.dezconto.dezconto_prot.cuponclasses.ListAdapterItem;
import com.dezconto.dezconto_prot.otherclass.CuponFragment;
import com.dezconto.dezconto_prot.otherclass.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by brhue on 26/05/2018.
 */

public class FireData {
    private static final DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    public static void getUser(String email, final TextView campo){
        Query q =  mDatabase.child("user").orderByChild("email").equalTo(email);
        q.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot ds, String s) {

             campo.setText(ds.child("nome").getValue().toString());

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public static void setCupomToUser(final Item itemPos, final String idUsuario){
        // VERIFICANDO CUPOM

        // Checar se o usuário já possui o cupom
        Query q =  mDatabase.child("usuario_cupom/"+idUsuario+'/'+itemPos.getIdCupom());
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.getValue() == null){
                    HashMap<String, Object> cupom = new HashMap<>();
                    cupom.put(itemPos.getIdCupom(), "true");
                    Map<String, Object> childUpdates = new HashMap<>();

                    childUpdates.put("usuario_cupom/"+idUsuario+'/'+itemPos.getIdCupom(), itemPos);
                    mDatabase.updateChildren(childUpdates);
                    mDatabase.child("user").child(idUsuario).child("cupons/" + itemPos.getIdCupom()).setValue("true");
                    updateQtdCupom(itemPos.getIdCupom());

                }else{
                    Log.d("CCC", "Você já possui este cupom!");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public static void updateQtdCupom(final String idCupom){
        Map<String, Object> childUpdates = new HashMap<>();
        Query qCupom = mDatabase.child("cupom/"+idCupom).child("qtdCupom");
        qCupom.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("VALUEDS", "VALUE: " + dataSnapshot.getValue().toString());
                int qtd = Integer.parseInt(dataSnapshot.getValue().toString()) - 1;
                String sQtd = String.valueOf(qtd);
                mDatabase.child("cupom/"+idCupom).child("qtdCupom").setValue(sQtd);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query qLojista = mDatabase.child("lojista_cupom/");
        qLojista.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds: dataSnapshot.getChildren()){
                    //Log.d( "DS2: " ,"DS" + ds.child(idCupom).child("qtdCupom").getValue());
                    try {
                        int qtd = Integer.parseInt(ds.child(idCupom).child("qtdCupom").getValue().toString()) - 1;
                        String sQtd = String.valueOf(qtd);
                        mDatabase.child("lojista_cupom/" + ds.getKey()+"/"+idCupom).child("qtdCupom").setValue(sQtd);
                    }catch (NullPointerException ignored) {

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public static void getCupom(){
        String idCupom, nomeLoja, nomeCupom, desCupom, qtdCupom, valCupom, valPromo;

        Query q = mDatabase.child("cupom");
        q.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.d("CUPONS", "Cupons: " + dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void getCupomFromUser(User user){


    }
}
