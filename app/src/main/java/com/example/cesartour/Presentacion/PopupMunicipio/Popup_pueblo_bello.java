package com.example.cesartour.Presentacion.PopupMunicipio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.cesartour.R;

public class Popup_pueblo_bello extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_pueblo_bello);
        DisplayMetrics medididaVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medididaVentana);

        int ancho =medididaVentana.widthPixels;
        int alto= medididaVentana.heightPixels;
        getWindow().setLayout((int)(ancho*0.3),(int)(alto* 0.3));
    }
}
