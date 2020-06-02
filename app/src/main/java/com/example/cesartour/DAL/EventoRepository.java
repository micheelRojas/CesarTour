package com.example.cesartour.DAL;

import android.content.Context;
import android.widget.ListView;

import com.example.cesartour.Entity.Evento;
import com.example.cesartour.Entity.ImagenEvento;
import com.example.cesartour.Entity.Sitio;
import com.example.cesartour.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class EventoRepository {
    private ArrayList<String> eventoList;
    private ArrayList<String> idList;
    private ArrayList<Evento> eventos = new ArrayList<>();;
    private Context context;

    private ArrayList<ImagenEvento> imagenes;

    public EventoRepository(Context contexto){
        this.context = contexto;
    }

    public ArrayList<Evento> displayDatabaseInfoText() throws IOException {
        InputStream archivo = this.context.getResources().openRawResource(R.raw.eventos);
        BufferedReader comodo;
        idList = new ArrayList<String>();
        eventoList = new ArrayList<String>();

        imagenes = new ArrayList<>();
        imagenes.add(new ImagenEvento(0, R.drawable.valledupar));
        imagenes.add(new ImagenEvento(1, R.drawable.pueblo_bello));
        imagenes.add(new ImagenEvento(2, R.drawable.manaure));
        imagenes.add(new ImagenEvento(3, R.drawable.valledupar));
        imagenes.add(new ImagenEvento(4, R.drawable.pueblo_bello));
        imagenes.add(new ImagenEvento(5, R.drawable.manaure));
        imagenes.add(new ImagenEvento(6, R.drawable.valledupar));
        imagenes.add(new ImagenEvento(7, R.drawable.pueblo_bello));
        imagenes.add(new ImagenEvento(8, R.drawable.manaure));
        imagenes.add(new ImagenEvento(9, R.drawable.manaure));

        try {
            comodo = new BufferedReader(new InputStreamReader(archivo));

            String linea, valores[];
            Evento evento;
            while ((linea = comodo.readLine()) != null) {
                valores = linea.split(";");
                evento = new Evento();
                evento.setCodigo(Integer.parseInt(valores[0]));
                evento.setNombre(valores[1]);
                evento.setMunicipio(valores[2]);
                evento.setFecha(valores[3]);
                evento.setDescripcion(valores[4]);
                for (ImagenEvento item: imagenes) {
                    if(item.getCodigo() == evento.getCodigo()){
                        evento.setImageEvento(item.getImagen());
                    }
                }

                eventos.add(evento);
            }
        } catch (Exception e) {

        } finally {
            archivo.close();
        }

        return eventos;
    }

}
