package com.example.cesartour.BLL;

import android.content.Context;

import com.example.cesartour.DAL.SitioRepository;
import com.example.cesartour.Entity.Sitio;

import java.io.IOException;
import java.util.ArrayList;

public class SitioService {
    Context context;
    private ArrayList<String> idList;
    //private ArrayList<Sitio> sitios = new ArrayList<>();

    public SitioService(Context contexto){
        this.context = contexto;
    }

    /*public ArrayList<String> displayDatabaseInfoText() throws IOException {
        SitioRepository sitioRepository = new SitioRepository(this.context);
        ArrayList<String> sitioList = sitioRepository.displayDatabaseInfoText();

        return sitioList;
    } */

    public ArrayList<Sitio> displayDatabaseInfoText() throws IOException {
        SitioRepository sitioRepository = new SitioRepository(this.context);
        ArrayList<Sitio> sitioList = sitioRepository.displayDatabaseInfoText();

        return sitioList;
    }

    /*public ArrayList<Sitio> returnSitios(){

        //idList = new ArrayList<>();
        SitioRepository sitioRepository = new SitioRepository(this.context);
        sitios = sitioRepository.returnSitios();
        ArrayList<Sitio> Lsitios = sitios;
        return Lsitios;
    } */
}
