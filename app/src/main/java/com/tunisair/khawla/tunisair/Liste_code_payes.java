package com.tunisair.khawla.tunisair;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Liste_code_payes extends ArrayAdapter<String> {

    Context context;
    String[] codes;
    int[] imge;
    LayoutInflater inflater;
    View row;

    public Liste_code_payes( Context context, String[] codes, int[] imge) {
        super(context, R.layout.liste_codes_pays,codes);
        this.context = context;
        this.codes = codes;
        this.imge = imge;
    }

    @Override
    public View getDropDownView(int position,  View convertView, ViewGroup parent) {

        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row=inflater.inflate(R.layout.liste_codes_pays,null);
        TextView code = (TextView) row.findViewById(R.id.code_payes);
        ImageView img = (ImageView) row.findViewById(R.id.img_code);

        code.setText(codes[position]);
        img.setImageResource(imge[position]);
        return row;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {

        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        row=inflater.inflate(R.layout.liste_codes_pays,null);
        TextView code = (TextView) row.findViewById(R.id.code_payes);
        ImageView imgv = (ImageView) row.findViewById(R.id.img_code);

        code.setText(codes[position]);
        imgv.setImageResource(imge[position]);

        return row;
    }


}
