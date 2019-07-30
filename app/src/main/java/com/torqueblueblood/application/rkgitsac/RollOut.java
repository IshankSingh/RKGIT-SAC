package com.torqueblueblood.application.rkgitsac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RollOut extends AppCompatActivity {
    private TextView name,gender,email,phone,rollno,event;

    String username,usergender,useremail,userphone,rollnumber,eventdetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll_out);
        RegisterActivity registerActivity=new RegisterActivity();

        name=findViewById(R.id.checkname);
        gender=findViewById(R.id.checkgender);
        email=findViewById(R.id.checkemail);
        phone=findViewById(R.id.checkphone);
        rollno=findViewById(R.id.checkrollno);
        event=findViewById(R.id.checkevent);

        username=registerActivity.username;
        usergender=registerActivity.usergender;
        useremail=registerActivity.useremail;
        userphone=registerActivity.userphone;
        rollnumber=registerActivity.rollnumber;
        eventdetail=registerActivity.eventdetail;

        name.setText(username);
        gender.setText(usergender);
        email.setText(useremail);
        phone.setText(userphone);
        rollno.setText(rollnumber);
        event.setText(eventdetail);
    }
    public void onBackPressed(){
        finish();
        startActivity(new Intent(RollOut.this,MainActivity.class));
    }
    public void checkedit(View view){
        finish();
        startActivity(new Intent(RollOut.this,ProfileEdit.class));

    }
    public void checksubmit(View view){
        finish();
        Toast.makeText(RollOut.this, "Thanks for your Registration", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(RollOut.this,MainActivity.class));
        }

}
