package com.game2.kea.class2015.boris.myapplication;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by oliwer on 09/05/2015.
 */
public class QuestController
{
    //   EVERYTHING TO WITH QUESTS IS BELOW THIS LINE _______________________________________________

    public ArrayList<Quest> Quests = new ArrayList<Quest>();
    public ArrayList<Quest> PlayerQuests = new ArrayList<Quest>();

    /*Reward(xp, lvl, item, gold, str, armor, hp)
    Example for creating a Quest
    private Quest q = Kill_X_monster("Dancing with the Wolves","Kill 20 wolf during your hunting",
                                        3,20,new Rewards(100,0,null,100,1,1,10));
    */

    //MAIN -  QUEST PROGRESS CHECK SECTION--------------------------------------------------

    //Checks the player's quest and updates their progress
    public Player updateQuestsProgress(Player player,Mob monster)
    {
        int index = 0;
        for(Iterator<Quest> i = player.getMyQuests().iterator(); i.hasNext(); )
        {
            Quest quest = i.next();
            switch ( player.getMyQuests().get(index).getTask().getType()) {
                case KILL_X_AMOUNT_OF_MONSTERS:Kill_X_monster_Update(index);break;
                case KILL_X_AMOUNT_OF_Y_MONSTERS:Kill_X_amount_Y_monster_Update(index, monster.getName());break;
                case COLLECT_X_GOLD:Collect_X_gold_Update(index,monster);break;
                case COLLECT_X_ITEM:Collect_X_item_Update(index,player);break;
            }
            index++;
            player = RefreshPlayerQuests(player);
        }
        return player;
    }

    //QUEST PROGRESS HANDLERS --------------------------------------------------

    public void Kill_X_monster_Update(int index)
    {
        PlayerQuests.get(index).getTask().setProgress(PlayerQuests.get(index).getTask().getProgress() + 1);

        if(PlayerQuests.get(index).getTask().getProgress() == PlayerQuests.get(index).getTask().getAmount())
        {
            PlayerQuests.get(index).setIsCompleted(true);
        }

        PlayerQuests.get(index).setdidProgress(true);

    }

    public void Kill_X_amount_Y_monster_Update(int index,String mobname)
    {
        if(mobname.equals(PlayerQuests.get(index).getTask().getMob_name()))
        {
            PlayerQuests.get(index).getTask().setProgress(PlayerQuests.get(index).getTask().getProgress() + 1);

            if (PlayerQuests.get(index).getTask().getProgress() == PlayerQuests.get(index).getTask().getAmount()) {
                PlayerQuests.get(index).setIsCompleted(true);
            }

            PlayerQuests.get(index).setdidProgress(true);
        }
    }

    public void Collect_X_gold_Update(int index,Mob monster)
    {
        PlayerQuests.get(index).getTask().setProgress(PlayerQuests.get(index).getTask().getProgress() + monster.getGold_drop());

        if(PlayerQuests.get(index).getTask().getProgress() >= PlayerQuests.get(index).getTask().getAmount())
        {
            PlayerQuests.get(index).setIsCompleted(true);
        }
        PlayerQuests.get(index).setdidProgress(true);
    }

    public void Collect_X_item_Update(int index, Player player)
    {
        for(Iterator<Item> i = player.getMyItems().iterator(); i.hasNext();)
        {
            Item item = i.next();

            if(item.getName().equals(player.getMyQuests().get(index).getTask().getItem().getName()))
            {
                PlayerQuests.get(index).setIsCompleted(true);
            }
        }
    }

    //QUESTS CREATOR SECTION-------------------------------------------------------------------

    public Quest Kill_X_monster(String name,String descr,int lvlreq,int amount,Rewards reward)
    {
        Task task = new Task(QuestType.KILL_X_AMOUNT_OF_MONSTERS,amount);
        Quest quest = new Quest(name,descr,false,lvlreq,reward,task);

        return quest;
    }

    public Quest Kill_X_amount_Y_monster(String name,String descr,int lvlreq,String mob_name,int amount,Rewards reward)
    {
        Task task = new Task(QuestType.KILL_X_AMOUNT_OF_Y_MONSTERS,mob_name,amount);
        Quest quest = new Quest(name,descr,false,lvlreq,reward,task);

        return quest;
    }

    public Quest Collect_X_gold(String name,String descr,int lvlreq,int amount,Rewards reward)
    {
        Task task = new Task(QuestType.COLLECT_X_GOLD,amount);
        Quest quest = new Quest(name,descr,false,lvlreq,reward,task);

        return quest;
    }

    public Quest Collect_X_item(String name,String descr,int lvlreq,Item item,Rewards reward)
    {
        Task task = new Task(QuestType.COLLECT_X_ITEM,item);
        Quest quest = new Quest(name,descr,false,lvlreq,reward,task);

        return quest;
    }

    //Descriptions for different quest-types-----------------------------------------------------

    //Returns a generic Quest description
    public String getQuestDescr(int selectedItemId)
    {
        return new String("Name: "+Quests.get(selectedItemId).getName() + "\n" +
                "Description:"+Quests.get(selectedItemId).getDescription() + "\n" +
                "Level requirements: "+Quests.get(selectedItemId).getLvl_requirements() + "\n" +
                "Reward: "+Quests.get(selectedItemId).getReward().toString());
    }

    //Returns a specific Quest description (for accepted Quests)
    public String QuestType_1_2_3(int selectedItemId)
    {
        return new String("Name:"+PlayerQuests.get(selectedItemId).getName() + "\n" +
                "Description:"+PlayerQuests.get(selectedItemId).getDescription() + "\n" +
                "Progress:"+PlayerQuests.get(selectedItemId).getTask().getProgress()+"/"+ PlayerQuests.get(selectedItemId).getTask().getAmount()+ "\n" +
                "Reward:"+PlayerQuests.get(selectedItemId).getReward().toString());
    }
    //Returns a specific Quest description (for accepted Quests)
    public String getPlayerQuestDescr(int selectedItemId)
    {

        switch(PlayerQuests.get(selectedItemId).getTask().getType())
        {
            case KILL_X_AMOUNT_OF_MONSTERS: return QuestType_1_2_3(selectedItemId);
            case KILL_X_AMOUNT_OF_Y_MONSTERS:return QuestType_1_2_3(selectedItemId);
            case COLLECT_X_GOLD:return QuestType_1_2_3(selectedItemId);
            case COLLECT_X_ITEM:break;
        }
        return "";
    }

    //-----------------------------------------------------------------------------------------

    //OTHER Helper methodes-------------------------------------------------------------------

    //Returns a sub-array of the PlayerQuest (Completed or in Progress)
    public ArrayList<Quest> subArray(String status)
    {

        ArrayList<Quest> quest = new ArrayList<Quest>();

        for(int i = 0;i < PlayerQuests.size();i++)
        {
            switch(status)
            {
                case"Completed":
                    if (PlayerQuests.get(i).getIsPayed())
                    {
                        quest.add(PlayerQuests.get(i));
                    }
                    break;
                case"Ongoing":
                    if (!PlayerQuests.get(i).getIsPayed())
                    {
                        quest.add(PlayerQuests.get(i));
                    }
                    break;
            }
        }
        return quest;
    }

    public Player ShowQuestProgress(MainActivity context,Player player) {

        int index = 0;
        for(Iterator<Quest> i = PlayerQuests.iterator(); i.hasNext(); )
        {
            Quest quest = i.next();

            if(!quest.getIsCompleted())
            {
                if(quest.getdidProgress())
                {
                    switch (quest.getTask().getType())
                    {
                        case KILL_X_AMOUNT_OF_MONSTERS:
                            Toast.makeText(context, "You killed " + quest.getTask().getProgress() + "/" + quest.getTask().getAmount() + "mobs", Toast.LENGTH_SHORT).show();
                            player.getMyQuests().get(index).setdidProgress(false);
                            PlayerQuests.get(index).setdidProgress(false);
                            break;
                        case KILL_X_AMOUNT_OF_Y_MONSTERS:
                            Toast.makeText(context, "You killed " + quest.getTask().getProgress() + "/" + quest.getTask().getAmount() + quest.getTask().getMob_name(), Toast.LENGTH_SHORT).show();
                            player.getMyQuests().get(index).setdidProgress(false);
                            PlayerQuests.get(index).setdidProgress(false);
                            break;
                        case COLLECT_X_GOLD:
                            Toast.makeText(context, "You collected " + quest.getTask().getProgress() + "/" + quest.getTask().getAmount() + "gold", Toast.LENGTH_SHORT).show();
                            player.getMyQuests().get(index).setdidProgress(false);
                            PlayerQuests.get(index).setdidProgress(false);
                            break;
                        case COLLECT_X_ITEM:
                            //Toast.makeText(context,"You killed "+quest.getTask().getProgress() + "/"+quest.getTask().getAmount()+"mobs",Toast.LENGTH_LONG).show();
                           // player.getMyQuests().get(index).setdidProgress(false);
                            //PlayerQuests.getMyQuests().get(index).setdidProgress(false);
                            break;
                    }
                }
            }
            else
            {
                if(!quest.getWasShowed()) {
                    Toast.makeText(context, quest.getName() + " has been completed. Go take your reward!", Toast.LENGTH_SHORT).show();
                    player.getMyQuests().get(index).setWasShowed(true);
                    PlayerQuests.get(index).setWasShowed(true);
                }
            }
            index++;

        }

        return player;
    }

    //Generates the Quests in case we dont already have them
    public void generateQuests()
    {
        Quest q1 = Kill_X_monster("Slaughter","Kill 20 mobs during your hunting",2,20,new Rewards(100,0,null,100,1,1,10));
        Quest q2 = Kill_X_amount_Y_monster("Be Putin","Kill 3 bears in the forest",1,"bear",3,new Rewards(200,0,null,100,1,1,10));
        Quest q3 = Collect_X_gold("Gold digger","Collect 200 gold",1,200,new Rewards(0,0,null,250,1,1,10));

        Quest q4 = Kill_X_monster("Holy Molly","Kill 3 mobs in the forest",3,3,new Rewards(100,0,null,100,1,1,10));
        Quest q5 = Kill_X_amount_Y_monster("Dancing with the Wolves","Kill 20 wolves during your hunting",2,"wolf",20,new Rewards(200,0,null,100,1,1,10));
        Quest q6 = Collect_X_gold("Gold miner","Collect 300 gold",2,300,new Rewards(0,0,null,250,1,1,10));

        Quest q7 = Kill_X_monster("Mom's spaghetti","Kill 5 mobs in mom's house",3,20,new Rewards(100,0,null,100,1,1,10));
        Quest q8 = Kill_X_amount_Y_monster("Tigers","Kill 15 bears in the forest",2,"tiger",15,new Rewards(200,0,null,100,1,1,10));
        Quest q9 = Collect_X_gold("Gold digger","Collect 50 gold",2,50,new Rewards(0,0,null,250,1,1,10));

        Quest q10 = Kill_X_amount_Y_monster("Be Putin","Kill 10 bears in the forest",2,"bear",10,new Rewards(200,0,null,100,1,1,10));

        Quests.add(q1); Quests.add(q2); Quests.add(q3);
        Quests.add(q4); Quests.add(q5); Quests.add(q6);
        Quests.add(q7); Quests.add(q8); Quests.add(q9);
        Quests.add(q10);
    }

    //Add Quest to player's ques list
    public Player  AddQuestToPlayer(int selectedItemId, Player player,Context context)
    {
        if(player.getLevel() >= Quests.get(selectedItemId).getLvl_requirements())
        {
            Quests.get(selectedItemId).setIsAccepted(true);
            PlayerQuests.add(Quests.get(selectedItemId));
            Quests.remove(selectedItemId);

            player = RefreshPlayerQuests(player);
        }
        else
        {
            Toast.makeText(context, "You need "+(Quests.get(selectedItemId).getLvl_requirements()-player.getLevel())+" level to be able to accept this Quest!",Toast.LENGTH_SHORT).show();
        }

        return player;
    }

    //Refreshes playerQuests list
    private  Player RefreshPlayerQuests(Player player)
    {
        player.setMyQuests(PlayerQuests);

        return player;
    }

    //When the Quest is completed and the player clicks Completed adds the Quest's reward to the player
    public Player TakeReward(int selectedItemId, Player player)
    {
        player.CollectReward(PlayerQuests.get(selectedItemId).getReward());
        PlayerQuests.get(selectedItemId).setIsPayed(true);
        player = RefreshPlayerQuests(player);

        return player;
    }

    //---------------------------------------------------------------------------------------
}
