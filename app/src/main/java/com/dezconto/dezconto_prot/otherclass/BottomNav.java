package com.dezconto.dezconto_prot.otherclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.dezconto.dezconto_prot.R;

public class BottomNav extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private User user = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        if(intent != null){
            Bundle params = intent.getExtras();
            if(params != null){
                User u = (User) getIntent().getSerializableExtra("UserClass");
                this.user = u;
                Log.d("NomeLogin", "Nome do use: " + u.getNome());


                loadFragment(new CuponFragment(this.user));
            }
        }
    }

    private boolean loadFragment(Fragment fragment){
      if(fragment!=null){
          getSupportFragmentManager()
                  .beginTransaction()
                  .replace(R.id.fragment_container, fragment)
                  .commit();
          return true;
      }

      return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        if(this.user != null){
            switch (item.getItemId()){
                case R.id.nav_cp:
                    fragment = new CuponFragment(this.user);
                    break;
                case R.id.nav_fav:
                    fragment = new FavFragment(this.user);
                    break;

                case R.id.nav_acc:
                    //fragment = new ContaFragment();

                        fragment = new ContaFragment(this.user);

                    break;
                }
        }

        return loadFragment(fragment);

    }
}
