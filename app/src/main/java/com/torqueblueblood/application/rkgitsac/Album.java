package com.torqueblueblood.application.rkgitsac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Album extends AppCompatActivity {
  public String data;
  public static int test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_album);
    }
    public void fest(View v){
        test=1;
        finish();
        Intent intent=new Intent(Album.this, Albumview.class);
        startActivity(intent);
    }
    public void fresher(View v){
        test=2;
        finish();
        Intent intent=new Intent(Album.this, Albumview.class);
        startActivity(intent);
    }
    public void alumini(View v){
        test=3;
        finish();
        Intent intent=new Intent(Album.this, Albumview.class);
        startActivity(intent);
    }
    public void others(View v){
        test=4;
        finish();
        Intent intent=new Intent(Album.this, Albumview.class);
        startActivity(intent);
    }
    public void onBackPressed(){
        finish();
        startActivity(new Intent(Album.this,MainActivity.class));
    }
}
