package com.example.cesartour.Entity;

import android.widget.ImageView;

import java.io.Serializable;

public class Evento implements Serializable {
    private int codigo;
    private String nombre;
    private String municipio;
    private String fecha;
    private String descripcion;

    // esta es la que debe ir
    private  int imageEvento;

    public Evento() {

    }

    public Evento(String nombre, String municipio, int imageEvento) {
        this.nombre = nombre;
        this.municipio = municipio;
        this.imageEvento = imageEvento;
    }

    public int getImageEvento() {
        return imageEvento;
    }

    public void setImageEvento(int imageEvento) {
        this.imageEvento = imageEvento;
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

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
