package com.example.myvenue.Fragment;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myvenue.BuildConfig;
import com.example.myvenue.R;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;


public class AboutFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        Element legalElement = new Element();
        legalElement.setTitle("Legal");

        View aboutPage = new AboutPage(getContext())
                .isRTL(false)
                .setImage(R.drawable.my)
                .setDescription(getString(R.string.about_us_description))
                .addItem(new Element("Version " + BuildConfig.VERSION_NAME, R.drawable.ic_info_black_24dp))
                .addGroup("Connect with us")
                .addEmail("waghdharebhushan71@gmail.com")
                .addWebsite("myvenue.epizy.com")
                .addGitHub("Bhushan2000")
                .addPlayStore(getContext().getPackageName())
                .addFacebook(getString(R.string.FACEBOOK_ID))
                .addTwitter(getString(R.string.TWITTER_ID))
                .addYoutube(String.valueOf(R.string.YOUTUBE_ID))
                .addItem(createCopyright())
                .create();


        return aboutPage;


    }

    private Element createCopyright() {
        Calendar c = Calendar.getInstance();
        Element copyRight = new Element();
        String copyRightString = String.format("CopyRight by MyVenue",c.get(Calendar.YEAR));
        copyRight.setTitle(copyRightString);
        copyRight.setGravity(Gravity.CENTER);
        copyRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), copyRightString, Toast.LENGTH_SHORT).show();
            }
        });
        return copyRight;


    }
}
