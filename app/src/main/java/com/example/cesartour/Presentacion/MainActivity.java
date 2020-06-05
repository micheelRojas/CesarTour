package com.example.cesartour.Presentacion;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.example.cesartour.DAL.ConexionSQLiteHelper_Actividad;
import com.example.cesartour.DAL.ConexionSQLiteHelper_Evento;
import com.example.cesartour.DAL.ConexionSQLiteHelper_Sitio;
import com.example.cesartour.R;
import com.example.cesartour.Presentacion.interfaces.iComunicaFragments;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements iComunicaFragments {


    private AppBarConfiguration mAppBarConfiguration;
    Dialog dialog;
    //EventosFragment popupValledupar;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
// lo de la base de datos
ConexionSQLiteHelper_Sitio conexionSitio;
ConexionSQLiteHelper_Actividad conexionActividad;
ConexionSQLiteHelper_Evento conexionEvento;
//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_cultura,R.id.nav_eventos,R.id.nav_actividades,
                R.id.nav_sitio,R.id.nav_municipio,R.id.nav_mis_eventos,R.id.nav_mis_actividades,
                R.id.nav_mis_sitios
        )
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

         dialog = new Dialog(this);

//aqui instancio tas conexiones de la base de datos, recuerda que cuando la vallas a utilizar, simpte debe tener eso parametros
        //sobre todo el nombre
        conexionEvento =new ConexionSQLiteHelper_Evento(this, "db_eventos",null,1);
        conexionActividad = new ConexionSQLiteHelper_Actividad(this,"db_actividades",null,1);
        conexionSitio = new ConexionSQLiteHelper_Sitio(this,"db_sitios",null,1);
    //
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


   @Override
    public void popup_valledupar(View view) {
      dialog.setContentView(R.layout.activity_popup_valledupar);
      dialog.show();
    }

    @Override
    public void popup_manaure(View view) {
        dialog.setContentView(R.layout.activity_popup_manaure);
        dialog.show();
    }

    @Override
    public void popup_pueblo_bello(View view) {
        dialog.setContentView(R.layout.activity_popup_pueblo_bello);
        dialog.show();
    }


    public void filter(View view) {
    }
}
