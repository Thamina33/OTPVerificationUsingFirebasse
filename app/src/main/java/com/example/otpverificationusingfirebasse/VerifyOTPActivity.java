package com.example.otpverificationusingfirebasse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.awt.font.NumericShaper;

public class VerifyOTPActivity extends AppCompatActivity {


    private EditText inputcode1,inputcode2,inputcode3,inputcode4,inputcode5,inputcode6;
    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_o_t_p);

        TextView textMobile = findViewById(R.id.textMobile);

        textMobile.setText(String.format(
                "+880-%s",getIntent().getStringExtra("mobile")
        ));

        inputcode1 = findViewById(R.id.inutcode1);
        inputcode2 = findViewById(R.id.inutcode2);
        inputcode3 = findViewById(R.id.inutcode3);
        inputcode4 = findViewById(R.id.inutcode4);
        inputcode5 = findViewById(R.id.inutcode5);
        inputcode6 = findViewById(R.id.inutcode6);

        setupOTPInputs();
        final ProgressBar progressBar = findViewById(R.id.progressBar);
        final Button buttonVerify = findViewById(R.id.buttonVerify);
        verificationId = getIntent().getStringExtra("verificationId");

        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputcode1.getText().toString().trim().isEmpty()
                ||inputcode2.getText().toString().trim().isEmpty()
                ||inputcode3.getText().toString().trim().isEmpty()
                ||inputcode4.getText().toString().trim().isEmpty()
                ||inputcode5.getText().toString().trim().isEmpty()
                ||inputcode6.getText().toString().trim().isEmpty()){

                    Toast.makeText(VerifyOTPActivity.this, "Please Enter Valid COde", Toast.LENGTH_SHORT).show();
                    return;
                }

                String code =
                        inputcode1.getText().toString()+
                                inputcode2.getText().toString()+
                                inputcode3.getText().toString()+
                                inputcode4.getText().toString()+
                                inputcode5.getText().toString()+
                                inputcode6.getText().toString();
                if(verificationId !=null){

                    progressBar.setVisibility(View.VISIBLE);
                    buttonVerify.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            verificationId,
                            code
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progressBar.setVisibility(View.GONE);
                                    buttonVerify.setVisibility(View.VISIBLE);
                                    if(task.isSuccessful()){

                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK | intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }
                                    else{

                                        Toast.makeText(VerifyOTPActivity.this, "The Verication Code  Entered was Invalid", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });

                }

            }
        });

        }
    private void setupOTPInputs() {

        inputcode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                if (!charSequence.toString().trim().isEmpty()){

                    inputcode2.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputcode2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                    if (!charSequence.toString().trim().isEmpty()){

                        inputcode3.requestFocus();
                    }

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        inputcode3.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                    if (!charSequence.toString().trim().isEmpty()){

                        inputcode4.requestFocus();
                    }

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        inputcode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                if (!charSequence.toString().trim().isEmpty()){

                    inputcode5.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        inputcode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                if (!charSequence.toString().trim().isEmpty()){

                    inputcode6.requestFocus();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}