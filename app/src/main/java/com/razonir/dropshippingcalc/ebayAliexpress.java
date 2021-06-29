package com.razonir.dropshippingcalc;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.razonir.dropshippingcalc.Adapters.ViewPagerAdapter;
import com.razonir.dropshippingcalc.Fragment.ebayAliexpressProfit;
import com.razonir.dropshippingcalc.Fragment.ebayAliexpressSellPrice;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.tabs.TabLayout;

public class ebayAliexpress extends AppCompatActivity {

    //back
    private ImageView back;

    //ILS
    private Button convertIls;

    //TabLayout
    TabLayout tab_layout;
    ViewPager view_pager;
    TextView result;

    private AdView mAdView;
    private AdView mAdView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ebay_aliexpress);


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView1 = findViewById(R.id.adView1);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView1.loadAd(adRequest1);




        //back
        back = (ImageView) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivitymain();
            }
        });

        //ILS
        convertIls = (Button) findViewById(R.id.convertILS);
        convertIls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIlsActivity();
            }
        });



        //TabLayout
        view_pager = findViewById(R.id.view_pager);
        setUpViewPager(view_pager);

        tab_layout = findViewById(R.id.tab_layout);
        tab_layout.setupWithViewPager(view_pager);
    }

    private void setUpViewPager(ViewPager view_pager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ebayAliexpressProfit(),"profit");
        adapter.addFragment(new ebayAliexpressSellPrice(),"sellPrice");
        view_pager.setAdapter(adapter);
    }

    public void openActivitymain(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }


    public void openIlsActivity(){
        Intent intent = new Intent(this,ebayAliexpressIls.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed(){
        //super.onBackPressed();
    }

    public void open(View view) {
    }
}