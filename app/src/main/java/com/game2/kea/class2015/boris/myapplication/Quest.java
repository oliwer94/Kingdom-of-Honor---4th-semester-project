package com.game2.kea.class2015.boris.myapplication;

import java.io.Serializable;

/**
 * Created by oliwer on 07/05/2015.
 */
public class Quest implements Serializable
{
    private static final long serialVersionUID = 0L;
    private String name;
    private String description;
    private int lvl_requirements;

    private Boolean isAccepted;
    private Boolean isCompleted;
    private Boolean isPayed;
    private Boolean wasShowed;
    private Boolean didProgress;

    private Rewards reward;
    private Task task;



    public Quest(String name,String descr,Boolean isAccepted,int lvl_requirements,Rewards reward, Task task)
    {
        this.name = name;
        this.description = descr;
        this.isAccepted = isAccepted;
        this.lvl_requirements = lvl_requirements;
        this.reward = reward;
        this.task = task;
        this.isCompleted = false;
        this.isPayed = false;
        this.wasShowed = false;
        this.didProgress = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(Boolean isAccepted)
    {
        this.isAccepted = isAccepted;
    }

    public int getLvl_requirements() {
        return lvl_requirements;
    }

    public Rewards getReward() {
        return reward;
    }

    public Task getTask() {
        return task;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted)
    {
        this.isCompleted = isCompleted;
    }

    public Boolean getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(Boolean isPayed) {
        this.isPayed = isPayed;
    }

    public Boolean getWasShowed() {
        return wasShowed;
    }

    public void setWasShowed(Boolean wasShowed) {
        this.wasShowed = wasShowed;
    }

    public Boolean getdidProgress() {
        return didProgress;
    }

    public void setdidProgress(Boolean didProgress) {
        this.didProgress = didProgress;
    }
}
