package com.razonir.dropshippingcalc;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.tabs.TabLayout;
import com.razonir.dropshippingcalc.Adapters.ViewPagerAdapter;
import com.razonir.dropshippingcalc.Fragment.ebayAmazonProfit;
import com.razonir.dropshippingcalc.Fragment.ebayAmazonSellPrice;

public class EbayAmazon extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;
    public final static String TAG = "AdMob";

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
        setContentView(R.layout.activity_ebay_amazon);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView1 = findViewById(R.id.adView1);
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView1.loadAd(adRequest1);

        InterstitialAd.load(this,"ca-app-pub-6247824013289332/4458841686", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                Log.i(TAG, "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.i(TAG, loadAdError.getMessage());
                mInterstitialAd = null;
            }
        });

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
                if(mInterstitialAd!=null){
                    mInterstitialAd.show(EbayAmazon.this);
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            openIlsActivity();
                        }
                    });
                }else {
                    openIlsActivity();
                }
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
        adapter.addFragment(new ebayAmazonProfit(),"profit");
        adapter.addFragment(new ebayAmazonSellPrice(),"sellPrice");
        view_pager.setAdapter(adapter);

    }

    public void openActivitymain(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }


    public void openIlsActivity(){
        Intent intent = new Intent(this,ebayAmazonIls.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed(){
        //super.onBackPressed();
    }

    public void open(View view) {
    }
}