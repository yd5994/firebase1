package com.example.firebase1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Activity5 extends AppCompatActivity {
    boolean isPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent gi = getIntent();
        isPlaying = gi.getBooleanExtra("Music_playing",false);
    }

    public void music(View view) {
        if(isPlaying){
            stopService(new Intent(Activity5.this,MusicService.class));
            isPlaying=false;
        }
        else{
            startService(new Intent(Activity5.this,MusicService.class));
            isPlaying=true;
        }
    }

    public void Main(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Music_playing",isPlaying);
        startActivity(intent);
    }
}