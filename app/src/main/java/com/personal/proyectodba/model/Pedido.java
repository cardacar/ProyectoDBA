package com.personal.proyectodba.model;

import java.util.ArrayList;

public class Pedido {
    private String nombre;
    private String telefono;
    private String direccion;
    private String codigo;
    private ArrayList<producto> productList;

    public Pedido() {

    }

    public Pedido(String nombre, String telefono, String direccion, String codigo, ArrayList<producto> productList) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.codigo = codigo;
        this.productList = productList;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ArrayList<producto> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<producto> productList) {
        this.productList = productList;
    }
}
