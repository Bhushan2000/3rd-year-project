package com.example.myvenue.itemlist;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myvenue.R;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemViewHolder> {
    private List<ItemModel> itemModelList;
    private int lastPosition = -1;

    public ItemListAdapter(List<ItemModel> itemModelList) {
        this.itemModelList = itemModelList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        String title = itemModelList.get(position).getName();
        String address = itemModelList.get(position).getAddress();
        String productId = itemModelList.get(position).getProductID();
        boolean book = itemModelList.get(position).isAvailability();
        String type = itemModelList.get(position).getType();


        holder.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobileNo = itemModelList.get(position).getMobileNo();
                String call = "tel:" + mobileNo.trim();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(call));
                v.getContext().startActivity(intent);


            }
        });
        holder.setData(productId,title, address, book,type);

        if (lastPosition < position) {
            Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.fade_in);
            holder.itemView.setAnimation(animation);
            lastPosition = position;

        }


    }

    @Override
    public int getItemCount() {
        return itemModelList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView cateringName;
        private TextView cateringAddress;
        private ImageButton phone;
        private TextView available;



        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            cateringName = itemView.findViewById(R.id.name);
            cateringAddress = itemView.findViewById(R.id.address);
            phone = itemView.findViewById(R.id.phone);
            available = itemView.findViewById(R.id.available);


        }

        private void setData(String productId, String title, String address, boolean book,String type ) {
            cateringName.setText(title);
            cateringAddress.setText(address);

            if (book){


                available.setText("Book");
                available.setTextColor(Color.parseColor("#000000"));


            }else{


                available.setTextColor(Color.parseColor("#FF3030"));

                available.setText("Unavailable");

            }





            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (book){
                        Intent intent = new Intent(itemView.getContext(), ItemDetailsActivity.class);
                        intent.putExtra("product_ID", productId);
                        intent.putExtra("type", type);
                        itemView.getContext().startActivity(intent);
                    }else{
                        Toast.makeText(itemView.getContext(), "Unavailable", Toast.LENGTH_SHORT).show();
                    }

                }
            });


        }


    }

}
