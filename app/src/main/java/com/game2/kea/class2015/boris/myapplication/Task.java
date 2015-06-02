package com.game2.kea.class2015.boris.myapplication;

import java.io.Serializable;

/**
 * Created by oliwer on 07/05/2015.
 */
public class Task implements Serializable
   {
       private static final long serialVersionUID = 0L;
    private String mob_name;
    private int amount;
    private int progress;
    private QuestType type;
    private Item item;
    private int timer;

    //Constructor for killing X amount of Y mobs
    public Task(QuestType type,String mob_name,int amount)
    {
        this.mob_name = mob_name;
        this.amount = amount;
        this.progress = 0;
        this.type = type;
    }

    //Constructor for killing / collecting X amount of gold/mob / killing a sequence of monsters
    public Task(QuestType type,int amount)
    {
        if(type == QuestType.SURVIVAL_FOR_X_SECONDS)
        {
            this.type = type;
            this.timer = amount;
        }
        else {
            this.amount = amount;
            this.progress = 0;
            this.type = type;
        }
    }

    //Constructor for finding a specific item
    public Task(QuestType type,Item item)
    {
        this.item = item;
        this.type = type;
    }

    //Constructor for killing x under y seconds
    public Task(QuestType type,int amount,int timer)
    {
        this.amount = amount;
        this.progress = 0;
        this.type = type;
        this.timer = timer;
    }

    //Constructor for survival
    public Task(QuestType type)
    {
        this.type = type;
    }



    public String getMob_name() {
        return mob_name;
    }

    public int getAmount() {
        return amount;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public QuestType getType() {
        return type;
    }

    public Item getItem(){return item;}
}
