package com.dezconto.dezconto_prot;

import com.google.firebase.auth.FirebaseAuth;


abstract class FbAuth {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public FbAuth(){
        mAuth = FirebaseAuth.getInstance();
    }


}
