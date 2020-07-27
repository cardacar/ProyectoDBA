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

import com.personal.proyectodba.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateFragment extends Fragment {

    EditText etNombre,etPrecio;
    RadioButton rbCat1,rbCat2,rbCat3,rbCat4;
    Button btnCancel,btnAcept;

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

        btnAcept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "hola mundo", Toast.LENGTH_SHORT).show();

            }
        });
        return view; /*inflater.inflate(R.layout.fragment_create, container, false);*/


        //
    }
}