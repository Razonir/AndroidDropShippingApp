package com.razonir.dropshippingcalc.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.razonir.dropshippingcalc.R;

import java.text.DecimalFormat;


public class ebayAmazonSellPriceIls extends Fragment {

    final float AMAZONTEX = (float) 1.08;
    final float PAYPALTEX = (float) 0.948;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ebay_amazon_sell_price_ils, container, false);
        TextView sellprice = (TextView)view.findViewById(R.id.resultProfit);
        EditText amazonPriceInput = (EditText)view.findViewById(R.id.amazonPrice);
        EditText profitWontedInput = (EditText)view.findViewById(R.id.sellPrice);
        TextView amazonTex = (TextView)view.findViewById(R.id.amazonTex);
        TextView paypalTex = (TextView)view.findViewById(R.id.paypalTex);
        TextView ebayTex = (TextView)view.findViewById(R.id.ebayTex);
        Button reset = (Button)view.findViewById(R.id.reset);
        EditText shippingCostInput = (EditText)view.findViewById(R.id.editcost);
        Switch topRated = (Switch)view.findViewById(R.id.topRated);
        Switch belowStandart = (Switch)view.findViewById(R.id.belowStandart);
        float sellerRate= (float) 0.1;
        if (belowStandart.isChecked()){
            sellerRate= (float) 0.15;
        }
        if (topRated.isChecked()){
            sellerRate= (float)  0.09;
        }
        topRated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(topRated.isChecked()){
                    belowStandart.setChecked(false);
                }
            }
        });
        belowStandart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(belowStandart.isChecked()){
                    topRated.setChecked(false);
                }
            }
        });
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            double curDollarRate = 3.26;
            double PAYPALDOLLAR = curDollarRate*0.972882511666837;
            double AMAZONDOLLAR = curDollarRate*1.023707663258139;
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                float sellerRate= (float) 0.1;
                if (belowStandart.isChecked()){
                    sellerRate= (float) 0.15;
                }
                if (topRated.isChecked()){
                    sellerRate= (float)  0.09;
                }
                DecimalFormat dFormatter = new DecimalFormat("0.00");
                float shippingCostVal = (float)0;
                if(!amazonPriceInput.getText().toString().equals("") && !profitWontedInput.getText().toString().equals("")){
                    float amazonPriceVal = Float.parseFloat(amazonPriceInput.getText().toString());
                    float profitWontedVal = Float.parseFloat(profitWontedInput.getText().toString());

                    if (shippingCostInput.getText().toString().equals("")){
                        shippingCostVal=(float)0;
                    }else {
                        shippingCostVal = Float.parseFloat(shippingCostInput.getText().toString());
                    }
                    amazonPriceVal = amazonPriceVal+shippingCostVal;
                    float sellPriceAfterCalc = (float) ((float) ((profitWontedVal+AMAZONTEX*AMAZONDOLLAR*(amazonPriceVal+shippingCostVal))/PAYPALTEX)/PAYPALDOLLAR);
                    float realprofit = (float) (profitWontedVal-(sellPriceAfterCalc*sellerRate*curDollarRate));
                    float temp = profitWontedVal;
                    while(Math.floor(realprofit)!=Math.floor(temp)){
                        profitWontedVal++;
                        sellPriceAfterCalc = (float) ((float) ((profitWontedVal+AMAZONTEX*AMAZONDOLLAR*amazonPriceVal)/PAYPALTEX)/PAYPALDOLLAR);
                        realprofit = (float) (profitWontedVal-(sellPriceAfterCalc*sellerRate*curDollarRate));
                    }


                    sellprice.setText(String.valueOf(dFormatter.format(sellPriceAfterCalc))+"$");


                    topRated.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(topRated.isChecked()){
                                belowStandart.setChecked(false);

                            }
                        }
                    });
                    belowStandart.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(belowStandart.isChecked()){
                                topRated.setChecked(false);

                            }
                        }
                    });

                    reset.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            amazonPriceInput.setText("");
                            profitWontedInput.setText("");
                            sellprice.setText("0.0$");
                            shippingCostInput.setText("");
                            ebayTex.setText("ebay Tax: 0.00$");
                            paypalTex.setText("paypal Tax: 0.00$");
                            amazonTex.setText("amazon Tax: 0.00$");
                        }
                    });

                    //tex rate
                    float amazonTexVal = (float) ((AMAZONTEX*(amazonPriceVal+shippingCostVal))-(amazonPriceVal+shippingCostVal));
                    float paypalTexVal = (float) ((sellPriceAfterCalc)-((sellPriceAfterCalc)*PAYPALTEX));
                    float ebayTexVal = (float) ((sellPriceAfterCalc)*sellerRate);
                    String amazonformat = dFormatter.format(amazonTexVal);
                    amazonTex.setText("amazon Tax: "+amazonformat+"$");
                    String paypalformat = dFormatter.format(paypalTexVal);
                    paypalTex.setText("paypal Tax: "+paypalformat+"$");
                    String ebayformat = dFormatter.format(ebayTexVal);
                    ebayTex.setText("ebay Tax: "+ebayformat+"$");
                    sellprice.setText(String.valueOf(dFormatter.format(sellPriceAfterCalc))+"$");


                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        };
        amazonPriceInput.addTextChangedListener(textWatcher);
        profitWontedInput.addTextChangedListener(textWatcher);
        shippingCostInput.addTextChangedListener(textWatcher);
        topRated.addTextChangedListener(textWatcher);
        belowStandart.addTextChangedListener(textWatcher);


        return view;
    }

}