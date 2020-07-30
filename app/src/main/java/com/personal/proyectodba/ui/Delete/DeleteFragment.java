package com.personal.proyectodba.ui.Delete;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.personal.proyectodba.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeleteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeleteFragment extends Fragment {

    TextView tvdeleteCodigo,tvdeleteNombre,tvDeleteCategoria,tvDeletePrecio;
    EditText findId;
    Button delete, deleteFind;



    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference,productoRef;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DeleteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeleteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeleteFragment newInstance(String param1, String param2) {
        DeleteFragment fragment = new DeleteFragment();
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
        View view = inflater.inflate(R.layout.fragment_delete, container, false);

        findId = (EditText)view.findViewById(R.id.findId);
        tvdeleteCodigo=(TextView)view.findViewById(R.id.tvDeleteCodigo);
        tvdeleteNombre=(TextView)view.findViewById(R.id.tvDeleteNombre);
        tvDeleteCategoria=(TextView)view.findViewById(R.id.tvDeleteCategoria);
        tvDeletePrecio=(TextView)view.findViewById(R.id.tvDeletePrecio);
        deleteFind = (Button)view.findViewById(R.id.btnFindElement);
        delete = (Button)view.findViewById(R.id.btnDelete);

        inicializarFirebase();

        deleteFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreIngresado;
                nombreIngresado = findId.getText().toString();
                databaseReference.child("Producto").child(nombreIngresado).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(snapshot.exists()){
                            String codigo,nombre,categoria,precio;
                            codigo = snapshot.child("codigo").getValue().toString();
                            tvdeleteCodigo.setText(codigo);
                            nombre = snapshot.child("nombre").getValue().toString();
                            tvdeleteNombre.setText(nombre);
                            categoria = snapshot.child("categoria").getValue().toString();
                            tvDeleteCategoria.setText(categoria);
                            precio = snapshot.child("precio").getValue().toString();
                            tvDeletePrecio.setText(precio);

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

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nombreIngresado;
                nombreIngresado = findId.getText().toString();
                //Modificar ya que entra al if y al else
                databaseReference.child("Producto").child(nombreIngresado).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            databaseReference.child("Producto").child(nombreIngresado).removeValue();
                            Toast.makeText(getActivity(), "Se elimino el dato", Toast.LENGTH_SHORT).show();
                            tvdeleteCodigo.setText("");
                            tvDeleteCategoria.setText("");
                            tvdeleteNombre.setText("");
                            tvDeletePrecio.setText("");
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



        return view;
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(getActivity());
        FirebaseApp.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
}