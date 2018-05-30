package com.tunisair.khawla.tunisair.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.tunisair.khawla.tunisair.AboutActivity;
import com.tunisair.khawla.tunisair.AchatBilletActivity;
import com.tunisair.khawla.tunisair.AdhessionActivity;
import com.tunisair.khawla.tunisair.BilletActivity;
import com.tunisair.khawla.tunisair.ConsultationActivity;
import com.tunisair.khawla.tunisair.Inscription2Activity;
import com.tunisair.khawla.tunisair.InscriptionActivity;
import com.tunisair.khawla.tunisair.LoginActivity;
import com.tunisair.khawla.tunisair.MapsActivity;
import com.tunisair.khawla.tunisair.MilesActivity;
import com.tunisair.khawla.tunisair.ProfilActivity;
import com.tunisair.khawla.tunisair.ReclamationActivity;
import com.tunisair.khawla.tunisair.ReponseActivity;

import static com.tunisair.khawla.tunisair.LoginActivity.dialog;

public class NetworkStateChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            if (isOnline(context)) {

                switch (LoginActivity.NB_Activity) {
                    case 0:
                        LoginActivity.dialog(true, context);
                        break;
                    case 1:
                        InscriptionActivity.dialog(true, context);
                        break;
                    case 2:
                        Inscription2Activity.dialog(true, context);
                        break;
                    case 3:
                        AdhessionActivity.dialog(true, context);
                        break;
                    case 4:
                        ProfilActivity.dialog(true, context);
                        break;
                    case 5:
                        BilletActivity.dialog(true, context);
                        break;
                    case 6:
                        AchatBilletActivity.dialog(true, context);
                        break;
                    case 7:
                        MilesActivity.dialog(true, context);
                        break;
                    case 8:
                        ReclamationActivity.dialog(true, context);
                        break;
                    case 9:
                        ConsultationActivity.dialog(true, context);
                        break;
                    case 10:
                        ReponseActivity.dialog(true, context);
                        break;
                    case 11:
                        AboutActivity.dialog(true, context);
                        break;
                    case 12:
                        MapsActivity.dialog(true, context);
                        break;
                }
            } else {
                switch (LoginActivity.NB_Activity) {
                    case 0:
                        LoginActivity.dialog(false, context);
                        break;
                    case 1:
                        InscriptionActivity.dialog(false, context);
                        break;
                    case 2:
                        Inscription2Activity.dialog(false, context);
                        break;
                    case 3:
                        AdhessionActivity.dialog(false, context);
                        break;
                    case 4:
                        ProfilActivity.dialog(false, context);
                        break;
                    case 5:
                        BilletActivity.dialog(false, context);
                        break;
                    case 6:
                        AchatBilletActivity.dialog(false, context);
                        break;
                    case 7:
                        MilesActivity.dialog(false, context);
                        break;
                    case 8:
                        ReclamationActivity.dialog(false, context);
                        break;
                    case 9:
                        ConsultationActivity.dialog(false, context);
                        break;
                    case 10:
                        ReponseActivity.dialog(false, context);
                        break;
                    case 11:
                        AboutActivity.dialog(false, context);
                        break;
                    case 12:
                        MapsActivity.dialog(false, context);
                        break;
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }
}
