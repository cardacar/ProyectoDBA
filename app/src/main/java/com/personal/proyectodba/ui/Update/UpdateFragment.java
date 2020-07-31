package com.personal.proyectodba.ui.Update;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.personal.proyectodba.R;
import com.personal.proyectodba.model.producto;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateFragment extends Fragment {

    EditText etUpdateFind,etUpdateName,etUpdatePrice,etUpdateCategory;
    TextView tvUpdateCodigo;
    Button update, updateFind;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UpdateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateFragment newInstance(String param1, String param2) {
        UpdateFragment fragment = new UpdateFragment();
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
        View view = inflater.inflate(R.layout.fragment_update, container, false);

        etUpdateFind = (EditText)view.findViewById(R.id.etUpdateFind);
        etUpdateName = (EditText)view.findViewById(R.id.etUpdateName);
        etUpdatePrice = (EditText)view.findViewById(R.id.etUpdatePrice);
        etUpdateCategory = (EditText)view.findViewById(R.id.etUpdateCategory);

        tvUpdateCodigo = (TextView)view.findViewById(R.id.tvUpdateCodigo);

        update = (Button)view.findViewById(R.id.btnUpdate);
        updateFind = (Button)view.findViewById(R.id.btnUpdateFind);

        inicializarFirebase();

        updateFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigoIngresado;
                codigoIngresado = etUpdateFind.getText().toString();
                databaseReference.child("Producto").child(codigoIngresado).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            String codigo,nombre,categoria,precio;
                            codigo = snapshot.child("codigo").getValue().toString();
                            tvUpdateCodigo.setText(codigo);
                            nombre = snapshot.child("nombre").getValue().toString();
                            etUpdateName.setText(nombre);
                            categoria = snapshot.child("categoria").getValue().toString();
                            etUpdateCategory.setText(categoria);
                            precio = snapshot.child("precio").getValue().toString();
                            etUpdatePrice.setText(precio);

                        }else{
                            Toast.makeText(getActivity(), "El dato no existe", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String codigoIngresado;
                codigoIngresado = etUpdateFind.getText().toString();

                databaseReference.child("Producto").child(codigoIngresado).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            String nombre,precio,categoria;
                            nombre = etUpdateName.getText().toString();
                            precio = etUpdatePrice.getText().toString();
                            categoria = etUpdateCategory.getText().toString();
                            producto p = new producto();
                            p.setCodigo(codigoIngresado);
                            p.setNombre(nombre);
                            p.setCategoria(categoria);
                            p.setPrecio(precio);
                            databaseReference.child("Producto").child(p.getCodigo()).setValue(p);
                        }else{
                            Toast.makeText(getActivity(), "El dato no existe", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                clean();

            }
        });



        return view;
    }

    private void clean(){
        etUpdateName.setText(" ");
        etUpdateCategory.setText(" ");
        etUpdatePrice.setText(" ");
        etUpdateFind.setText(" ");
        tvUpdateCodigo.setText(" ");
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(getActivity());
        FirebaseApp.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
}