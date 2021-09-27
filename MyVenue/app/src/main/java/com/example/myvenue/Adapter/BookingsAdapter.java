package com.example.myvenue.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myvenue.Model.BookingsModel;
import com.example.myvenue.R;

import java.util.List;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.BookingsViewHolder> {

    private List<BookingsModel> bookingsModelList;
    private int lastPosition = -1;


    public BookingsAdapter(List<BookingsModel> bookingsModelList) {
        this.bookingsModelList = bookingsModelList;
    }

    @NonNull
    @Override
    public BookingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_layout, parent, false);

        return new BookingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingsViewHolder holder, int position) {
        holder.name.setText(bookingsModelList.get(position).getName());
        holder.date_time.setText(bookingsModelList.get(position).getDate_time());

        if (lastPosition < position) {
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.fade_in);
            holder.itemView.setAnimation(animation);
            lastPosition = position;

        }

    }

    @Override
    public int getItemCount() {
        return bookingsModelList.size();
    }

    public class BookingsViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView date_time;


        public BookingsViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.Booking_user_name);
            date_time = itemView.findViewById(R.id.date_time);
        }
    }
}
