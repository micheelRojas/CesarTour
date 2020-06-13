package com.example.cesartour.DAL;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;

import com.example.cesartour.Data.BD_CesarTour;
import com.example.cesartour.Entity.Evento;
import com.example.cesartour.Entity.ImagenEvento;
import com.example.cesartour.Entity.ImagenSitio;
import com.example.cesartour.Entity.Sitio;
import com.example.cesartour.R;

import java.util.ArrayList;

public class MisSitiosRepository {
    ArrayList<Sitio> Datos = new ArrayList<>();
    private ArrayList<ImagenSitio> imagenes;

    private ArrayList<Sitio> ConsultarTodosMisSitio(ConexionSQLiteHelper_Sitio conn) {
        imagenes = new ArrayList<>();
        imagenes.add(new ImagenSitio(0, R.drawable.slasierra));
        imagenes.add(new ImagenSitio(1, R.drawable.sitiosonesta));
        imagenes.add(new ImagenSitio(2, R.drawable.sitiokasvel));
        imagenes.add(new ImagenSitio(3, R.drawable.sitioelgordosaul));
        imagenes.add(new ImagenSitio(4, R.drawable.sitioeltucan));
        imagenes.add(new ImagenSitio(5, R.drawable.sitiobrasamanaurera));
        imagenes.add(new ImagenSitio(6, R.drawable.sitiobosconiaimperial));
        imagenes.add(new ImagenSitio(7, R.drawable.sitiojorlin));
        imagenes.add(new ImagenSitio(8, R.drawable.sitiolabrasa));

        SQLiteDatabase db = conn.getReadableDatabase();
       Sitio sitio;
        Cursor cursor = db.rawQuery("SELECT * FROM " + BD_CesarTour.TABLA_SITIO,null);
        while (cursor.moveToNext()){
            sitio= new Sitio();
            sitio.setCodigo(cursor.getInt(0));
            sitio.setNombre(cursor.getString(1));
            sitio.setCategoria(cursor.getString(2));
            sitio.setDireccion(cursor.getString(3));
            sitio.setDescripcion(cursor.getString(4));
            sitio.setMunicipio(cursor.getString(5));
            sitio.setTipoObjeto(cursor.getString(6));
            for (ImagenSitio item: imagenes) {
                if(item.getCodigo() == sitio.getCodigo()){
                    sitio.setImageSitio(item.getImagen());
                }
            }
            Datos.add(sitio);

        }
        db.close();
        return Datos;
    }
    private long registrarMisSitios(ConexionSQLiteHelper_Sitio conn,Sitio sitio) {

        SQLiteDatabase db=conn.getWritableDatabase();
        try {

            ContentValues values=new ContentValues();
            values.put(BD_CesarTour.CAMPO_CODIGO_SITIO ,sitio.getCodigo());
            values.put(BD_CesarTour.CAMPO_NOMBRE_SITIO ,sitio.getNombre().toString());
            values.put(BD_CesarTour.CAMPO_CATEGORIA_SITIO ,sitio.getCategoria().toString());
            values.put(BD_CesarTour.CAMPO_DIRECION_SITIO ,sitio.getDireccion().toString());
            values.put(BD_CesarTour.CAMPO_DESCRIPCION_SITIO ,sitio.getDescripcion().toString());
            values.put(BD_CesarTour.CAMPO_MUNICIPIO_SITIO ,sitio.getMunicipio().toString());
            values.put(BD_CesarTour.CAMPO_TIPO_SITIO ,sitio.getTipoObjeto());
            values.put(BD_CesarTour.CAMPO_IMAGESITIO ,sitio.getImageSitio());

            long idResultante = db.insert(BD_CesarTour.TABLA_SITIO,BD_CesarTour.CAMPO_CODIGO_SITIO,values);

            return idResultante;
        }
        finally {
            db.close();

        }
    }
    private void EliminarUnMiSitio(ConexionSQLiteHelper_Sitio conn,String codigo) {
        SQLiteDatabase db = conn.getWritableDatabase();
        String[] parametro ={codigo.toString()};
        db.delete(BD_CesarTour.TABLA_SITIO,BD_CesarTour.CAMPO_CODIGO_SITIO+"=?",parametro);
        db.close();
    }
    private  Sitio  ConsultarUnMiSitio(ConexionSQLiteHelper_Sitio conn,String codigo){
        Sitio sitio= null;
        SQLiteDatabase db = conn.getReadableDatabase();
        String[] parametro ={codigo.toString()};
        String [] campos = {BD_CesarTour.CAMPO_NOMBRE_SITIO,BD_CesarTour.CAMPO_CATEGORIA_SITIO,BD_CesarTour.CAMPO_DIRECION_SITIO,
                BD_CesarTour.CAMPO_DESCRIPCION_SITIO,BD_CesarTour.CAMPO_MUNICIPIO_SITIO,BD_CesarTour.CAMPO_IMAGESITIO};
        try {
            Cursor cursor = db.query(BD_CesarTour.TABLA_SITIO,campos,BD_CesarTour.CAMPO_CODIGO_SITIO+"=?",parametro,null,null,null);
            cursor.moveToFirst();
            sitio = new Sitio();
            sitio.setNombre(cursor.getString(0));
            sitio.setCategoria(cursor.getString(1));
            sitio.setDireccion(cursor.getString(2));
            sitio.setDescripcion(cursor.getString(3));
            sitio.setMunicipio(cursor.getString(4));
            sitio.setImageSitio(cursor.getInt(5));

            cursor.close();
        } catch (Exception e){

        }finally {
            db.close();
        }
        return  sitio;
    }
}
