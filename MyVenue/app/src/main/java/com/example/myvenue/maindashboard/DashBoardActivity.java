package com.example.myvenue.maindashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.myvenue.Fragment.AboutFragment;
import com.example.myvenue.Fragment.ContactUsFragment;
import com.example.myvenue.Fragment.HomeFragment;
import com.example.myvenue.Fragment.ProfileFragment;
import com.example.myvenue.login.LoginActivity;
import com.example.myvenue.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import static com.example.myvenue.SplashActivity.thread1;

public class DashBoardActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener {
    BottomNavigationView bottomNavigationView;

    FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        frameLayout = findViewById(R.id.container);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Fragment selectFragment = null;
                switch (item.getItemId()) {
                    case R.id.home:
//                        Toast.makeText(DashBoardActivity.this, "Home item click", Toast.LENGTH_SHORT).show();
                        selectFragment = new HomeFragment();

                        break;

                    case R.id.contact_us:
//                        Toast.makeText(DashBoardActivity.this, "Book item click", Toast.LENGTH_SHORT).show();
                        selectFragment = new ContactUsFragment();

                        break;

                    case R.id.profile:
//                        Toast.makeText(DashBoardActivity.this, "Contact us item click", Toast.LENGTH_SHORT).show();
                        selectFragment = new ProfileFragment();

                        break;

                    case R.id.about:
//                        Toast.makeText(DashBoardActivity.this, "Profile item click", Toast.LENGTH_SHORT).show();
                        selectFragment = new AboutFragment();

                        break;
                }


                getSupportFragmentManager().beginTransaction().replace(R.id.container, selectFragment).commit();
                return true;
            }
        });


    }


    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        if (firebaseAuth.getCurrentUser() == null) {
            startLoginActivity();

            return;

        }
    }

    private void startLoginActivity() {
        Intent intent = new Intent(DashBoardActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseAuth.getInstance().addAuthStateListener(this);

    }

    @Override
    protected void onStop() {
        super.onStop();
        FirebaseAuth.getInstance().removeAuthStateListener(this);

    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setTitle("Really Exit?")
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DashBoardActivity.super.onBackPressed();
                        try {


                            finishAffinity();
                            thread1.stop();
                            
                        }catch (Exception e){
                            Toast.makeText(DashBoardActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }).create().show();


    }
}
