package com.dezconto.dezconto_prot.otherclass;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;


public class User {
    private String nome;
    private String senha;
    private String email;

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

    private String userId;

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

    private User(String nome, String email, String userId){
        this.nome = nome;
        this.email = email;
        this.userId = userId;
    }

    public static void writeNewUser(DatabaseReference mDatabase, String nome, String email, String userId){
        User user = new User(nome, email, userId);
        mDatabase.child("user").child(userId).setValue(user);

        Log.d("User:writeNewUser", "Tentativa para salvar usuário");
    }

}
