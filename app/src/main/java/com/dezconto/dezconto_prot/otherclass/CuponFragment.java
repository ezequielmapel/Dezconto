package com.dezconto.dezconto_prot.otherclass;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dezconto.dezconto_prot.FireData;
import com.dezconto.dezconto_prot.R;
import com.dezconto.dezconto_prot.cuponclasses.Item;
import com.dezconto.dezconto_prot.cuponclasses.ListAdapterItem;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by brhue on 28/04/2018.
 */

public class CuponFragment extends Fragment{
    private User user;
    private ProgressBar progressCupons;
    private ProgressBar progressAtt;
    private ImageButton btnAttCupons;

    private Button t;
    @SuppressLint("ValidFragment")

    public CuponFragment(User user){
        this.user = user;
    }

    public CuponFragment(){}
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cp, null);
        progressCupons = view.findViewById(R.id.progressCupons);
        //progressAtt = view.findViewById(R.id.progressAtt);
        btnAttCupons = view.findViewById(R.id.btnAttCupons);

        carregarCupons();

        btnAttCupons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carregarCupons();
            }
        });

        return view;
    }


    public void carregarCupons(){
        final ArrayList<Item> lista = new ArrayList<Item>();
        String idCupom, nomeLoja, nomeCupom, desCupom, qtdCupom, valCupom, valPromo;
        final String[] info = new String[0];
        final User u = this.user;
        progressCupons.setVisibility(View.VISIBLE);


        //progressAtt.setVisibility(View.VISIBLE);
        btnAttCupons.setVisibility(View.INVISIBLE);
        if(u != null) {
            Query q = FirebaseDatabase.getInstance().getReference().child("cupom");
            q.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    final Item cupom = dataSnapshot.getValue(Item.class);
                    // Checar a quantidade de cupons, se ela for > 0
                    if(cupom != null && Integer.parseInt(cupom.getQtdCupom()) > 0){
                        Query cuponsUser = FirebaseDatabase.getInstance().getReference().child("usuario_cupom/"+u.getUserId()+"/"+cupom.getIdCupom());

                        cuponsUser.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                if(dataSnapshot.getValue() == null){
                                    lista.add(cupom);


                                    try {
                                        ListAdapterItem adapterItem = new ListAdapterItem(getContext(), lista, u);
                                        ListView listView = getView().findViewById(R.id.idListView);
                                        listView.setAdapter(adapterItem);


                                        progressCupons.setVisibility(View.INVISIBLE);
                                        listView.setVisibility(View.VISIBLE);


                                        // progressAtt.setVisibility(View.INVISIBLE);

                                        btnAttCupons.setVisibility(View.VISIBLE);
                                    } catch (NullPointerException ignored) {

                                    }

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                    }




//               for (DataSnapshot ds: dataSnapshot.getChildren()){
//                    Log.d("DS", "Algo: " + ds.toString());
//               };
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
}
