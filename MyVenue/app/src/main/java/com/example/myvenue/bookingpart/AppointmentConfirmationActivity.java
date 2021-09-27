package com.example.myvenue.bookingpart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.myvenue.R;
import com.example.myvenue.maindashboard.DashBoardActivity;

public class AppointmentConfirmationActivity extends AppCompatActivity {
ImageButton continue_shopping_Btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_confirmation);

        continue_shopping_Btn = findViewById(R.id.continue_shopping_Btn);
        continue_shopping_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AppointmentConfirmationActivity.this, DashBoardActivity.class));
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AppointmentConfirmationActivity.this,DashBoardActivity.class));
        finish();

    }
}
