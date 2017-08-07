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

/**
 * Created by max241 on 2016/4/7.
 */
public class UploadActivity2 extends Activity{

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

    ProductInfoBean productObj=new ProductInfoBean();
    String barcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

                Intent i = new Intent(UploadActivity2.this, UploadScannerActivity.class);
                startActivityForResult(i, resultNum);




            }
        });




        btn2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {

                if(tv1.getText().toString().equals(""))
                {
                    handler2.sendEmptyMessage(0);
                }
                else{


                    new Thread(new Runnable() {
                        public void run() {

                            Log.v("wwwwwwwww", "http://140.121.197.132:8080/fim/UpdateProduct?action=productName" + "&oldData=" + productObj.getProductName() + "&newData=" + et1.getText() + "&barcode=" + barcode);
                            response1 = GetPostUtil.sendGet("http://140.121.197.132:8080/fim/UpdateProduct", "action=productName" + "&oldData=" + productObj.getProductName() + "&newData=" + et1.getText() + "&barcode=" + barcode);
                            handler.sendEmptyMessage(0);

                        }
                    }).start();
                }
            }

        });


        btn3.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                if(tv1.getText().toString().equals(""))
                {
                    handler2.sendEmptyMessage(0);
                }
                else {
                    new Thread(new Runnable() {
                        public void run() {
                            Log.v("wwwwwwwww", "http://140.121.197.132:8080/fim/UpdateProduct?action=importer" + "&oldData=" + productObj.getImporter() + "&newData=" + et2.getText() + "&barcode=" + barcode);
                            response1 = GetPostUtil.sendGet("http://140.121.197.132:8080/fim/UpdateProduct", "action=importer" + "&oldData=" + productObj.getImporter() + "&newData=" + et2.getText() + "&barcode=" + barcode);
                            handler.sendEmptyMessage(0);

                        }
                    }).start();

                }
            }
        });

        btn4.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                if(tv1.getText().toString().equals(""))
                {
                    handler2.sendEmptyMessage(0);
                }
                else {
                    new Thread(new Runnable() {
                        public void run() {
                            Log.v("wwwwwwwww", "http://140.121.197.132:8080/fim/UpdateProduct?action=agents" + "&oldData=" + productObj.getAgent() + "&newData=" + et3.getText() + "&barcode=" + barcode);
                            response1 = GetPostUtil.sendGet("http://140.121.197.132:8080/fim/UpdateProduct", "action=agents" + "&oldData=" + productObj.getAgent() + "&newData=" + et3.getText() + "&barcode=" + barcode);
                            handler.sendEmptyMessage(0);

                        }
                    }).start();
                }

    }
        });



        btn5.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                if(tv1.getText().toString().equals(""))
                {
                    handler2.sendEmptyMessage(0);
                }
                else {
                    new Thread(new Runnable() {
                        public void run() {
                            Log.v("wwwwwwwww", "http://140.121.197.132:8080/fim/UpdateProduct?action=placeOfOrigin" + "&oldData=" + productObj.getPlaceOfOrigin() + "&newData=" + et4.getText() + "&barcode=" + barcode);
                            response1 = GetPostUtil.sendGet("http://140.121.197.132:8080/fim/UpdateProduct", "action=placeOfOrigin" + "&oldData=" + productObj.getPlaceOfOrigin() + "&newData=" + et4.getText() + "&barcode=" + barcode);
                            handler.sendEmptyMessage(0);

                        }
                    }).start();
                }

        }
        });

        btn6.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                if(tv1.getText().toString().equals(""))
                {
                    handler2.sendEmptyMessage(0);
                }
                else {
                    new Thread(new Runnable() {
                        public void run() {
                            Log.v("wwwwwwwww", "http://140.121.197.132:8080/fim/UpdateProduct?action=manufactAddress" + "&oldData=" + productObj.getManuAddress() + "&newData=" + et5.getText() + "&barcode=" + barcode);
                            response1 = GetPostUtil.sendGet("http://140.121.197.132:8080/fim/UpdateProduct", "action=manufactAddress" + "&oldData=" + productObj.getManuAddress() + "&newData=" + et5.getText() + "&barcode=" + barcode);
                            handler.sendEmptyMessage(0);

                        }
                    }).start();
                }

        }
        });

        btn7.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                if(tv1.getText().toString().equals(""))
                {
                    handler2.sendEmptyMessage(0);
                }
                else {
                    new Thread(new Runnable() {
                        public void run() {

                            String ParamStr = "action=relInfo" + "&oldData=" + productObj.getRelInfo() + "&newData=[" + et7.getText() + "]&barcode=" + barcode;
                            ParamStr.replaceAll("\\s+", "");
                            Log.v("wwwwwwwww", "http://140.121.197.132:8080/fim/UpdateProduct?action=additive" + "&oldData=" + productObj.getAdditive() + "&newData=[" + et6.getText() + "]&barcode=" + barcode);
                            response1 = GetPostUtil.sendGet("http://140.121.197.132:8080/fim/UpdateProduct", ParamStr);
                            handler.sendEmptyMessage(0);

                        }
                    }).start();
                }

        }
        });

        btn8.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {

                if (tv1.getText().toString().equals(""))
                {
                    handler2.sendEmptyMessage(0);
                }
                else {
                    new Thread(new Runnable() {
                        public void run() {

                            String ParamStr = "action=relInfo" + "&oldData=" + productObj.getRelInfo() + "&newData=[" + et7.getText() + "]&barcode=" + barcode;
                            ParamStr.replaceAll("\\s+", "");

                            Log.v("wwwwwwwww", "http://140.121.197.132:8080/fim/UpdateProduct?action=relInfo" + "&oldData=" + productObj.getRelInfo() + "&newData=[" + et7.getText() + "]&barcode=" + barcode);


                            response1 = GetPostUtil.sendGet("http://140.121.197.132:8080/fim/UpdateProduct", ParamStr);
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
            Toast.makeText(getApplicationContext(),response1, Toast.LENGTH_SHORT).show();



        }

    };
    Handler handler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {


            Toast.makeText(getApplicationContext(),"請先掃描條碼資料!", Toast.LENGTH_SHORT).show();




        }

    };





}
