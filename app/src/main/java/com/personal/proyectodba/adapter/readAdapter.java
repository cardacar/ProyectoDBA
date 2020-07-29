package com.personal.proyectodba.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.personal.proyectodba.R;
import com.personal.proyectodba.model.producto;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class readAdapter
        extends RecyclerView.Adapter<readAdapter.ViewHolder>
        implements View.OnClickListener{

    private int resource;
    private ArrayList<producto> productList;
    private View.OnClickListener listener;

    public readAdapter(ArrayList<producto> productList,int resource){
        this.productList = productList;
        this.resource = resource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Primero obtenemos la vista xml que se creo para el producto
        View view = LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);

        view.setOnClickListener(this);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        producto product = productList.get(position);
        holder.tvCodigo.setText(product.getCodigo());
        holder.tvNombre.setText(product.getNombre());
        holder.tvPrecio.setText(product.getPrecio());
        holder.tvCategoria.setText(product.getCategoria());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;

    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }

    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tvCodigo,tvNombre,tvPrecio,tvCategoria;
        public View view;

        public ViewHolder(View view){
            super(view);
            this.view = view;

            this.tvCodigo = (TextView)view.findViewById(R.id.tvCodigo);
            this.tvNombre = (TextView)view.findViewById(R.id.tvNombre);
            this.tvPrecio = (TextView)view.findViewById(R.id.tvprecio);
            this.tvCategoria = (TextView)view.findViewById(R.id.tvCategoria);

        }
    }
}
