package com.example.fim;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.localDB.DBHelper;
import com.example.model.ProductInfoBean;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class SimpleScannerActivity extends Activity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    String response;
    String barcode;
    ProgressDialog pd;
    ProductInfoBean productObj;
    JSONObject alldata;

    DBHelper helper;
    public String db_name = "inviteMoreDB";
    //table name
    public String table_name = "inviteMoreLog";




    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        AppConfig globalVariable = (AppConfig)getApplicationContext();

        helper=globalVariable.helper;








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





        pd=ProgressDialog.show(SimpleScannerActivity.this, "Loading", "Please wait....");

        new Thread(new Runnable() {
            public void run() {

                String Url = getApplicationContext().getResources().getString(R.string.domain) + "/getProduct";

                response = GetPostUtil.sendGet(Url, "barcode="+barcode);

                Log.i("response", response);

                productObj=new ProductInfoBean();

                try {

                    alldata =new JSONObject(response);

                if(alldata.get("status").equals("OK")) {
                    add_db(barcode);
                    JSONObject productJson = new JSONObject(alldata.get("data").toString());
                    productObj.setProductName(productJson.get("ProductName").toString());
                    /*
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
*/
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


            Log.v("response",response);

            try{
            if(alldata.get("status").equals("OK"))
            {
                new AlertDialog.Builder(SimpleScannerActivity.this)
                        .setTitle("找到產品 " + productObj.getProductName())
                        .setMessage("是否進一步觀看詳細資訊??")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                pd.dismiss();
                                Intent i = new Intent(SimpleScannerActivity.this, ProductActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("barcode",barcode);
                                bundle.putSerializable("productObj ", productObj);
                                i.putExtras(bundle);

                                startActivity(i);

                            }
                        })
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "go back", Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                                Intent i = new Intent(SimpleScannerActivity.this, SimpleScannerActivity.class);
                                startActivity(i);
                            }
                        }).show();

            }
            else {

                new AlertDialog.Builder(SimpleScannerActivity.this)
                        .setTitle("很抱歉 目前沒有這個產品資訊")
                        .setMessage("是否幫忙上傳商品資訊?")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "前往資料上傳區", Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                                Intent i = new Intent(SimpleScannerActivity.this, UploadActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("barcode",barcode);
                                i.putExtras(bundle);

                                startActivity(i);

                            }
                        })
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                pd.dismiss();
                                Toast.makeText(getApplicationContext(), "stay here", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(SimpleScannerActivity.this, SimpleScannerActivity.class);
                                startActivity(i);
                            }
                        }).show();
            }

            }catch(Exception e){
                    e.printStackTrace();
            }









        }







    };




    private void add_db(String barcode)
    {
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("barcode", barcode.toString());
        values.put("status", 1);
        values.put("usedDate", currentDateTimeString);
        db.insert(table_name, null, values);
    }


    public String[] getIdArray()
    {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select barcode from inviteMoreLog", null);

        //用陣列存資料
        String[] sNote = new String[cursor.getCount()];

        //取得資料表列數
        int rows_num = cursor.getCount();

        if(rows_num != 0)
        {
            cursor.moveToFirst();   //將指標移至第一筆資料
            for(int i=0; i<rows_num; i++)
            {
                String strValue = cursor.getString(0);
                sNote[i]=strValue;
                cursor.moveToNext();//將指標移至下一筆資料
            }
        }
        cursor.close(); //關閉Cursor
        return sNote;
    }

    private void close_db()
    {
        helper.close();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//捕捉返回键
      if(keyCode == KeyEvent.KEYCODE_BACK){
            SimpleScannerActivity.this.finish();
          Intent i = new Intent(SimpleScannerActivity.this,MainActivity.class);
            startActivity(i);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }





}