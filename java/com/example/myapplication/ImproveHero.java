package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ImproveHero extends AppCompatActivity {

    Button back_improve_hero;
    Button improve_1;
    Button improve_2;
    Button improve_3;
    TextView improve_info;
    Button improve_button;

    Skill skill;

    Integer cost;

    Character hero;

    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_improve_hero);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        hero = (Character) getIntent().getSerializableExtra("hero_get");

        back_improve_hero = findViewById(R.id.back_improve_hero);
        improve_1 = findViewById(R.id.improve_1);
        improve_2 = findViewById(R.id.improve_2);
        improve_3 = findViewById(R.id.improve_3);
        improve_info = findViewById(R.id.improve_info);
        improve_button = findViewById(R.id.improve_button);

        ButtonTreatment bt = new ButtonTreatment();

        back_improve_hero.setOnClickListener(bt);
        improve_button.setOnClickListener(bt);
        improve_1.setOnClickListener(bt);
        improve_2.setOnClickListener(bt);
    }

    private class ButtonTreatment implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.back_improve_hero:
                    Intent intent = new Intent(ImproveHero.this, Locate.class);
                    intent.putExtra("hero_get", hero);
                    startActivity(intent);
                    finish();
                    break;

                case R.id.improve_button:
                    if (skill != null) {
                        for (int i = 0; i < hero.getSkills().size(); i++) {
                            if (hero.getSkills().get(i).getSkill().equals(skill.getSkill())) {
                                Toast.makeText(ImproveHero.this, "Навык уже" +
                                        " изучен", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        if (hero.getImpPoint() >= cost) {
                            hero.setImpPoint(hero.getImpPoint() - cost);
                            ArrayList<Skill> hs = hero.getSkills();
                            hs.add(skill);
                            hero.setSkills(hs);
                        } else {
                            Toast.makeText(ImproveHero.this, "Недостаточно" +
                                    " очков", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ImproveHero.this, "Выберете" +
                                " навык", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.improve_1:
                    improve_info.setText("Увеличивает атаку на 10%");
                    cost = 1;
                    skill = new Skill("1.atk_up", true);
                    break;

                case R.id.improve_2:
                    for (int i = 0; i < hero.getSkills().size(); i++) {
                        if (hero.getSkills().get(i).getSkill().equals("1.atk_up")) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        skill = new Skill("2.skill_atk.0.2", true);
                        cost = 0;
                        flag = false;
                    } else {
                        Toast.makeText(ImproveHero.this, "Выучите предыдуий" +
                                " навык", Toast.LENGTH_SHORT).show();
                        flag = false;
                    }

                    break;

            }
        }
    }

}
