package com.example.fim;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.model.ProductInfoBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductActivity extends Activity {
    private String data;


    WebView wv;
    String barcode;
    String response=null;
    ProgressDialog pd;
    ProductInfoBean productObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);








        Bundle bundle2 =this.getIntent().getExtras();
        //productObj = (ProductInfoBean) getIntent().getSerializableExtra("productObj ");

        barcode=bundle2.getString("barcode");


        wv = (WebView)findViewById(R.id.webView);


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);



        wv.loadUrl(getApplicationContext().getResources().getString(R.string.domain)+"CollapsibleTable.html?barcode="+barcode);



        wv.getSettings().setJavaScriptEnabled(true);//可用JS
        wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);//滚动条风格，为0就是不给滚动条留空间，滚动条覆盖在网页上
        wv.setBackgroundColor(0);//先设置背景色为transparent

        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
                loadurl(view, url);//载入网页
                return true;
            }//重写点击动作,用webview载入

        });
        wv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {//载入进度改变而触发
                if (progress == 100) {
                    handler.sendEmptyMessage(1);//如果全部载入,隐藏进度对话框
                }
                super.onProgressChanged(view, progress);
            }
        });

        wv.getSettings().setSupportZoom(false);
        wv.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        //barcode = bundle2.getString("Barcode");


/*
        pd=ProgressDialog.show(ProductActivity.this, "Loading", "Please wait....");
        new Thread(new Runnable() {
            public void run() {

                response = GetPostUtil.sendGet("http://140.121.197.132:8080/fim/getProduct", "barcode="+barcode);
                Log.i("response", response);

               productObj=new ProductInfoBean();

                try {
                    JSONObject productJson=new JSONObject(response);


                    ArrayList<String> additiveAL = new ArrayList<String>();

                    ArrayList<String> relInfoAL = new ArrayList<String>();


                    productObj.setImporter(productJson.get("importer").toString());
                    productObj.setProductName(productJson.get("ProductName").toString());
                    productObj.setPlaceOfOrigin(productJson.get("placeOfOrigin").toString());
                    productObj.setManuAddress(productJson.get("manuAddress").toString());
                    productObj.setAgent(productJson.get("agents").toString());


                    for(int i=0;i<productJson.getJSONArray("additive").length();i++)
                    {
                        additiveAL.add(productJson.getJSONArray("additive").get(i).toString());
                    }
                    for(int i=0;i<productJson.getJSONArray("relInfo").length();i++)
                    {
                        relInfoAL.add(productJson.getJSONArray("relInfo").get(i).toString());
                    }

                    productObj.setAdditive(additiveAL);
                    productObj.setRelInfo(relInfoAL);

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }





                    handler.sendEmptyMessage(0);
            }
        }).start();
*/


/*

        System.out.println(barcode);


        System.out.println(response);

        tv.setText(productObj.getProductName());
        tv1.setText(productObj.getImporter());
        tv2.setText(productObj.getAgent());
        tv3.setText(productObj.getPlaceOfOrigin());
        tv4.setText(productObj.getManuAddress());

        String additiveStr=new String();

        tv5.setText(productObj.getAdditive().toString());


        tv6.setText(productObj.getRelInfo().toString());

*/
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
// handler接收到消息後就會執行此方法
           // pd.dismiss();




// 關閉ProgressDialog
        }
    };

 

    public void loadurl(final WebView view,final String url){

        view.post(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
                view.loadUrl(url);//载入网页
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//捕捉返回键
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()) {
            wv.goBack();
            return true;
        }else if(keyCode == KeyEvent.KEYCODE_BACK){
            ProductActivity.this.finish();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }





}
