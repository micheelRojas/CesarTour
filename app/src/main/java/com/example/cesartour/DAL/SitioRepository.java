package com.example.cesartour.DAL;

import android.content.Context;
import android.widget.ListView;


import com.example.cesartour.Entity.ImagenEvento;
import com.example.cesartour.Entity.ImagenSitio;
import com.example.cesartour.Entity.Sitio;
import com.example.cesartour.R;

import java.io.BufferedReader;
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

    private ArrayList<ImagenSitio> imagenes;

    public SitioRepository(Context contexto){
        this.context = contexto;
    }

    public ArrayList<Sitio> displayDatabaseInfoText() throws IOException {
        InputStream archivo = this.context.getResources().openRawResource(R.raw.sitios);
        BufferedReader comodo;
        idList = new ArrayList<String>();
        sitioList = new ArrayList<String>();

        imagenes = new ArrayList<>();
        imagenes.add(new ImagenSitio(0, R.drawable.slasierra));
        imagenes.add(new ImagenSitio(1, R.drawable.sitiosonesta));
        imagenes.add(new ImagenSitio(2, R.drawable.sitiokasvel));
        imagenes.add(new ImagenSitio(3, R.drawable.sitioelgordosaul));
        imagenes.add(new ImagenSitio(4, R.drawable.sitioeltucan));
        imagenes.add(new ImagenSitio(5, R.drawable.sitiobrasamanaurera));
        imagenes.add(new ImagenSitio(6, R.drawable.sitiobosconiaimperial));
        imagenes.add(new ImagenSitio(7, R.drawable.sitiojorlin));
        imagenes.add(new ImagenSitio(8, R.drawable.sitiolabrasa));


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
                sitio.setTipoObjeto(valores[6]);
                for (ImagenSitio item: imagenes) {
                    if(item.getCodigo() == sitio.getCodigo()){
                        sitio.setImageSitio(item.getImagen());
                    }
                }

                sitios.add(sitio);
            }
        } catch (Exception e) {

        } finally {
            archivo.close();
        }

        return sitios;
    }
}
