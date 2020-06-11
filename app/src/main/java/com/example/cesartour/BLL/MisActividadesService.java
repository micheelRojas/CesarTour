package com.example.cesartour.BLL;

import com.example.cesartour.DAL.ConexionSQLiteHelper_Actividad;
import com.example.cesartour.DAL.MisActividadesRepository;
import com.example.cesartour.Entity.Actividad;

import java.util.ArrayList;

public class MisActividadesService {
    MisActividadesRepository misActividadesRepository = new MisActividadesRepository();


    public long registrarMisActividades(ConexionSQLiteHelper_Actividad conn, Actividad actividad){

        long idResultante = misActividadesRepository.registrarMisActividades(conn, actividad);
        return idResultante;
    }

    public ArrayList<Actividad> consultarTodas(ConexionSQLiteHelper_Actividad conn){
        ArrayList<Actividad> actividades;
        actividades = misActividadesRepository.ConsultarTodosMisActividades(conn);
        return actividades;
    }

    public void eliminarActividad(ConexionSQLiteHelper_Actividad conn,String codigo){
        misActividadesRepository.EliminarUnMiActividad(conn, codigo);
    }
}
