package com.game2.kea.class2015.boris.myapplication;

/**
 * Created by oliwer on 16/04/2015.
 */

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;



public class Controller {

    public Player player;

    // public ArrayList<Item> Items = new ArrayList<Item>();
    //public ArrayList<Item> PlayerItems = new ArrayList<Item>();

    public boolean farming = false;
    // public boolean success = false; //for checking if the purchase was successful or not
    public int selectedItemId;

    private String plyrstr = "";
    private String plyrstraftr = "";
    private String mobstraftr = "";
    private String mobhp = "";
    private String playerhp = "";
    private String plyrgold = "";
    private String plyrlvl = "";
    private String mobname = "";
    private String mobarmor = "";
    private String mobmaxhp = "";
    private String mobstr = "";
    private Boolean refresh = false;
    private Mob monster;

    private static Random rand = new Random();

    protected MainActivity context;
    public QuestController qc;
    public ItemController ic;

    // Constructor
    public Controller(MainActivity context) {
        this.context = context;
        this.qc = new QuestController();
        this.ic = new ItemController();
    }

    //Create new Player
    public void createPlayer(String name, String race, String classs, String origin, int icon)
    {
        player = new Player(name, race, origin, classs, icon);
        ic.Items = new ArrayList<Item>();
        qc.Quests = new ArrayList<Quest>();
        setPlayerLists();

        qc.generateQuests();
        ic.generateXitems();

        SaveItems();
        SaveQuests();
        SavePlayer();

    }

    //Farming


    public void Farming() {

        String[] names = {"wolf", "tiger", "bear"};
        Random r = new Random();

        do {
            monster = new Mob(names[r.nextInt(3)], "monster",
                    r.nextInt(41) + 5, "melee",
                    r.nextInt(player.getLevel() + 2) + 1, 0.1,
                    player.getLevel() * 75);

            plyrgold = Integer.toString(player.getGold());
            plyrlvl = Integer.toString(player.getLevel());
            plyrstr = Integer.toString(player.getStr());
            mobname = monster.getName();
            mobarmor = Integer.toString(monster.getArmor());
            mobstr = Integer.toString(monster.getStr());
            plyrstraftr = Integer.toString((monster.getHp() - dmgtaken(monster.getArmor(), monster.getHp(), player.getStr())));
            mobstraftr = Integer.toString((player.getHealth() - dmgtaken(player.getArmor(), player.getHealth(), monster.getStr())));
            mobmaxhp = Integer.toString(monster.getHp());
            mobhp = mobmaxhp;
            playerhp = Integer.toString(player.getHealth());
            refresh = false;
            update();

            do {
                int hpm = monster.getHp();
                int hpp = player.getHealth();

                mobhp = Integer.toString(hpm);
                playerhp = Integer.toString(hpp);

                monster.setHp(dmgtaken(monster.getArmor(), monster.getHp(), player.getStr()));
                player.setHealth(dmgtaken(player.getArmor(),player.getHealth(),monster.getStr()));

                hpm = monster.getHp();
                if (hpm < 0) {
                    hpm = 0;
                    monster.setHp(0);
                }
                mobhp = Integer.toString(hpm);

                hpp = player.getHealth();
                if (hpp <= 0) {
                    hpp = 0;
                    player.setHealth(0);
                    this.farming = false;
                    mobhp = Integer.toString(hpm);
                    playerhp = Integer.toString(hpp);

                    break;
                }

                mobhp = Integer.toString(hpm);
                playerhp = Integer.toString(hpp);

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }

                update();

            } while (monster.getHp() > 0);

            if(player.getHealth() > 0 ) {
                player.setGold(player.getGold() + monster.getGold_drop());
                player.setExperience(player.getExperience() + monster.getExp_drop());
                plyrgold = Integer.toString(player.getGold());
                plyrlvl = Integer.toString(player.getLevel());
                refresh = true;
                UQP();
            }

            update();

        } while (farming);

        context.stopfarming();
        Save();
    }

    // Damage after reduction calculator
    private static int dmgtaken(int armor, int hp, int str) {
        double s = str;
        double ar = armor;
        double dmg = s * (100 / (ar + 100));
        return hp - (int) dmg;
    }

    //Updates GUI-elements on the Farming-View
    public void update() {
        String a = Integer.toString(player.getArmor());
        context.Kappa(mobstraftr, mobarmor,a, plyrstraftr,mobname, player.getImg(), player.getNext_lvl_exp_req(), player.getExperience(), plyrgold, plyrlvl, mobmaxhp, mobhp, refresh,playerhp,Integer.toString(player.getMaxhealth()));
    }


    //QUEST-------------------------------------------------------------------------

    public void UQP() {
        this.player = qc.updateQuestsProgress(player, monster);
    }

    public void TakeReward() {
        this.player = qc.TakeReward(selectedItemId, player);Save();
    }

    public void AcceptQuest() {
        this.player = qc.AddQuestToPlayer(selectedItemId, player, context);Save();
    }

    public String getQuestDescription() {
        return qc.getQuestDescr(selectedItemId);
    }

    public String getPlayerQuestDescription() {
        return qc.getPlayerQuestDescr(selectedItemId);
    }

    public void showQuestProgress() {
        this.player = qc.ShowQuestProgress(context, player);
    }

    public ArrayList<Quest> subArrayList(String status) {
        return qc.subArray(status);
    }


    //ITEMS-----------------------------------------------------------------------------

    public void Equip_Unequip_Item() {
        this.player = ic.Equip_Unequip(player, selectedItemId, context);Save();
    }

    public String ShopItemDescription() {
        return ic.Descript_Item(selectedItemId);
    }

    public String PlayerItemDescription() {
        return ic.Descript_PlayerItem(selectedItemId);
    }

    public void AddItemToPlayer() {
        this.player = ic.AddItemtoPlayer(player, selectedItemId);Save();
    }

    public void Drop_Item() {
        this.player = ic.DropItem(player, selectedItemId);Save();
    }

    public void Buy() {
        this.player = ic.buy(player, selectedItemId, context);Save();
    }

    public void Sell() {
        this.player = ic.sell(player, selectedItemId);Save();
    }

    //Saving and Loading -----------------------------------------------------


    public void Save()
    {
        SavePlayer();
        SaveQuests();
        SaveItems();
    }

    public void SaveQuests()
    {
        ArrayList<Quest> allQuests = qc.Quests;

        String filename;
        FileOutputStream fos;

        try {
            filename = "Saved_Quests.xml";
            fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(allQuests);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SaveItems()
    {
        ArrayList<Item> allItems = ic.Items;

        String filename;
        FileOutputStream fos;

        try {

            filename = "Saved_Items.xml";
            fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(allItems);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SavePlayer()
    {
        String filename;
        FileOutputStream fos;

        try {

            filename = "Saved_Player.xml";
            fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(player);
            oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void DataForNewGame()
    {
        String filename;
        FileInputStream fos;

        try {

            filename = "Items.xml";
            fos = context.openFileInput(filename);
            ObjectInputStream oos = new ObjectInputStream(fos);

            ic.Items = (ArrayList<Item>) oos.readObject();
            oos.close();

            filename = "Quests.xml";
            fos = context.openFileInput(filename);
            oos = new ObjectInputStream(fos);

            qc.Quests = (ArrayList<Quest>) oos.readObject();
            oos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Load()
    {
        String filename;
        FileInputStream fos;

        try {

            filename = "Saved_Items.xml";
            fos = context.openFileInput(filename);
            ObjectInputStream oos = new ObjectInputStream(fos);

            ic.Items = (ArrayList<Item>) oos.readObject();
            oos.close();

            filename = "Saved_Player.xml";
            fos = context.openFileInput(filename);
            oos = new ObjectInputStream(fos);

            player = (Player) oos.readObject();
            oos.close();

            filename = "Saved_Quests.xml";
            fos = context.openFileInput(filename);
            oos = new ObjectInputStream(fos);

            qc.Quests = (ArrayList<Quest>) oos.readObject();
            oos.close();




        } catch (Exception e) {
            e.printStackTrace();
        }

        setPlayerLists();
    }

    private void setPlayerLists()
    {
        int a = 5;
        ic.PlayerItems = player.getMyItems();
        qc.PlayerQuests = player.getMyQuests();
    }

    // GETTERS and SETTERS -----------------------------------------------------------------------


    //Checks if the Item is equipped by the player
    public  Boolean isEquipped() {
        return ic.PlayerItems.get(selectedItemId).getIsEquipped();
    }

    //Returns the player's gold
    public  int getGold()
    {
        return player.getGold();
    }

    //Returns the player's gold
    public  String getName()
    {
        return player.getName();
    }

    //Returns the player's gold
    public  int getUP()
    {
        return player.getUpgrade_points();
    }

    public  void setUP()
    {
        player.setUpgrade_points(player.getUpgrade_points() - 1);
    }

    //Returns the player's gold
    public  int getArmor()
    {
        return player.getArmor();
    }

    public  void setArmor()
    {
        player.setArmor(player.getArmor() + 1);
    }

    //Returns the player's gold
    public  int getStr()
    {
        return player.getStr();
    }

    public  void setStr()
    {
        player.setStr(player.getStr() + 1);
    }

    //Returns the player's gold
    public  int getHP()
    {
        return player.getMaxhealth();
    }

    public  void setHP()
    {
        player.setHealth(player.getMaxhealth() + 10);
    }

    public Boolean FullHp()
    {
        if(player.getHealth() == player.getMaxhealth())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void regen() {

        do{
        player.setHealth(player.getHealth()+1);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }}
        while(player.getHealth() < player.getMaxhealth());

    }

    //-----------------------------------------------------------------------------------------
}
