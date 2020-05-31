package com.example.cesartour.DAL;

import android.content.Context;

import com.example.cesartour.Entity.Actividad;
import com.example.cesartour.Entity.Cultura;
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

    public CulturaRepository(Context contexto){
        this.context = contexto;
    }

    public ArrayList<Cultura> displayDatabaseInfoText() throws IOException {
        InputStream archivo = this.context.getResources().openRawResource(R.raw.cultura);
        BufferedReader comodo;
        idList = new ArrayList<String>();
        culturaList = new ArrayList<String>();

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
                culturas.add(cultura);
            }
        } catch (Exception e) {

        } finally {
            archivo.close();
        }

        return culturas;
    }
}
