package com.example.cesartour.DAL;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cesartour.Data.BD_CesarTour;
import com.example.cesartour.Entity.Actividad;
import com.example.cesartour.Entity.Evento;
import com.example.cesartour.Entity.ImagenActividad;
import com.example.cesartour.R;

import java.util.ArrayList;

public class MisActividadesRepository {
    ArrayList<Actividad> Datos = new ArrayList<>();
    private ArrayList<ImagenActividad> imagenes;

    public ArrayList<Actividad> ConsultarTodosMisActividades(ConexionSQLiteHelper_Actividad conn) {
        imagenes = new ArrayList<>();
        imagenes.add(new ImagenActividad(0, R.drawable.actrioguatapuri));
        imagenes.add(new ImagenActividad(1, R.drawable.actparapentemanaure));
        imagenes.add(new ImagenActividad(2, R.drawable.actcaminatamanaure));
        imagenes.add(new ImagenActividad(3, R.drawable.actcaminatavalledupar));
        imagenes.add(new ImagenActividad(4, R.drawable.actcaminatamanaureladanta));
        imagenes.add(new ImagenActividad(5, R.drawable.actcomervalledupar));
        imagenes.add(new ImagenActividad(6, R.drawable.actcomeralmojabanas));
        imagenes.add(new ImagenActividad(7, R.drawable.actavistamientodeaves));
        imagenes.add(new ImagenActividad(8, R.drawable.actnavegarzapatosa));
        imagenes.add(new ImagenActividad(9, R.drawable.actbalneariolamina));
        imagenes.add(new ImagenActividad(10, R.drawable.actriobadillo));

        SQLiteDatabase db = conn.getReadableDatabase();
        Actividad actividad;
        Cursor cursor = db.rawQuery("SELECT * FROM " + BD_CesarTour.TABLA_ACTIVIDAD,null);
        while (cursor.moveToNext()){
            actividad= new Actividad();
            actividad.setCodigo(cursor.getInt(0));
            actividad.setNombre(cursor.getString(1));
            actividad.setCategoria(cursor.getString(2));
            actividad.setDescripcion(cursor.getString(3));
            actividad.setMunicipio(cursor.getString(4));
            actividad.setTipoObjeto(cursor.getString(5));
            for (ImagenActividad item: imagenes) {
                if(item.getCodigo() == actividad.getCodigo()){
                    actividad.setImageActividad(item.getImagen());
                }
            }
            Datos.add(actividad);

        }
        db.close();
        return Datos;
    }

    public long registrarMisActividades(ConexionSQLiteHelper_Actividad conn,Actividad actividad) {

        SQLiteDatabase db=conn.getWritableDatabase();
        try {

            ContentValues values=new ContentValues();
            values.put(BD_CesarTour.CAMPO_CODIGO_ACTIVIDAD ,actividad.getCodigo());
            values.put(BD_CesarTour.CAMPO_NOMBRE_ACTIVIDAD ,actividad.getNombre().toString());
            values.put(BD_CesarTour.CAMPO_CATEGORIA_ACTIVIDAD ,actividad.getCategoria().toString());
            values.put(BD_CesarTour.CAMPO_DESCRIPCION_ACTIVIDAD ,actividad.getDescripcion().toString());
            values.put(BD_CesarTour.CAMPO_MUNICIPIO_ACTIVIDAD ,actividad.getMunicipio().toString());
            values.put(BD_CesarTour.CAMPO_TIPO_ACTIVIDAD ,actividad.getTipoObjeto());
            values.put(BD_CesarTour.CAMPO_IMAGEACTIVIDA ,actividad.getImageActividad());
            long idResultante = db.insert(BD_CesarTour.TABLA_ACTIVIDAD,BD_CesarTour.CAMPO_CODIGO_ACTIVIDAD,values);

            return idResultante;
        }
        finally {
            db.close();

        }
    }

    public void EliminarUnMiActividad(ConexionSQLiteHelper_Actividad conn,String codigo) {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametro ={codigo.toString()};
        db.delete(BD_CesarTour.TABLA_ACTIVIDAD,BD_CesarTour.CAMPO_CODIGO_ACTIVIDAD+"=?",parametro);
        db.close();
    }
    private  Actividad  ConsultarUnMiActividad(ConexionSQLiteHelper_Actividad conn,String codigo){
       Actividad actividad = null;
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametro ={codigo.toString()};
        String [] campos = {BD_CesarTour.CAMPO_NOMBRE_ACTIVIDAD,BD_CesarTour.CAMPO_CATEGORIA_ACTIVIDAD,BD_CesarTour.CAMPO_DESCRIPCION_ACTIVIDAD,
                BD_CesarTour.CAMPO_MUNICIPIO_ACTIVIDAD,BD_CesarTour.CAMPO_IMAGEACTIVIDA};
        try {
            Cursor cursor = db.query(BD_CesarTour.TABLA_ACTIVIDAD,campos,BD_CesarTour.CAMPO_CODIGO_ACTIVIDAD+"=?",parametro,null,null,null);
            cursor.moveToFirst();
            actividad = new Actividad();
            actividad.setNombre(cursor.getString(0));
            actividad.setCategoria(cursor.getString(1));
            actividad.setDescripcion(cursor.getString(2));
            actividad.setMunicipio(cursor.getString(3));
            actividad.setImageActividad(cursor.getInt(4));

            cursor.close();
        } catch (Exception e){

        }finally {
            db.close();
        }
        return  actividad;
    }
}
