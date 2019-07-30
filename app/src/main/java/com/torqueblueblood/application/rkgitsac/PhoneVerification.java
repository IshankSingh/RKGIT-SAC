package com.torqueblueblood.application.rkgitsac;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneVerification extends AppCompatActivity {
    EditText phone;
    private EditText code;
    FirebaseAuth firebaseAuth;
    Button sendnumber,verifycode;
    TextView indiacode;
    String codeSent;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verification);

        phone=findViewById(R.id.vernuberedit);
        code=findViewById(R.id.vercodeedit);
        firebaseAuth=FirebaseAuth.getInstance();
        sendnumber=findViewById(R.id.sendcodeverbutton);
        verifycode=findViewById(R.id.verifyphonebutton);
        indiacode=findViewById(R.id.india_code);

       sendnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendVerificationCode();

            }
        });
        verifycode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verifySignInCode();

            }
        });
    }

    public  void verifySignInCode(){
        progressDialog.setMessage("Verifying...");
        progressDialog.show();

        String codeno=code.getText().toString();
        if(codeno.isEmpty()){
            progressDialog.dismiss();
            code.setError("OTP Required");
            code.requestFocus();
            return;
        }
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, codeno);
        signInWithPhoneAuthCredential(credential);

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //here you can open new activity
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Verification Successful", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(PhoneVerification.this, RegistrationActivity.class);
                            startActivity(i);
                            overridePendingTransition(0, 0);

                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Incorrect Verification Code", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                });
}

    public void sendVerificationCode() {

            String phoneNumber = phone.getText().toString();
            String phn="+91"+phoneNumber;

            if (phoneNumber.isEmpty()) {
                phone.setError("Phone Number is Required");
                phone.requestFocus();
                return;
            }

            if (phoneNumber.length() < 10 || phoneNumber.length()>10) {
                phone.setError("Please enter a valid phone");
                phone.requestFocus();
                return;
            }

        phone.setVisibility(View.GONE);
        sendnumber.setVisibility(View.GONE);
        verifycode.setVisibility(View.VISIBLE);
        code.setVisibility(View.VISIBLE);
        indiacode.setVisibility(View.GONE);

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                    phn,        // Phone number to verify
                    60,                 // Timeout duration
                    TimeUnit.SECONDS,   // Unit of timeout
                    this,               // Activity (for callback binding)
                    mCallbacks);        // OnVerificationStateChangedCallbacks

        }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            codeSent =s;
        }
    };
    public void onBackPressed(){
        finish();
        startActivity(new Intent(PhoneVerification.this,LoginActivity.class));
        overridePendingTransition(0, 0);
    }
}
