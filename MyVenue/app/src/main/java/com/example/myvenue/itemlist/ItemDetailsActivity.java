package com.example.myvenue.itemlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myvenue.Adapter.CateringImagesAdapter;
import com.example.myvenue.R;
import com.example.myvenue.bookingpart.BookingForm;
import com.example.myvenue.util.CustomView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import java.util.ArrayList;
import java.util.List;

public class ItemDetailsActivity extends AppCompatActivity {
    TextView title;
    TextView description;
    TextView contactNo;
    TextView address;
    String productID,type;
    FirebaseFirestore firebaseFirestore;
    public static Button button_catering;
    ViewPager viewPager;
    SpringDotsIndicator springDotsIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemetails);

        button_catering = findViewById(R.id.button_catering);

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        address = findViewById(R.id.address);
        contactNo = findViewById(R.id.contact_no);

        viewPager = findViewById(R.id.products_images_viewpager);

        springDotsIndicator = findViewById(R.id.worm_dot);


        firebaseFirestore = FirebaseFirestore.getInstance();
        final List<String> productsImages = new ArrayList<>();
        productID = getIntent().getStringExtra("product_ID");
        type = getIntent().getStringExtra("type");




        switch (type){

            case "catering":
                CustomView.showDialog(this);

                firebaseFirestore.collection("CATERING_DETAILS").document(productID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            CustomView.hideDialog();


                            for (long x = 1; x < (long) task.getResult().get("no_of_caterer_images") + 1; x++) {

                                productsImages.add(task.getResult().get("photo_" + x).toString());
                            }
                            CateringImagesAdapter cateringImagesAdapter = new CateringImagesAdapter(productsImages);
                            viewPager.setAdapter(cateringImagesAdapter);
                            springDotsIndicator.setViewPager(viewPager);


                            title.setText(task.getResult().get("title").toString());
                            address.setText(task.getResult().get("address").toString());
                            description.setText(task.getResult().get("description").toString());
                            contactNo.setText(task.getResult().get("contactNo").toString());
                            button_catering.setText(task.getResult().get("available").toString());


                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(ItemDetailsActivity.this, error, Toast.LENGTH_SHORT).show();
                            CustomView.hideDialog();

                        }
                    }
                });


                break;

            case "hall":
                CustomView.showDialog(this);


                firebaseFirestore.collection("HALL_DETAILS").document(productID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    CustomView.hideDialog();

                                    ////////////////


                                    for (long x = 1; x < (long) task.getResult().get("no_of_hall_images") + 1; x++) {

                                        productsImages.add(task.getResult().get("photo_" + x).toString());
                                    }
                                    CateringImagesAdapter cateringImagesAdapter = new CateringImagesAdapter(productsImages);
                                    viewPager.setAdapter(cateringImagesAdapter);
                                    springDotsIndicator.setViewPager(viewPager);


                                    title.setText(task.getResult().get("title").toString());
                                    address.setText(task.getResult().get("address").toString());
                                    description.setText(task.getResult().get("description").toString());
                                    contactNo.setText(task.getResult().get("contactNo").toString());
                                    button_catering.setText(task.getResult().get("available").toString());



                                    /////////////


                                } else {
                                    String error = task.getException().getMessage();
                                    Toast.makeText(ItemDetailsActivity.this, error, Toast.LENGTH_SHORT).show();
                                    CustomView.hideDialog();

                                }


                            }
                        });

//


                break;
            case "decoration":

                CustomView.showDialog(this);

                firebaseFirestore.collection("DECORATION_DETAILS").document(productID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            CustomView.hideDialog();


                            for (long x = 1; x < (long) task.getResult().get("no_of_decoration_images") + 1; x++) {

                                productsImages.add(task.getResult().get("photo_" + x).toString());
                            }
                            CateringImagesAdapter cateringImagesAdapter = new CateringImagesAdapter(productsImages);
                            viewPager.setAdapter(cateringImagesAdapter);
                            springDotsIndicator.setViewPager(viewPager);


                            title.setText(task.getResult().get("title").toString());
                            address.setText(task.getResult().get("address").toString());
                            description.setText(task.getResult().get("description").toString());
                            contactNo.setText(task.getResult().get("contactNo").toString());
                            button_catering.setText(task.getResult().get("available").toString());


                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(ItemDetailsActivity.this, error, Toast.LENGTH_SHORT).show();
                            CustomView.hideDialog();

                        }
                    }
                });




                break;

            case "music":
                CustomView.showDialog(this);

                firebaseFirestore.collection("MUSIC_DETAILS").document(productID).get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    CustomView.hideDialog();

                                    ////////////////


                                    for (long x = 1; x < (long) task.getResult().get("no_of_music_images") + 1; x++) {

                                        productsImages.add(task.getResult().get("photo_" + x).toString());
                                    }
                                    CateringImagesAdapter cateringImagesAdapter = new CateringImagesAdapter(productsImages);
                                    viewPager.setAdapter(cateringImagesAdapter);
                                    springDotsIndicator.setViewPager(viewPager);


                                    title.setText(task.getResult().get("title").toString());
                                    address.setText(task.getResult().get("address").toString());
                                    description.setText(task.getResult().get("description").toString());
                                    contactNo.setText(task.getResult().get("contactNo").toString());
                                    button_catering.setText(task.getResult().get("available").toString());



                                    /////////////


                                } else {
                                    String error = task.getException().getMessage();
                                    Toast.makeText(ItemDetailsActivity.this, error, Toast.LENGTH_SHORT).show();
                                    CustomView.hideDialog();

                                }


                            }
                        });

//


                break;

            default:
                break;


        }



















        button_catering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(ItemDetailsActivity.this, BookingForm.class));

            }
        });

    }
}