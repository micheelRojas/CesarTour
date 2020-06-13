package com.example.cesartour.BLL;

import com.example.cesartour.DAL.ConexionSQLiteHelper_Actividad;
import com.example.cesartour.DAL.ConexionSQLiteHelper_Evento;
import com.example.cesartour.DAL.MisActividadesRepository;
import com.example.cesartour.DAL.MisEventosRepository;
import com.example.cesartour.Entity.Actividad;
import com.example.cesartour.Entity.Evento;

import java.util.ArrayList;

public class MisEventosService {
    MisEventosRepository misEventosRepository = new MisEventosRepository();


    public long registrarMisEventos(ConexionSQLiteHelper_Evento conn, Evento evento){

        long idResultante = misEventosRepository.registrarMisEventos(conn, evento);
        return idResultante;
    }

    public ArrayList<Evento> consultarTodas(ConexionSQLiteHelper_Evento conn){
        ArrayList<Evento> eventos;
        eventos = misEventosRepository.ConsultarTodosMisEventos(conn);
        return eventos;
    }

    public void eliminarEvento(ConexionSQLiteHelper_Evento conn,String codigo){
        misEventosRepository.EliminarUnMiEvento(conn, codigo);
    }

    public boolean validar(String id, ConexionSQLiteHelper_Evento conn){
        ArrayList<Evento> eventoList;
        eventoList = consultarTodas(conn);
        for (Evento item: eventoList) {
            if(item.getCodigo() == Integer.parseInt(id)){
                return true;
            }
        }
        return false;
    }
}
