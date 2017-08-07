package com.example.fim;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        Button scanBTN = (Button) findViewById(R.id.button10);

        scanBTN.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, SimpleScannerActivity.class);
                startActivity(i);
            }
        });


        Button uploadBTN = (Button) findViewById(R.id.button11);




        uploadBTN.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, UploadActivity.class);
                startActivity(i);
            }
        });


        Button historyBTN = (Button) findViewById(R.id.button12);

        historyBTN.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(i);
            }
        });

        Button keywordBTN = (Button) findViewById(R.id.button13);

        keywordBTN.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(i);
            }
        });

     

    }

    public static void goHome(Context context) {
        Intent i = new Intent(context, MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(i);
    }


}








