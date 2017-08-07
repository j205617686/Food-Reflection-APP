package com.example.fim;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.localDB.DBHelper;

/**
 * Created by max241 on 2016/4/13.
 */
public class AppConfig extends Application{
    public static int historyCount;

    public String db_name = "inviteMoreDB";
    //table namehh
    public String table_name = "inviteMoreLog";

    //輔助類名
    DBHelper helper = new DBHelper(AppConfig.this, db_name);







}
