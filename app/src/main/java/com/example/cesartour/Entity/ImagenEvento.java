package com.example.cesartour.Entity;

public class ImagenEvento {
    private int Codigo;
    private int Imagen;

    public ImagenEvento(int codigo, int imagen) {
        Codigo = codigo;
        Imagen = imagen;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public int getImagen() {
        return Imagen;
    }

    public void setImagen(int imagen) {
        Imagen = imagen;
    }
}
