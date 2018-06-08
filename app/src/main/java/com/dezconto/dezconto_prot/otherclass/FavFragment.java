package com.dezconto.dezconto_prot.otherclass;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.dezconto.dezconto_prot.FireData;
import com.dezconto.dezconto_prot.R;
import com.dezconto.dezconto_prot.cuponclasses.Item;
import com.dezconto.dezconto_prot.cuponclasses.ListAdapterItem;
import com.dezconto.dezconto_prot.cuponclasses.ListFavoritosAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

/**
 * Created by brhue on 28/04/2018.
 */

public class FavFragment extends Fragment{
    private User user;
    private ImageButton btnAtt;
    private ProgressBar progressCupons;
    public FavFragment(){}

    @SuppressLint("ValidFragment")
    public FavFragment(User user){
        this.user = user;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_fav, null);

        btnAtt = view.findViewById(R.id.btnAttCupons);
        progressCupons = view.findViewById(R.id.progressCupons);

        final User user = this.user;
        final ArrayList<Item> arrayItem = new ArrayList<Item>();
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        Query q = mDatabase.child("usuario_cupom/" + user.getUserId());
        q.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Item cupom = dataSnapshot.getValue(Item.class);
                arrayItem.add(cupom);

                try{
                    ListFavoritosAdapter adapterItem = new ListFavoritosAdapter(getContext(), arrayItem, user);
                    ListView listView = getView().findViewById(R.id.idListView);
                    listView.setAdapter(adapterItem);

                    progressCupons.setVisibility(View.INVISIBLE);
                    listView.setVisibility(View.VISIBLE);
                    btnAtt.setVisibility(View.VISIBLE);

                }catch (NullPointerException ignored){}

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


        return view;
    }
}
