package com.example.prevenshop;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prevenshop.model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class AllFragment extends Fragment {

    private RecyclerView allRecycler;
    private RecyclerView allRecycler2;
    private DatabaseReference mCartOneDatabase;
    private DatabaseReference mCartTwoDatabase;
    Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myview = inflater.inflate(R.layout.fragment_all, container, false);

        mCartOneDatabase= FirebaseDatabase.getInstance().getReference().child("ProfilatticiDatabase");
        mCartTwoDatabase = FirebaseDatabase.getInstance().getReference().child("AccessoriDatabase");

        allRecycler = myview.findViewById(R.id.recycler_all);
        allRecycler2= myview.findViewById(R.id.recycler_all2);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        allRecycler.setHasFixedSize(true);
        allRecycler2.setHasFixedSize(true);
        allRecycler.setLayoutManager(layoutManager);
        allRecycler2.setLayoutManager(layoutManager1);
        return myview;

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Data, ProfilatticiViewHolder> adapterone = new FirebaseRecyclerAdapter<Data, ProfilatticiViewHolder>
                (Data.class,
                R.layout.item_data,
                ProfilatticiViewHolder.class,
                        mCartOneDatabase
                ) {
            @Override
            protected void populateViewHolder(ProfilatticiViewHolder profilatticiViewHolder, final Data model, int i) {
                profilatticiViewHolder.setTitle(model.getTitle());
                profilatticiViewHolder.setDescription(model.getDescription());
                profilatticiViewHolder.setImage(model.getImage());
                profilatticiViewHolder.setPrice(model.getPrice());

                profilatticiViewHolder.myview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent= new Intent(getActivity(),ProfilatticiDetailsActivity.class);

                        intent.putExtra("title", model.getTitle() );
                        intent.putExtra("description",model.getDescription());
                        intent.putExtra("image", model.getImage());
                        intent.putExtra("price",model.getPrice());
                        startActivity(intent);
                    }
                });




            }
        };
        allRecycler.setAdapter(adapterone);

        FirebaseRecyclerAdapter<Data, AccessoriViewHolder> adaptertwo = new FirebaseRecyclerAdapter<Data, AccessoriViewHolder>
                (Data.class,
                        R.layout.item_data,
                        AccessoriViewHolder.class,
                        mCartTwoDatabase
                ) {
            @Override
            protected void populateViewHolder(AccessoriViewHolder accessoriViewHolder, final Data model, int i) {
                accessoriViewHolder.setTitle(model.getTitle());
                accessoriViewHolder.setDescription(model.getDescription());
                accessoriViewHolder.setImage(model.getImage());
                accessoriViewHolder.setPrice(model.getPrice());

                accessoriViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent= new Intent(getActivity(),AccessoriDetailsActivity.class);

                        intent.putExtra("title", model.getTitle() );
                        intent.putExtra("description",model.getDescription());
                        intent.putExtra("image", model.getImage());
                        intent.putExtra("price",model.getPrice());
                        startActivity(intent);
                    }
                });




            }
        };



        allRecycler2.setAdapter(adaptertwo);

    }

        public static class ProfilatticiViewHolder extends RecyclerView.ViewHolder {
            View myview;

            public ProfilatticiViewHolder(@NonNull View itemView) {
                super(itemView);
                myview = itemView;
            }

            public void setTitle(String title) {
                TextView mTitle = myview.findViewById(R.id.title);
                mTitle.setText(title);
            }


            public void setDescription(String description) {
                TextView mDescription = myview.findViewById(R.id.description);
                mDescription.setText(description);
            }

            public void setPrice(String price){
                TextView mPrice = myview.findViewById(R.id.price);
                mPrice.setText("€"+price);
            }

            public void setImage(final String image) {
                final ImageView mImage = myview.findViewById(R.id.imageView);
                Picasso.get().load(image).networkPolicy(NetworkPolicy.OFFLINE).into(mImage, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get().load(image).into(mImage);
                    }
                });


            }
        }

        public static class AccessoriViewHolder extends RecyclerView.ViewHolder{
            View mView;
            public AccessoriViewHolder(@NonNull View itemView) {
                super(itemView);
                mView=itemView;

            }
            public void setTitle(String title) {
                TextView mTitle = mView.findViewById(R.id.title);
                mTitle.setText(title);
            }


            public void setDescription(String description) {
                TextView mDescription = mView.findViewById(R.id.description);
                mDescription.setText(description);
            }

            public void setPrice(String price){
                TextView mPrice = mView.findViewById(R.id.price);
                mPrice.setText("€"+price);
            }

            public void setImage(final String image) {
                final ImageView mImage = mView.findViewById(R.id.imageView);
                Picasso.get().load(image).networkPolicy(NetworkPolicy.OFFLINE).into(mImage, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get().load(image).into(mImage);
                    }
                });

            }

        }

}