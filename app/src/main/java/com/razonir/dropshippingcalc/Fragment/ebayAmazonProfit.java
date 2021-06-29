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


public class ebayAmazonProfit extends Fragment {


    final double AMAZONTEX = 1.08;
    final double PAYPALTEX = 0.948;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ebay_amazon_profit, container, false);
        TextView resultProfit = (TextView)view.findViewById(R.id.resultProfit);
        EditText amazonPriceInput = (EditText)view.findViewById(R.id.amazonPrice);
        EditText sellPriceInput = (EditText)view.findViewById(R.id.sellPrice);
        TextView amazonTex = (TextView)view.findViewById(R.id.amazonTex);
        TextView paypalTex = (TextView)view.findViewById(R.id.paypalTex);
        TextView ebayTex = (TextView)view.findViewById(R.id.ebayTex);
        Button reset = (Button)view.findViewById(R.id.reset);
        EditText shippingChargeInput = (EditText)view.findViewById(R.id.editCharge);
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
        float curDollar = (float)3.24;
        double paypalDollar = curDollar*0.972882511666837;
        double amazonDollar = curDollar*1.023707663258139;
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

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
                float shippingChargeVal = (float)0;
                float shippingCostVal = (float)0;
                if(!amazonPriceInput.getText().toString().equals("") && !sellPriceInput.getText().toString().equals("")){
                    float amazonPriceVal = Float.parseFloat(amazonPriceInput.getText().toString());
                    float sellPriceVal = Float.parseFloat(sellPriceInput.getText().toString());
                    if (shippingChargeInput.getText().toString().equals("")){
                        shippingChargeVal=(float)0;
                    }else {
                        shippingChargeVal = Float.parseFloat(shippingChargeInput.getText().toString());
                    }
                    if (shippingCostInput.getText().toString().equals("")){
                        shippingCostVal=(float)0;
                    }else {
                        shippingCostVal = Float.parseFloat(shippingCostInput.getText().toString());
                    }
                    float profitResultAfterCalc = (float) (((sellPriceVal+shippingChargeVal)*PAYPALTEX)-((sellPriceVal+shippingChargeVal)*sellerRate)-((amazonPriceVal+shippingCostVal)*AMAZONTEX));
                    resultProfit.setText(String.valueOf(dFormatter.format(profitResultAfterCalc))+"$");


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
                            sellPriceInput.setText("");
                            resultProfit.setText("0.0$");
                            shippingChargeInput.setText("");
                            shippingCostInput.setText("");
                            ebayTex.setText("ebay Tax: 0.00$");
                            paypalTex.setText("paypal Tax: 0.00$");
                            amazonTex.setText("amazon Tax: 0.00$");
                        }
                    });

                    //tex rate
                    float amazonTexVal = (float) ((AMAZONTEX*(amazonPriceVal+shippingCostVal))-(amazonPriceVal+shippingCostVal));
                    float paypalTexVal = (float) ((sellPriceVal+shippingChargeVal)-((sellPriceVal+shippingChargeVal)*PAYPALTEX));
                    float ebayTexVal = (float) ((sellPriceVal+shippingChargeVal)*sellerRate);
                    String amazonformat = dFormatter.format(amazonTexVal);
                    amazonTex.setText("amazon Tax: "+amazonformat+"$");
                    String paypalformat = dFormatter.format(paypalTexVal);
                    paypalTex.setText("paypal Tax: "+paypalformat+"$");
                    String ebayformat = dFormatter.format(ebayTexVal);
                    ebayTex.setText("ebay Tax: "+ebayformat+"$");
                    resultProfit.setText(String.valueOf(dFormatter.format(profitResultAfterCalc))+"$");


                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        };
        amazonPriceInput.addTextChangedListener(textWatcher);
        sellPriceInput.addTextChangedListener(textWatcher);
        shippingChargeInput.addTextChangedListener(textWatcher);
        shippingCostInput.addTextChangedListener(textWatcher);
        topRated.addTextChangedListener(textWatcher);
        belowStandart.addTextChangedListener(textWatcher);



        return view;
    }

}