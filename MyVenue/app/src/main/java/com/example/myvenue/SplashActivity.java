package com.example.myvenue;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myvenue.login.LoginActivity;
import com.example.myvenue.maindashboard.DashBoardActivity;
import com.example.myvenue.onboarding.SliderActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static java.lang.Thread.sleep;

public class SplashActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {
    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;

    public static Thread thread1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences getPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                boolean isFirstStart = getPref.getBoolean("firstStart",true);
                if (isFirstStart){
                    startActivity(new Intent(SplashActivity.this, SliderActivity.class));
                    SharedPreferences.Editor editor = getPref.edit();
                    editor.putBoolean("firstStart",false);
                    editor.commit();
                }else{
                    thread1 = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                sleep(2000);

                                onAuthStateChanged(firebaseAuth);


                            } catch (InterruptedException e) {
                                e.printStackTrace();
                                finish();
                            }

                        }
                    });

                    thread1.start();

                }

            }
        });

        thread.start();






















    }


    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser !=null){
            startActivity(new Intent(this, DashBoardActivity.class));
        }else{
            startActivity(new Intent(this, LoginActivity.class));

        }

    }



}
