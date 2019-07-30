package com.torqueblueblood.application.rkgitsac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DeveloperPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_page);
    }
    public void onBackPressed(){
        finish();
        startActivity(new Intent(DeveloperPage.this,MainActivity.class));
        overridePendingTransition(0, 0);
    }
}
