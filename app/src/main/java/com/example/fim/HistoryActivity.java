package com.example.fim;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.localDB.DBHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by max241 on 2016/4/13.
 */
public class HistoryActivity extends Activity {
    public String db_name = "inviteMoreDB";
    //table name
    public String table_name = "inviteMoreLog";
    DBHelper helper;

    ArrayList<String> history;
    ArrayList<String> historyName;
    ProgressDialog pd;

    ListView list;
    ArrayList<HashMap<String, String>> Hashlist = new ArrayList<HashMap<String, String>>();
    SimpleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_page);
        AppConfig globalVariable = (AppConfig)getApplicationContext();

        helper=globalVariable.helper;


        list=(ListView) findViewById(R.id.listView);



       // tv=(TextView)findViewById(R.id.textView13);


        history = new ArrayList<String>();

        for(int i=0;i<getIdArray().length;i++) {

            if(getIdArray()[i].equals(null)!=true)
                history.add(getIdArray()[i]);
       }


        historyName=new ArrayList<String>();

        pd=ProgressDialog.show(HistoryActivity.this, "Loading", "Please wait....");
            new Thread(new Runnable() {
                public void run() {

                    for(int i=0;i<history.size();i++) {

                    String response = GetPostUtil.sendGet(getApplicationContext().getResources().getString(R.string.domain)+"getProduct", "barcode=" + history.get(i));
                    Log.i("response", response);


                        try {

                            JSONObject alldata = new JSONObject(response);

                            if (alldata.get("status").equals("OK")) {

                                JSONObject productJson = new JSONObject(alldata.get("data").toString());
                                historyName.add(productJson.get("ProductName").toString());

                            }
                        }catch (Exception e)
                        {

                        }


                    }

                    handler.sendEmptyMessage(0);
                }
            }).start();





        adapter = new SimpleAdapter(
                this,
                Hashlist,
                R.layout.listitem,
                new String[]{"title", "description"},
                new int[]{R.id.textView01,R.id.textView02});




        //ListAdapter ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1,history);








        class ListListener implements AdapterView.OnItemClickListener{


            List<String> data;
            Activity activity;


            public ListListener(List<String> data,Activity activity)
            {
                this.data = data;
                this.activity = activity;

            }


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



                if(history.get(i).toString().equals("")!=true) {

                    Intent intent = new Intent(HistoryActivity.this, ProductActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("barcode", history.get(i));

                    intent.putExtras(bundle);

                    startActivity(intent);
                }
                else
                {
                    Context context2 = getApplication();

                    CharSequence text2 = "現在暫無此花種的資料";

                    int duration2 = Toast.LENGTH_SHORT;

                    Toast.makeText(context2, text2, duration2).show();
                }


            }
        }


        list.setOnItemClickListener(new ListListener(history, this));

































    }




    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {


            for (int i = 0; i < history.size(); i++) {
                HashMap<String, String> item = new HashMap<String, String>();

                item.put("title", history.get(i));

                item.put("description",historyName.get(i));
                Hashlist.add(item);
            }












            pd.dismiss();
            list.setAdapter(adapter);



        }

    };




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



}