package com.example.selfprofile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ChangeSettingsActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_USERNAME = "extra_username";
    public static final String EXTRA_BIO = "extra_bio";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        EditText etName = findViewById(R.id.etName);
        EditText etUsername = findViewById(R.id.etUsername);
        EditText etBio = findViewById(R.id.etBio);

        Button btnSave = findViewById(R.id.btnSave);
        ImageButton btnBack = findViewById(R.id.btnBack);

        // Pobieranie Danych z Profilu
        Intent intent = getIntent();
        etName.setText(intent.getStringExtra(EXTRA_NAME));
        etUsername.setText(intent.getStringExtra(EXTRA_USERNAME));
        etBio.setText(intent.getStringExtra(EXTRA_BIO));

        // Cofnij
        btnBack.setOnClickListener(v -> finish());

        // Zapisz
        btnSave.setOnClickListener(v -> {
            Intent result = new Intent();
            result.putExtra(EXTRA_NAME, etName.getText().toString());
            result.putExtra(EXTRA_USERNAME, etUsername.getText().toString());
            result.putExtra(EXTRA_BIO, etBio.getText().toString());

            setResult(RESULT_OK, result);
            finish();
        });
    }
}
