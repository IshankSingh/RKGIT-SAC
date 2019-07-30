package com.torqueblueblood.application.rkgitsac;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private EditText login_user_name,login_user_password;
 private TextView signup,forgot_password;
    private Button login_button;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_user_name=(EditText)findViewById(R.id.loginname);
        login_user_password=(EditText)findViewById(R.id.loginpassword);
        forgot_password=(TextView) findViewById(R.id.forgotpassword);

        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user !=null){
            finish();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }


        signup=(TextView)findViewById(R.id.loginregistertext);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(LoginActivity.this,Slide.class);
                startActivity(i);
            }
        });
        login_button=(Button)findViewById(R.id.loginbutton);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(login_user_name)) {
                    login_user_name.setError("Please enter email");
                } else if (isEmpty(login_user_password)) {
                    login_user_password.setError("Please enter password");
                } else {

                    validate(login_user_name.getText().toString(), login_user_password.getText().toString());
                }
            }
        });
        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent i=new Intent(LoginActivity.this,ForgotpasswordActivity.class);
               startActivity(i);
                overridePendingTransition(0, 0);
            }
        });
    }
    public void validate(String loginname,String password){
        progressDialog.setMessage("Loading....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(loginname,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                   progressDialog.dismiss();
                   Toast.makeText(LoginActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                      finish();
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    overridePendingTransition(0, 0);}
                else{
                    Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }

       boolean isEmpty(EditText editText){
        CharSequence str=editText.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public void onBackPressed(){
       finish();
    }

}
