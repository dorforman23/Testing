package com.furmandevs.myapplicationn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.IOException;

public class ImageFullScreen extends AppCompatActivity {

    ImageView mainImage;
    String mainUrl;
    Button wallpaperBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_image_full_screen);

        mainImage = findViewById(R.id.mainImage);
        mainUrl = getIntent().getExtras().getString("url");
        wallpaperBTN = findViewById(R.id.wallBTN);

        Glide
                .with(this)
                .load(mainUrl)
                .centerCrop()
                .into(mainImage);

        wallpaperBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = ((BitmapDrawable)mainImage.getDrawable()).getBitmap();
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                try {
                    wallpaperManager.setBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
