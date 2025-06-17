package com.example.utsa;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class profileactivity extends AppCompatActivity {

    EditText edtNama, edtID, edtFakultas, edtPassword;
    RadioGroup rdgProgramStudi;
    RadioButton rdbTI, rdbSI, rdbHukum;
    CheckBox ckbSetuju;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profileactivity);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi View
        edtID = findViewById(R.id.edtID);
        edtNama = findViewById(R.id.edtNama);
        edtPassword = findViewById(R.id.edtPassword);
        edtFakultas = findViewById(R.id.edtFakultas);
        rdgProgramStudi = findViewById(R.id.rdgProgramStudi);
        rdbTI = findViewById(R.id.rdbTI);
        rdbSI = findViewById(R.id.rdbSI);
        rdbHukum = findViewById(R.id.rdbHukum);
        ckbSetuju = findViewById(R.id.ckbSetuju);
    }

    // Validasi input
    public boolean isValidated() {
        String nama = edtNama.getText().toString().trim();
        String id = edtID.getText().toString().trim();
        String fakultas = edtFakultas.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        int selectedProdiId = rdgProgramStudi.getCheckedRadioButtonId();

        if (nama.isEmpty()) {
            edtNama.setError("Nama wajib diisi");
            return false;
        } else if (id.isEmpty()) {
            edtID.setError("Student ID wajib diisi");
            return false;
        } else if (fakultas.isEmpty()) {
            Toast.makeText(this, "Fakultas wajib diisi", Toast.LENGTH_SHORT).show();
            return false;
        } else if (password.isEmpty()) {
            edtPassword.setError("Password wajib diisi");
            return false;
        } else if (selectedProdiId == -1) {
            Toast.makeText(this, "Pilih program studi", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!ckbSetuju.isChecked()) {
            Toast.makeText(this, "Anda belum menyetujui syarat dan ketentuan", Toast.LENGTH_SHORT).show();
            return false;
        }

        // Validasi fakultas vs program studi
        String prodiDipilih = "";
        if (selectedProdiId == R.id.rdbTI || selectedProdiId == R.id.rdbSI) {
            prodiDipilih = "Ilmu Komputer";
        } else if (selectedProdiId == R.id.rdbHukum) {
            prodiDipilih = "Hukum";
        }

        if (fakultas.equalsIgnoreCase("ilmu komputer") || fakultas.equalsIgnoreCase("Fakultas Ilmu Komputer")) {
            if (!prodiDipilih.equalsIgnoreCase("Ilmu Komputer")) {
                Toast.makeText(this, "Program studi tidak sesuai dengan fakultas", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else if (fakultas.equalsIgnoreCase("Fakultas Hukum") || fakultas.equalsIgnoreCase("Hukum")) {
            if (!prodiDipilih.equalsIgnoreCase("Hukum")) {
                Toast.makeText(this, "Program studi tidak sesuai dengan fakultas", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(this, "Fakultas tidak dikenali", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    // Fungsi simpan data
    public void simpan() {
        if (isValidated()) {
            Toast.makeText(this, "Data disimpan!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(profileactivity.this, dashboardactivity.class);
            startActivity(intent);
            finish(); // Supaya tidak bisa balik ke halaman profile
        }
    }

    // Fungsi bersihkan form
    public void bersihkan() {
        edtNama.setText("");
        edtID.setText("");
        edtFakultas.setText("");
        edtPassword.setText("");
        rdgProgramStudi.clearCheck();
        ckbSetuju.setChecked(false);
        Toast.makeText(this, "Data dibersihkan!", Toast.LENGTH_SHORT).show();
    }

    // Tampilkan menu titik tiga
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    // Aksi menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_save) {
            simpan();
            return true;
        } else if (id == R.id.menu_clear) {
            bersihkan();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
