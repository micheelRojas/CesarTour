package com.example.cesartour.DAL;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cesartour.Data.BD_CesarTour;
import com.example.cesartour.Entity.Actividad;
import com.example.cesartour.Entity.Evento;
import com.example.cesartour.Entity.ImagenActividad;
import com.example.cesartour.Entity.ImagenEvento;
import com.example.cesartour.Entity.Sitio;
import com.example.cesartour.R;

import java.util.ArrayList;

public class MisEventosRepository {
    ArrayList<Evento> Datos = new ArrayList<>();
    private ArrayList<ImagenEvento> imagenes;

    public ArrayList<Evento> ConsultarTodosMisEventos(ConexionSQLiteHelper_Evento conn) {
        imagenes = new ArrayList<>();
        imagenes.add(new ImagenEvento(0, R.drawable.festivalvallenato));
        imagenes.add(new ImagenEvento(1, R.drawable.fiestasanjuanbosco));
        imagenes.add(new ImagenEvento(2, R.drawable.fiestavirgendelcarmen));
        imagenes.add(new ImagenEvento(3, R.drawable.festivaldelaquinta));
        imagenes.add(new ImagenEvento(4, R.drawable.vallenatoenvivo));
        imagenes.add(new ImagenEvento(5, R.drawable.fiestadesanroque));
        imagenes.add(new ImagenEvento(6, R.drawable.fiestavirgendelcarmen));
        imagenes.add(new ImagenEvento(7, R.drawable.fiestadechimichagua));
        imagenes.add(new ImagenEvento(8, R.drawable.fiestadesantarita));
        imagenes.add(new ImagenEvento(9, R.drawable.cumplebosconia));


        SQLiteDatabase db = conn.getReadableDatabase();
        Evento evento;
        Cursor cursor = db.rawQuery("SELECT * FROM " + BD_CesarTour.TABLA_EVENTO,null);
        while (cursor.moveToNext()){
            evento= new Evento();
            evento.setCodigo(cursor.getInt(0));
            evento.setNombre(cursor.getString(1));
            evento.setMunicipio(cursor.getString(2));
            evento.setFecha(cursor.getString(3));
            evento.setDescripcion(cursor.getString(4));
            evento.setTipoObjeto(cursor.getString(5));
            for (ImagenEvento item: imagenes) {
                if(item.getCodigo() == evento.getCodigo()){
                    evento.setImageEvento(item.getImagen());
                }
            }
            Datos.add(evento);

        }
        db.close();
        return Datos;
    }

    public long registrarMisEventos(ConexionSQLiteHelper_Evento conn,Evento evento) {

        SQLiteDatabase db=conn.getWritableDatabase();
        try {

            ContentValues values=new ContentValues();
            values.put(BD_CesarTour.CAMPO_CODIGO_EVENTO ,evento.getCodigo());
            values.put(BD_CesarTour.CAMPO_NOMBRE_EVENTO ,evento.getNombre().toString());
            values.put(BD_CesarTour.CAMPO_MUNICIPIO_EVENTO ,evento.getMunicipio().toString());
            values.put(BD_CesarTour.CAMPO_FECHA_EVENTO ,evento.getFecha().toString());
            values.put(BD_CesarTour.CAMPO_DESCRIPCION_EVENTO ,evento.getDescripcion().toString());
            values.put(BD_CesarTour.CAMPO_TIPO_EVENTO ,evento.getTipoObjeto());
            values.put(BD_CesarTour.CAMPO_IMAGEEVENTO ,evento.getImageEvento());

            long idResultante = db.insert(BD_CesarTour.TABLA_EVENTO,BD_CesarTour.CAMPO_CODIGO_EVENTO,values);

            return idResultante;
        }
        finally {
            db.close();

        }
    }

    public void EliminarUnMiEvento(ConexionSQLiteHelper_Evento conn,String codigo) {
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
