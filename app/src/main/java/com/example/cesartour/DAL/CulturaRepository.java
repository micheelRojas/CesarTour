package com.example.cesartour.DAL;

import android.content.Context;

import com.example.cesartour.Entity.Actividad;
import com.example.cesartour.Entity.Cultura;
import com.example.cesartour.Entity.ImagenActividad;
import com.example.cesartour.Entity.ImagenCultura;
import com.example.cesartour.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CulturaRepository {
    private ArrayList<String> culturaList;
    private ArrayList<String> idList;
    private ArrayList<Cultura> culturas = new ArrayList<>();;
    private Context context;

    private ArrayList<ImagenCultura> imagenes;

    public CulturaRepository(Context contexto){
        this.context = contexto;
    }

    public ArrayList<Cultura> displayDatabaseInfoText() throws IOException {
        InputStream archivo = this.context.getResources().openRawResource(R.raw.cultura);
        BufferedReader comodo;
        idList = new ArrayList<String>();
        culturaList = new ArrayList<String>();

        imagenes = new ArrayList<>();
        imagenes.add(new ImagenCultura(0, R.drawable.cultenbuenasmanos));
        imagenes.add(new ImagenCultura(1, R.drawable.cultsirena));
        imagenes.add(new ImagenCultura(2, R.drawable.cultfrancisco));
        imagenes.add(new ImagenCultura(3, R.drawable.cultlaceibita));
        imagenes.add(new ImagenCultura(4, R.drawable.culteusebio));
        imagenes.add(new ImagenCultura(5, R.drawable.cultcasaenaire));
        imagenes.add(new ImagenCultura(6, R.drawable.cult039));
        imagenes.add(new ImagenCultura(7, R.drawable.cultsinmedirdistancias));
        imagenes.add(new ImagenCultura(8, R.drawable.cultlallorona));
        imagenes.add(new ImagenCultura(9, R.drawable.cultalvaro));
        imagenes.add(new ImagenCultura(10, R.drawable.cultchiche));
        imagenes.add(new ImagenCultura(11, R.drawable.cultomar));

        try {
            comodo = new BufferedReader(new InputStreamReader(archivo));

            String linea, valores[];
            Cultura cultura;
            while ((linea = comodo.readLine()) != null) {
                valores = linea.split(";");
                cultura = new Cultura();
                cultura.setCodigo(Integer.parseInt(valores[0]));
                cultura.setNombre(valores[1]);
                cultura.setTipo(valores[2]);
                cultura.setDescripcion(valores[3]);
                cultura.setMunicipio(valores[4]);
                for (ImagenCultura item: imagenes) {
                    if(item.getCodigo() == cultura.getCodigo()){
                        cultura.setImageCultura(item.getImagen());
                    }
                }

                culturas.add(cultura);
            }
        } catch (Exception e) {

        } finally {
            archivo.close();
        }

        return culturas;
    }
}
