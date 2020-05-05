package com.example.cesartour;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

public class PopupValledupar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_valledupar);
        DisplayMetrics medididaVentana = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(medididaVentana);

        int ancho =medididaVentana.widthPixels;
        int alto= medididaVentana.heightPixels;
        getWindow().setLayout((int)(ancho*0.85),(int)(alto* 0.5));



    }
}
