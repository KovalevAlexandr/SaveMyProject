package com.example.myapplication;

import android.graphics.Color;

import java.util.ArrayList;

public class Generator {
    private int stage;
    private int level;

    public Generator(int stage, int level) {
        this.stage = stage;
        this.level = level;
    }

    public Unit enemyGenerator() {
        ArrayList<Enemy> basEnemy = new ArrayList<>();
        ArrayList<Enemy> bossEnemy = new ArrayList<>();

        if (stage == 1 && level != 10) {
            basEnemy.add(new Enemy(50, 50, 15, 3, "New enemy 1", "1"));
            basEnemy.add(new Enemy(150, 150, 8, 1, "New enemy 2", "2"));
            basEnemy.add(new Enemy(15, 15, 35, 7, "New enemy 3", "3"));
            basEnemy.add(new Enemy(40, 40, 20, 10, "New enemy 4", "4"));
            basEnemy.add(new Enemy(30, 30, 25, 3, "New enemy 5", "5"));
            return basEnemy.get((int) (Math.random() * basEnemy.size()));
        } else {
            bossEnemy.add(new Enemy(250, 250, 15, 10, "New boss 1", "1.1"));
            bossEnemy.add(new Enemy(40, 40, 40, 7, "New boss 1", "1.2"));
            return bossEnemy.get((int) (Math.random() * bossEnemy.size()));
        }
    }

    public ArrayList<ArrayList<Item>> allItems() {

        ArrayList<Item> commonItems = new ArrayList<>();
        ArrayList<Item> rareItems = new ArrayList<>();
        ArrayList<Item> epicItems = new ArrayList<>();
        ArrayList<Item> legendaryItems = new ArrayList<>();

        if (MainActivity.getStage() == 1) {
            commonItems.add(new Item(2, 0, 0, "C.1"));
            commonItems.add(new Item(0, 1, 0, "C.2"));
            commonItems.add(new Item(0, 0, 5, "C.3"));
            commonItems.add(new Item(1, 0, 3, "C.4"));
            commonItems.add(new Item(-1, 1, 2, "C.5"));
            commonItems.add(new Item(1, 1, -2, "C.6"));

            rareItems.add(new Item(5, 0, 0, "R.1"));
            rareItems.add(new Item(0, 2, 4, "R.2"));
            rareItems.add(new Item(2, 1, 3, "R.3"));
            rareItems.add(new Item(5, 2, -7, "R.4"));

            epicItems.add(new Item(-3, 5, 20, "E.1"));
            epicItems.add(new Item(12, -2, -10, "E.2"));

            legendaryItems.add(new Item(7, 3, 10, "L.1"));

        }

        if (MainActivity.getStage() == 2) {

            commonItems.add(new Item(2, 0, 0, "C.7"));
            commonItems.add(new Item(1, 0, 2, "C.8"));
            commonItems.add(new Item(3, 1, -1, "C.9"));
            commonItems.add(new Item(2, 0, 2, "C.10"));
            commonItems.add(new Item(3, -1, 1, "C.11"));

            rareItems.add(new Item(5, 2, 0, "R.5"));
            rareItems.add(new Item(3, 2, 1, "R.6"));
            rareItems.add(new Item(4, 0, 3, "R.7"));
            rareItems.add(new Item(3, 1, 4, "R.8"));

            epicItems.add(new Item(3, 5, 10, "E.3"));
            epicItems.add(new Item(10, -3, 7, "E.4"));

            legendaryItems.add(new Item(9, 5, 12, "L.2"));
        }


        if (MainActivity.getStage() == 3) {

            commonItems.add(new Item(3, 2, 0, "C.12"));
            commonItems.add(new Item(0, 4, 1, "C.13"));
            commonItems.add(new Item(3, 4, -1, "C.14"));
            commonItems.add(new Item(-2, 5, 0, "C.15"));
            commonItems.add(new Item(4, -1, 5, "C.16"));

            rareItems.add(new Item(7, 0, 3, "R.9"));
            rareItems.add(new Item(6, 2, 4, "R.10"));
            rareItems.add(new Item(4, 2, 6, "R.11"));
            rareItems.add(new Item(5, -2, 5, "R.12"));

            epicItems.add(new Item(5, 7, 11, "E.5"));
            epicItems.add(new Item(11, 15, -5, "E.6"));

            legendaryItems.add(new Item(10, 16, 12, "L.3"));
        }


        if (MainActivity.getStage() == 4) {

            commonItems.add(new Item(4, -1, 1, "C.17"));
            commonItems.add(new Item(-1, 3, 3, "C.18"));
            commonItems.add(new Item(3, 3, 2, "C.19"));
            commonItems.add(new Item(3, 0, 1, "C.20"));
            commonItems.add(new Item(3, -2, 5, "C.21"));

            rareItems.add(new Item(9, 0, 4, "R.13"));
            rareItems.add(new Item(10, -4, 4, "R.14"));
            rareItems.add(new Item(5, 7, -3, "R.15"));
            rareItems.add(new Item(4, 8, -3, "R.16"));

            epicItems.add(new Item(-2, 11, 16, "E.7"));
            epicItems.add(new Item(18, 13, -3, "E.8"));

            legendaryItems.add(new Item(11, 14, 19, "L.4"));
        }

        if (MainActivity.getStage() == 5) {

            commonItems.add(new Item(5, 3, -1, "C.22"));
            commonItems.add(new Item(-2, 5, 3, "C.23"));
            commonItems.add(new Item(2, 4, 1, "C.24"));
            commonItems.add(new Item(1, 2, 5, "C.25"));
            commonItems.add(new Item(4, -3, 5, "C.26"));

            rareItems.add(new Item(12, 4, 8, "R.17"));
            rareItems.add(new Item(5, 4, 6, "R.18"));
            rareItems.add(new Item(6, 9, 2, "R.19"));
            rareItems.add(new Item(5, 8, -1, "R.20"));

            epicItems.add(new Item(12, -1, 20, "E.9"));
            epicItems.add(new Item(10, 19, -5, "E.10"));

            legendaryItems.add(new Item(18, 15, 25, "L.5"));
        }

        ArrayList<ArrayList<Item>> allItems = new ArrayList<>();

        allItems.add(commonItems);
        allItems.add(rareItems);
        allItems.add(epicItems);
        allItems.add(legendaryItems);

        return allItems;
    }

    public Item itemForFightGenerator(ArrayList<Item> heroItems) {

        ArrayList<ArrayList<Item>> allItems = allItems();

        Item goItem = null;

        while (goItem == null) {
            int randomItem = ((int) (Math.random() * 100));
            if (randomItem <= 49) {
                goItem = allItems.get(0).get(((int) (Math.random() * allItems.get(0).size())));
            } else if (randomItem <= 74) {
                goItem = allItems.get(1).get(((int) (Math.random() * allItems.get(1).size())));
            } else if (randomItem <= 89) {
                goItem = allItems.get(2).get(((int) (Math.random() * allItems.get(2).size())));
            } else {
                goItem = allItems.get(3).get(((int) (Math.random() * allItems.get(3).size())));
            }
            for (int i = 0; i < heroItems.size(); i++) {
                if (goItem.getModif().equals(heroItems.get(i).getModif())) {
                    goItem = null;
                    break;
                }
            }
        }

        return goItem;
    }

    public ArrayList<Item> itemForShopGenerator(ArrayList<Item> heroItems) {

        ArrayList<ArrayList<Item>> allItems = allItems();

        ArrayList<Item> goInShop = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            int randomItem = ((int) (Math.random() * 100));
            if (randomItem <= 64) {
                goInShop.add(allItems.get(0).get(((int) (Math.random() * allItems.get(0).size()))));
            } else if (randomItem <= 84) {
                goInShop.add(allItems.get(1).get(((int) (Math.random() * allItems.get(1).size()))));
            } else if (randomItem <= 94) {
                goInShop.add(allItems.get(2).get(((int) (Math.random() * allItems.get(2).size()))));
            } else {
                goInShop.add(allItems.get(3).get(((int) (Math.random() * allItems.get(3).size()))));
            }
            Item isFree = goInShop.get(i);
            for (int i1 = 0; i1 < goInShop.size() - 1; i1++) {
                if (isFree.getModif().equals(goInShop.get(i1).getModif())) {
                    goInShop.remove(i);
                    i--;
                    break;
                }
                if (i1 + 1 == goInShop.size() - 1) {
                    for (int i2 = 0; i2 < heroItems.size(); i2++) {
                        if (isFree.getModif().equals(heroItems.get(i2).getModif())) {
                            goInShop.remove(i);
                            i--;
                            break;
                        }
                    }
                }
            }
        }
        return goInShop;
    }

    public String infoAboutItemInShop(Item item) {
        String inf = "";
        if (item.getAtk() != 0) {
            if (item.getAtk() > 0) {
                inf += "Атака: +" + item.getAtk();
                inf += "\n";
            } else {
                inf += "Атака: " + item.getAtk();
                inf += "\n";
            }
        }
        if (item.getDef() != 0) {
            if (item.getDef() > 0) {
                inf += " Защита: +" + item.getDef();
                inf += "\n";
            } else {
                inf += " Защита: " + item.getDef();
                inf += "\n";
            }
        }
        if (item.getMaxHp() != 0) {
            if (item.getMaxHp() > 0) {
                inf += " Макс HP: +" + item.getMaxHp();
                inf += "\n";
            } else {
                inf += " Макс HP: " + item.getMaxHp();
                inf += "\n";
            }
        }
        switch (item.getModif().split("\\.")[0]) {
            case "C":
                inf += ".Обычный";
                break;
            case "R":
                inf += ".Редкий";
                break;
            case "E":
                inf += ".Эпичный";
                break;
            default:
                inf += ".Легендарный";
                break;
        }
        return inf;
    }

    public int priceOfItemInStore(Item item) {
        int i;
        switch (item.getModif().split("\\.")[0]) {
            case "C":
                i = 15 * stage;
                break;
            case "R":
                i = 40 * stage;
                break;
            case "E":
                i = 70 * stage;
                break;
            default:
                i = 120 * stage;
                break;
        }
        return i;
    }

    public int colorOfItemRarity(Item item) {
        int green = Color.parseColor("#47A04A");
        int blue = Color.parseColor("#3F51B5");
        int purple = Color.parseColor("#9C27B0");
        int orange = Color.parseColor("#E25324");
        switch (item.getModif().split("\\.")[0]) {
            case "C":
                return green;
            case "R":
                return blue;
            case "E":
                return purple;
            default:
                return orange;
        }
    }

    public String allForLevelUpSkill(int skillLevel, int maxSkillLevel, int needPoint, int needHeroLevel) {
        return "Нужный уровень героя: " + needHeroLevel + "\n" +
                "Нужно очков: " + needPoint + "\n" +
                "Уровень навыка: " + skillLevel + "/" + maxSkillLevel;
    }
}

