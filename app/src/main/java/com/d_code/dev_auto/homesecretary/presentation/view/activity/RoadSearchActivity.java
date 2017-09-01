package com.example.gyutaebbb.tmapsample;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

public class RoadSearchActivity extends AppCompatActivity {
    private WebView webView = null;
    private TextView textView = null;
    private Button btn_save = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_search);
        webView = (WebView)findViewById(R.id.webView);
        textView = (TextView)findViewById(R.id.textView);
        btn_save = (Button)findViewById(R.id.btn_save);

        SetWebview();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(webView.getUrl());
            }
        });


    }
    protected void SetWebview(){ //setting for app
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSaveFormData(false);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.loadUrl("http://m.map.naver.com/route.nhn");
        webView.setWebViewClient(new MyWebViewClient());
    }

}
