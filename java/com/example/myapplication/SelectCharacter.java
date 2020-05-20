package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class SelectCharacter extends AppCompatActivity {

    String heroName;
    View view;
    Button character_1, character_2, character_3;
    ImageButton back;
    EditText select_name;
    Character hero;
    ArrayList<Item> heroItems;
    ArrayList<Skill> skills;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_character);

        view = getWindow().getDecorView();

        /*startService(new Intent(SelectCharacter.this, MusicService.class)
                .putExtra("activity", "mainActivity"));*/

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

        back = findViewById(R.id.btn_back);
        select_name = findViewById(R.id.select_name);
        character_1 = findViewById(R.id.select_first_character);
        character_2 = findViewById(R.id.select_second_character);
        character_3 = findViewById(R.id.select_third_character);

        heroItems = new ArrayList<>();

        skills = new ArrayList<>();

        ButtonTreatment bt = new ButtonTreatment();

        back.setOnClickListener(bt);
        character_1.setOnClickListener(bt);
        character_2.setOnClickListener(bt);
        character_3.setOnClickListener(bt);
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
                case R.id.btn_back:
                    back.setEnabled(false);
                    i = new Intent(SelectCharacter.this, MainActivity.class);
                    startActivity(i);
                    finish();
                    break;

                case R.id.select_first_character:
                    character_1.setEnabled(false);
                    if (!select_name.getText().toString().equals("")) {
                        heroName = select_name.getText().toString();
                    } else {
                        Toast.makeText(SelectCharacter.this, "Пожалуйста, введите имя " +
                                "персонажа", Toast.LENGTH_SHORT).show();
                        character_1.setEnabled(true);
                        break;
                    }
                    i = new Intent(SelectCharacter.this, Locate_1.class);
                    hero = new Character(100, 100, 25, 5, heroName + ".1",
                            0, 10, 1, 0, heroItems, skills, 0);
                    i.putExtra("hero_get", hero);
                    startActivity(i);
                    finish();
                    break;

                case R.id.select_second_character:
                    character_2.setEnabled(false);
                    if (!select_name.getText().toString().equals("")) {
                        heroName = select_name.getText().toString();
                    } else {
                        Toast.makeText(SelectCharacter.this, "Пожалуйста, введите имя " +
                                "персонажа", Toast.LENGTH_SHORT).show();
                        character_2.setEnabled(true);
                        break;
                    }
                    i = new Intent(SelectCharacter.this, Locate_1.class);
                    hero = new Character(130, 130, 15, 8, heroName + ".2",
                            0, 10, 1, 0, heroItems, skills, 0);
                    i.putExtra("hero_get", hero);
                    startActivity(i);
                    finish();
                    break;

                case R.id.select_third_character:
                    character_3.setEnabled(false);
                    if (!select_name.getText().toString().equals("")) {
                        heroName = select_name.getText().toString();
                    } else {
                        Toast.makeText(SelectCharacter.this, "Пожалуйста, введите имя " +
                                "персонажа", Toast.LENGTH_SHORT).show();
                        character_3.setEnabled(true);
                        break;
                    }
                    i = new Intent(SelectCharacter.this, Locate_1.class);
                    hero = new Character(60, 60, 30, 10, heroName + ".3",
                            0, 30, 1, 0, heroItems, skills, 0);
                    i.putExtra("hero_get", hero);
                    startActivity(i);
                    finish();
                    break;
            }
        }
    }

    /*@Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(SelectCharacter.this, MusicService.class));
    }*/
}
