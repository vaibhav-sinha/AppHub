package com.adobe.apphub.Fragments;


import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.adobe.apphub.Activities.ProductDetailsActivity;
import com.adobe.apphub.Base.BaseFragment;
import com.adobe.apphub.Models.AppData;
import com.adobe.apphub.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;


public class ProductDetailsFragment extends BaseFragment {

    private AppData appData;

    private ImageView pdIcon;
    private TextView pdName;
    private TextView pdRating;
    private TextView pdDescription;
    private TextView pdType;
    private TextView pdIAP;
    private TextView pdDate;
    private Button pdStore;
    private Button pdShare;
    private Button pdBack;
    private Button pdSms;

    public ProductDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_details, container, false);
        pdIcon = (ImageView)rootView.findViewById(R.id.pdIcon);
        pdName = (TextView)rootView.findViewById(R.id.pdName);
        pdRating = (TextView)rootView.findViewById(R.id.pdRating);
        pdDescription = (TextView)rootView.findViewById(R.id.pdDescription);
        pdType = (TextView)rootView.findViewById(R.id.pdType);
        pdIAP = (TextView)rootView.findViewById(R.id.pdIAP);
        pdDate = (TextView)rootView.findViewById(R.id.pdDate);
        pdStore = (Button)rootView.findViewById(R.id.pdStore);
        pdShare = (Button)rootView.findViewById(R.id.pdShare);
        pdBack = (Button)rootView.findViewById(R.id.pdBack);
        pdSms = (Button)rootView.findViewById(R.id.pdSms);

        appData = (AppData) getActivity().getIntent().getSerializableExtra("PRODUCT");

        pdName.setText(appData.getName());
        pdRating.setText(appData.getRating());
        pdDescription.setText(appData.getDescription());
        pdType.setText(appData.getType());
        pdIAP.setText(appData.getInapp_purchase());
        pdDate.setText(appData.getLast_updated());
        Picasso.with(getActivity()).load(appData.getImage()).into(pdIcon);

        pdStore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(appData.getUrl())));
            }
        });

        pdShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, new Gson().toJson(appData));
                sendIntent.setType("application/json");
                startActivity(sendIntent);
            }
        });

        pdBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitApp();
            }
        });

        pdSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

                alert.setTitle("Send SMS");
                alert.setMessage("Enter phone number");

                final EditText input = new EditText(getActivity());
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String number = input.getText().toString();
                        sendSMS(number, appData.getUrl());
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();
            }
        });
        return rootView;
    }

    private void exitApp() {
        getActivity().finish();
    }

    private void sendSMS(String phoneNumber, String message)
    {
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, null, null);
    }

}
