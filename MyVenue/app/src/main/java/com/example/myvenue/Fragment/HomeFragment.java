package com.example.myvenue.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myvenue.itemlist.ItemsListActivity;
import com.example.myvenue.R;


public class HomeFragment extends Fragment {

    ViewFlipper viewFlipper;
    LinearLayout music,hall,decoration,catering;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home,container,false);


        viewFlipper = view.findViewById(R.id.flipper);
        int images [] = {R.drawable.siddhivinayak1,R.drawable.siddhivinayak2,R.drawable.siddhivinayak3};



        for (int image : images){
            FlipperImage(image);
        }


        catering = view.findViewById(R.id.catering);
        hall = view.findViewById(R.id.hall);
        decoration = view.findViewById(R.id.decoration);
        music = view.findViewById(R.id.music);

        catering.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), ItemsListActivity.class);
                intent.putExtra("goto","catering");
                startActivity(intent);







            }
        });

        hall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//

                Intent intent = new Intent(getContext(), ItemsListActivity.class);
                intent.putExtra("goto","hall");
                startActivity(intent);

            }
        });

        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ItemsListActivity.class);
                intent.putExtra("goto","music");
                startActivity(intent);


            }
        });

        decoration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ItemsListActivity.class);
                intent.putExtra("goto","decoration");
                startActivity(intent);
            }
        });



        return view;

    }
        public void FlipperImage(int image){
        ImageView imageView = new ImageView(getContext());
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(2000);
        viewFlipper.setAutoStart(true);
        viewFlipper.setInAnimation(getContext(),android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getContext(),android.R.anim.slide_out_right);

    }

}
