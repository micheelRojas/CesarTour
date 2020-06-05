package com.example.cesartour.DAL;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cesartour.Data.BD_CesarTour;
import com.example.cesartour.Entity.Evento;
import com.example.cesartour.Entity.Sitio;

import java.util.ArrayList;

public class MisEventosRepository {

    private ArrayList<Evento> ConsultarTodosMisEventos(ConexionSQLiteHelper_Evento conn) {
        ArrayList<Evento> Datos = null;
        SQLiteDatabase db = conn.getReadableDatabase();
        Evento evento= null;
        Cursor cursor = db.rawQuery("SELECT * FROM " + BD_CesarTour.TABLA_EVENTO,null);
        while (cursor.moveToNext()){
            evento= new Evento();
            evento.setCodigo(cursor.getInt(0));
            evento.setNombre(cursor.getString(1));
            evento.setMunicipio(cursor.getString(2));
            evento.setImageEvento(cursor.getInt(5));
            Datos.add(evento);

        }
        db.close();
        return Datos;
    }
    private long registrarMisEventos(ConexionSQLiteHelper_Evento conn,Evento evento) {

        SQLiteDatabase db=conn.getWritableDatabase();
        try {

            ContentValues values=new ContentValues();
            values.put(BD_CesarTour.CAMPO_CODIGO_EVENTO ,evento.getCodigo());
            values.put(BD_CesarTour.CAMPO_NOMBRE_EVENTO ,evento.getNombre().toString());
            values.put(BD_CesarTour.CAMPO_MUNICIPIO_EVENTO ,evento.getMunicipio().toString());
            values.put(BD_CesarTour.CAMPO_FECHA_EVENTO ,evento.getFecha().toString());
            values.put(BD_CesarTour.CAMPO_DESCRIPCION_EVENTO ,evento.getDescripcion().toString());
            values.put(BD_CesarTour.CAMPO_IMAGEEVENTO ,evento.getImageEvento());

            long idResultante = db.insert(BD_CesarTour.TABLA_EVENTO,BD_CesarTour.CAMPO_CODIGO_EVENTO,values);

            return idResultante;
        }
        finally {
            db.close();

        }
    }
    private void EliminarUnMiEvento(ConexionSQLiteHelper_Evento conn,String codigo) {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametro ={codigo.toString()};
        db.delete(BD_CesarTour.TABLA_EVENTO,BD_CesarTour.CAMPO_CODIGO_EVENTO+"=?",parametro);
        db.close();
    }
    private  Evento  ConsultarUnMiEvento(ConexionSQLiteHelper_Evento conn,String codigo){
        Evento evento = null;
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametro ={codigo.toString()};
        String [] campos = {BD_CesarTour.CAMPO_NOMBRE_EVENTO,BD_CesarTour.CAMPO_MUNICIPIO_EVENTO,BD_CesarTour.CAMPO_FECHA_EVENTO,
                BD_CesarTour.CAMPO_DESCRIPCION_EVENTO,BD_CesarTour.CAMPO_IMAGEEVENTO};
        try {
            Cursor cursor = db.query(BD_CesarTour.TABLA_EVENTO,campos,BD_CesarTour.CAMPO_CODIGO_EVENTO+"=?",parametro,null,null,null);
            cursor.moveToFirst();
            evento = new Evento();
            evento.setNombre(cursor.getString(0));
            evento.setMunicipio(cursor.getString(1));
            evento.setFecha(cursor.getString(2));
            evento.setDescripcion(cursor.getString(3));
            evento.setImageEvento(cursor.getInt(4));

            cursor.close();
        } catch (Exception e){

        }finally {
            db.close();
        }
        return  evento;
    }
}
