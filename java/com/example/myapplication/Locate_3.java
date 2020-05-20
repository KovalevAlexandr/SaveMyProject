package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Locate_3 extends AppCompatActivity {

    View view;

    Button level_19, level_20, level_21, level_22, level_23, level_24, level_25, level_26,
            level_27, level_28, level_29, level_30, go_improve_hero, tmpButton;

    int level;

    Character hero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locate_3);

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

        /*if (MainActivity.getStage() >= 3) {
            Intent i = new Intent(Locate_2.this, Locate_3.class);
            i.putExtra("hero_get", hero);
            startActivity(i);
            finish();
        }*/

        level_19 = findViewById(R.id.level_19);
        level_20 = findViewById(R.id.level_20);
        level_21 = findViewById(R.id.level_21);
        level_22 = findViewById(R.id.level_22);
        level_23 = findViewById(R.id.level_23);
        level_24 = findViewById(R.id.level_24);
        level_25 = findViewById(R.id.level_25);
        level_26 = findViewById(R.id.level_26);
        level_27 = findViewById(R.id.level_27);
        level_28 = findViewById(R.id.level_28);
        level_29 = findViewById(R.id.level_29);
        level_30 = findViewById(R.id.level_30);
        go_improve_hero = findViewById(R.id.go_improve_hero);

        ButtonTreatment bt = new ButtonTreatment();

        level_19.setOnClickListener(bt);
        level_20.setOnClickListener(bt);
        level_21.setOnClickListener(bt);
        level_22.setOnClickListener(bt);
        level_23.setOnClickListener(bt);
        level_24.setOnClickListener(bt);
        level_25.setOnClickListener(bt);
        level_26.setOnClickListener(bt);
        level_27.setOnClickListener(bt);
        level_28.setOnClickListener(bt);
        level_29.setOnClickListener(bt);
        level_30.setOnClickListener(bt);
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
                case R.id.level_19:
                    tmpButton = level_19;
                    level = 19;
                    stageTreatment();
                    break;

                case R.id.level_20:
                    tmpButton = level_20;
                    level = 20;
                    stageTreatment();
                    break;

                case R.id.level_21:
                    tmpButton = level_21;
                    level = 21;
                    stageTreatment();
                    break;

                case R.id.level_22:
                    tmpButton = level_22;
                    level = 22;
                    stageTreatment();
                    break;

                case R.id.level_23:
                    tmpButton = level_23;
                    level = 23;
                    stageTreatment();
                    break;

                case R.id.level_24:
                    tmpButton = level_24;
                    level = 24;
                    stageTreatment();
                    break;

                case R.id.level_25:
                    tmpButton = level_25;
                    level = 25;
                    stageTreatment();
                    break;

                case R.id.level_26:
                    tmpButton = level_26;
                    level = 26;
                    stageTreatment();
                    break;

                case R.id.level_27:
                    tmpButton = level_27;
                    level = 27;
                    stageTreatment();
                    break;

                case R.id.level_28:
                    tmpButton = level_28;
                    level = 28;
                    stageTreatment();
                    break;

                case R.id.level_29:
                    tmpButton = level_29;
                    level = 29;
                    stageTreatment();
                    break;

                case R.id.level_30:
                    tmpButton = level_30;
                    level = 30;
                    stageTreatment();
                    break;

                case R.id.go_improve_hero:
                    Intent i = new Intent(Locate_3.this, ImproveHero.class);
                    i.putExtra("hero_get", hero);
                    startActivity(i);
                    finish();
            }
        }
    }

    private void stageTreatment () {
        tmpButton.setEnabled(false);
        if (MainActivity.getLevel() == level) {
            if (stopService(new Intent(Locate_3.this, MusicService.class))) {
                Intent i;
                if (level != 23 && level != 27) {
                   i = new Intent(Locate_3.this, Fight.class);
                }  else {
                    i = new Intent(Locate_3.this, Shop.class);
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
