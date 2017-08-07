package com.example.fim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.model.ProductInfoBean;

import org.w3c.dom.Text;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by max241 on 2016/4/7.
 */
public class UploadActivity extends Activity{

    public static final int resultNum = 0;
    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;
    Button btn9;

    TextView tv1;
    EditText et1;
    EditText et2;
    EditText et3;
    EditText et4;
    EditText et5;
    EditText et6;
    EditText et7;


    String response1;
String allresponse;
    ProductInfoBean productObj=new ProductInfoBean();
    String barcode;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_page);


        btn1 = (Button) findViewById(R.id.button1);

        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        btn6 = (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);
        btn8 = (Button) findViewById(R.id.button8);
        btn9 = (Button) findViewById(R.id.button9);







        tv1 = (TextView) findViewById(R.id.textView8);

        et1 =(EditText)findViewById(R.id.editText);
        et2 =(EditText)findViewById(R.id.editText2);
        et3 =(EditText)findViewById(R.id.editText3);
        et4 =(EditText)findViewById(R.id.editText4);
        et5 =(EditText)findViewById(R.id.editText5);
        et6 =(EditText)findViewById(R.id.editText6);
        et7 =(EditText)findViewById(R.id.editText7);



        btn1.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {

                Intent i = new Intent(UploadActivity.this, UploadScannerActivity.class);
                startActivityForResult(i, resultNum);




            }
        });




        btn9.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {

                int count=0;
                if (tv1.getText().toString().equals(""))
                {
                    handler2.sendEmptyMessage(0);
                }
                else {
                    new Thread(new Runnable() {
                        public void run() {

                            try {
                                allresponse="";

                                Log.v("wwwwwwwww", "http://140.121.197.132:8079/fim/UpdateProduct?action=productName" + "&oldData=" + productObj.getProductName() + "&newData=" + et1.getText() + "&barcode=" + barcode);
                                response1 = GetPostUtil.sendGet(getApplicationContext().getResources().getString(R.string.domain)+"UpdateProduct", "action=productName" + "&oldData=" + URLEncoder.encode(productObj.getProductName().toString(),"UTF-8") + "&newData=" +URLEncoder.encode(et1.getText().toString(), "UTF-8") + "&barcode=" + barcode);
                                allresponse += "productName " + response1;


                                Log.v("wwwwwwwww", "http://140.121.197.132:8079/fim/UpdateProduct?action=importer" + "&oldData=" + productObj.getImporter() + "&newData=" + et2.getText() + "&barcode=" + barcode);
                                response1 = GetPostUtil.sendGet(getApplicationContext().getResources().getString(R.string.domain)+"UpdateProduct", "action=importer" + "&oldData=" + URLEncoder.encode(productObj.getImporter().toString(),"UTF-8") + "&newData=" + URLEncoder.encode(et2.getText().toString(), "UTF-8") + "&barcode=" + barcode);
                                allresponse += "importer " + response1;

                                Log.v("wwwwwwwww", "http://140.121.197.132:8079/fim/UpdateProduct?action=agents" + "&oldData=" + productObj.getAgent() + "&newData=" + et3.getText() + "&barcode=" + barcode);
                                response1 = GetPostUtil.sendGet(getApplicationContext().getResources().getString(R.string.domain)+"UpdateProduct", "action=agents" + "&oldData=" + URLEncoder.encode(productObj.getAgent().toString(),"UTF-8") + "&newData=" + URLEncoder.encode(et3.getText().toString(), "UTF-8") + "&barcode=" + barcode);
                                allresponse += "agents " + response1;


                                Log.v("wwwwwwwww", "http://140.121.197.132:8079/fim/UpdateProduct?action=placeOfOrigin" + "&oldData=" + productObj.getPlaceOfOrigin() + "&newData=" + et4.getText() + "&barcode=" + barcode);
                                response1 = GetPostUtil.sendGet(getApplicationContext().getResources().getString(R.string.domain)+"UpdateProduct", "action=placeOfOrigin" + "&oldData=" + URLEncoder.encode(productObj.getPlaceOfOrigin().toString(),"UTF-8") + "&newData=" + URLEncoder.encode(et4.getText().toString(), "UTF-8") + "&barcode=" + barcode);
                                allresponse += "placeOfOrigin " + response1;


                                Log.v("wwwwwwwww", "http://140.121.197.132:8079/fim/UpdateProduct?action=manufactAddress" + "&oldData=" + productObj.getManuAddress() + "&newData=" + et5.getText() + "&barcode=" + barcode);
                                response1 = GetPostUtil.sendGet(getApplicationContext().getResources().getString(R.string.domain)+"UpdateProduct", "action=manufactAddress" + "&oldData=" + URLEncoder.encode(productObj.getManuAddress().toString(),"UTF-8") + "&newData=" +URLEncoder.encode(et5.getText().toString(), "UTF-8") + "&barcode=" + barcode);
                                allresponse += "manufactAddress " + response1;


                                String ParamStr1 = "action=additive" + "&oldData=" + URLEncoder.encode(productObj.getAdditive().toString(), "UTF-8") + "&newData=[" + URLEncoder.encode(et6.getText().toString(), "UTF-8") + "]&barcode=" + barcode;
                                ParamStr1.replaceAll("\\s*", "");
                                Log.v("param1", ParamStr1);

                                response1 = GetPostUtil.sendGet(getApplicationContext().getResources().getString(R.string.domain)+"UpdateProduct", ParamStr1);
                                allresponse += "additive " + response1;


                                String ParamStr2 = "action=relInfo" + "&oldData=" + URLEncoder.encode(productObj.getRelInfo().toString(), "UTF-8") + "&newData=[" + URLEncoder.encode(et7.getText().toString(),"UTF-8") + "]&barcode=" + barcode;
                                ParamStr2.replaceAll("\\s*", "");
                                Log.v("param2", ParamStr2);

                                response1 = GetPostUtil.sendGet(getApplicationContext().getResources().getString(R.string.domain)+"UpdateProduct", ParamStr2);
                                allresponse += "relInfo " + response1;

                            }catch (UnsupportedEncodingException e)
                            {
                                e.printStackTrace();
                            }


/*
                            if(count>0)
                            {
                                allresponse="Got an Error";

                            }
                            else
                            {
                                allresponse="status OK!";
                            }
*/


                            handler.sendEmptyMessage(0);

                        }
                    }).start();
                }
            }
        });






    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Bundle b = data.getExtras();

        if(resultCode == RESULT_OK){
            if(requestCode == resultNum){

               // Log.v("dewdewdew", b.getSerializable("productObj").toString());
                productObj=(ProductInfoBean) data.getSerializableExtra("productObj");

                barcode=data.getExtras().getString("barcode");

                tv1.setText(barcode);

                et1.setText(productObj.getProductName());
                et2.setText(productObj.getAgent());
                et3.setText(productObj.getImporter());
                et4.setText(productObj.getPlaceOfOrigin());
                et5.setText(productObj.getManuAddress());


                et6.setText(productObj.getAdditive().toString().substring(1, productObj.getAdditive().toString().length() - 1));
                et7.setText(productObj.getRelInfo().toString().substring(1, productObj.getRelInfo().toString().length() - 1));






            }
        }
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Toast.makeText(getApplicationContext(),allresponse, Toast.LENGTH_SHORT).show();
            allresponse="";


        }

    };
    Handler handler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {


            Toast.makeText(getApplicationContext(),"請先掃描條碼資料!", Toast.LENGTH_SHORT).show();




        }

    };





}
