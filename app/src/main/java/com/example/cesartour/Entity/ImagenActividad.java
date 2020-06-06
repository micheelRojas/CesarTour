package com.example.cesartour.Entity;

public class ImagenActividad {
    private int Codigo;
    private int Imagen;

    public ImagenActividad(int codigo, int imagen) {
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
