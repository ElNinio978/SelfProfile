package com.example.selfprofile;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PostFullScreenActivity extends AppCompatActivity {

    public static final String EXTRA_IMAGE_RES = "extra_image_res";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_fullscreen);

        // Post
        ImageView img = findViewById(R.id.imgFullScreen);
        int imageRes = getIntent().getIntExtra(EXTRA_IMAGE_RES, R.drawable.post1);
        img.setImageResource(imageRes);

        // Avatar + Nazwa + Ustawienia
        ImageView avatar = findViewById(R.id.imgAvatarFull);
        TextView username = findViewById(R.id.tvUsernameFull);
        ImageButton settings = findViewById(R.id.btnSettingsFull);

        avatar.setImageResource(R.drawable.ava);
        username.setText("elninio978");

        settings.setOnClickListener(v -> {
            // TODO: Ustawienia dla posta
        });

        // ArrowBack
        ImageButton back = findViewById(R.id.btnBack);
        back.setOnClickListener(v -> finish());
    }
}
