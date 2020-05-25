package com.example.prevenshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prevenshop.model.Carrello;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProfilatticiDetailsActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView title;
    private TextView description;
    private String productID="";
    private TextView price;
    public String prod;
    public Carrello carrello;
    private String mprice;
    Button addToCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilattici);



        productID= getIntent().getStringExtra("id");

        imageView = findViewById(R.id.image_dt);
        title = findViewById(R.id.title_details);
        description = findViewById(R.id.description_details);
        addToCart = findViewById(R.id.button2);
        price = findViewById(R.id.prezzo);

        Intent intent = getIntent();

        String mTitle = intent.getStringExtra("title");
        String mDescription = intent.getStringExtra("description");
        final String mImage = intent.getStringExtra("image");
        mprice = intent.getStringExtra("price");
        carrello=new Carrello();
        final String prod = mTitle +"," +" " +mprice +"€";
        title.setText(mTitle);
        description.setText(mDescription);
        price.setText("€"+mprice);


        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addingToCartList(prod);

            }
        });



        Picasso.get().load(mImage).networkPolicy(NetworkPolicy.OFFLINE).into(imageView, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError(Exception e) {
                Picasso.get().load(mImage).into(imageView);
            }
        });
    }

    private void addingToCartList(String prod) {
        carrello=new Carrello();
        carrello.addElement(prod);

        CarrelloFragment carr = new CarrelloFragment();
        carr.setTotas(Double.parseDouble(mprice));
        Toast.makeText(this, "Prodotto aggiunto al carrello", Toast.LENGTH_SHORT).show();


    }


}
