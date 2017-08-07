package com.example.fim;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends Activity {
    WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);



        wv = (WebView)findViewById(R.id.webView2);

        wv.loadUrl(getApplicationContext().getResources().getString(R.string.domain)+"search.html");



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


    }

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
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && wv.canGoBack()) {
            wv.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
// handler接收到消息後就會執行此方法
            // pd.dismiss();




// 關閉ProgressDialog
        }
    };




}
