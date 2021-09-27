package com.example.myvenue.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.myvenue.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ContactUsFragment extends Fragment {
    EditText name, email, phoneNo, message;
    Button submit;
    ProgressBar progressBar;
    FirebaseFirestore firebaseFirestore;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact_us, container, false);

        name = v.findViewById(R.id.name);

        email = v.findViewById(R.id.email);
        phoneNo = v.findViewById(R.id.phone_No);
        message = v.findViewById(R.id.message);
        submit = v.findViewById(R.id.submit);
        progressBar = v.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        firebaseFirestore = FirebaseFirestore.getInstance();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submit();
            }
        });


        return v;


    }

    private void submit() {

        final String Email = email.getText().toString();
        final String Message = message.getText().toString();
        final String Name = name.getText().toString();
        final String PhoneNo = phoneNo.getText().toString();

        if (!TextUtils.isEmpty(name.getText())) {

            if (!TextUtils.isEmpty(phoneNo.getText()) && phoneNo.length() <= 10) {

                if (!TextUtils.isEmpty(email.getText())) {

                    if (!TextUtils.isEmpty(message.getText())) {

                        progressBar.setVisibility(View.VISIBLE);

                        Map<String, Object> contact_us = new HashMap<>();
                        contact_us.put("name", Name);
                        contact_us.put("email", Email);
                        contact_us.put("phone_no", PhoneNo);
                        contact_us.put("message", Message);



                        firebaseFirestore.collection("USERS").document(FirebaseAuth.getInstance().getUid()).collection("USER_DATA")
                                .document("CONTACT_US")
                                .set(contact_us)
                               .addOnCompleteListener(new OnCompleteListener<Void>() {
                                   @Override
                                   public void onComplete(@NonNull Task<Void> task) {
                                       if (task.isSuccessful()){


                                            progressBar.setVisibility(View.GONE);
                                            Toast.makeText(getContext(), "Thank you for Contact us.", Toast.LENGTH_SHORT).show();


                                        } else {
                                            progressBar.setVisibility(View.GONE);
                                            Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });


                    } else {
                        message.requestFocus();
                        message.setError("Please Enter message ");
                        progressBar.setVisibility(View.GONE);
                    }
                } else {
                    email.requestFocus();
                    email.setError("Please Enter email");
                    progressBar.setVisibility(View.GONE);

                }
            } else {

                phoneNo.requestFocus();
                phoneNo.setError("Please enter correct phone no");
                progressBar.setVisibility(View.GONE);


            }
        }else{
            name.requestFocus();
            name.setError("Please enter the name");
            progressBar.setVisibility(View.GONE);
        }


    }


}
