package com.razonir.dropshippingcalc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.tabs.TabLayout;
import com.razonir.dropshippingcalc.Adapters.ViewPagerAdapter;
import com.razonir.dropshippingcalc.Fragment.comingSoonShop;
import com.razonir.dropshippingcalc.Fragment.eBay;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;

    public final static String TAG = "AdMob";

    Button button;



    TabLayout tab_layout;
    ViewPager view_pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(getWindow().FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        try {
            Document doc = Jsoup.connect("http://www.x-rates.com/table/?from=USD&amount=1").get();
            Elements rows = doc.select("table.ratesTable > tbody > tr");
            for (Element row : rows) {
                Elements tds = row.select("td");

                String currency = tds.get(0).text();
                if(currency.equals("Israeli Shekel")) {
                    double dollarRate = Double.valueOf(tds.get(1).text());
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }

        view_pager = findViewById(R.id.view_pager);
        setUpViewPager(view_pager);

        tab_layout = findViewById(R.id.tab_layout);
        tab_layout.setupWithViewPager(view_pager);

        //Ads Implemnet

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();

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

        RelativeLayout premiumB = (RelativeLayout) findViewById(R.id.premuimout);
        premiumB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mInterstitialAd!=null){
                    mInterstitialAd.show(MainActivity.this);
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            openActivityPremuim();
                        }
                    });
                }else {
                    openActivityPremuim();
                }
            }
        });
        RelativeLayout youtubeB = (RelativeLayout) findViewById(R.id.youtubeout);
        youtubeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mInterstitialAd!=null){
                    mInterstitialAd.show(MainActivity.this);
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            openActivityYoutube();
                        }
                    });
                }else {
                    openActivityYoutube();
                }
            }
        });

        RelativeLayout email = (RelativeLayout) findViewById(R.id.emailB);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mInterstitialAd!=null){
                    mInterstitialAd.show(MainActivity.this);
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            super.onAdDismissedFullScreenContent();
                            openActivityEmail();
                        }
                    });
                }else {
                    openActivityEmail();
                }
            }
        });
    }

    public void openActivityPremuim() {
        startActivity(new Intent(MainActivity.this,premium.class));
    }
    public void openActivityEmail() {
        startActivity(new Intent(MainActivity.this,email.class));
    }
    public void openActivityYoutube() {
        startActivity(new Intent(MainActivity.this,youtube.class));
    }

    private void setUpViewPager(ViewPager view_pager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new eBay(),"eBay");
        adapter.addFragment(new comingSoonShop(),"ComingSoon");

        view_pager.setAdapter(adapter);
    }

    @Override
    public void onBackPressed(){
        //super.onBackPressed();
    }



    public void bPremium(View view) {

    }


}
