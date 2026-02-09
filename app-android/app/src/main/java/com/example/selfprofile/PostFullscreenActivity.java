package com.example.selfprofile;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class PostFullscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_fullscreen);

        ImageView imgPost = findViewById(R.id.imgFullscreenPost);
        ImageButton btnBack = findViewById(R.id.btnBack);

        int imageRes = getIntent().getIntExtra("image", 0);
        if (imageRes != 0) {
            imgPost.setImageResource(imageRes);
        }

        // Cofnij
        btnBack.setOnClickListener(v -> finish());
    }
}
