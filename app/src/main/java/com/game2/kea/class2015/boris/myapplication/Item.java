package com.game2.kea.class2015.boris.myapplication;

/**
 * Created by oliwer on 16/04/2015.
 */


import java.io.Serializable;

/**
 * Created by oliwer on 23/03/2015.
 */
public class Item implements Serializable
{
    private static final long serialVersionUID = 0L;
    private String name;
    private String race;
    private int buy_price;
    private int sell_price;
    private String classs;
    private int level;
    private String description;
    private int img;
    private Boolean isEquipped;
    private Boolean owned;
    // stats
    private int str;
    private int armor;
    private int hp;

    public Item(String _name, String _classs, String _description, int _str,
                int _hp, int _armor, int _price,int _img,int lvl)
    {
        this.name = _name;
        this.race = "race";
        this.buy_price = _price;
        this.classs = _classs;
        this.level = lvl;
        this.str = _str;
        this.hp = _hp;
        this.armor = _armor;
        this.sell_price = this.buy_price / 2;
        this.description = _description;
        this.img = _img;
        this.isEquipped = false;
        this.owned = false;
    }

    public String getName()
    {
        return name;
    }

    public String getRace()
    {
        return race;
    }

    public String getClasss()
    {
        return classs;
    }

    public int getBuy_price()
    {
        return buy_price;
    }

    public int getSell_price()
    {
        return sell_price;
    }

    public void setBuy_price(int buy_price)
    {
        this.buy_price = buy_price;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public int getStr()
    {
        return str;
    }

    public void setStr(int str)
    {
        this.str = str;
    }

    public int getImg()
    {
        return img;
    }

    public void setImg(int img)
    {
        this.img = img;
    }

    public int getArmor()
    {
        return armor;
    }

    public void setArmor(int armor)
    {
        this.armor = armor;
    }

    public int getHp()
    {
        return hp;
    }

    public void setHp(int hp)
    {
        this.hp = hp;
    }

    public Boolean getIsEquipped()
    {
        return isEquipped;
    }

    public void setIsEquipped(Boolean isEquipped)
    {
        this.isEquipped = isEquipped;
    }

    public Boolean getOwned()
    {
        return owned;
    }

    public void setOwned(Boolean owned)
    {
        this.owned = owned;
    }


}

