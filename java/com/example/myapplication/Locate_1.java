package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Locate_1 extends AppCompatActivity {
    View view;

    Button level_1, level_2, level_3, level_4, level_5, level_6, level_7, level_8, level_9, level_10,
            go_improve_hero, tmpButton;

    int level;

    Character hero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate);

        startService(new Intent(Locate_1.this, MusicService.class)
                .putExtra("activity", "locate"));

        view = getWindow().getDecorView();

        view.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0)
                    view.setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
            }
        });

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        if (MainActivity.getLevel() == null) {
            MainActivity.setLevel(1);
        }
        if (MainActivity.getStage() == null) {
            MainActivity.setStage(1);
        }

        hero = (Character) getIntent().getSerializableExtra("hero_get");

        if (MainActivity.getStage() >= 2) {
            Intent i = new Intent(Locate_1.this, Locate_2.class);
            i.putExtra("hero_get", hero);
            startActivity(i);
            finish();
        }

        level_1 = findViewById(R.id.level_1);
        level_2 = findViewById(R.id.level_2);
        level_3 = findViewById(R.id.level_3);
        level_4 = findViewById(R.id.level_4);
        level_5 = findViewById(R.id.level_5);
        level_6 = findViewById(R.id.level_6);
        level_7 = findViewById(R.id.level_7);
        level_8 = findViewById(R.id.level_8);
        level_9 = findViewById(R.id.level_9);
        level_10 = findViewById(R.id.level_10);
        go_improve_hero = findViewById(R.id.go_improve_hero);

        ButtonTreatment bt = new ButtonTreatment();

        level_1.setOnClickListener(bt);
        level_2.setOnClickListener(bt);
        level_3.setOnClickListener(bt);
        level_4.setOnClickListener(bt);
        level_5.setOnClickListener(bt);
        level_6.setOnClickListener(bt);
        level_7.setOnClickListener(bt);
        level_8.setOnClickListener(bt);
        level_9.setOnClickListener(bt);
        level_10.setOnClickListener(bt);
        go_improve_hero.setOnClickListener(bt);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            view.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    }

    private class ButtonTreatment implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.level_1:
                    tmpButton = level_1;
                    level = 1;
                    stageTreatment();
                    break;

                case R.id.level_2:
                    tmpButton = level_2;
                    level = 2;
                    stageTreatment();
                    break;

                case R.id.level_3:
                    tmpButton = level_3;
                    level = 3;
                    stageTreatment();
                    break;

                case R.id.level_4:
                    tmpButton = level_4;
                    level = 4;
                    stageTreatment();
                    break;

                case R.id.level_5:
                    tmpButton = level_5;
                    level = 5;
                    stageTreatment();
                    break;

                case R.id.level_6:
                    tmpButton = level_6;
                    level = 6;
                    stageTreatment();
                    break;

                case R.id.level_7:
                    tmpButton = level_7;
                    level = 7;
                    stageTreatment();
                    break;

                case R.id.level_8:
                    tmpButton = level_8;
                    level = 8;
                    stageTreatment();
                    break;

                case R.id.level_9:
                    tmpButton = level_9;
                    level = 9;
                    stageTreatment();
                    break;

                case R.id.level_10:
                    tmpButton = level_10;
                    level = 10;
                    stageTreatment();
                    break;

                case R.id.go_improve_hero:
                    Intent i = new Intent(Locate_1.this, ImproveHero.class);
                    i.putExtra("hero_get", hero);
                    startActivity(i);
                    finish();
            }
        }
    }

    private void stageTreatment () {
        tmpButton.setEnabled(false);
        if (MainActivity.getLevel() == level) {
            if (stopService(new Intent(Locate_1.this, MusicService.class))) {
                Intent i;
                if (level != 7) {
                    i = new Intent(Locate_1.this, Fight.class);
                } else {
                    i = new Intent(Locate_1.this, Shop.class);
                }
                i.putExtra("hero_get", hero);
                startActivity(i);
                finish();
            }
        } else {
            tmpButton.setEnabled(true);
            if (MainActivity.getLevel() < level) {
                Toast.makeText(getApplicationContext(), "Вы ещё не прошли предыдущий этап." +
                        " Номер вашего этапа: " + MainActivity.getLevel(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Вы уже прошли этот этап." +
                        " Номер вашего этапа: " + MainActivity.getLevel(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
