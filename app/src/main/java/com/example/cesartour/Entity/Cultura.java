package com.example.cesartour.Entity;

import java.io.Serializable;

public class Cultura implements Serializable {
    // aqui solo estoy poninedo los atributos que nesecito para el adatador pones los otros que van el archivo plano
    //y que no estoy considerando aqui
    private int codigo;
    private String nombre;
    private String tipo;
    private  int imageCultura;

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
    public int getImageCultura() {
        return imageCultura;
    }

    public void setImageCultura(int imageCultura) {
        this.imageCultura = imageCultura;
    }




}
