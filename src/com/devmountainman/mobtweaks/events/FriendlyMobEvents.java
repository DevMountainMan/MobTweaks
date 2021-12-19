package com.devmountainman.mobtweaks.events;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

import static com.devmountainman.mobtweaks.MobTweaks.*;

public class FriendlyMobEvents implements Listener {

    public static String eventPrefix = ChatColor.YELLOW + "[EVENT] " + ChatColor.WHITE;

    @EventHandler
    public static void onBreed(EntityBreedEvent e){

        Bukkit.getConsoleSender().sendMessage(consolePrefix + eventPrefix + "Entity: " + e.getEntity().getType());
        if(breedMultiplierApply.contains(e.getEntity().getType().toString())){

            //Bukkit.getServer().getConsoleSender().sendMessage(consolePrefix + "MOB QUALIFIES FOR BREED MULTIPLIER.");
            World eWorld = e.getEntity().getWorld();
            Location eLocation = e.getEntity().getLocation();
            Random rng = new Random(System.currentTimeMillis());

            if(e.getEntity() instanceof Sheep && enableRandomSheep){
                ((Sheep) e.getEntity()).setColor(dyeColors.get(rng.nextInt(dyeColors.size() - 1)));
            }

            for(int i = breedMultiplier - 1; i > 0; i--){
                if(rng.nextDouble() <= breedMultiplyChance){
                    Entity newEntity = eWorld.spawnEntity(eLocation, e.getEntityType());
                    if (newEntity instanceof Ageable){ // This should be true for any breed-able entity
                        ((Ageable) newEntity).setBaby();
                    }

                    if(newEntity instanceof Sheep && rng.nextDouble() <= randomSheepChance){
                        ((Sheep) newEntity).setColor(dyeColors.get(rng.nextInt(dyeColors.size() - 1)));
                    }
                }
            }
        }
    }

    @EventHandler
    public static void onDeath(EntityDeathEvent e){

        if(breedMultiplierApply.contains(e.getEntity().getType().toString())){

            Bukkit.getConsoleSender().sendMessage(consolePrefix +
                    ChatColor.LIGHT_PURPLE + "[DEATH]" + ChatColor.WHITE + "Loot: " + e.getDrops());

            Random rng = new Random(System.currentTimeMillis());

            if (rng.nextDouble() <= friendlyDropMultiplyChance){
                Bukkit.getConsoleSender().sendMessage(consolePrefix +
                        ChatColor.LIGHT_PURPLE + "[DEATH]" + ChatColor.WHITE + "DROP MULTIPLIER SUCCESS");
                for(ItemStack i: e.getDrops()){
                    i.setAmount(i.getAmount() * friendlyDropMultiplier);
                }
            }
        }
    }
}
