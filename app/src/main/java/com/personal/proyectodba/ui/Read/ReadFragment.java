package com.personal.proyectodba.ui.Read;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.personal.proyectodba.adapter.readAdapter;
import com.personal.proyectodba.model.producto;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.personal.proyectodba.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReadFragment extends Fragment {


    private RecyclerView myReadRecyclerView;
    CheckBox check;
    private readAdapter rAdapter;
    private ArrayList<producto> lProducto = new ArrayList<>();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference productoRef;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReadFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReadFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReadFragment newInstance(String param1, String param2) {
        ReadFragment fragment = new ReadFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //Inflo mi vista
        View readView = inflater.inflate(R.layout.fragment_read, container, false);
        //Obtengo el id del recyclerview
        inicializarFirebase();
        myReadRecyclerView = (RecyclerView)readView.findViewById(R.id.recyclerRead);
        myReadRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        getProductoFromFirebase();

        check = (CheckBox)readView.findViewById(R.id.checkD);
        





        return readView;
    }

    private void getProductoFromFirebase(){
        productoRef.child("Producto").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot ds: snapshot.getChildren()){
                        String codigo = ds.child("codigo").getValue().toString();
                        String nombre = ds.child("nombre").getValue().toString();
                        String precio = ds.child("precio").getValue().toString();
                        String categoria = ds.child("categoria").getValue().toString();

                        lProducto.add(new producto(codigo,nombre,precio,categoria));
                    }
                    rAdapter = new readAdapter(lProducto,R.layout.vistaproducto);
                    myReadRecyclerView.setAdapter(rAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(getActivity());
        firebaseDatabase = FirebaseDatabase.getInstance();
        productoRef = firebaseDatabase.getReference();
    }


}