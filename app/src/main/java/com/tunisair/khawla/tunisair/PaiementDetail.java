package com.tunisair.khawla.tunisair;

import android.content.Intent;
import android.media.MediaRecorder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class PaiementDetail extends AppCompatActivity {

    TextView txtId,txtStatu,txtMontant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paiement_detail);

        txtId = (TextView) findViewById(R.id.txtId);
        txtStatu = (TextView) findViewById(R.id.txtstatut);
        txtMontant = (TextView) findViewById(R.id.txtmontant);

        Intent intent=getIntent();

        try {
            JSONObject jsonObject=new JSONObject(intent.getStringExtra("PaiementDetail"));
            showDetail(jsonObject.getJSONObject("response"),intent.getStringExtra("PaymentMontant"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showDetail(JSONObject response, String paymentMontant) {
        try {
            txtId.setText(response.getString("id"));
            txtStatu.setText(response.getString("state"));
            txtMontant.setText("$"+paymentMontant);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
