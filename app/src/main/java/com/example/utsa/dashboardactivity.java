package com.example.utsa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class dashboardactivity extends AppCompatActivity {

    LinearLayout llyProfile, llyCalender, llyLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboardactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        llyProfile = findViewById(R.id.llyProfile);
        llyCalender = findViewById(R.id.llyCalender);
        llyLogout = findViewById(R.id.llyLogout);

        // Ambil username dari intent
        String username = getIntent().getStringExtra("USERNAME");

// Cari TextView dan ganti teksnya
        TextView textViewWelcome = findViewById(R.id.textView); // pastikan ID-nya benar
        textViewWelcome.setText("Selamat Datang, " + username + "!");

        llyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toProfile();
            }
        });

        llyLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLogin(); // pindah ke login saat logout ditekan
            }
        });
    }

    public void toProfile(){
        Intent intent = new Intent(this, profileactivity.class);
        startActivity(intent);
    }

    public void toLogin(){
        Intent intent = new Intent(this, loginactivity.class);
        startActivity(intent);
        finish(); // agar tidak bisa kembali ke dashboard dengan tombol back
    }

}