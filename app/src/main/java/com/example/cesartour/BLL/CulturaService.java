package com.example.cesartour.BLL;

import android.content.Context;

import com.example.cesartour.DAL.ActividadRepository;
import com.example.cesartour.DAL.CulturaRepository;
import com.example.cesartour.Entity.Actividad;
import com.example.cesartour.Entity.Cultura;

import java.io.IOException;
import java.util.ArrayList;

public class CulturaService {
    Context context;
    private ArrayList<String> idList;

    public CulturaService(Context contexto){
        this.context = contexto;
    }

    public ArrayList<Cultura> displayDatabaseInfoText() throws IOException {
        CulturaRepository culturaRepository = new CulturaRepository(this.context);
        ArrayList<Cultura> culturaList = culturaRepository.displayDatabaseInfoText();

        return culturaList;
    }
}
