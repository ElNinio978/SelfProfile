package com.example.selfprofile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout rootLayout;
    private int currentTheme = 0;

    private TextView tvFullName;
    private TextView tvUsername;
    private TextView tvBio;

    private static final int REQUEST_EDIT_PROFILE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // TextView
        tvFullName = findViewById(R.id.tvFullName);
        tvUsername = findViewById(R.id.tvUsername);
        tvBio = findViewById(R.id.tvBio);

        // Root layout
        rootLayout = findViewById(R.id.root);

        // Padding "Root layout"
        ViewCompat.setOnApplyWindowInsetsListener(rootLayout, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Avatar
        ImageView avatar = findViewById(R.id.imgAvatar);
        avatar.setClipToOutline(true);

        // Buttons
        Button btnEdit = findViewById(R.id.btnEditProfile);
        Button btnShare = findViewById(R.id.btnShare);
        ImageButton btnSettings = findViewById(R.id.btnSettings);

        // Zmiana kolorów tła
        btnSettings.setOnClickListener(v -> {
            int[] colors = {
                    R.color.bg_black,
                    R.color.bg_graphite,
                    R.color.bg_midnight,
                    R.color.bg_navy,
                    R.color.bg_emerald,
                    R.color.bg_purple,
                    R.color.black
            };

            rootLayout.setBackgroundColor(getResources().getColor(colors[currentTheme]));

            currentTheme++;
            if (currentTheme >= colors.length) currentTheme = 0;
        });

        // RecyclerView – Highlights
        RecyclerView rvHighlights = findViewById(R.id.rvHighlights);
        rvHighlights.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvHighlights.setAdapter(new HighlightAdapter(6));

        // RecyclerView – Posts Grid
        RecyclerView rvPosts = findViewById(R.id.rvPosts);
        rvPosts.setLayoutManager(new GridLayoutManager(this, 3));

        int[] postImages = {
                R.drawable.post1, R.drawable.post2, R.drawable.post3,
                R.drawable.post4, R.drawable.post5, R.drawable.post6,
                R.drawable.post7, R.drawable.post8, R.drawable.post9,
                R.drawable.post10, R.drawable.post11, R.drawable.post12,
                R.drawable.post13, R.drawable.post14, R.drawable.post15,
                R.drawable.post16, R.drawable.post17, R.drawable.post18,
                R.drawable.post19, R.drawable.post20, R.drawable.post21
        };
        rvPosts.setAdapter(new PostAdapter(this, postImages));

        int spacing = (int) (8 * getResources().getDisplayMetrics().density);
        rvPosts.addItemDecoration(new GridSpacingItemDecoration(3, spacing, true));

        // BTN "Edytuj profil"
        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ChangeSettingsActivity.class);
            intent.putExtra(ChangeSettingsActivity.EXTRA_NAME, tvFullName.getText().toString());
            intent.putExtra(ChangeSettingsActivity.EXTRA_USERNAME, tvUsername.getText().toString());
            intent.putExtra(ChangeSettingsActivity.EXTRA_BIO, tvBio.getText().toString());
            startActivityForResult(intent, REQUEST_EDIT_PROFILE);
        });

        // BTN "Udostępnij" – można zostawić pusty na razie
        btnShare.setOnClickListener(v -> {
        });

        // Pobierz profil z Node.js po starcie aplikacji
        fetchProfileFromApi();
    }

    private void fetchProfileFromApi() {
        RetrofitInstance.getApi().hello().enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    MessageResponse profile = response.body();
                    tvFullName.setText(profile.getFullName());
                    tvUsername.setText(profile.getUsername());
                    tvBio.setText(profile.getBio());
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Log.e("MainActivity", "Błąd pobierania profilu: " + t.getMessage());
            }
        });
    }

    // Obsługa wyników z ChangeSettingsActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_EDIT_PROFILE && resultCode == RESULT_OK && data != null) {
            String newName = data.getStringExtra(ChangeSettingsActivity.EXTRA_NAME);
            String newUsername = data.getStringExtra(ChangeSettingsActivity.EXTRA_USERNAME);
            String newBio = data.getStringExtra(ChangeSettingsActivity.EXTRA_BIO);

            if (newName != null) tvFullName.setText(newName);
            if (newUsername != null) tvUsername.setText(newUsername);
            if (newBio != null) tvBio.setText(newBio);

            // Wyślij zmiany do Node.js
            UpdateProfileRequest request = new UpdateProfileRequest(
                    newName != null ? newName : "",
                    newUsername != null ? newUsername : "",
                    newBio != null ? newBio : ""
            );

            RetrofitInstance.getApi().updateProfile(request).enqueue(new Callback<UpdateProfileResponse>() {
                @Override
                public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        MessageResponse updatedProfile = response.body().getProfile();
                        Log.d("MainActivity", "Profil zaktualizowany na serwerze: " + updatedProfile.getFullName());
                    }
                }

                @Override
                public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {
                    Log.e("MainActivity", "Błąd wysyłania profilu: " + t.getMessage());
                }
            });

        }
    }
}
