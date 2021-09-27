package com.example.myvenue.onboarding;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myvenue.login.LoginActivity;
import com.example.myvenue.R;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class SliderActivity extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance("Music",
                "Make Your Events Easy", R.drawable.music, Color.parseColor("#8E078E")));

        addSlide(AppIntroFragment.newInstance("Hall",
                "The Best Choice Venues in the town", R.drawable.hall, Color.parseColor("#A56507")));

        addSlide(AppIntroFragment.newInstance("Decoration",
                "The world class decorations", R.drawable.decoration, Color.parseColor("#6076E4")));

        addSlide(AppIntroFragment.newInstance("Catering",
                 "The Standard Cooking Chiefs", R.drawable.catering, Color.parseColor("#027C6C")));


        showStatusBar(false);



    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
                startActivity(new Intent(this, LoginActivity.class));
                setFadeAnimation();
                finish();

    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
         setZoomAnimation();
        startActivity(new Intent(this,LoginActivity.class));
        finish();

    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
//        Toast.makeText(this, "On Slide Changed", Toast.LENGTH_SHORT).show();
        setDepthAnimation();

    }
}