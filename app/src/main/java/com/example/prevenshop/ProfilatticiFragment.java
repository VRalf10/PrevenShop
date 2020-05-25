package com.example.prevenshop;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prevenshop.model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


public class ProfilatticiFragment extends Fragment {

    private RecyclerView recyclerView;

    //firebase...

    private DatabaseReference mProfilatticiDatabase;




    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview = inflater.inflate(R.layout.fragment_profilattici, container, false);

        mProfilatticiDatabase= FirebaseDatabase.getInstance().getReference().child("ProfilatticiDatabase");
        mProfilatticiDatabase.keepSynced(true);

        recyclerView = myview.findViewById(R.id.recycler_profilattici);

        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity());
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        return myview;
    }

    @Override
    public void onStart() {
        super.onStart();

        final FirebaseRecyclerAdapter<Data,ProfilatticiviewHolder>adapter=new FirebaseRecyclerAdapter<Data, ProfilatticiviewHolder>
                (
                        Data.class,
                        R.layout.cust_idem_data,
                        ProfilatticiFragment.ProfilatticiviewHolder.class,
                        mProfilatticiDatabase
                ) {

            @Override
            protected void populateViewHolder(ProfilatticiviewHolder profilatticiviewHolder, final Data data, int i) {

                profilatticiviewHolder.setTitle(data.getTitle());
                profilatticiviewHolder.setDescription(data.getDescription());
                profilatticiviewHolder.setImage(data.getImage());
                profilatticiviewHolder.setPrice(data.getPrice());

                profilatticiviewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(),ProfilatticiDetailsActivity.class);
                        intent.putExtra("title", data.getTitle() );
                        intent.putExtra("description",data.getDescription());
                        intent.putExtra("image", data.getImage());
                        intent.putExtra("price",data.getPrice());
                        startActivity(intent);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }





    public static class ProfilatticiviewHolder extends RecyclerView.ViewHolder{

        View mview;

        public ProfilatticiviewHolder(@NonNull View itemView) {
            super(itemView);
            mview=itemView;
        }

        public void setTitle(String title){
            TextView mTitle=mview.findViewById(R.id.post_title);
            mTitle.setText(title);
        }

        public void setDescription(String description){
            TextView mDescription=mview.findViewById(R.id.post_details);
            mDescription.setText(description);
        }

        public void setPrice(String price){
            TextView mPrice = mview.findViewById(R.id.post_price);
            mPrice.setText("€"+price);
        }


        public void setImage(final String image){
            final ImageView mImage = mview.findViewById(R.id.post_image);
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
