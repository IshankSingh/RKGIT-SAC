package com.torqueblueblood.application.rkgitsac;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends AppCompatActivity {
        private TextView name,gender,email,phone;
        private EditText rollno,event;
        private CardView checkbuttoncardview;
        public static int count=0;
        private FirebaseAuth firebaseAuth;
        private FirebaseDatabase firebaseDatabase;

      public static String username,usergender,useremail,userphone,rollnumber,eventdetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name=findViewById(R.id.eventtxtname);
        gender=findViewById(R.id.eventtxtgender);
        email=findViewById(R.id.eventtxtemail);
        phone=findViewById(R.id.eventtxtphone);

        rollno=findViewById(R.id.eventtxtrollno);
        event=findViewById(R.id.eventtxtevent);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference myreference=firebaseDatabase.getReference(firebaseAuth.getUid());
        myreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserData userData=dataSnapshot.getValue(UserData.class);
                name.setText(userData.getUsername());
                gender.setText(userData.getGender());
                email.setText(userData.getUseremail());
                phone.setText(userData.getUserphone());
                rollno.setText(userData.getRollno());
                event.setText(userData.getEvent_det());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(RegisterActivity.this,"Something wrong with the database please try again after sometime",Toast.LENGTH_LONG).show();
            }
        });


        checkbuttoncardview=findViewById(R.id.checkcardview);
        checkbuttoncardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isEmpty(rollno)){
                    rollno.setError("Please Enter Roll Number");
                }
                else if(isEmpty(event)){
                    event.setError("Please Enter Event Detail");
                }
                else {
                    sendData();
                    if(count==0) {
                        count++;
                        finish();
                        startActivity(new Intent(RegisterActivity.this,RollOut.class));
                        overridePendingTransition(0, 0);

                    }else{
                        finish();
                        startActivity(new Intent(RegisterActivity.this,RollOut.class));
                        overridePendingTransition(0, 0);
                    }
                }
            }
        });

    }
    public void sendData(){
        rollnumber=rollno.getText().toString().trim();
        eventdetail=event.getText().toString();
        username=name.getText().toString();
        usergender=gender.getText().toString().trim();
        useremail=email.getText().toString().toLowerCase().trim();
        userphone=phone.getText().toString().trim();

        DatabaseReference databaseReference=firebaseDatabase.getReference(firebaseAuth.getUid());
        UserData userData = new UserData(username,useremail,userphone,usergender,rollnumber,eventdetail);
        databaseReference.setValue(userData);
    }
    boolean isEmpty(EditText editText){
        CharSequence str=editText.getText().toString();
        return TextUtils.isEmpty(str);
    }
    public void edit(View view){
        finish();
        Intent intent=new Intent(RegisterActivity.this,ProfileEdit.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
    public void onBackPressed(){
        finish();
        startActivity(new Intent(RegisterActivity.this,MainActivity.class));
        overridePendingTransition(0, 0);
    }

}
