package com.example.prevenshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.prevenshop.model.Carrello;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private BottomNavigationView carrellNavigationView;
    private AllFragment allFragment;
    private ProfilatticiFragment profilatticiFragment;
    private CarrelloFragment carrelloFragment;
    private AccessoriFragment accessoriFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        allFragment=new AllFragment();
        profilatticiFragment=new ProfilatticiFragment();
        carrelloFragment=new CarrelloFragment();
        accessoriFragment=new AccessoriFragment();
        setFragment(allFragment);
        bottomNavigationView=findViewById(R.id.bottombar);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.all:
                        bottomNavigationView.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(allFragment);
                        return true;
                    case R.id.profilattici:
                        bottomNavigationView.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(profilatticiFragment);
                        return true;
                    case R.id.carrello:
                        bottomNavigationView.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(carrelloFragment);
                        return true;
                    case R.id.accessori:
                        bottomNavigationView.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(accessoriFragment);
                        return true;
                    default:
                        return false;
                }
            }
        });


    }

    public void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.shopframe, fragment);
        fragmentTransaction.commit();



    }


    /*public void bannerFliper(int image){
        ImageView imageView=new ImageView(this);
        imageView.setImageResource(image);
        imgBanner.addView(imageView);
        imgBanner.setFlipInterval(6000);
        imgBanner.setAutoStart(true);
        imgBanner.setInAnimation(this, android.R.anim.fade_in);
        imgBanner.setOutAnimation(this, android.R.anim.fade_out);
    }*/

}
