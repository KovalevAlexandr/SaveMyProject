package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Fight extends AppCompatActivity {
    View view;

    TextView health_view1_fight, health_view2_fight, attack_view1_fight, attack_view2_fight,
            protection_view1_fight, protection_view2_fight, heroIcon, enemyIcon, character1_name,
            character2_name;
    Button default_atk, breakdown_atk, proportional_atk, tmpButton;

    Character hero, tmpHero;

    Enemy enemy;

    Treatment.Fight fight;

    Item itemWin;

    int dmg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight);

        hero = (Character) getIntent().getSerializableExtra("hero_get");

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        startService(new Intent(Fight.this, MusicService.class)
                .putExtra("activity", "fight")
                .putExtra("level", MainActivity.getLevel())
                .putExtra("stage", MainActivity.getStage()));

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

        heroIcon = findViewById(R.id.character_icon1_fight);
        enemyIcon = findViewById(R.id.character_icon2_fight);
        health_view1_fight = findViewById(R.id.health_view1_fight);
        health_view2_fight = findViewById(R.id.health_view2_fight);
        attack_view1_fight = findViewById(R.id.attack_view1_fight);
        attack_view2_fight = findViewById(R.id.attack_view2_fight);
        protection_view1_fight = findViewById(R.id.protection_view1_fight);
        protection_view2_fight = findViewById(R.id.protection_view2_fight);
        default_atk = findViewById(R.id.skill_1_fight);
        breakdown_atk = findViewById(R.id.skill_2_fight);
        proportional_atk = findViewById(R.id.skill_3_fight);
        character1_name = findViewById(R.id.character1_name);
        character2_name = findViewById(R.id.character2_name);

        switch (hero.getName().split("\\.")[1]) {
            case "1":
                heroIcon.setBackgroundDrawable(ContextCompat.getDrawable
                        (this, R.drawable.f_pink_monster));
                break;
            case "2":
                heroIcon.setBackgroundDrawable(ContextCompat.getDrawable
                        (this, R.drawable.f_white_monster));
                break;
            default:
                heroIcon.setBackgroundDrawable(ContextCompat.getDrawable
                        (this, R.drawable.f_blue_monster));
        }

        Generator generator = new Generator(MainActivity.getStage(), MainActivity.getLevel());

        if ((int) (Math.random() * 10) < 3) {
            itemWin = generator.itemForFightGenerator(hero.getHeroItems());
        }

        character1_name.setText(hero.getName().split("\\.")[0]);

        enemy = (Enemy) new Generator(MainActivity.getStage(), MainActivity.getLevel()).enemyGenerator();

        character2_name.setText(enemy.getName());

        tmpHero = new Character(hero.getHp(), hero.getMaxHp(), hero.getAtk(), hero.getDef(), hero.getName(),
                hero.getExp(), hero.getMoney(), hero.getLvl(), hero.getImpPoint(), hero.getHeroItems(),
                hero.getSkills(), hero.getPoints());

        tmpHero.setAll(new Treatment.Fight(tmpHero, hero.getHeroItems(), enemy).setFightIndicator());

        fight = new Treatment.Fight(tmpHero, hero.getHeroItems(), enemy);

        /*if (tmpHero.getHp() > tmpHero.getMaxHp()) {
            tmpHero.setHp(tmpHero.getMaxHp());
        }*/

        ButtonTreatment bt = new ButtonTreatment();

        health_view1_fight.setText(tmpHero.getHp() + "/" + tmpHero.getMaxHp());
        health_view2_fight.setText(enemy.getHp() + "/" + enemy.getMaxHp());
        attack_view1_fight.setText(String.valueOf(tmpHero.getAtk()));
        attack_view2_fight.setText(String.valueOf(enemy.getAtk()));
        protection_view1_fight.setText(String.valueOf(tmpHero.getDef()));
        protection_view2_fight.setText(String.valueOf(enemy.getDef()));

        default_atk.setOnClickListener(bt);
        breakdown_atk.setOnClickListener(bt);
        proportional_atk.setOnClickListener(bt);

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
                case R.id.skill_1_fight:
                    tmpButton = default_atk;
                    enemy.setHp(fight.basAttack());
                    tmpHero.setSkills(fight.skillsUpdateStep(""));
                    buttonBattleTreatment();
                    break;

                case R.id.skill_2_fight:
                    tmpButton = breakdown_atk;
                    if (fight.skillLearned("skill_atk")) {
                        if (fight.skillIsReady("skill_atk")) {
                            enemy.setHp(fight.secondAttack(hero.getSkills()));
                            tmpHero.setSkills(fight.skillsUpdateStep("skill_atk"));
                            tmpHero.setSkills(fight.setSkillStep("skill_atk"));
                            buttonBattleTreatment();
                            break;
                        } else {
                            Toast.makeText(Fight.this, "Навык не готов", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Fight.this, "Навык не изучен", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.skill_3_fight:
                    tmpButton = proportional_atk;
                    if (fight.skillLearned("shadow_atk")) {
                        if (fight.skillIsReady("shadow_atk")) {
                            enemy.setHp(fight.shadowAttack(hero.getSkills(), enemy));
                            tmpHero.setSkills(fight.skillsUpdateStep("shadow_atk"));
                            tmpHero.setSkills(fight.setSkillStep("shadow_atk"));
                            buttonBattleTreatment();
                            break;
                        } else {
                            Toast.makeText(Fight.this, "Навык не готов", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Fight.this, "Навык не изучен", Toast.LENGTH_SHORT).show();
                    }
                    break;

            }
        }
    }

    private void buttonBattleTreatment() {
        if (enemy.getHp() <= 0) {
            tmpButton.setEnabled(false);
            if (stopService(new Intent(Fight.this, MusicService.class))) {
                hero.setHp(tmpHero.getHp());
                hero.setSkills(fight.setSkillsAfterFight());
                hero.setAll(Treatment.battleWin(hero, MainActivity.getStage(), MainActivity.getLevel()));
                Intent i = new Intent(Fight.this, Locate_1.class);
                if (itemWin != null) {
                    ArrayList<Item> hi = hero.getHeroItems();
                    hi.add(itemWin);
                    hero.setHeroItems(hi);
                }
                i.putExtra("hero_get", hero);
                startActivity(i);
                health_view2_fight.setText(enemy.getHp() + "/" + enemy.getMaxHp());
                finish();
            }
        }

        health_view2_fight.setText(enemy.getHp() + "/" + enemy.getMaxHp());

        dmg = Math.max(enemy.getAtk() - tmpHero.getDef(), 1);
        tmpHero.setHp(tmpHero.getHp() - dmg);

        health_view1_fight.setText(tmpHero.getHp() + "/" + tmpHero.getMaxHp());
        attack_view1_fight.setText(String.valueOf(tmpHero.getAtk()));
        protection_view1_fight.setText(String.valueOf(tmpHero.getDef()));

        if (tmpHero.getHp() <= 0) {
            tmpButton.setEnabled(false);
            if (stopService(new Intent(Fight.this, MusicService.class))) {
                MainActivity.setLevel(null);
                Toast.makeText(getApplicationContext(), "Вы проиграли", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Fight.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }
    }
}

