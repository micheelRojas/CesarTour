package com.example.cesartour.DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.cesartour.Data.BD_CesarTour;

public class ConexionSQLiteHelper_Actividad extends SQLiteOpenHelper {
    public ConexionSQLiteHelper_Actividad(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BD_CesarTour.CREAR_TABLA_ACTIVIDAD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS actividad");
        onCreate(db);
    }
}
