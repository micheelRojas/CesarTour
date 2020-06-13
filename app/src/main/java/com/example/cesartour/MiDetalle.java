package com.example.cesartour;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cesartour.Presentacion.MainActivity;
import com.example.cesartour.Presentacion.MisActividadesFragment;
import com.example.cesartour.Presentacion.MisEventosFragment;
import com.example.cesartour.Presentacion.MisSitiosFragment;

public class MiDetalle extends AppCompatActivity {
    private TextView mDescription;
    private TextView mName;
    private TextView mCategory;
    private ImageView imagen;

    //private String id, nombre, categoria, descripcion, municipio, tipoObjeto, fecha, direccion;

    Button eliminarMis;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_detalle);

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

        eliminarMis = (Button) findViewById(R.id.eliminarMis);
        eliminarMis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarRecordatorio();
            }
        });
    }

    private void eliminarRecordatorio(){
        if(getIntent().getStringExtra("id") != null){

            String tipoObjeto = getIntent().getStringExtra("tipoObjeto");
            if(tipoObjeto.equals("Actividad")){
                MisActividadesFragment misActividadesFragment = new MisActividadesFragment();
                misActividadesFragment.eliminarActividad(getIntent().getStringExtra("id"));
                Toast.makeText(getApplicationContext(),"Actividad eliminada",Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }else{
                if(tipoObjeto.equals("Evento")){
                    MisEventosFragment misEventosFragment = new MisEventosFragment();
                    misEventosFragment.eliminarEvento(getIntent().getStringExtra("id"));
                    Toast.makeText(getApplicationContext(),"Evento eliminado",Toast.LENGTH_SHORT).show();
                    finish();
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }else{
                    if(tipoObjeto.equals("Sitio")){
                        MisSitiosFragment misSitiosFragment = new MisSitiosFragment();
                        misSitiosFragment.eliminarSitio(getIntent().getStringExtra("id"));
                        Toast.makeText(getApplicationContext(),"Sitio eliminado",Toast.LENGTH_SHORT).show();
                        finish();
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    }
                }
            }



        }
    }
}
