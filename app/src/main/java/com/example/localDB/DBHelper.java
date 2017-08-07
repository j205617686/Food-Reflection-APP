package com.example.localDB;

import android.database.sqlite.SQLiteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DBHelper extends SQLiteOpenHelper
{
    //資料庫版本關係到App更新時，資料庫是否要調用onUpgrade()
    private static final int VERSION = 1;//資料庫版本

    //建構子
    public DBHelper(Context context, String name, CursorFactory factory,int version)
    {
        super(context, name, factory, version);
    }

    public DBHelper(Context context, String name)
    {
        this(context, name, null, VERSION);
    }

    public DBHelper(Context context, String name, int version)
    {
        this(context, name, null, version);
    }

    //輔助類建立時運行該方法
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String DATABASE_CREATE_TABLE =
                "create table inviteMoreLog("
                        + "logId INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL,"
                        + "barcode VARCHAR,"
                        + "status INT,"
                        + "usedDate VARCHAR"
                        + ")";
        db.execSQL(DATABASE_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //oldVersion=舊的資料庫版本；newVersion=新的資料庫版本
        db.execSQL("DROP TABLE IF EXISTS inviteMoreLog"); //刪除舊有的資料表
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db)
    {
        // TODO 每次成功打開數據庫後首先被執行
        super.onOpen(db);
    }

    @Override
    public synchronized void close()
    {
        super.close();
    }
}