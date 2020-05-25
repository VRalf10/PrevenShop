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

import com.example.prevenshop.ProfilatticiDetailsActivity;
import com.example.prevenshop.R;
import com.example.prevenshop.model.Data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;


public class AccessoriFragment extends Fragment {

    private RecyclerView recyclerView;

    //firebase...

    private DatabaseReference mAccessoriDatabase;




    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview = inflater.inflate(R.layout.fragment_accessori, container, false);

        mAccessoriDatabase= FirebaseDatabase.getInstance().getReference().child("AccessoriDatabase");
        mAccessoriDatabase.keepSynced(true);

        recyclerView = myview.findViewById(R.id.recycler_accessori);

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

        final FirebaseRecyclerAdapter<Data, AccessoriViewHolder> adapter = new FirebaseRecyclerAdapter<Data, AccessoriViewHolder>(
                Data.class,
                R.layout.cust_idem_data,
                AccessoriFragment.AccessoriViewHolder.class,
                mAccessoriDatabase
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

                        Intent intent = new Intent(getActivity(), AccessoriDetailsActivity.class);

                        intent.putExtra("title", model.getTitle());
                        intent.putExtra("description", model.getDescription());
                        intent.putExtra("image", model.getImage());
                        intent.putExtra("price", model.getPrice());
                        startActivity(intent);
                    }
                });
            }
        };


        recyclerView.setAdapter(adapter);
    }


    public static class AccessoriViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public AccessoriViewHolder(@NonNull View itemView) {
            super(itemView);
            mView=itemView;

        }
        public void setTitle(String title) {
            TextView mTitle = mView.findViewById(R.id.post_title);
            mTitle.setText(title);
        }


        public void setDescription(String description) {
            TextView mDescription = mView.findViewById(R.id.post_details);
            mDescription.setText(description);
        }

        public void setPrice(String price){
            TextView mPrice = mView.findViewById(R.id.post_price);
            mPrice.setText("€"+price);
        }

        public void setImage(final String image) {
            final ImageView mImage = mView.findViewById(R.id.post_image);
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
