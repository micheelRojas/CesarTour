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
import com.example.cesartour.Entity.Evento;
import com.example.cesartour.Entity.Sitio;
import com.example.cesartour.R;

public class Detalles extends AppCompatActivity {
    private TextView mDescription;
    private TextView mName;
    private TextView mCategory;
    private ImageView imagen;

    //private String id, nombre, categoria, descripcion, municipio, tipoObjeto, fecha, direccion;

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
            String tipoObjeto = getIntent().getStringExtra("tipoObjeto");
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
            }else{
                if (tipoObjeto.equals("Evento")) {
                    MisEventosFragment misEventosFragment = new MisEventosFragment();
                    boolean respuesta = misEventosFragment.validar(getIntent().getStringExtra("id"));
                    if (respuesta == true) {
                        Toast.makeText(getApplicationContext(), "Este evento ya está guardado", Toast.LENGTH_SHORT).show();
                    } else {
                        Evento evento = new Evento();
                        evento.setCodigo(Integer.parseInt(getIntent().getStringExtra("id")));
                        evento.setNombre(getIntent().getStringExtra("nombre"));
                        evento.setFecha(getIntent().getStringExtra("fecha"));
                        evento.setDescripcion(getIntent().getStringExtra("descripcion"));
                        evento.setMunicipio(getIntent().getStringExtra("municipio"));
                        evento.setTipoObjeto(getIntent().getStringExtra("tipoObjeto"));

                        long idResultante = misEventosFragment.registrarEvento(evento);
                        if (idResultante != -1) {
                            Toast.makeText(getApplicationContext(), "Registro correcto", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "No se pudo registrar", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    if (tipoObjeto.equals("Sitio")) {
                        MisSitiosFragment misSitiosFragment = new MisSitiosFragment();
                        boolean respuesta = misSitiosFragment.validar(getIntent().getStringExtra("id"));
                        if (respuesta == true) {
                            Toast.makeText(getApplicationContext(), "Este sitio ya está guardado", Toast.LENGTH_SHORT).show();
                        } else {
                            Sitio sitio = new Sitio();
                            sitio.setCodigo(Integer.parseInt(getIntent().getStringExtra("id")));
                            sitio.setNombre(getIntent().getStringExtra("nombre"));
                            sitio.setCategoria(getIntent().getStringExtra("categoria"));
                            sitio.setDescripcion(getIntent().getStringExtra("descripcion"));
                            sitio.setDireccion(getIntent().getStringExtra("direccion"));
                            sitio.setMunicipio(getIntent().getStringExtra("municipio"));
                            sitio.setTipoObjeto(getIntent().getStringExtra("tipoObjeto"));

                            long idResultante = misSitiosFragment.registrarSitio(sitio);
                            if (idResultante != -1) {
                                Toast.makeText(getApplicationContext(), "Registro correcto", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "No se pudo registrar", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }else{
                        registrarMis.setVisibility(View.INVISIBLE);
                    }
                }
            }
        }
    }
}
