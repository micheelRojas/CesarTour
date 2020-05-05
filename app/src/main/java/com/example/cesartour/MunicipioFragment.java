package com.example.cesartour;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MunicipioFragment extends Fragment {

    private ImageView btnvalleduparPopup;
    Dialog dia;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_municipio, container, false);

        btnvalleduparPopup = view.findViewById(R.id.button_valledupar);


        btnvalleduparPopup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                abrir();

              //  startActivity(new Intent(MunicipioFragment.this,PopupValledupar.class));//

            }
        });


        return view;


    }
@SuppressLint("ResourceAsColor")
public  void abrir(){
        dia.setContentView(R.layout.activity_popup_valledupar);
        dia.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));
        dia.show();

}
}
