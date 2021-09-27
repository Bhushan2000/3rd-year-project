package com.example.myvenue.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.myvenue.R;

import java.util.List;

public class CateringImagesAdapter extends PagerAdapter {

    List<String> cateringPhotos;

    public CateringImagesAdapter(List<String> cateringPhotos) {
        this.cateringPhotos = cateringPhotos;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        ImageView photos =new ImageView(container.getContext());
        Glide.with(container.getContext()).load(cateringPhotos.get(position)).apply(new RequestOptions().placeholder(R.drawable.unload)).into(photos);
        container.addView(photos,0);

        return photos;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView)object);
    }


    @Override
    public int getCount() {
        return cateringPhotos.size();
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
}
