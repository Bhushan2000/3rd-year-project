package com.example.myvenue.bookingpart;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.ViewGroup;

import com.example.myvenue.Adapter.BookingsAdapter;
import com.example.myvenue.R;
import com.example.myvenue.databasequeries.DBQueries;
import com.example.myvenue.util.CustomView;

public class BookingsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Dialog loadingDialogue;
    public static BookingsAdapter bookingsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings);
        recyclerView = findViewById(R.id.booking_recyclerview);



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        CustomView.showDialog(this);



        if (DBQueries.bookingsModelList.size() == 0){
            DBQueries.loadBookings(this,loadingDialogue);
            CustomView.hideDialog();
        }
        else{
            CustomView.hideDialog();
        }

        bookingsAdapter = new BookingsAdapter(DBQueries.bookingsModelList);
        recyclerView.setAdapter(bookingsAdapter);
        bookingsAdapter.notifyDataSetChanged();
    }
}