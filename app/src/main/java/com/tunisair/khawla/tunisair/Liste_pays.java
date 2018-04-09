package com.tunisair.khawla.tunisair;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

    public class Liste_pays extends ArrayAdapter<String> {
        Context context;
        String[] pay;
        LayoutInflater inflater;
        View row;
        public Liste_pays(Context context, String[] pay) {
            super(context, R.layout.liste_pays,pay);
            this.context = context;
            this.pay = pay;
        }

        @Override
        public View getDropDownView(int position,  View convertView, ViewGroup parent) {

            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.liste_pays,null);
            TextView code = (TextView) row.findViewById(R.id.code_payes);

            code.setText(pay[position]);
            return row;
        }

        @Override
        public View getView(int position, View convertView,ViewGroup parent) {

            inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.liste_codes_pays,null);
            TextView code = (TextView) row.findViewById(R.id.code_payes);

            code.setText(pay[position]);


            return row;
        }

    }
