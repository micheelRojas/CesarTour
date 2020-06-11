package com.example.cesartour.Data;

public class BD_CesarTour {
    //POR EL MOMENTO LAS IMAGENENES LAS VOY A DEJAR COMO EN LA CLASE DE LA ENTITY LUEGO SE BUSCA
    //COMO SE HACE PARA GUARGAR O COMO SE HACE PARA CONSULTAR, SUPONGO QUE SERA IGUAL QUE EN EL RECYLCER
    //constantes de la tabla actividad
    public static String TABLA_ACTIVIDAD = "actividad";
    public static String CAMPO_CODIGO_ACTIVIDAD = "codigo";
    public static String CAMPO_NOMBRE_ACTIVIDAD = "nombre";
    public static String CAMPO_CATEGORIA_ACTIVIDAD = "categoria";
    public static String CAMPO_DESCRIPCION_ACTIVIDAD = "descripcion";
    public static String CAMPO_MUNICIPIO_ACTIVIDAD= "municipio";
    public static String CAMPO_TIPO_ACTIVIDAD= "tipoObjeto";
    public static String CAMPO_IMAGEACTIVIDA = "imageActividad";
    public static final  String CREAR_TABLA_ACTIVIDAD="CREATE TABLE" +
            " " + TABLA_ACTIVIDAD + " (" +CAMPO_CODIGO_ACTIVIDAD+" "+
            "INTEGER, "+ CAMPO_NOMBRE_ACTIVIDAD+" " +" TEXT," +  CAMPO_CATEGORIA_ACTIVIDAD+" " +" TEXT,"+ CAMPO_DESCRIPCION_ACTIVIDAD+" " +" TEXT,"
            + CAMPO_MUNICIPIO_ACTIVIDAD+" " +" TEXT," + CAMPO_TIPO_ACTIVIDAD+" " +" TEXT,"+CAMPO_IMAGEACTIVIDA+" "+ "INTEGER)";
    //constantes de la tabla evento
    public static String TABLA_EVENTO = "evento";
    public static String CAMPO_CODIGO_EVENTO = "codigo";
    public static String CAMPO_NOMBRE_EVENTO = "nombre";
    public static String CAMPO_MUNICIPIO_EVENTO= "municipio";
    public static String CAMPO_FECHA_EVENTO= "fecha";
    public static String CAMPO_DESCRIPCION_EVENTO= "descripcion";
    public static String CAMPO_IMAGEEVENTO = "imageEvento";
    public static final  String CREAR_TABLA_EVENTO="CREATE TABLE" +
            " " + TABLA_EVENTO + " (" +CAMPO_CODIGO_EVENTO+" "+
            "INTEGER, "+ CAMPO_NOMBRE_EVENTO+" " +" TEXT," +  CAMPO_MUNICIPIO_EVENTO+" " +" TEXT,"+ CAMPO_FECHA_EVENTO+" " +" TEXT,"
            + CAMPO_DESCRIPCION_EVENTO+" " +" TEXT,"+CAMPO_IMAGEEVENTO+" "+ "INTEGER)";

    //constantes de la tabla sitio
    public static String TABLA_SITIO = "sitio";
    public static String CAMPO_CODIGO_SITIO  = "codigo";
    public static String CAMPO_NOMBRE_SITIO  = "nombre";
    public static String CAMPO_CATEGORIA_SITIO  = "categoria";
    public static String CAMPO_DIRECION_SITIO  = "direccion";
    public static String CAMPO_DESCRIPCION_SITIO= "descripcion";
    public static String CAMPO_MUNICIPIO_SITIO= "municipio";
    public static String CAMPO_IMAGESITIO = "imageSitio";
    public static final  String CREAR_TABLA_SITIO="CREATE TABLE" +
            " " + TABLA_SITIO + " (" +CAMPO_CODIGO_SITIO+" "+
            "INTEGER, "+ CAMPO_NOMBRE_SITIO+" " +" TEXT," +  CAMPO_CATEGORIA_SITIO+" " +" TEXT,"+ CAMPO_DIRECION_SITIO+" " +" TEXT,"
            + CAMPO_DESCRIPCION_SITIO+" " +" TEXT,"+  CAMPO_MUNICIPIO_SITIO+" " +" TEXT,"+CAMPO_IMAGESITIO+" "+ "INTEGER)";


}

