package com.tunisair.khawla.tunisair;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

public class Timer_pop extends DialogFragment implements View.OnClickListener {

    View view;
    Button ok, anuller;
    TimePicker time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.pop_timer, container, false);
        ok = (Button) view.findViewById(R.id.bt_ok);
        anuller = (Button) view.findViewById(R.id.bt_anuler);
        time = (TimePicker) view.findViewById(R.id.timepic);
        ok.setOnClickListener(this);
        anuller.setOnClickListener(this);
        return view;
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {

        Button button = (Button) view;

        if (button.getText().toString().equals("Anuler")) {
            this.dismiss();
        } else {
            String times=time.getHour()+":"+time.getMinute();

            BilletActivity inscrr = (BilletActivity) getActivity();
            if(inscrr.t==1){
                inscrr.settime(times);
            }else{
                inscrr.settime2(times);

            }
            this.dismiss();
        }
    }
}
