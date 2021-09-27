package com.example.myvenue.login;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myvenue.R;
import com.example.myvenue.facebooklogin.Facebook_Activity;
import com.example.myvenue.maindashboard.DashBoardActivity;
import com.example.myvenue.signup.Sign_Up;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    //Initialize variables
    EditText emailTxt;
    EditText  passwordTxt;
    Button sign_in;
    Button Sign_In_With_Facebook;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    TextView sign_in_textView;
    ProgressDialog progressDialog;
    TextView Forgot_Password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        emailTxt=findViewById(R.id.emailTxt);
        passwordTxt=findViewById(R.id.passwordText);
        sign_in=findViewById(R.id.sign_in);



        firebaseAuth= FirebaseAuth.getInstance();



        sign_in_textView=(TextView) findViewById(R.id.sign_in_textView);
        sign_in_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, Sign_Up.class));
            }
        });
        Forgot_Password= findViewById(R.id.Forgot_Password);
        Forgot_Password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"you clicked on Forgot Password",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, com.example.myvenue.ForgotPassword.Forgot_Password.class));
            }
        });
        Sign_In_With_Facebook = findViewById(R.id.Sign_In_With_Facebook);

        Sign_In_With_Facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, Facebook_Activity.class));
            }
        });














        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String Email = emailTxt.getText().toString();
                final String Password = passwordTxt.getText().toString();

                if(Email.isEmpty()){
                    emailTxt.setError("Please enter the valid email address");
                    emailTxt.requestFocus();

                }else if(Password.isEmpty()){
                    passwordTxt.setError("Please enter the the password");
                    passwordTxt.requestFocus();
                }else{


                    //Initialize  progressbar
                    progressDialog = new ProgressDialog(LoginActivity.this);
                    //show Progressbar
                    progressDialog.show();

                    //set content view
                    progressDialog.setContentView(R.layout.progress_dialog);
                    //set transparent background
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);


                    FirebaseAuth.getInstance();
                    firebaseAuth.signInWithEmailAndPassword(emailTxt.getText().toString(),  passwordTxt.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                startActivity(new Intent(LoginActivity.this, DashBoardActivity.class));

                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }

                        }
                    });

                }






            }
        });











    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no,null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                                LoginActivity.super.onBackPressed();
                    }
                }).create().show();




    }
}
