package com.torqueblueblood.application.rkgitsac;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotpasswordActivity extends AppCompatActivity {
    private EditText reset_email;
    private Button reset_button;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpassword);
        reset_email=(EditText)findViewById(R.id.reset_email);
        reset_button=(Button)findViewById(R.id.resetpasswordbutton);
        firebaseAuth=FirebaseAuth.getInstance();

        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String useremail = reset_email.getText().toString().trim();

                if(useremail.equals("")){
                    Toast.makeText(ForgotpasswordActivity.this,"Please enter you registered email",Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ForgotpasswordActivity.this,"Password reset email sent",Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(ForgotpasswordActivity.this,LoginActivity.class));
                            }else{
                                Toast.makeText(ForgotpasswordActivity.this,"Error in sending email",Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
                }
            }
        });
    }
    public void onBackPressed(){
        finish();
        startActivity(new Intent(ForgotpasswordActivity.this,LoginActivity.class));
        overridePendingTransition(0, 0);
    }
}
