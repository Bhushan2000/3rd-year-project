package com.example.myvenue.databasequeries;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;


import com.example.myvenue.bookingpart.AppointmentConfirmationActivity;
import com.example.myvenue.bookingpart.BookingsActivity;
import com.example.myvenue.Model.AddressModel;
import com.example.myvenue.Model.BookingsModel;
import com.example.myvenue.itemlist.ItemModel;
import com.example.myvenue.util.CustomView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DBQueries {
    public static FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    public static List<AddressModel> addressModelList = new ArrayList<>();
    public static List<BookingsModel> bookingsModelList = new ArrayList<>();
    private static final String TAG = "DBQueries";

    public static void loadAddresses(final Context context, final Dialog loadingDialogue) {
        addressModelList.clear();


        firebaseFirestore.collection("USERS").document(Objects.requireNonNull(FirebaseAuth.getInstance().getUid())).collection("USER_DATA").document("MY_ADDRESSES")
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {


                    Intent deliveryIntent;

                    if ((long) task.getResult().get("list_size") == 0) {
                        deliveryIntent = new Intent(context, AppointmentConfirmationActivity.class);
                        deliveryIntent.putExtra("INTENT", "deliveryIntent");
                    } else {

                        for (long x = 1; x < (long) task.getResult().get("list_size") + 1; x++) {

                            addressModelList.add(new AddressModel(task.getResult().get("fullname_" + x).toString()
                                    , task.getResult().get("address_" + x).toString()
                                    , task.getResult().get("pinCode_" + x).toString()
                                    , (boolean) task.getResult().get("selected_" + x)
                                    , task.getResult().get("mobile_no_" + x).toString()));


                        }

                        deliveryIntent = new Intent(context, AppointmentConfirmationActivity.class);


                    }
                    context.startActivity(deliveryIntent);

                } else {
                    String error = Objects.requireNonNull(task.getException()).getMessage();
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                }

                loadingDialogue.dismiss();
            }
        });
    }

    public static void loadBookings(Context context, final Dialog loadingDialogue) {
        bookingsModelList.clear();

        firebaseFirestore.collection("USERS").document(FirebaseAuth.getInstance().getUid()).collection("USER_DATA")
                .document("MY_ADDRESSES").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    CustomView.hideDialog();
                    for (long x = 1; x < (long) task.getResult().get("list_size") + 1; x++) {

                        bookingsModelList.add(new BookingsModel(task.getResult().get("fullname_" + x).toString(),
                                task.getResult().get("date_time_" + x).toString()));
                    }
//
//

                    BookingsActivity.bookingsAdapter.notifyDataSetChanged();

                } else {
                    String error = task.getException().getMessage();
                    Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                    CustomView.hideDialog();
                }

            }
        });
    }

    public static void clearData() {
        addressModelList.clear();

        bookingsModelList.clear();

    }


}
