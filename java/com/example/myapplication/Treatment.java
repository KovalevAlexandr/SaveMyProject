package com.example.myapplication;

import java.util.ArrayList;

public class Treatment {
    static public Character battleWin(Character hero, int stage, int level) {

        if (level != 5) {
            hero.setExp(hero.getExp() + 5 * stage + level);
        } else {
            hero.setExp(hero.getExp() + 10 * stage + level);
        }

        if (level != 5) {
            hero.setMoney(hero.getMoney() + 5);
        } else {
            hero.setMoney(hero.getMoney() + 10);
        }

        while (true) {
            int needExp = 10;
            for (int i = 0; i < hero.getLvl(); i++) {
                needExp += needExp * 0.3;
            }
            if (hero.getExp() >= needExp) {
                hero.setExp(hero.getExp() - needExp);
                hero.setLvl(hero.getLvl() + 1);
                hero.setMaxHp((int) (hero.getMaxHp() + hero.getMaxHp() * 0.2));
                if (hero.getHp() + hero.getMaxHp() * 0.25 < hero.getMaxHp()) {
                    hero.setHp((int) (hero.getHp() + hero.getMaxHp() * 0.25));
                } else {
                    hero.setHp(hero.getMaxHp());
                }
                hero.setAtk((int) (hero.getAtk() + hero.getAtk() * 0.15));
                hero.setDef((int) (hero.getDef() + hero.getDef() * 0.1));
                hero.setImpPoint(hero.getImpPoint() + 1);
            } else {
                break;
            }
        }

        MainActivity.setLevel(MainActivity.getLevel() + 1);
        return hero;
    }

    static class Fight {
        Character hero;
        ArrayList<Item> heroItems;
        Enemy enemy;

        public Fight(Character hero, ArrayList<Item> heroItems, Enemy enemy) {
            this.hero = hero;
            this.heroItems = heroItems;
            this.enemy = enemy;
        }

        public Character setFightIndicator() {

            for (int i = 0; i < hero.getSkills().size(); i++) {
                if (hero.getSkills().get(i).getSkill().split("\\.")[0].equals("1")) {
                    if (hero.getSkills().get(i).getSkill().split("\\.")[1].equals("atk_up")
                            && hero.getSkills().get(i).isFlag()) {
                        hero.setAtk((int) (hero.getAtk() * 1.1));
                    }
                }
            }

            for (int i = 0; i < heroItems.size(); i++) {
                hero.setAtk(hero.getAtk() + heroItems.get(i).getAtk());
                hero.setDef(hero.getDef() + heroItems.get(i).getDef());
                hero.setMaxHp(hero.getMaxHp() + heroItems.get(i).getMaxHp());
            }
            return hero;
        }

        public boolean skillIsReady(String mod) {
            boolean flag = false;
            for (int i = 0; i < hero.getSkills().size(); i++) {
                String eMod = hero.getSkills().get(i).getSkill();
                if (eMod.split("\\.")[1].equals(mod)) {
                    int step = Integer.parseInt(eMod.split("\\.")[2]);
                    if (step == 0) {
                        flag = true;
                    }
                }
            }
            return flag;
        }

        public ArrayList<Skill> setSkillStep(String target) {
            ArrayList<Skill> skills = hero.getSkills();
            for (int i = 0; i < skills.size(); i++) {
                String info = skills.get(i).getSkill();
                if (!info.split("\\.")[0].equals("1")) {
                    if (info.split("\\.")[1].equals(target)) {
                        int up = Integer.parseInt(info.split("\\.")[3]);
                        skills.get(i).setSkill(info.split("\\.")[0]
                                + "." + info.split("\\.")[1] + "." +
                                up + "." + info.split("\\.")[3]);
                    }
                }
            }
            return skills;
        }

        public ArrayList<Skill> skillsUpdateStep(String target) {
            ArrayList<Skill> skills = hero.getSkills();
            for (int i = 0; i < skills.size(); i++) {
                String info = skills.get(i).getSkill();
                if (!info.split("\\.")[0].equals("1")) {
                    if (!info.split("\\.")[1].equals(target)) {
                        int step = Integer.parseInt(info.split("\\.")[2]);
                        if (step > 0) {
                            step--;
                            skills.get(i).setSkill(info.split("\\.")[0]
                                    + "." + info.split("\\.")[1] + "." +
                                    step + "." + info.split("\\.")[3]);
                        }
                    }
                }
            }
            return skills;
        }

        public ArrayList<Skill> setSkillsAfterFight() {
            ArrayList<Skill> skills = hero.getSkills();
            for (int i = 0; i < skills.size(); i++) {
                String info = skills.get(i).getSkill();
                if (!info.split("\\.")[0].equals("1")) {
                    skills.get(i).setSkill(info.split("\\.")[0]
                            + "." + info.split("\\.")[1] + "." +
                            0 + "." + info.split("\\.")[3]);
                }
            }
            return skills;
        }

        public int basAttack() {
            int dmg = hero.getAtk() - enemy.getDef();
            enemy.setHp(Math.max(enemy.getHp() - dmg, 0));
            return enemy.getHp();
        }

        public int secondAttack() {
            int dmg = (int) (hero.getAtk() - enemy.getDef() * 0.7);
            enemy.setHp(Math.max(enemy.getHp() - dmg, 0));
            return enemy.getHp();
        }
    }

}
