package com.example.cesartour.BLL;

import android.content.Context;

import com.example.cesartour.DAL.ActividadRepository;
import com.example.cesartour.DAL.ConexionSQLiteHelper_Actividad;
import com.example.cesartour.DAL.SitioRepository;
import com.example.cesartour.Entity.Actividad;
import com.example.cesartour.Entity.Sitio;

import java.io.IOException;
import java.util.ArrayList;

public class ActividadService {
    Context context;
    private ArrayList<String> idList;


    public ActividadService(Context contexto){
        this.context = contexto;
    }

    public ArrayList<Actividad> displayDatabaseInfoText() throws IOException {
        ActividadRepository actividadRepository = new ActividadRepository(this.context);
        ArrayList<Actividad> actividadList = actividadRepository.displayDatabaseInfoText();

        return actividadList;
    }


}
