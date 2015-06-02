package com.game2.kea.class2015.boris.myapplication;

import java.io.Serializable;

/**
 * Created by oliwer on 07/05/2015.
 */
public class Rewards implements Serializable
{
    private static final long serialVersionUID = 0L;
    private int xp;
    private int lvl;
    private Item item;
    private int gold;
    private int str;
    private int armor;
    private int hp;

    public Rewards(int xp,int lvl,Item item,int gold,int str,int armor,int hp)
    {
        this.xp = xp;
        this.lvl = lvl;
        this.item = item;
        this.gold = gold;
        this.str = str;
        this.armor = armor;
        this.hp = hp;
    }

    public String toString()
    {
        return new String("\n  experience: "+ this.xp+"\n"+
                          "  Level: "+ this.lvl+"\n"+
                          "  Gold: "+ this.gold+"\n"+
                          "  Strength: "+ this.str+"\n"+
                          "  Armor: "+ this.armor+"\n"+
                          "  Health: "+ this.hp);
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }



}
