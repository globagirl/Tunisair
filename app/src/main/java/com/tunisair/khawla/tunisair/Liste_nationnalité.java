package com.tunisair.khawla.tunisair;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Liste_nationnalité extends ArrayAdapter<String>  {

        Context context;
        String[] natio;
        LayoutInflater inflater;
        View row;

    public Liste_nationnalité( Context context, String[] natio) {
        super(context, R.layout.liste_nationnalite,natio);
        this.context = context;
        this.natio = natio;
    }
    @Override
        public View getDropDownView(int position,  View convertView, ViewGroup parent) {

            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.liste_nationnalite,null);
            TextView code = (TextView) row.findViewById(R.id.code_payes);


            code.setText(natio[position]);
            return row;
        }

        @Override
        public View getView(int position, View convertView,ViewGroup parent) {

            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.liste_nationnalite,null);
            TextView code = (TextView) row.findViewById(R.id.code_payes);

            code.setText(natio[position]);

            return row;
        }


    }


