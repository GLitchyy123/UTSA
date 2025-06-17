package com.example.utsa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class loginactivity extends AppCompatActivity {

    EditText edtNama, edtPassword;

    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loginactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtNama = findViewById(R.id.edtNama);
        edtPassword = findViewById(R.id.edtPassword);
        btnlogin = findViewById(R.id.btnlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidated()) {
                    toDashboard();
                }
            }
        });
    }

    public boolean isValidated() {
        String nama = edtNama.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (nama.isEmpty()) {
            edtNama.setError("Nama wajib diisi");
            return false;
        } else if (password.isEmpty()) {
            edtPassword.setError("Password wajib diisi");
            return false;
        }

        return true; // Jika semua valid


    }

    public void toDashboard(){
        String nama = edtNama.getText().toString().trim(); // Ambil nama
        Intent intent = new Intent(this, dashboardactivity.class);
        intent.putExtra("USERNAME", nama); // Kirim lewat intent
        startActivity(intent);
        finish();
    }

}