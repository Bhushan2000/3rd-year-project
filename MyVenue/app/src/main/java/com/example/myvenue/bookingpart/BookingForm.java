package com.example.myvenue.bookingpart;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.example.myvenue.Fragment.DatePickerFragment;
import com.example.myvenue.Fragment.TimePickerFragment;
import com.example.myvenue.Model.AddressModel;
import com.example.myvenue.R;
import com.example.myvenue.databasequeries.DBQueries;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class BookingForm extends AppCompatActivity {

    EditText city, locality, flatNo, pinCode, landmark, name, phoneNo, alternatePhoneNo;
    Spinner spinnerCountry;
    Button save;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    private String[] stateList;
    private String selectedState;
    private Dialog loadingDialogue;
    public static EditText date;
    public static EditText time;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        save = findViewById(R.id.submit);

        city = findViewById(R.id.city);
        locality = findViewById(R.id.message);
        flatNo = findViewById(R.id.flat_no);
        pinCode = findViewById(R.id.pincode);
        spinnerCountry = (Spinner) findViewById(R.id.spinner);
        landmark = findViewById(R.id.landMark);
        name = findViewById(R.id.name);
        phoneNo = findViewById(R.id.phone_No);
        alternatePhoneNo = findViewById(R.id.alternate_phone_no);

        date = findViewById(R.id.edit_text_date);
        time = findViewById(R.id.edit_text_time);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Date picker");
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getSupportFragmentManager(), "TimePicker");
            }
        });


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                /////////////
                if (!TextUtils.isEmpty(city.getText())) {

                    if (!TextUtils.isEmpty(locality.getText())) {

                        if (!TextUtils.isEmpty(flatNo.getText())) {
                            if (!TextUtils.isEmpty(pinCode.getText()) && pinCode.length() <= 6) {


                                if (!TextUtils.isEmpty(name.getText())) {

                                    if (!TextUtils.isEmpty(phoneNo.getText()) && phoneNo.length() <= 10) {

                                        if (!TextUtils.isEmpty(date.getText())) {

                                            if (!TextUtils.isEmpty(time.getText())) {

                                                save.setEnabled(true);
                                                save.setTextColor(Color.rgb(255, 255, 255));


                                                //////////////////////////////////

                                                loadingDialogue.show();

                                                final String fullAddress = flatNo.getText().toString() + " " + locality.getText().toString() + " " + landmark.getText().toString() + " " + city.getText().toString() + " " + selectedState;


                                                Map<String, Object> addresses = new HashMap<>();

                                                addresses.put("list_size",  (long) DBQueries.addressModelList.size() + 1);
                                                addresses.put("fullname_"+  String.valueOf((long) DBQueries.addressModelList.size() + 1), name.getText().toString());
                                                addresses.put("address_" +   String.valueOf((long) DBQueries.addressModelList.size() + 1), fullAddress);
                                                addresses.put("pinCode_" +   String.valueOf((long) DBQueries.addressModelList.size() + 1), pinCode.getText().toString());
                                                addresses.put("selected_"+   String.valueOf((long) DBQueries.addressModelList.size() + 1), true);
                                                addresses.put("date_time_" +  String.valueOf((long) DBQueries.addressModelList.size() + 1), date.getText().toString() + "  &  " + time.getText().toString());






                                                if (TextUtils.isEmpty(alternatePhoneNo.getText())) {

                                                    addresses.put("mobile_no_" +  String.valueOf((long) DBQueries.addressModelList.size() + 1), phoneNo.getText().toString());
                                                } else {
                                                    addresses.put("mobile_no_" +  String.valueOf((long) DBQueries.addressModelList.size() + 1), phoneNo.getText().toString() + " or " + alternatePhoneNo.getText().toString());

                                                }




                                                if (DBQueries.addressModelList.size() > 0) {
                                                    addresses.put("selected_" + (selectedState + 1), false);
                                                }

                                                FirebaseFirestore.getInstance().collection("USERS")
                                                        .document(FirebaseAuth.getInstance().getUid()).collection("USER_DATA")
                                                        .document("MY_ADDRESSES")
                                                        .update(addresses).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {

//
                                                            if (TextUtils.isEmpty(alternatePhoneNo.getText())) {

                                                                DBQueries.addressModelList.add(new AddressModel(name.getText().toString(), fullAddress, pinCode.getText().toString(), true, phoneNo.getText().toString()));
                                                            } else {
                                                                DBQueries.addressModelList.add(new AddressModel(name.getText().toString(), fullAddress, pinCode.getText().toString(), true, phoneNo.getText().toString() + alternatePhoneNo.getText().toString()));

                                                            }

                                                            startActivity(new Intent(BookingForm.this, AppointmentConfirmationActivity.class));
                                                            finish();


                                                        } else {
                                                            String error = task.getException().getMessage();
                                                            Toast.makeText(BookingForm.this, error, Toast.LENGTH_SHORT).show();
                                                        }
                                                        loadingDialogue.dismiss();
                                                    }
                                                });


                                                //////////////////////////////////


                                            } else {

                                                time.requestFocus();
                                                time.setError("Please enter the time");

                                            }


                                        } else {

                                            date.requestFocus();
                                            date.setError("Please enter the date");

                                        }


                                    } else {

                                        phoneNo.requestFocus();
                                        phoneNo.setError("Please enter correct phone no");

                                    }
                                } else {
                                    name.requestFocus();
                                    name.setError("Please enter the name");

                                }

                            } else {

                                pinCode.requestFocus();
                                pinCode.setError("Please enter the pin-code");
                            }
                        } else {
                            flatNo.requestFocus();
                            flatNo.setError("Please Enter the Flat No.");
                        }
                    } else {
                        locality.requestFocus();
                        locality.setError("Please enter the locality");
                    }
                } else {
                    city.requestFocus();
                    city.setError("Enter city name");
                }
                /////////////


            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();


        ///////////////////////////////////loading dialogue/////////////////////////////////////////////

        loadingDialogue = new Dialog(BookingForm.this);
        loadingDialogue.setContentView(R.layout.loadingprogressdialogue);
        loadingDialogue.setCancelable(true);
        loadingDialogue.getWindow().setBackgroundDrawable(this.getDrawable(R.drawable.slider_background));
        loadingDialogue.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


/////////////////////////////////////loading dialogue/////////////////////////////////////////////

        stateList = getResources().getStringArray(R.array.india_states);


        ArrayAdapter spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, stateList);


        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCountry.setAdapter(spinnerAdapter);

        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedState = stateList[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void checkInputs() {


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
