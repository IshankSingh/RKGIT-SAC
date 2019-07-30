package com.torqueblueblood.application.rkgitsac;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import im.delight.android.webview.AdvancedWebView;

public class Albumview extends AppCompatActivity implements AdvancedWebView.Listener {
    private int k,l;

    private AdvancedWebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albumview);

        mWebView = (AdvancedWebView) findViewById(R.id.webview);
        mWebView.setListener(this, this);
        mWebView.setDesktopMode(false);
        Album album = new Album();
        MainActivity mainActivity=new MainActivity();
        l=mainActivity.notice;
        k = album.test;
        if (k == 1) {
            mWebView.loadUrl("https://drive.google.com/open?id=1TRW0IfoJW4RZQ_YDass9DMcQk9LVtouM");
            }else if(k==2){
                mWebView.loadUrl("https://drive.google.com/open?id=1f0GQV1prbp1vJve5ycm7YDBh2xzWdSwM");}
            else if(k==3)
                mWebView.loadUrl("https://drive.google.com/open?id=1waE3sbxq-Y4MWLCT9a_s9bOCz_S1c9Qg");
            else if(k==4)
                mWebView.loadUrl("https://drive.google.com/open?id=1MrJmT00ALvu8Zbjp_sQWYmpsEs-2Gun1");
        else if(l==1){
            mWebView.loadUrl("https://drive.google.com/open?id=1NLSMIBxaXNbGcMNpsaHy8u6BYcmEfPwZ");}
        }


    @Override
    public void onBackPressed() {
        if (!mWebView.onBackPressed()) { return; }
      else
          finish();
        startActivity(new Intent(Albumview.this,Album.class));
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
