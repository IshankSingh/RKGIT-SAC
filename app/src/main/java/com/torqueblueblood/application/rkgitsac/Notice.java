package com.torqueblueblood.application.rkgitsac;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import im.delight.android.webview.AdvancedWebView;

public class Notice extends AppCompatActivity implements AdvancedWebView.Listener {
    private AdvancedWebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        mWebView = (AdvancedWebView) findViewById(R.id.webview1);
        mWebView.setListener(this, this);
        mWebView.setDesktopMode(false);
            mWebView.loadUrl("https://drive.google.com/open?id=1NLSMIBxaXNbGcMNpsaHy8u6BYcmEfPwZ");}



    @Override
    public void onBackPressed() {
        if (!mWebView.onBackPressed()) { return; }
        else
            finish();
            startActivity(new Intent(Notice.this,MainActivity.class));
            overridePendingTransition(0, 0);
        }



    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        mWebView.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        mWebView.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mWebView.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        mWebView.onActivityResult(requestCode, resultCode, intent);
        // ...
    }


    @Override
    public void onPageStarted(String url, Bitmap favicon) {

    }

    @Override
    public void onPageFinished(String url) {

    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

        if (AdvancedWebView.handleDownload(this, url, suggestedFilename)) {
            // download successfully handled
        }
        else {
            // download couldn't be handled because user has disabled download manager app on the device
            // TODO show some notice to the user
        }
    }

    @Override
    public void onExternalPageRequest(String url) {

    }
}
