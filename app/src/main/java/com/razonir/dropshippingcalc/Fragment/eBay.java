package com.razonir.dropshippingcalc.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.razonir.dropshippingcalc.EbayAmazon;
import com.razonir.dropshippingcalc.MainActivity;
import com.razonir.dropshippingcalc.R;
import com.razonir.dropshippingcalc.ebayAliexpress;

public class eBay extends Fragment {
    private final static String TAG = "AdMob";
    RelativeLayout amazon;
    RelativeLayout ali;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_e_bay, container, false);

        View.OnClickListener listnr = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EbayAmazon.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                ((MainActivity) getActivity()).startActivity(intent);
            }
        };

        amazon = (RelativeLayout) view.findViewById(R.id.amazon);
        amazon.setOnClickListener(listnr);


        View.OnClickListener aliB = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ebayAliexpress.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                ((MainActivity) getActivity()).startActivity(intent);
            }
        };

        ali = (RelativeLayout) view.findViewById(R.id.alicard);
        ali.setOnClickListener(aliB);



        return view;
    }


    public void open(View view) {
    }
}