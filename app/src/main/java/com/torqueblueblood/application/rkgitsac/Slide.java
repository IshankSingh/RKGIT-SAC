package com.torqueblueblood.application.rkgitsac;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class Slide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        TextView textView = (TextView)findViewById(R.id.textView);
        Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(this,
                R.anim.slide);
        textView.startAnimation(hyperspaceJumpAnimation);
        hyperspaceJumpAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                Intent i=new Intent(Slide.this,PhoneVerification.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}