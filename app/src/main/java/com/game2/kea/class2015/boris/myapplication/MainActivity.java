package com.game2.kea.class2015.boris.myapplication;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity
{

    public Controller control;
    public Context cont;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        control = new Controller(this);
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

       setContentView(R.layout.mainmenu_1204x600);
        Sound("title");

    }

    private MediaPlayer mediaPlayerMusic;
    private MediaPlayer mediaPlayerEffect;
    private String menu="title";

    private void clicksound()
    {
        mediaPlayerEffect = MediaPlayer.create(getApplicationContext(), R.raw.click);
        mediaPlayerEffect.start(); // no need to call prepare(); create() does that for you
    }

    private void Sound(String location)
    {
        if(location.equalsIgnoreCase("town")) {
            mediaPlayerMusic = MediaPlayer.create(getApplicationContext(), R.raw.townmusic);
            mediaPlayerMusic.start(); // no need to call prepare(); create() does that for you
        }
        if(location.equalsIgnoreCase("title")) {
            mediaPlayerMusic = MediaPlayer.create(getApplicationContext(), R.raw.titlemusic);
            mediaPlayerMusic.start(); // no need to call prepare(); create() does that for you
        }
    }

    private void stopmusic()
    {
        mediaPlayerMusic.stop(); // no need to call prepare(); create() does that for you
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Sound(menu);
        try {
            control.Load();

        }catch(Exception e){}

    }

    @Override
    protected void onPause()
    {
        super.onPause();
        control.Save();
        stopmusic();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        control.Save();
        stopmusic();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        control.Save();
        stopmusic();
    }

    //Auto-generated
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }



    public void stopfarming(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Button btnNewButton = (Button)findViewById(R.id.button_Start);

                Button back = (Button)findViewById(R.id.button_back_to_town);

                Drawable new_image= getResources().getDrawable( R.drawable.start_farming_button);
                btnNewButton.setBackgroundDrawable(new_image);
                btnNewButton.setEnabled(true);

                Drawable backgr = getResources().getDrawable( R.drawable.back_button);
                back.setBackgroundDrawable(backgr);
                back.setEnabled(true);
                btnNewButton.setText("Start");

                control.farming = false;
            }
        });
    }
    //Helper for the GUI refresh at Farming View
    public void Kappa(final String mobstr, final String mobarmor,final String playerarmor,
                      final String plyrstraftr,final String mobname, final int img, final int next_lvl_exp_req,
                      final int experience, final String plyrgold, final String plyrlvl,final String mobmaxhp,
                      final String mobhp,final Boolean refresh,final String playerhp,final String playermaxhp){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                DisplayFarming(mobstr, mobarmor, playerarmor, plyrstraftr,mobname,img, next_lvl_exp_req, experience, plyrgold, plyrlvl,mobmaxhp,mobhp,refresh,playerhp,playermaxhp);
            }
        });
    }

    //Refresh the GUI in the Farming View
    public void DisplayFarming(String mobstr, String mobarmor,String playerarmor, String plyrstraftr,String mobname, int img,
                               int next_lvl_exp_req, int experience, String plyrgold, String plyrlvl,String mobmaxhp,
                               String mobhp,Boolean refresh,String playerhp,String playermaxhp)
    {
        ProgressBar xp = (ProgressBar)findViewById(R.id.progressBar_exp);
        xp.setMax(next_lvl_exp_req);
        xp.setProgress(experience);

        ProgressBar mobhpbar = (ProgressBar)findViewById(R.id.progressBar_mob_hp);
        mobhpbar.setMax(Integer.parseInt(mobmaxhp));
        mobhpbar.setProgress(Integer.parseInt(mobhp));

        ProgressBar playerhpbar = (ProgressBar)findViewById(R.id.progressBar_player_hp);
        playerhpbar.setMax(Integer.parseInt(playermaxhp));
        playerhpbar.setProgress(Integer.parseInt(playerhp));

        double b = Double.parseDouble(playerhp) / Double.parseDouble(playermaxhp);
        if(b*100 > 60)
            playerhpbar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);

        if(b*100 < 60 && b*100 > 20)
            playerhpbar.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);

        if(b*100 < 20)
            playerhpbar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);

        if(playerhp.equals("0"))
            Toast.makeText(getApplicationContext(), "You ded sun.", Toast.LENGTH_SHORT).show();


        double a = Double.parseDouble(mobhp) / Double.parseDouble(mobmaxhp);
        if(a*100 > 60)
        mobhpbar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);

        if(a*100 < 60 && a*100 > 20)
            mobhpbar.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);

        if(a*100 < 20)
            mobhpbar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);



        TextView xpbar = (TextView)findViewById(R.id.textView_pbar);
        xpbar.setText(experience+"/"+next_lvl_exp_req);

        TextView mobhptxt = (TextView)findViewById(R.id.textView_mobhp);
        mobhptxt.setText(mobhp+"/"+mobmaxhp);

        TextView playerhptxt = (TextView)findViewById(R.id.textView_playerhp);
        playerhptxt.setText(playerhp+"/"+playermaxhp);

        TextView lvl = (TextView)findViewById(R.id.textView_lvl_value);
        lvl.setText(plyrlvl);

        TextView gold = (TextView)findViewById(R.id.textView_gold_value);
        gold.setText(plyrgold);

        TextView playerstr = (TextView)findViewById(R.id.textView_player_str_value);
        playerstr.setText(plyrstraftr);

        TextView playerarmortv = (TextView)findViewById(R.id.textView_mob_armor_value);
        playerarmortv.setText(playerarmor);

        TextView mstr = (TextView)findViewById(R.id.textView_mob_str_value);
        mstr.setText(mobstr);

        TextView marmor = (TextView)findViewById(R.id.textView_mob_armor_value);
        marmor.setText(mobarmor);

        ImageView playerpic = (ImageView)findViewById(R.id.imageView_player_img);

        playerpic.setImageResource(img);

        ImageView mobpic = (ImageView)findViewById(R.id.imageView_mob_img);

        int path = 0;
        switch(mobname) {
            case "wolf":      path = R.drawable.wolf;
                mobpic.setImageResource(path);           break;
            case "bear":       path = R.drawable.bear;
                mobpic.setImageResource(path);          break;
            case "tiger":      path = R.drawable.tiger;
                mobpic.setImageResource(path);           break;

        }

        if(refresh) {
            control.showQuestProgress();
        }
    }

    // Auto-generated code
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private Thread healthRegenThread = new Thread()
    {
        public void run()
        {
           control.regen();
        }

    };

    //BUTTON LISTENERS HERE----------------------------------------------------------------------------


    //Set layout to Town
    public void buttonOnClickBackToTown(View v)
    {
        clicksound();
        setContentView(R.layout.activity_main_1024x600);

        if(!mediaPlayerMusic.isPlaying())
        Sound("town");
        menu = "town";
    }

    //Opens Shop Main Menu
    public void buttonOnClickShop(View v)
    {
        clicksound();
        setContentView(R.layout.shoplayout);
    }

    public void buttonOnClickBackToMain(View v)
    {
        clicksound();
        setContentView(R.layout.mainmenu_1204x600);

        if(mediaPlayerMusic.isPlaying())
        {}
        else
        {
            Sound("title");
            menu = "title";
        }

    }

    //Back to Main-menu
    public void buttonOnClickMenu(View v)
    {
        clicksound();
        setContentView(R.layout.mainmenu_1204x600);

        mediaPlayerMusic.stop();
        Sound("title");
        menu = "title";
    }

    // Load the last saved Game
    public void buttonOnClickLoad(View v)
    {
        clicksound();
        setContentView(R.layout.activity_main_1024x600);
        control.Load();
        if(!control.FullHp())
        {
            healthRegenThread = new Thread()
        {
            public void run()
            {
                control.regen();
            }

        };
            healthRegenThread.start();
        }
        mediaPlayerMusic.stop();
        Sound("town");
        menu = "town";
    }

    // Create a new character and Start a new Game
    public void buttonOnClickCreateandLoad(View v)
    {
        clicksound();
        TextView name = (TextView)findViewById(R.id.textView_name);
        Spinner race = (Spinner)findViewById(R.id.spinner_race);
        Spinner classs = (Spinner)findViewById(R.id.spinner_class);
        TextView origin = (TextView)findViewById(R.id.textview_origin);
        ImageView image = (ImageView)findViewById(R.id.imageView_create);

        int a = 0;
        if(race.getSelectedItemId() == 1)
        {
            a = R.drawable.char_2;
        }
        else
        {
            a = R.drawable.char_1;
        }


        control.createPlayer(name.getText().toString(),race.getSelectedItem().toString(),classs.getSelectedItem().toString(),origin.getText().toString(),a);
        setContentView(R.layout.activity_main_1024x600);
        mediaPlayerMusic.stop();
        Sound("town");
        menu = "town";
    }

    // Open farming view
    public void buttonOnClickFarming(View v)
    {
        clicksound();
        setContentView(R.layout.farming_view_1024x600);
    }

    //Opens to character-creation
    public void buttonOnClickStart(View v)
    {
        clicksound();
        setContentView(R.layout.create_character_1024x600);

        Spinner race_spin = (Spinner)findViewById(R.id.spinner_race);

        ArrayAdapter<CharSequence> adapter_day = ArrayAdapter
                .createFromResource(this, R.array.race_array,android.R.layout.simple_spinner_item);
        adapter_day.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        race_spin.setAdapter(adapter_day);
        race_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ImageView playerpicture = (ImageView)findViewById(R.id.imageView_create);

                if(position == 0)
                {
                    Drawable new_image= getResources().getDrawable(R.drawable.char_1);
                    playerpicture.setBackgroundDrawable(new_image);
                }
                if(position == 1)
                {
                    Drawable new_image= getResources().getDrawable(R.drawable.char_2);
                    playerpicture.setBackgroundDrawable(new_image);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void buttonOnClickBackAndRegen(View v)
    {
        clicksound();
        setContentView(R.layout.activity_main_1024x600);

        if(!control.FullHp())
        {
            healthRegenThread = new Thread()
            {
                public void run()
                {
                    control.regen();
                }

            };
            healthRegenThread.start();
        }
    }

    //Displays the shops buy view
    public void buttonOnClickBuyView(View v)
    {
        clicksound();
        setContentView(R.layout.shoplayout_buy);

        cont = getApplicationContext();

        GridView gridview = (GridView) findViewById(R.id.gridView_buy_grid);
        gridview.setAdapter(new ImageAdapter(getApplicationContext(),control.ic.Items));
        TextView tv = (TextView)findViewById(R.id.textView_buy_gold_text);
        tv.setText("Player Gold: "+control.getGold());
        Button c = (Button)findViewById(R.id.button_buy_buytab);
        c.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                control.ic.success = false;
                control.Buy();
                if(control.ic.success)
                {
                    buttonOnClickBuyView(v);
                }
            }
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                control.selectedItemId = position;

                ImageView itempic = (ImageView)findViewById(R.id.imageView_buy_pic);
                itempic.setBackgroundResource(control.ic.Items.get(position).getImg());

                TextView txt = (TextView)findViewById(R.id.textView_buy_descr);
                txt.setText(control.ShopItemDescription());
            }
        });
    }

    //Displays the shops sell view
    public void buttonOnClickSellView(View v)
    {
        clicksound();
        setContentView(R.layout.shoplayout_sell);

        cont = getApplicationContext();

        GridView gridview = (GridView) findViewById(R.id.gridView_sell_grid);
        gridview.setAdapter(new ImageAdapter(getApplicationContext(),control.ic.PlayerItems));
        TextView tv = (TextView)findViewById(R.id.textView_sell_gold_text);
        tv.setText("Player Gold: "+control.getGold());
        Button c = (Button)findViewById(R.id.button_sell_selltab);
        c.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                control.Sell();
                buttonOnClickSellView(v);
            }
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                control.selectedItemId = position;

                ImageView itempic = (ImageView)findViewById(R.id.imageView_sell_pic);
                itempic.setBackgroundResource(control.ic.PlayerItems.get(position).getImg());

                TextView txt = (TextView)findViewById(R.id.textView_sell_descr);
                txt.setText(control.PlayerItemDescription());
            }
        });
    }

    //Goes to Invetory View
    public void buttonOnClickInventory(View v)
    {
        clicksound();
        setContentView(R.layout.inventorylayout_1024x600);

        cont = getApplicationContext();

        GridView gridview = (GridView) findViewById(R.id.gridView);
        gridview.setAdapter(new ImageAdapter(getApplicationContext(),control.ic.PlayerItems));

        Button c = (Button)findViewById(R.id.button_equip);
        c.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                clicksound();
                if(control.player.getMyItems().size() != 0) {
                    control.Equip_Unequip_Item();
                    Button cc = (Button) findViewById(R.id.button_equip);
                    TextView txt = (TextView) findViewById(R.id.textView_InvDescr);
                    txt.setText(control.PlayerItemDescription());

                    if (control.ic.PlayerItems.get(control.selectedItemId).getIsEquipped()) {
                        Drawable new_image = getResources().getDrawable(R.drawable.button_unequip);
                        cc.setBackgroundDrawable(new_image);
                    } else {
                        Drawable new_image = getResources().getDrawable(R.drawable.equip_button);
                        cc.setBackgroundDrawable(new_image);
                    }
                }
            }
        });

        Button a = (Button)findViewById(R.id.button_drop);
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicksound();
                if(control.player.getMyItems().size() != 0) {
                    control.Drop_Item();
                    buttonOnClickInventory(v);
                }
            }
        });

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v,   int position, long id)
            {
                control.selectedItemId = position;

              //  Toast.makeText(getApplicationContext(),position,Toast.LENGTH_SHORT).show();

                Button b = (Button) findViewById(R.id.button_equip);

                ImageView itempic = (ImageView)findViewById(R.id.imageView_Inv_Item);
                itempic.setBackgroundResource(control.ic.PlayerItems.get(position).getImg());

                TextView txt = (TextView)findViewById(R.id.textView_InvDescr);
                txt.setText(control.PlayerItemDescription());

                if (control.isEquipped())
                {
                    Drawable new_image= getResources().getDrawable( R.drawable.button_unequip);
                    b.setBackgroundDrawable(new_image);
                }
                else
                {
                    Drawable new_image= getResources().getDrawable( R.drawable.equip_button);
                    b.setBackgroundDrawable(new_image);
                }
            }
        });
    }

    // listener for the Start farming button
    public void buttonOnClickFarmingStart(View v)
    {
        clicksound();
        Button btnNewButton = (Button)findViewById(R.id.button_Start);

        Button back = (Button)findViewById(R.id.button_back_to_town);

        if (btnNewButton.getText() == "Stop")
        {
            // int a = R.drawable.start_farming_button;
            // btnNewButton.setBackgroundResource(a);
            control.farming = false;


            Drawable new_image= getResources().getDrawable( R.drawable.wait);
            btnNewButton.setBackgroundDrawable(new_image);
            btnNewButton.setEnabled(false);


        } else
        {
            btnNewButton.setText("Stop");

            Drawable backgr = getResources().getDrawable( R.drawable.back_stop);
            back.setBackgroundDrawable(backgr);
            back.setEnabled(false);


            Drawable new_image= getResources().getDrawable( R.drawable.stop_farming_button);
            btnNewButton.setBackgroundDrawable(new_image);

            if(healthRegenThread.isAlive())
            healthRegenThread.interrupt();

           Thread queryThread = new Thread()
        {
            public void run()
            {
                if (control.farming) {
                    control.farming = false;

                } else {
                    control.farming = true;
                    control.Farming();
                }
            }

        };
            queryThread.start();
        }
    }

    //Opens Stats View
    public void buttonOnClickStats(View v)
    {
        clicksound();
        setContentView(R.layout.stats_layout);

        Button armorplus = (Button)findViewById(R.id.button_armor_plus);
        Button strplus = (Button)findViewById(R.id.button_str_plus);
        Button hpplus = (Button)findViewById(R.id.button_hp_plus);

        TextView armorvalue = (TextView)findViewById(R.id.textView_armor_value_stats);
        armorvalue.setText(control.getArmor()+"");
        TextView strvalue = (TextView)findViewById(R.id.textView_str_value_stats);
        strvalue.setText(control.getStr()+"");
        TextView hpvalue = (TextView)findViewById(R.id.textView_hp_value_stats);
        hpvalue.setText(control.getHP()+"");
        TextView namevalue = (TextView)findViewById(R.id.textView_name_stats);
        namevalue.setText(control.getName()+"");
        TextView upointsvalue = (TextView)findViewById(R.id.textView_upgradepoints_stats);
        upointsvalue.setText(control.getUP() + "");

        TextView levelvalue = (TextView)findViewById(R.id.textView_stats_level);
        levelvalue.setText(control.player.getLevel() + "");
        TextView goldvalue = (TextView)findViewById(R.id.textView_stats_gold);
        goldvalue.setText(control.player.getGold() + "");
        TextView currenthpvalue = (TextView)findViewById(R.id.textView_stats_currenthp);
        currenthpvalue.setText(control.player.getHealth() + " / " + control.player.getMaxhealth());
        currenthpvalue.setGravity(View.TEXT_ALIGNMENT_CENTER);



        armorplus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(control.getUP() > 0)
                {
                    clicksound();
                    TextView armorvalue = (TextView)findViewById(R.id.textView_armor_value_stats);
                    TextView upointsvalue = (TextView)findViewById(R.id.textView_upgradepoints_stats);
                    control.setArmor();
                    control.setUP();
                    armorvalue.setText(control.getArmor()+"");
                    upointsvalue.setText(control.getUP() + "");

                }
            }
        });
        strplus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(control.getUP() > 0)
                {
                    clicksound();
                    TextView strvalue = (TextView)findViewById(R.id.textView_str_value_stats);
                    TextView upointsvalue = (TextView)findViewById(R.id.textView_upgradepoints_stats);
                    control.setStr();
                    control.setUP();
                    strvalue.setText(control.getStr()+"");
                    upointsvalue.setText(control.getUP() + "");
                }
            }
        });
        hpplus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(control.getUP() > 0)
                {
                    clicksound();
                    TextView hpvalue = (TextView)findViewById(R.id.textView_hp_value_stats);
                    TextView upointsvalue = (TextView)findViewById(R.id.textView_upgradepoints_stats);
                    control.setHP();
                    control.setUP();
                    hpvalue.setText(control.getHP()+"");
                    upointsvalue.setText(control.getUP() + "");
                }
            }
        });
    }


    // listener for the Quest button
    public void buttonOnClickQuests(View v)
    {
        clicksound();
        setContentView(R.layout.quest_layout_1024x600);
    cont = this;
        GridView gridview = (GridView) findViewById(R.id.gridView2);
        gridview.setAdapter(new ImageAdapterQuests(getApplicationContext(),control.qc.Quests));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v,   int position, long id)
            {
                control.selectedItemId = position;


                TextView txt = (TextView)findViewById(R.id.textView_quest_descr);
                txt.setText(control.getQuestDescription());
                Button btn = (Button)findViewById(R.id.button_accept);
                btn.setVisibility(View.VISIBLE);
            }
        });

        Button btnNewButton = (Button)findViewById(R.id.button_accept);
        btnNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                control.AcceptQuest();
                buttonOnClickQuests(v);
            }
        });

        btnNewButton.setVisibility(View.INVISIBLE);
    }

    public void buttonOnClickQuestHistory(View v)
    {
        clicksound();
        setContentView(R.layout.questhistory_layout);

        GridView gridview = (GridView) findViewById(R.id.gridView3);
        gridview.setAdapter(new ImageAdapterQuests(getApplicationContext(), control.subArrayList("Completed")));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v,   int position, long id)
            {
                control.selectedItemId = position;

            }
        });

        GridView gridview3 = (GridView) findViewById(R.id.gridView4);
        gridview3.setAdapter(new ImageAdapterQuests(getApplicationContext(),control.subArrayList("Ongoing")));

        gridview3.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View v,   int position, long id)
            {
                control.selectedItemId = position;

                ImageView scroll = (ImageView)findViewById(R.id.imageView_Scroll_history);
                scroll.setVisibility(View.VISIBLE);

                TextView descr = (TextView)findViewById(R.id.textView_history);
                descr.setVisibility(View.VISIBLE);
                descr.setText(control.getPlayerQuestDescription());

                Button btn = (Button)findViewById(R.id.button_completed);
                if(control.qc.PlayerQuests.get(control.selectedItemId).getIsCompleted()) {
                    btn.setVisibility(View.VISIBLE);
                }

                scroll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        clicksound();
                        ImageView scroll = (ImageView)findViewById(R.id.imageView_Scroll_history);
                        scroll.setVisibility(View.INVISIBLE);

                        TextView descr = (TextView)findViewById(R.id.textView_history);
                        descr.setVisibility(View.INVISIBLE);

                        Button btn = (Button)findViewById(R.id.button_completed);
                        btn.setVisibility(View.INVISIBLE);
                    }
                });

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clicksound();

                        ImageView scroll = (ImageView)findViewById(R.id.imageView_Scroll_history);
                        scroll.setVisibility(View.INVISIBLE);

                        TextView descr = (TextView)findViewById(R.id.textView_history);
                        descr.setVisibility(View.INVISIBLE);

                        Button btn = (Button)findViewById(R.id.button_completed);
                        btn.setVisibility(View.INVISIBLE);

                        control.TakeReward();
                        buttonOnClickQuestHistory(v);
                    }
                });

            }
        });
    }



    //exit buttons listener
    public void buttonOnClickExit(View v)
    {

    }
    //----------------------------------------------------------------------------


}
