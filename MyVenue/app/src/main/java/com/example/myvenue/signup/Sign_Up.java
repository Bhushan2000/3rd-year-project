package com.example.myvenue.signup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myvenue.login.LoginActivity;
import com.example.myvenue.R;
import com.example.myvenue.maindashboard.DashBoardActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sign_Up extends AppCompatActivity {

    EditText emailText;
    EditText name;
    EditText  passwordText;
    Button Sign_Up;
    Button Sign_In;
    FirebaseAuth firebaseAuth;

    ProgressDialog progressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);



        emailText=findViewById(R.id.emailTxt);
        passwordText=findViewById(R.id.passwordText);
        Sign_Up=findViewById(R.id.Sign_Up);
        Sign_In=findViewById(R.id.Sign_In);
        name=findViewById(R.id.name);





        Sign_In.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Sign_Up.this, LoginActivity.class));

            }
        });



        firebaseAuth= FirebaseAuth.getInstance();
        Sign_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Email = emailText.getText().toString();
                final String Password = passwordText.getText().toString();
                final String Name = name.getText().toString();
                if(Name.isEmpty()){
                    emailText.setError("Please enter the name");
                    emailText.requestFocus();

                }else if(Email.isEmpty()){
                    emailText.setError("Please enter the valid email address");
                    emailText.requestFocus();

                }else if(Password.isEmpty()){
                    passwordText.setError("Please enter the the password");
                    passwordText.requestFocus();
                }else if(!Email.isEmpty() && !Password.isEmpty()) {
                    //Initialize  progressbar
                    progressDialog = new ProgressDialog(Sign_Up.this);
                    //show Progressbar
                    progressDialog.show();

                    //set content view
                    progressDialog.setContentView(R.layout.progress_dialog);
                    //set transparent background
                    progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

//                    firebaseAuth.createUserWithEmailAndPassword(emailText.getText().toString(),passwordText.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//
//                            progressDialog.dismiss();
//                            if(task.isSuccessful()){
//                                Toast.makeText(Sign_Up.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
//                            }else{
//                                progressDialog.dismiss();
//                                Toast.makeText(Sign_Up.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//
//                        }
//                    });







                    firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                final Map<String, Object> userdata = new HashMap<>();
                                userdata.put("fullname",  Name);
                                FirebaseFirestore.getInstance().collection("USERS").document(firebaseAuth.getUid())
                                        .set(userdata)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                if (task.isSuccessful()) {

                                                    CollectionReference userDataReference =  FirebaseFirestore.getInstance().collection("USERS").document(firebaseAuth.getUid()).collection("USER_DATA");

                                                    ////////////////////Maps////////////////////////

                                                    Map<String, Object> contactUs = new HashMap<>();
                                                    contactUs.put("list_size", (long) 0);
                                                    Map<String, Object> addresses = new HashMap<>();
                                                    addresses.put("list_size", (long) 0);




                                                    ////////////////////Maps////////////////////////


                                                    final List<String> documentNames = new ArrayList<>();
                                                    documentNames.add("CONTACT_US");
                                                    documentNames.add("MY_ADDRESSES");



                                                    List<Map<String, Object>> documentsFields = new ArrayList<>();
                                                    documentsFields.add(contactUs);
                                                    documentsFields.add(addresses);



                                                    for (int x = 0; x < documentNames.size(); x++) {
                                                        final int finalX = x;
                                                        userDataReference.document(documentNames.get(x))
                                                                .set(documentsFields.get(x)).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    if (finalX == documentNames.size() - 1) {
                                                                        goMainScreen();
                                                                    }

                                                                } else {

                                                                    progressDialog.dismiss();
                                                                    Sign_Up.setEnabled(true);
                                                                    Sign_Up.setTextColor(Color.rgb(255, 255, 255));
                                                                    String error = task.getException().getMessage();
                                                                    Toast.makeText(Sign_Up.this, error, Toast.LENGTH_LONG).show();
                                                                }

                                                            }
                                                        });
                                                    }





                                                } else {


                                                    String error = task.getException().getMessage();
                                                    Toast.makeText( Sign_Up.this, error, Toast.LENGTH_LONG).show();

                                                }

                                            }
                                        });


                            } else {
                                //set progressbar invisible Here

                                progressDialog.dismiss();
                                Sign_Up.setEnabled(true);

                                Sign_Up.setTextColor(Color.rgb(255, 255, 255));

                                String error = task.getException().getMessage();
                                Toast.makeText(Sign_Up.this, error, Toast.LENGTH_LONG).show();
                            }


                        }
                    });





                }






            }
        });










    }

    private void goMainScreen() {

            Intent intent = new Intent(Sign_Up.this, DashBoardActivity.class);

            startActivity(intent);

            finish();

    }

}





