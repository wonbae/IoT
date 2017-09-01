package com.d_code.dev_auto.homesecretary.presentation.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.d_code.dev_auto.homesecretary.R;
import com.d_code.dev_auto.homesecretary.presentation.view.component.MyWebViewClient;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class RoadSearchActivity extends AppCompatActivity {
    private WebView webView = null;
    private TextView textView = null;
    private TextView ok = null;
    private TextView cancel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road_search);
        webView = (WebView)findViewById(R.id.webView);
        ok = (TextView)findViewById(R.id.OK_btn);
        cancel = (TextView)findViewById(R.id.Cancel_btn);

        SetWebview();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String result = java.net.URLDecoder.decode(webView.getUrl(), "UTF-8");
                    String result2 = java.net.URLDecoder.decode(result, "UTF-8");
                    String regx = "/";
                    String[] departAndArrival = result2.split(regx);
                    String[] depart = departAndArrival[7].split(",");
                    String[] arrival = departAndArrival[8].split(",");
                    String departToArrival = depart[0] + " > " + arrival[0];
                    Intent response = getIntent();
                    response.putExtra("pathURI",webView.getUrl());
                    response.putExtra("departureToArrival",departToArrival);
                    setResult(Activity.RESULT_OK,response);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                finish();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });


    }
    protected void SetWebview(){ //setting for app
        String pathURI = getIntent().getStringExtra("pathURI");
        if(pathURI == null || pathURI.equals("경로")) {
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDomStorageEnabled(true);
            webSettings.setSupportZoom(true);
            webSettings.setBuiltInZoomControls(true);
            webSettings.setSaveFormData(false);
            webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
            webView.loadUrl("http://m.map.naver.com/route.nhn");
            webView.setWebViewClient(new MyWebViewClient());
        }else
        {
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDomStorageEnabled(true);
            webSettings.setSupportZoom(true);
            webSettings.setBuiltInZoomControls(true);
            webSettings.setSaveFormData(false);
            webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
            webView.loadUrl(pathURI);
            webView.setWebViewClient(new MyWebViewClient());
        }
    }

}
