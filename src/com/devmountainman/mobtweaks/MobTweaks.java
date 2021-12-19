package com.devmountainman.mobtweaks;

import com.devmountainman.mobtweaks.events.FriendlyMobEvents;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Arrays;
import java.util.List;

public class MobTweaks extends org.bukkit.plugin.java.JavaPlugin{

    //Prefix for sending console messages
    public static String consolePrefix = ChatColor.DARK_GREEN + "[MobTweaks] " + ChatColor.WHITE;

    //Config options
    public static boolean enableTweaks;
    public static int breedMultiplier;
    public static List<String> breedMultiplierApply;
    public static double breedMultiplyChance;
    public static int friendlyDropMultiplier;
    public static double friendlyDropMultiplyChance;
    public static List<String> friendlyDropMultiplierApply;
    public static boolean enableRandomSheep;
    public static double randomSheepChance;

    public static List<DyeColor> dyeColors = Arrays.asList(DyeColor.BLACK
            ,DyeColor.BLUE, DyeColor.BROWN, DyeColor.CYAN, DyeColor.GRAY, DyeColor.GRAY
            ,DyeColor.LIGHT_BLUE, DyeColor.LIGHT_GRAY, DyeColor.LIME, DyeColor.MAGENTA
            ,DyeColor.ORANGE, DyeColor.PINK, DyeColor.PURPLE, DyeColor.RED, DyeColor.WHITE
            ,DyeColor.YELLOW);

    @Override
    public void onEnable(){
        // Save the default config.yml
        this.saveDefaultConfig();

        // Loading Configs
        FileConfiguration config = this.getConfig();

        enableTweaks = config.getBoolean("enablePlugin");

        breedMultiplierApply = config.getStringList("breedMultiplierApply");
        breedMultiplier = config.getInt("breedMultiplier");
        if(breedMultiplier > 10 || breedMultiplier < 1){
            breedMultiplier = 2;
        }
        breedMultiplyChance = config.getDouble("breedMultiplyChance");
        if(breedMultiplyChance > 1 || breedMultiplyChance < 0){ // Set default if invalid value
            breedMultiplyChance = .5;
        }

        enableRandomSheep = config.getBoolean("enableRandomSheep");
        randomSheepChance = config.getDouble("randomSheepChance");

        friendlyDropMultiplierApply = config.getStringList("friendlyDropMultiplierApply");
        friendlyDropMultiplier = config.getInt("friendlyDropMultiplier");
        if(friendlyDropMultiplier > 5 || friendlyDropMultiplier < 1){
            friendlyDropMultiplier = 2;
        }
        friendlyDropMultiplyChance = config.getDouble("friendlyDropMultiplyChance");
        if(friendlyDropMultiplyChance > 1 || friendlyDropMultiplyChance < 0){ // Set default if invalid value
            friendlyDropMultiplyChance = .1;
        }

        getServer().getConsoleSender().sendMessage(consolePrefix + "Thanks for using MobTweaks!");
        getServer().getPluginManager().registerEvents(new FriendlyMobEvents(), this);
    }

    @Override
    public void onDisable(){
        getServer().getConsoleSender().sendMessage(consolePrefix + "MobTweaks successfully disabled.");
    }
}
