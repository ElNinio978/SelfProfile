package com.example.selfprofile;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // PADDING DLA LAYOUTu
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.root), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // AVATAR
        ImageView avatar = findViewById(R.id.imgAvatar);
        avatar.setClipToOutline(true);

        // BUTTONS
        Button btnEdit = findViewById(R.id.btnEditProfile);
        Button btnShare = findViewById(R.id.btnShare);
        btnEdit.setOnClickListener(v -> {
            // TODO: Edycja profilu
        });
        btnShare.setOnClickListener(v -> {
            // TODO: Udostępnianie
        });

        // BTN SETTINGS
        ImageButton btnSettings = findViewById(R.id.btnSettings);
        btnSettings.setOnClickListener(v -> {
            // TODO: Ustawienia
        });

        // HIGHLIGHTS
        RecyclerView rvHighlights = findViewById(R.id.rvHighlights);
        rvHighlights.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvHighlights.setAdapter(new HighlightAdapter(6));

        // POSTS GRID
        RecyclerView rvPosts = findViewById(R.id.rvPosts);
        rvPosts.setLayoutManager(new GridLayoutManager(this, 3));

        // Tablica z Twoimi obrazami
        int[] postImages = {R.drawable.post1, R.drawable.post2, R.drawable.post3, R.drawable.post4, R.drawable.post5, R.drawable.post6, R.drawable.post7, R.drawable.post8, R.drawable.post9, R.drawable.post10, R.drawable.post11, R.drawable.post12 };
        rvPosts.setAdapter(new PostAdapter(postImages));

        int spacingInPixels = (int) (8 * getResources().getDisplayMetrics().density);
        rvPosts.addItemDecoration(new GridSpacingItemDecoration(3, spacingInPixels, true));
    }
}
