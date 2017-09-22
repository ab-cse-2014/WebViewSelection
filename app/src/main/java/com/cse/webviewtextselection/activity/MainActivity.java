package com.cse.webviewtextselection.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cse.webviewtextselection.R;
import com.cse.webviewtextselection.custom_view.CustomWebView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomWebView customWebView = (CustomWebView) findViewById(R.id.customWebView);
        customWebView.loadUrl("file:///android_asset/content.html");

    }
}
