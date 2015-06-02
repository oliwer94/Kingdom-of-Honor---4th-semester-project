package com.game2.kea.class2015.boris.myapplication;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by oliwer on 09/05/2015.
 */
public class ItemController {

    public ArrayList<Item> Items = new ArrayList<Item>();
    public ArrayList<Item> PlayerItems = new ArrayList<Item>();
    public boolean success = false; //for checking if the purchase was successful or not
    private static Random rand = new Random();
    // METHODES FOR THE ITEMS --------------------------------------------------------------

    //Add new item to the List (Shop is using this list)
    private  void AddNewItem(Item item)
    {
        Items.add(item);
    }

    //Create new Item
    public  void generateItem(String name, String classs, String description, int str, int hp, int armor, int price,int img,int lvl)
    {
        AddNewItem(new Item(name, classs, description, str, hp, armor, price, img,lvl));
    }

    //Equip / Unequip item
    public  Player Equip_Unequip(Player player,int selectedItemId,MainActivity context)
    {
        if(player.getMyItems().size()!= 0) {
            if (player.getLevel() >= PlayerItems.get(selectedItemId).getLevel()) {
                if (PlayerItems.get(selectedItemId).getIsEquipped()) {
                    player.Unequip_item(selectedItemId);
                    PlayerItems.get(selectedItemId).setIsEquipped(false);

                } else {
                    player.Equip_item(selectedItemId);
                    PlayerItems.get(selectedItemId).setIsEquipped(true);
                }
                RefreshPlayerItems(player);
            } else {
                Toast.makeText(context, "You can't use this Item.", Toast.LENGTH_SHORT).show();
            }
        }

        return player;
    }

    //Returns the description of a specific shop Item
    public  String Descript_Item(int selectedItemId)
    {
        return new String("armor: " + Items.get(selectedItemId).getArmor() + "\n" + "Str: "
                + Items.get(selectedItemId).getStr() + "\n" + "Price: "
                + Items.get(selectedItemId).getBuy_price() + "\n" + "description: "
                + Items.get(selectedItemId).getDescription()+ "\n" + "Name: "
                + Items.get(selectedItemId).getName()+ "\n" + "Lvl req:: "
                + Items.get(selectedItemId).getLevel());
    }

    //Returns the description of a specific Player Item
    public  String Descript_PlayerItem(int selectedItemId)
    {
        return new String("armor: " + PlayerItems.get(selectedItemId).getArmor() + "\n" + "Str: "
                + PlayerItems.get(selectedItemId).getStr() + "\n" + "Sell price: "
                + PlayerItems.get(selectedItemId).getSell_price() + "\n" + "description: "
                + PlayerItems.get(selectedItemId).getDescription()+ "\n" + "Equipped: "
                + PlayerItems.get(selectedItemId).getIsEquipped()+ "\n" + "Lvl req: "
                + PlayerItems.get(selectedItemId).getLevel());
    }

    //Refreshes playerItems list
    public  Player RefreshPlayerItems(Player player)
    {
        player.setMyItems(PlayerItems);
        PlayerItems = player.getMyItems();

        return player;
    }

    // Add Item to the player (Shop - buy button)
    public  Player AddItemtoPlayer(Player player,int selectedItemId)
    {
        Items.get(selectedItemId).setOwned(true);
        PlayerItems.add(Items.get(selectedItemId));
        Items.remove(selectedItemId);
        return RefreshPlayerItems(player);
    }
    //Controls the selling of an Item.
    public Player sell(Player player, int selectedItemId)
    {
        player.setGold(player.getGold() + PlayerItems.get(selectedItemId).getSell_price());
        return DropItem(player,selectedItemId);
    }
    //Drop Item from the Player's inventory
    public  Player DropItem(Player player, int selectedItemId)
    {
        if(player.getMyItems().size()!= 0) {
            Items.get(selectedItemId).setOwned(false);
            Items.add(PlayerItems.get(selectedItemId));
            PlayerItems.remove(selectedItemId);
        }
        return RefreshPlayerItems(player);
    }



    //Controls the purchase of an Item.
    public Player buy(Player player, int selectedItemId,MainActivity context)
    {
        if (player.getGold() - Items.get(selectedItemId).getBuy_price() > 0)
        {
            player.setGold(player.getGold() - Items.get(selectedItemId).getBuy_price());
            player = AddItemtoPlayer(player,selectedItemId);
            success = true;
        }
        else
        {
            success = false;
            Toast.makeText(context,"You don't have enough gold to buy this Item.",Toast.LENGTH_SHORT).show();
        }
        return player;
    }

    //Generates a specific number of Item(s).
    public void generateXitems()
    {

       // public Item(String _name, String _classs, String _description, int _str, int _hp, int _armor, int _price,int _img,int lvl)

        generateItem("Leather Armor", "Warrior",
                "Your first cape", 0, 50, 5, 150, R.drawable.armor_1,2);
        generateItem("Leather Helmet", "Warrior",
                "Your first cape", 0, 20, 5, 80,R.drawable.helmet_1,2);
        generateItem("Leather Boots", "Warrior",
                "Your first cape", 0, 20, 5, 75, R.drawable.boots_1,2);
        generateItem("Leather Pants", "Warrior",
                "Your first cape", 0, 20, 5, 100,R.drawable.pants_1,2);
        generateItem("Leather Gloves", "Warrior",
                "Your first cape", 0, 20, 5, 50,R.drawable.gloves_1,2);
        generateItem("Iron Sword", "Warrior",
                "Your first cape", 15, 0, 0, 100, R.drawable.sword_1,2);
        generateItem("Wood Shield", "Warrior", "Your first cape",
                                   0, 0, 10, 150, R.drawable.shield_1,2);


        generateItem("Iron Armor", "Warrior",
                "Your first cape", 0, 60, 25, 350, R.drawable.armor_2,5);
        generateItem("Iron Helmet", "Warrior",
                "Your first cape", 0, 35, 10, 250,R.drawable.helmet_2,5);
        generateItem("Iron Boots", "Warrior",
                "Your first cape", 0, 20, 10, 225, R.drawable.boots_2,5);
        generateItem("Iron Pants", "Warrior",
                "Your first cape",0, 35, 15, 300,R.drawable.pants_2,5);
        generateItem("Iron Gloves", "Warrior",
                "Your first cape", 0, 20, 10, 200,R.drawable.gloves_2,5);
        generateItem("Steel Sword", "Warrior",
                "Your first cape", 30, 0, 0, 340, R.drawable.sword_2,5);
        generateItem("Iron Shield", "Warrior",
                "Your first cape",0, 0, 25, 300, R.drawable.shield_2,5);

        generateItem("Golden Armor", "Warrior",
                "Your first cape", 0, 100, 40, 600, R.drawable.armor_3,11);
        generateItem("Golden Helmet", "Warrior",
                "Your first cape", 0, 60, 40, 600,R.drawable.helmet_3,11);
        generateItem("Golden Boots", "Warrior",
                "Your first cape", 0, 50, 40, 600, R.drawable.boots_3,11);
        generateItem("Golden Pants", "Warrior",
                "Your first cape",0, 50, 40, 600,R.drawable.pants_3,11);
        generateItem("Golden Gloves", "Warrior",
                "Your first cape", 0, 50, 40, 600,R.drawable.gloves_3,11);
        generateItem("Golden Sword", "Warrior",
                "Your first cape", 60, 0, 0, 660, R.drawable.sword_3,11);
        generateItem("Golden Shield", "Warrior",
                "Your first cape",0, 0, 50, 600, R.drawable.shield_3,11);

    }



    private void setPlayerItems()
    {
        PlayerItems = new ArrayList<Item>();

        for(int i = Items.size()-1; i>-1 ;i--)
        {
            if (Items.get(i).getOwned())
            {
                PlayerItems.add(Items.get(i));
                Items.remove(i);
            }
        }
    }

}
