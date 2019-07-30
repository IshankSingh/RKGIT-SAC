package com.torqueblueblood.application.rkgitsac;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoViewAttacher;

public class CalenderActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        imageView=findViewById(R.id.photo_view);
        PhotoViewAttacher photoViewAttacher=new PhotoViewAttacher(imageView);
        photoViewAttacher.update();
    }
    public void onBackPressed(){
        finish();
        startActivity(new Intent(CalenderActivity.this,MainActivity.class));
        overridePendingTransition(0, 0);
    }
}
