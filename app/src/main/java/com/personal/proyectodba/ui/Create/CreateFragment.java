package com.personal.proyectodba.ui.Create;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.personal.proyectodba.MainActivity;
import com.personal.proyectodba.R;
import com.personal.proyectodba.model.producto;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateFragment extends Fragment {


    private EditText etNombre,etPrecio;
    private RadioButton rbCat1,rbCat2,rbCat3,rbCat4;
    private Button btnCancel,btnAcept;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    //private FirebaseUser user;
    //private FirebaseAuth mAuth;
    //private String currentUserID;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateFragment newInstance(String param1, String param2) {
        CreateFragment fragment = new CreateFragment();
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
        View view = inflater.inflate(R.layout.fragment_create, container, false);
        //Busco los id de los editText
        etNombre = (EditText)view.findViewById(R.id.ETnombre);
        etPrecio = (EditText)view.findViewById(R.id.ETprecio);

        //Id de los radioButton
        rbCat1 = (RadioButton)view.findViewById(R.id.rbcat1);
        rbCat2 = (RadioButton)view.findViewById(R.id.rbcat2);
        rbCat3 = (RadioButton)view.findViewById(R.id.rbcat3);
        rbCat4 = (RadioButton)view.findViewById(R.id.rbcat4);

        //Id de los botones
        btnAcept = (Button)view.findViewById(R.id.btnAcept);
        btnCancel = (Button)view.findViewById(R.id.btnCancel);
        inicializarFirebase();




        btnAcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "hola mundo", Toast.LENGTH_SHORT).show();
                String nombre = etNombre.getText().toString();
                String categoria = chekoutRB();
                String price = etPrecio.getText().toString();



                if (!nombre.isEmpty() && !price.isEmpty() &&!categoria.isEmpty()){

                    producto p = new producto();
                    p.setCodigo(UUID.randomUUID().toString());
                    p.setNombre(nombre);
                    p.setPrecio(price);
                    p.setCategoria(categoria);
                    /*databaseReference.child("Producto").child(p.getCategoria()).child(p.getCodigo()).setValue(p);
                    * Con lo de arriba puedo ingresar el producto a firebase dependiendo de la categoria ingresada*/
                    databaseReference.child("Producto").child(p.getCodigo()).setValue(p);

                    Toast.makeText(getActivity(), "Se ingresaron los datos a la base de datos ", Toast.LENGTH_SHORT).show();
                    clean();

                }else{
                    Toast.makeText(getActivity(), "Por favor complete los datos", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clean();
            }
        });

        return view; /*inflater.inflate(R.layout.fragment_create, container, false);*/
    }

    //Obtiene el texto de los rabioButton
    private String chekoutRB(){
        String textoRButton = "";
       if (rbCat1.isChecked()){
           textoRButton = rbCat1.getText().toString();
           return textoRButton;
       }else if (rbCat2.isChecked()){
           textoRButton = rbCat2.getText().toString();
           return textoRButton;
       }else if(rbCat3.isChecked()){
           textoRButton = rbCat3.getText().toString();
           return textoRButton;
       }else if (rbCat4.isChecked()){
           textoRButton = rbCat4.getText().toString();
           return textoRButton;
       }else {
           return "";
       }
    }

    //Limpia el fragment
    private void clean(){
        etPrecio.setText("");
        etNombre.setText("");
        rbCat1.setChecked(false);
        rbCat2.setChecked(false);
        rbCat3.setChecked(false);
        rbCat4.setChecked(false);
    }


    private void inicializarFirebase(){
        FirebaseApp.initializeApp(getActivity());
        FirebaseApp.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }
}