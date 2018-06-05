package com.dezconto.dezconto_prot;

import android.util.Log;
import android.widget.TextView;

import com.dezconto.dezconto_prot.otherclass.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

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
}
