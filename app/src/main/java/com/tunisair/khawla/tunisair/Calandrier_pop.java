package com.tunisair.khawla.tunisair;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

import static android.content.ContentValues.TAG;

/**
 * Created by ASUS on 03/02/2018.
 */

public class Calandrier_pop extends DialogFragment implements View.OnClickListener {

    View view;
    Button ok, anuller;
    DatePicker naisence;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.pop_calendrier, container, false);
        ok = (Button) view.findViewById(R.id.bt_ok);
        anuller = (Button) view.findViewById(R.id.bt_anuler);
        naisence = (DatePicker) view.findViewById(R.id.datePicker);
        ok.setOnClickListener(this);
        anuller.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        String date;
        Button button = (Button) view;
        if (button.getText().toString().equals("Anuler")) {
            this.dismiss();
        } else {
            int x = naisence.getYear();
            int mois = naisence.getMonth()+1;
            int day = naisence.getDayOfMonth();

            date = day + "/" + mois + "/" + x;
            if (InscriptionActivity.p ==1) {
                InscriptionActivity inscr = (InscriptionActivity) getActivity();
                inscr.setdate(date);
            }else if (InscriptionActivity.p== 2) {
                Inscription2Activity inscrr = (Inscription2Activity) getActivity();
                inscrr.setdate2(date);
            }else if (InscriptionActivity.p== 3) {
                BilletActivity inscrr = (BilletActivity) getActivity();
                inscrr.setdate(date);
            }else if (InscriptionActivity.p== 4) {
                BilletActivity inscrr = (BilletActivity) getActivity();
                inscrr.setdate2(date);
            }else if (InscriptionActivity.p== 5) {
                ReclamationActivity inscrr = (ReclamationActivity) getActivity();
                inscrr.setdate(date);
            }else if (InscriptionActivity.p== 6) {
                ReclamationActivity inscrr = (ReclamationActivity) getActivity();
                inscrr.setdate2(date);
            }
                this.dismiss();
        }
    }
}
