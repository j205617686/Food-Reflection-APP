package com.example.fim;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.example.model.ProductInfoBean;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class UploadScannerActivity extends Activity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    String response;
    String barcode;
    ProgressDialog pd;
    ProductInfoBean productObj;
    JSONObject alldata;


    public static final String FLAG = "BACK_STRING";

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v("TAG", rawResult.getText()); // Prints scan results
        Log.v("TAG", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

        barcode=rawResult.getText();

        pd = ProgressDialog.show(UploadScannerActivity.this, "Loading", "Please wait....");

        new Thread(new Runnable() {
            public void run() {

                response = GetPostUtil.sendGet(getApplicationContext().getResources().getString(R.string.domain)+"getProduct", "barcode="+barcode);
                Log.i("response", response);

                productObj=new ProductInfoBean();

                try {

                    alldata =new JSONObject(response);

                if(alldata.get("status").equals("OK")) {

                        JSONObject productJson = new JSONObject(alldata.get("data").toString());


                        ArrayList<String> additiveAL = new ArrayList<String>();

                        ArrayList<String> relInfoAL = new ArrayList<String>();


                        productObj.setImporter(productJson.get("importer").toString());
                        productObj.setProductName(productJson.get("ProductName").toString());
                        productObj.setPlaceOfOrigin(productJson.get("placeOfOrigin").toString());
                        productObj.setManuAddress(productJson.get("manuAddress").toString());
                        productObj.setAgent(productJson.get("agents").toString());


                        for (int i = 0; i < productJson.getJSONArray("additive").length(); i++) {
                            additiveAL.add(productJson.getJSONArray("additive").get(i).toString());
                        }
                        for (int i = 0; i < productJson.getJSONArray("relInfo").length(); i++) {
                            relInfoAL.add(productJson.getJSONArray("relInfo").get(i).toString());
                        }

                        productObj.setAdditive(additiveAL);
                        productObj.setRelInfo(relInfoAL);


                }
                else {

                }

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                handler.sendEmptyMessage(0);
            }
        }).start();








        // If you would like to resume scanning, call this method below:
        //mScannerView.resumeCameraPreview(this);
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {




            Intent intent = new Intent();
            Bundle b = new Bundle();
            Log.v("productObj",productObj.toString());

            b.putString("barcode", barcode);
            intent.putExtras(b);
            intent.putExtra("productObj",productObj);

            UploadScannerActivity.this.setResult(RESULT_OK, intent);
            finish();








        }







    };







}