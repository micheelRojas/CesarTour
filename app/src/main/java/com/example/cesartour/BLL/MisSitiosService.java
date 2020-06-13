package com.example.cesartour.BLL;

import com.example.cesartour.DAL.ConexionSQLiteHelper_Evento;
import com.example.cesartour.DAL.ConexionSQLiteHelper_Sitio;
import com.example.cesartour.DAL.MisEventosRepository;
import com.example.cesartour.DAL.MisSitiosRepository;
import com.example.cesartour.Entity.Evento;
import com.example.cesartour.Entity.Sitio;

import java.util.ArrayList;

public class MisSitiosService {
    MisSitiosRepository misSitiosRepository = new MisSitiosRepository();


    public long registrarMisSitios(ConexionSQLiteHelper_Sitio conn, Sitio sitio){

        long idResultante = misSitiosRepository.registrarMisSitios(conn, sitio);
        return idResultante;
    }

    public ArrayList<Sitio> consultarTodas(ConexionSQLiteHelper_Sitio conn){
        ArrayList<Sitio> sitios;
        sitios = misSitiosRepository.ConsultarTodosMisSitio(conn);
        return sitios;
    }

    public void eliminarSitio(ConexionSQLiteHelper_Sitio conn,String codigo){
        misSitiosRepository.EliminarUnMiSitio(conn, codigo);
    }

    public boolean validar(String id, ConexionSQLiteHelper_Sitio conn){
        ArrayList<Sitio> sitioList;
        sitioList = consultarTodas(conn);
        for (Sitio item: sitioList) {
            if(item.getCodigo() == Integer.parseInt(id)){
                return true;
            }
        }
        return false;
    }
}
