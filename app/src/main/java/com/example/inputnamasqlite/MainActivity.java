package com.example.inputnamasqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button simpan, getAll;
    private EditText txtNama;
    private TextView tvHasil;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button simpan = findViewById(R.id.btSimpan);
        Button getAll = findViewById(R.id.btGetAll);
        EditText txtNama = findViewById(R.id.txtNama);
        TextView tvHasil = findViewById(R.id.tvHasil);

        databaseHelper = new DatabaseHelper(this);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtNama.getText().toString();
                Long l = databaseHelper.AddStudent(name);
                if (l>0){
                    Toast.makeText(MainActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                }
            }
        });

        getAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> all = databaseHelper.getAll();
                if (all != null && !all.isEmpty()) {
                    StringBuffer buffer = new StringBuffer();
                    for (String name : all) {
                        buffer.append(name);
                        buffer.append(", ");
                    }
                    String names = buffer.toString();
                    tvHasil.setText(names.substring(0, names.length() - 1));
                }
            }
        });

    }
}