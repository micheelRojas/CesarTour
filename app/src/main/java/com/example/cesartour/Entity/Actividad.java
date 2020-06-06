package com.example.cesartour.Entity;

import android.widget.ImageView;

import java.io.Serializable;

public class Actividad implements Serializable {
    private int codigo;
    private String nombre;
    private String categoria;
    private String descripcion;
    private String municipio;

    // esta es la que debe ir
    private  int imageActividad;

    public Actividad() {

    }

    public Actividad(String nombre, String categoria, int imageActividad) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.imageActividad = imageActividad;
    }

    public int getImageActividad() {
        return imageActividad;
    }

    public void setImageActividad(int imageActividad) {
        this.imageActividad = imageActividad;
    }


    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
}
