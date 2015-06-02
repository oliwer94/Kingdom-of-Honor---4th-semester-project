package com.game2.kea.class2015.boris.myapplication;

/**
 * Created by oliwer on 16/04/2015.
 */


/**
 * Created by oliwer on 23/03/2015.
 */
/**
 * Created by oliwer on 23/03/2015.
 */
public class Mob {

    private String name;
    private String race;
    private int gold_drop;
    private String classs;
    private int level;
    private int exp_drop;
    private double itemdrop_chance;
    // Stats
    private int hp;
    private int str;
    private int armor;
    public Mob(String name, String race, int gold_drop, String classs, int level, double itemdrop_chance,int _hp) {
        this.name = name;
        this.race = race;
        this.gold_drop = gold_drop;
        this.classs = classs;
        this.level = level;
        this.itemdrop_chance = itemdrop_chance;
        this.exp_drop = this.level * 20;

        this.hp = _hp;
        this.str = this.level*2;
        this.armor = this.level * 6;
    }

    public String getName() {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public int getGold_drop() {
        return gold_drop;
    }

    public String getClasss() {
        return classs;
    }

    public int getLevel() {
        return level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }


    public double getItemdrop_chance() {
        return itemdrop_chance;
    }

    public int getExp_drop()
    {
        return exp_drop;
    }

    public int getStr()
    {
        return str;
    }

    public void setStr(int str)
    {
        this.str = str;
    }

    public int getArmor()
    {
        return armor;
    }

    public void setArmor(int armor)
    {
        this.armor = armor;
    }

}
