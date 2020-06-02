package com.example.cesartour.Entity;

import java.io.Serializable;

public class Cultura implements Serializable {
    // aqui solo estoy poninedo los atributos que nesecito para el adatador pones los otros que van el archivo plano
    //y que no estoy considerando aqui
    private int codigo;
    private String nombre;
    private String tipo;
    private String descripcion;
    private String municipio;
    private  int imageCultura;

    public Cultura() {

    }

    public Cultura(String nombre, String tipo, int imageCultura) {

        this.nombre = nombre;
        this.tipo = tipo;
        this.imageCultura = imageCultura;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImageCultura() {
        return imageCultura;
    }

    public void setImageCultura(int imageCultura) {
        this.imageCultura = imageCultura;
    }




}
