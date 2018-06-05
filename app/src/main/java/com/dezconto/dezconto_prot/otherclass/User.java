package com.dezconto.dezconto_prot.otherclass;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.dezconto.dezconto_prot.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;


public class User implements Serializable{

    private String nome;
    private String senha;
    private String email;
    private String userId;
    private String imagePerfil;

    public String getImagePerfil() {
        return imagePerfil;
    }

    public void setImagePerfil(String imagePerfil) {
        this.imagePerfil = imagePerfil;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }




    public User(){

    }

    public User(String nome, String email, String userId){
        this.nome = nome;
        this.email = email;
        this.userId = userId;
    }

    public User(String nome, String email){
        this.nome = nome;
        this.email = email;
    }

    public User(String nome, String email, String userId, String imagePerfil){
        this.nome = nome;
        this.email = email;
        this.userId = userId;
        this.imagePerfil = imagePerfil;
    }

    public static void writeNewUser(DatabaseReference mDatabase, String nome, String email, String userId){
        User user = new User(nome, email, userId);
        mDatabase.child("user").child(userId).setValue(user);

        Log.d("User:writeNewUser", "Tentativa para salvar usuário");
    }

    public static void writeNewUser(final DatabaseReference mDatabase, final User user){
        Log.d("User:writeNewUser2", "Salvar usuário do Gmail checando se ele já existe");

        Query q = mDatabase.child("user").orderByChild("email").equalTo(user.getEmail());
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() == null){
                    mDatabase.child("user").child(user.getUserId()).setValue(user);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }



}
