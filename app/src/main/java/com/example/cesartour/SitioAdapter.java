package com.example.cesartour;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.cesartour.Entity.Sitio;

import java.util.ArrayList;
import java.util.Collections;

public class SitioAdapter extends ArrayAdapter<Sitio> {
    private static final String tag = "SitioAdapter";
    Context contexto;
    ArrayList<String> sitioList;

    public SitioAdapter(Context contexto, int textViewResourceId, ArrayList<String> sitioList){
        super(contexto,textViewResourceId);
        this.contexto = contexto;
        this.sitioList = sitioList;
        Collections.sort(sitioList);
    }

    /*
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null)
        {
            // ROW INFLATION
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.simple_list_item_2, parent, false);
        }

        // Get item
        Buddy buddy = getItem(position);
        buddy.refresh();

        buddyName = (TextView) row.findViewById(R.id.buddy_name);   //change this to textField1  from simple_list_item_2
        buddyName.setText(buddy.toString());

        buddyStatus = (TextView) row.findViewById(R.id.buddy_mood); //change this to textField2 from simple_list_item_2
        buddyStatus.setText(buddy.getMood());
        //      Log.d(tag, buddy.getIdentity()+"'s mood is "+buddyStatus.getText());
        return row;
    } */

    @Override
    public int getCount() {
        return sitioList.size();
    }

    @Override
    public Sitio getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


}
