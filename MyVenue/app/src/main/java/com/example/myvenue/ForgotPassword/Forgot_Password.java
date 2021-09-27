package com.example.myvenue.ForgotPassword;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myvenue.R;
import com.example.myvenue.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgot_Password extends AppCompatActivity {
    FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);

        mAuth=FirebaseAuth.getInstance();

        //forgot password
         Button button1=(Button)findViewById(R.id.button1);
         final EditText emailText=(EditText)findViewById(R.id.emailText);




        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                 String email  = emailText.getText().toString();
                 if(email.isEmpty())
                 {
//                     Toast.makeText(Forgot_Password.this, "Please Enter valid Email ID", Toast.LENGTH_SHORT).show();
                     emailText.setError("Please Enter valid Email ID");
                     emailText.requestFocus();

                 }else{
                     mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                         @Override
                         public void onComplete(@NonNull Task<Void> task) {
                             if(task.isSuccessful()){

                                 startActivity(new Intent(Forgot_Password.this, LoginActivity.class));
                                 Toast.makeText(Forgot_Password.this, "Please check your Email ", Toast.LENGTH_LONG);
                             }else{
                                 String Message =task.getException().getMessage();
                                 Toast.makeText(Forgot_Password.this, "Error Occurred "+ Message, Toast.LENGTH_SHORT);
                             }
                         }
                     });
                 }


            }




        });


        ImageView exit=(ImageView) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Forgot_Password.this, LoginActivity.class));


            }
        });



    }
}
