package com.example.cesartour.DAL;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import androidx.core.content.ContextCompat;

import com.example.cesartour.Entity.Sitio;
import com.example.cesartour.MainActivity;
import com.example.cesartour.R;
import com.example.cesartour.SitioFragment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;




public class SitioRepository {
    private ListView listViewSitios;
    private ArrayList<String> sitioList;
    private ArrayList<String> idList;
    private ArrayList<Sitio> sitios = new ArrayList<>();;
    private Context context;

    public SitioRepository(Context contexto){
        this.context = contexto;
    }

    /*public ArrayList<String> displayDatabaseInfoText() throws IOException {
        InputStream archivo = this.context.getResources().openRawResource(R.raw.sitios);
        BufferedReader comodo;
        idList = new ArrayList<String>();
        sitioList = new ArrayList<String>();

            try {
                comodo = new BufferedReader(new InputStreamReader(archivo));

                String linea, valores[];
                Sitio sitio;
                while ((linea = comodo.readLine()) != null) {
                    valores = linea.split(";");
                    sitio = new Sitio();
                    sitio.setCodigo(Integer.parseInt(valores[0]));
                    sitio.setNombre(valores[1]);
                    sitio.setCategoria(valores[2]);
                    sitio.setDireccion(valores[3]);
                    sitio.setDescripcion(valores[4]);
                    sitio.setMunicipio(valores[5]);
                    sitios.add(sitio);

                    String dateSitios = sitio.getNombre() + "\n" + sitio.getCategoria();
                    sitioList.add(dateSitios);
                    idList.add(sitio.getCodigo()+"");
                }
            } catch (Exception e) {

            } finally {
                archivo.close();
            }

        return sitioList;
    } */

    public ArrayList<Sitio> displayDatabaseInfoText() throws IOException {
        InputStream archivo = this.context.getResources().openRawResource(R.raw.sitios);
        BufferedReader comodo;
        idList = new ArrayList<String>();
        sitioList = new ArrayList<String>();

        try {
            comodo = new BufferedReader(new InputStreamReader(archivo));

            String linea, valores[];
            Sitio sitio;
            while ((linea = comodo.readLine()) != null) {
                valores = linea.split(";");
                sitio = new Sitio();
                sitio.setCodigo(Integer.parseInt(valores[0]));
                sitio.setNombre(valores[1]);
                sitio.setCategoria(valores[2]);
                sitio.setDireccion(valores[3]);
                sitio.setDescripcion(valores[4]);
                sitio.setMunicipio(valores[5]);
                sitios.add(sitio);

                String dateSitios = sitio.getNombre() + "\n" + sitio.getCategoria();
                sitioList.add(dateSitios);
                idList.add(sitio.getCodigo()+"");
            }
        } catch (Exception e) {

        } finally {
            archivo.close();
        }

        return sitios;
    }

    /*public ArrayList<Sitio> returnSitios(){
        ArrayList<Sitio> Lsitios = this.sitios;
        return Lsitios;
    } */
}
