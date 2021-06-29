package com.razonir.dropshippingcalc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class premium extends AppCompatActivity {

    private ImageView back;
    private RelativeLayout aleart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_premium);

        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitymain();
            }
        });

        aleart = (RelativeLayout) findViewById(R.id.aleart);
        aleart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mAlertBuilder = new AlertDialog.Builder(premium.this);
                mAlertBuilder.setTitle("Wait a little longer");
                mAlertBuilder.setMessage("I understand you can not wait.\n" +
                        "But wait a little longer for your premium account");
                mAlertBuilder.show();
            }
        });

    }
    public void openActivitymain(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }


    public void bPremium(View view) {
    }
}