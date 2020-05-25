package com.example.prevenshop;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prevenshop.model.Carrello;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.Inflater;

import static com.example.prevenshop.R.layout.fragment_carrello;


/**
 * A simple {@link Fragment} subclass.
 */
public class CarrelloFragment extends Fragment {

    private ListView listView;
    private DatabaseReference mCartOneDatabase;
    private ArrayList<String> arraylist;
    private TextView tot;
    private static double mtot;
    private Carrello carrello = new Carrello();
    public static int id=0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(fragment_carrello,container,false);
        Button button=(Button) view.findViewById(R.id.buttonpaga);
        Button cancella=(Button) view.findViewById(R.id.cancellButton);
        final ArrayList<String> arrayList = carrello.getCarrello();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                android.R.layout.simple_list_item_1,
                arrayList
                );
        ListView lvData = (ListView) view.findViewById(R.id.list_Carrello);
        lvData.setAdapter(adapter);
        tot = (TextView)view.findViewById(R.id.total);
        tot.setText("€"+String.valueOf(mtot));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(arrayList.isEmpty()){
                    Toast.makeText(view.getContext(),"INSERISCI QUALCOSA NEL CARRELLO", Toast.LENGTH_LONG).show();
                }
                else {
                    FirebaseAuth fAuth = FirebaseAuth.getInstance();
                    FirebaseFirestore fStore = FirebaseFirestore.getInstance();
                    final String userID = fAuth.getCurrentUser().getUid();
                    DocumentReference documentReference = fStore.collection("ordini").document(userID+id);
                    Map<String,Object> ordini = new HashMap<>();
                    ordini.put("ID",id+1);
                    for(int i=0; i<arrayList.size(); i++){
                        ordini.put("prodotto" +i,arrayList.get(i));
                    }
                    documentReference.set(ordini).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("TAG", "onSuccess: ORDINE INVIATO PER "+ userID);
                        }
                    });

                    Toast.makeText(view.getContext(), "PAGAMENTO EFFETTUATO", Toast.LENGTH_LONG).show();
                    arrayList.clear();
					mtot=0;
                    FragmentTransaction ftr = getFragmentManager().beginTransaction();
                    ftr.detach(CarrelloFragment.this).attach(CarrelloFragment.this).commit();
                }
            }
        });

        cancella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(arrayList.isEmpty()){
                    Toast.makeText(view.getContext(),"IL CARRELLO E' GIA' VUOTO", Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(view.getContext(),"PRODOTTI ELIMINATI DAL CARRELLO",Toast.LENGTH_LONG).show();
                    arrayList.clear();
                    mtot=0;
                    FragmentTransaction ftr = getFragmentManager().beginTransaction();
                    ftr.detach(CarrelloFragment.this).attach(CarrelloFragment.this).commit();
                }
            }
        });
        id++;
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();


    }


    public void setTotas(double price){
        mtot=price+mtot;
    }
}
