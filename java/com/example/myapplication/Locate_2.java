package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.TreeSet;

public class Locate_2 extends AppCompatActivity {

    View view;

    Button level_11, level_12, level_13, level_14, level_15, level_16, level_17, level_18,
            go_improve_hero, tmpButton;

    int level;

    Character hero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate_2);

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

        hero = (Character) getIntent().getSerializableExtra("hero_get");

        if (MainActivity.getStage() >= 3) {
            Intent i = new Intent(Locate_2.this, Locate_3.class);
            i.putExtra("hero_get", hero);
            startActivity(i);
            finish();
        }

        level_11 = findViewById(R.id.level_11);
        level_12 = findViewById(R.id.level_12);
        level_13 = findViewById(R.id.level_13);
        level_14 = findViewById(R.id.level_14);
        level_15 = findViewById(R.id.level_15);
        level_16 = findViewById(R.id.level_16);
        level_17 = findViewById(R.id.level_17);
        level_18 = findViewById(R.id.level_18);
        go_improve_hero = findViewById(R.id.go_improve_hero);

        ButtonTreatment bt = new ButtonTreatment();

        level_11.setOnClickListener(bt);
        level_12.setOnClickListener(bt);
        level_13.setOnClickListener(bt);
        level_14.setOnClickListener(bt);
        level_15.setOnClickListener(bt);
        level_16.setOnClickListener(bt);
        level_17.setOnClickListener(bt);
        level_18.setOnClickListener(bt);
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
                case R.id.level_11:
                    tmpButton = level_11;
                    level = 11;
                    stageTreatment();
                    break;

                case R.id.level_12:
                    tmpButton = level_12;
                    level = 12;
                    stageTreatment();
                    break;

                case R.id.level_13:
                    tmpButton = level_13;
                    level = 13;
                    stageTreatment();
                    break;

                case R.id.level_14:
                    tmpButton = level_14;
                    level = 14;
                    stageTreatment();
                    break;

                case R.id.level_15:
                    tmpButton = level_15;
                    level = 15;
                    stageTreatment();
                    break;

                case R.id.level_16:
                    tmpButton = level_16;
                    level = 16;
                    stageTreatment();
                    break;

                case R.id.level_17:
                    tmpButton = level_17;
                    level = 17;
                    stageTreatment();
                    break;

                case R.id.level_18:
                    tmpButton = level_18;
                    level = 18;
                    stageTreatment();
                    break;

                case R.id.go_improve_hero:
                    Intent i = new Intent(Locate_2.this, ImproveHero.class);
                    i.putExtra("hero_get", hero);
                    startActivity(i);
                    finish();
            }
        }
    }

    private void stageTreatment () {
        tmpButton.setEnabled(false);
        if (MainActivity.getLevel() == level) {
            if (stopService(new Intent(Locate_2.this, MusicService.class))) {
                Intent i;
                if (level != 16) {
                    i = new Intent(Locate_2.this, Fight.class);
                } else {
                    i = new Intent(Locate_2.this, Shop.class);
                }
                i.putExtra("hero_get", hero);
                startActivity(i);
                finish();
            }
        } else {
            if (MainActivity.getLevel() < level) {
                Toast.makeText(getApplicationContext(), "Вы ещё не прошли предыдущий этап." +
                        " Номер вашего этапа: " + MainActivity.getLevel(), Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getApplicationContext(), "Вы уже прошли этот этап." +
                        " Номер вашего этапа: " + MainActivity.getLevel(), Toast.LENGTH_LONG).show();
            }
        }
        tmpButton.setEnabled(true);
    }

}
