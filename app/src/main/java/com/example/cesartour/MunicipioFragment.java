package com.example.cesartour;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cesartour.PopupMunicipio.PopupValledupar;
import com.example.cesartour.interfaces.iComunicaFragments;


public class MunicipioFragment extends Fragment {

    private ImageButton btnvalleduparPopup;
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

//     view2 =inflater.inflate(R.layout.activity_popup_valledupar,container,false);

        btnvalleduparPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



             interfaceComunicaFraments.popup_valledupar(v);



            }
        });


        return view;


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
