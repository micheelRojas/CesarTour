package com.example.cesartour.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cesartour.Entity.Actividad;
import com.example.cesartour.R;

public class Detalles extends AppCompatActivity {
    private TextView mDescription;
    private TextView mName;
    private TextView mCategory;
    private ImageView imagen;

    private String id, nombre, categoria, descripcion, municipio, tipoObjeto, fecha, direccion;

    Button registrarMis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        mCategory = (TextView) findViewById(R.id.textView_Category2);
        mName = (TextView) findViewById(R.id.textView_Name);
        mDescription = (TextView) findViewById(R.id.textView_Description);
        mDescription.setMovementMethod(new ScrollingMovementMethod());
        imagen = (ImageView) findViewById(R.id.imageView_foto);

        if(getIntent().getStringExtra("id") != null){
            mCategory.setText(getIntent().getStringExtra("categoria"));
            mName.setText(getIntent().getStringExtra("nombre"));
            mDescription.setText(getIntent().getStringExtra("descripcion"));
            imagen.setImageResource(Integer.parseInt(getIntent().getStringExtra("imagen")));

        }

        registrarMis = (Button) findViewById(R.id.registrarMis);
        registrarMis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarRecordatorio();
            }
        });

    }

    private void guardarRecordatorio(){
        if(getIntent().getStringExtra("id") != null){
            tipoObjeto = getIntent().getStringExtra("tipoObjeto");
            if (tipoObjeto.equals("Actividad")) {
                MisActividadesFragment misActividadesFragment = new MisActividadesFragment();
                boolean respuesta = misActividadesFragment.validar(getIntent().getStringExtra("id"));
                if (respuesta == true) {
                    Toast.makeText(getApplicationContext(), "Esta actividad ya está guardada", Toast.LENGTH_SHORT).show();
                } else {
                    Actividad actividad = new Actividad();
                    actividad.setCodigo(Integer.parseInt(getIntent().getStringExtra("id")));
                    actividad.setNombre(getIntent().getStringExtra("nombre"));
                    actividad.setCategoria(getIntent().getStringExtra("categoria"));
                    actividad.setDescripcion(getIntent().getStringExtra("descripcion"));
                    actividad.setMunicipio(getIntent().getStringExtra("municipio"));
                    actividad.setTipoObjeto(getIntent().getStringExtra("tipoObjeto"));

                    long idResultante = misActividadesFragment.registrarActividad(actividad);
                    if (idResultante != -1) {
                        Toast.makeText(getApplicationContext(), "Registro correcto", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "No se pudo registrar", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}
