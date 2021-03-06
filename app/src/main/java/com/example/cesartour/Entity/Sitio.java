package com.example.cesartour.Entity;

import android.widget.ImageView;

import java.io.Serializable;

public class Sitio implements Serializable {
    private int codigo;
    private String nombre;
    private String categoria;
    private String direccion;
    private String descripcion;
    private String municipio;
    //esta se debe quitar

    // esta es la que debe  ir
    private int imageSitio;
    private String tipoObjeto;

    public Sitio(String nombre, String categoria,  int imageSitio) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.imageSitio = imageSitio;
    }
    public Sitio() {
    }

    public int getImageSitio() {
        return imageSitio;
    }

    public void setImageSitio(int imageSitio) {
        this.imageSitio = imageSitio;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getTipoObjeto() {
        return tipoObjeto;
    }

    public void setTipoObjeto(String tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
    }
}
