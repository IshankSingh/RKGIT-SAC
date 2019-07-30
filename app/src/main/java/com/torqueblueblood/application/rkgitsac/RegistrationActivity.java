package com.torqueblueblood.application.rkgitsac;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.swing.text.html.HTML;

public class RegistrationActivity extends AppCompatActivity {

    private static String Tag="Registration Activty";
    private EditText user_name,user_email,user_password,user_phone;
    private Button register_button;
    private TextView login_textview;
    private FirebaseAuth firebaseAuth;
    String uname,password,email,phone,radiobutton="Male";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIview();
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validate()){
                    //upload data to the database
                    String useremail=user_email.getText().toString().trim();
                    String userpassword=user_password.getText().toString().trim();
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();

                    firebaseAuth.createUserWithEmailAndPassword(useremail,userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                progressDialog.dismiss();
                                Toast.makeText(RegistrationActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                               sendEmailVerification();
                               }else{
                                progressDialog.dismiss();
                                Log.d(Tag,"User not Created");
                                Toast.makeText(RegistrationActivity.this,"Registration Failed",Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
        login_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                overridePendingTransition(0, 0);
            }
        });
    }

    private void setupUIview(){

        user_name=(EditText)findViewById(R.id.registernametext);
        user_email=(EditText)findViewById(R.id.registeremailtext);
        user_password=(EditText)findViewById(R.id.registerpassword);
        user_phone=(EditText)findViewById(R.id.registernumbertext);
        register_button=(Button)findViewById(R.id.registerbutton);
        login_textview=(TextView)findViewById(R.id.registerlogintextview);



    }

    private Boolean validate(){
        Boolean result=false;

         uname=user_name.getText().toString();
         password=user_password.getText().toString();
         email=user_email.getText().toString();
         phone=user_phone.getText().toString();



        if(uname.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()){
            Toast.makeText(this,"Please Enter all the details",Toast.LENGTH_SHORT).show();
        }else{
            result =true;
        }
        return result;
    }
    private void sendEmailVerification(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        sendUserData();
                        Toast.makeText(RegistrationActivity.this,"Successfully registered, Verification email is send",Toast.LENGTH_LONG).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                        overridePendingTransition(0, 0);
                    }else{
                        Log.d(Tag,"Email Verification Failed");
                        Toast.makeText(RegistrationActivity.this,"Verification email has'nt been sent",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserData(){

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference myRef=firebaseDatabase.getReference(firebaseAuth.getUid());
        UserData userData=new UserData(uname,email,phone,radiobutton);
        myRef.setValue(userData);

    }

    public void onRadioButtonClicked(View view){
        boolean checked=((RadioButton)view).isChecked();
        switch (view.getId()){
            case R.id.radioMale:
                if (checked){
                    radiobutton="Male";
                    break;
                }
            case R.id.radioFemale:
                if(checked) {
                    radiobutton = "Female";
                    break;
                }
        }

    }
    public void onBackPressed(){
        finish();
        Intent intent=new Intent(RegistrationActivity.this,PhoneVerification.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}