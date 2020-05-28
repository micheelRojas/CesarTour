package com.example.cesartour.Presentacion;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cesartour.Presentacion.PopupMunicipio.PopupValledupar;
import com.example.cesartour.R;
import com.example.cesartour.Presentacion.interfaces.iComunicaFragments;


public class MunicipioFragment extends Fragment {

    private ImageButton btnvalleduparPopup;
    private ImageButton btnmanaurePopup;
    private ImageButton btnpueblo_belloPopup;
    iComunicaFragments interfaceComunicaFraments;
    Activity activity;
    View view;
    View view2;
    private Context context;
   PopupValledupar popupValledupar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

      view  =inflater.inflate(R.layout.fragment_municipio, container, false);


        btnvalleduparPopup = view.findViewById(R.id.button_valledupar);
        btnpueblo_belloPopup = view.findViewById(R.id.button_pueblo_bello);
        btnmanaurePopup = view.findViewById(R.id.button_manaure);
        Municipios();
        return view;


    }
    public  void Municipios(){
        btnvalleduparPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                interfaceComunicaFraments.popup_valledupar(v);

            }
        });
       btnmanaurePopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                interfaceComunicaFraments.popup_manaure(v);

            }
        });
       btnpueblo_belloPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                interfaceComunicaFraments.popup_pueblo_bello(v);

            }
        });

    }

    /**
     * Called when a fragment is first attached to its context.
     * {@link #onCreate(Bundle)} will be called after this.
     *
     * @param context
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
           activity= (Activity) context;
           interfaceComunicaFraments= (iComunicaFragments) activity;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
