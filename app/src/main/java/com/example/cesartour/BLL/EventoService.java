package com.example.cesartour.BLL;

import android.content.Context;

import com.example.cesartour.DAL.EventoRepository;
import com.example.cesartour.DAL.SitioRepository;
import com.example.cesartour.Entity.Evento;
import com.example.cesartour.Entity.Sitio;

import java.io.IOException;
import java.util.ArrayList;

public class EventoService {
    Context context;
    private ArrayList<String> idList;

    public EventoService(Context contexto){
        this.context = contexto;
    }

    public ArrayList<Evento> displayDatabaseInfoText() throws IOException {
        EventoRepository eventoRepository = new EventoRepository(this.context);
        ArrayList<Evento> eventoList = eventoRepository.displayDatabaseInfoText();

        return eventoList;
    }
}
