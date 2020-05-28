package com.example.cesartour.Presentacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.cesartour.R;

public class Detalles extends AppCompatActivity {
    private TextView mDescription;
    private TextView mName;
    private TextView mCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        mCategory = (TextView) findViewById(R.id.textView_Category2);
        mName = (TextView) findViewById(R.id.textView_Name);
        mDescription = (TextView) findViewById(R.id.textView_Description);

        if(getIntent().getStringExtra("id") != null){
            mCategory.setText(getIntent().getStringExtra("categoria"));
            mName.setText(getIntent().getStringExtra("nombre"));
            mDescription.setText(getIntent().getStringExtra("descripcion"));
        }

    }
}
