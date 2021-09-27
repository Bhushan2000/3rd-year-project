package com.example.myvenue.itemlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.myvenue.R;
import com.example.myvenue.util.CustomView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ItemsListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ItemListAdapter itemListAdapter;
    String message;
    private List<ItemModel> itemModelList = new ArrayList<>();
    public FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);

        Bundle bundle = getIntent().getExtras();
        message = bundle.getString("goto");


        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this);
        linearLayout.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayout);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayout.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        CustomView.showDialog(this);


        switch (message) {

            case "catering":

                itemModelList.clear();

                firebaseFirestore.collection("CATERING").document("eubsbVIgbokrA5ppTfAP").get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    CustomView.hideDialog();

                                    for (long x = 1; x < (long) task.getResult().get("list_size") + 1; x++) {
                                        itemModelList.add(new ItemModel(
                                                task.getResult().get("name_" + x).toString(),
                                                task.getResult().get("address_" + x).toString(),
                                                task.getResult().get("catering_ID_" + x).toString()
                                                , task.getResult().get("contact_no_" + x).toString()
                                                , (boolean) task.getResult().get("availablity_" + x)
                                                , message));
                                    }


                                } else {
                                    String error = task.getException().getMessage();
                                    Toast.makeText(ItemsListActivity.this, error, Toast.LENGTH_SHORT).show();
                                    CustomView.hideDialog();

                                }


                                itemListAdapter = new ItemListAdapter(itemModelList);
                                recyclerView.setAdapter(itemListAdapter);
                                itemListAdapter.notifyDataSetChanged();


                            }
                        });


                break;

            case "hall":
                itemModelList.clear();

                firebaseFirestore.collection("HALLS").document("4cFUxS2EaSTU5w9ob9qe").get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
//
                                    CustomView.hideDialog();

                                    ////////////////

                                    for (long x = 1; x < (long) task.getResult().get("list_size") + 1; x++) {


                                        itemModelList.add(new ItemModel(task.getResult().get("name_" + x).toString()
                                                , task.getResult().get("address_" + x).toString()
                                                , task.getResult().get("Hall_ID_" + x).toString()
                                                , task.getResult().get("contact_no_" + x).toString()
                                                , (boolean) task.getResult().get("availablity_" + x)
                                                , message));


                                    }


                                } else {
                                    String error = task.getException().getMessage();
                                    Toast.makeText(ItemsListActivity.this, error, Toast.LENGTH_SHORT).show();
                                    CustomView.hideDialog();
                                }
                                itemListAdapter = new ItemListAdapter(itemModelList);
                                recyclerView.setAdapter(itemListAdapter);
                                itemListAdapter.notifyDataSetChanged();
                            }
                        });

                break;
            case "decoration":
                itemModelList.clear();

                firebaseFirestore.collection("DECORATION").document("p7oDAmcMwjWUNamdRmPn").get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    CustomView.hideDialog();

                                    ////////////////

                                    for (long x = 1; x < (long) task.getResult().get("list_size") + 1; x++) {
                                        itemModelList.add(new ItemModel(

                                                task.getResult().get("name_" + x).toString(),
                                                task.getResult().get("address_" + x).toString(),
                                                task.getResult().get("decoration_ID_" + x).toString()


                                                , task.getResult().get("contact_no_" + x).toString()
                                                , (boolean) task.getResult().get("availablity_" + x)
                                                , message

                                        ));

                                    }


                                    /////////////


                                } else {
                                    String error = task.getException().getMessage();
                                    Toast.makeText(ItemsListActivity.this, error, Toast.LENGTH_SHORT).show();
                                    CustomView.hideDialog();

                                }
                                itemListAdapter = new ItemListAdapter(itemModelList);
                                recyclerView.setAdapter(itemListAdapter);
                                itemListAdapter.notifyDataSetChanged();


                            }
                        });


                break;

            case "music":
                itemModelList.clear();

                firebaseFirestore.collection("MUSIC").document("cib3xHIVN4NpROr4S9JZ").get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    CustomView.hideDialog();

                                    ////////////////

                                    for (long x = 1; x < (long) task.getResult().get("list_size") + 1; x++) {
                                        itemModelList.add(new ItemModel(task.getResult().get("name_" + x).toString(),
                                                task.getResult().get("address_" + x).toString()
                                                , task.getResult().get("music_ID_" + x).toString()
                                                , task.getResult().get("contact_no_" + x).toString()
                                                , (boolean) task.getResult().get("availablity_" + x)
                                                , message

                                        ));

                                    }


                                } else {
                                    String error = task.getException().getMessage();
                                    Toast.makeText(ItemsListActivity.this, error, Toast.LENGTH_SHORT).show();
                                    CustomView.hideDialog();
                                }
                                itemListAdapter = new ItemListAdapter(itemModelList);
                                recyclerView.setAdapter(itemListAdapter);
                                itemListAdapter.notifyDataSetChanged();

                            }
                        });


                break;

            default:
                break;


        }

    }
}