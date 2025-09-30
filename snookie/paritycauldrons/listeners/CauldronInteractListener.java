package com.william.paritycauldrons.listeners;

import com.william.paritycauldrons.ParityCauldrons;
import com.william.paritycauldrons.CauldronManager;
import com.william.paritycauldrons.CauldronType;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.block.Action;

public class CauldronInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Block block = event.getClickedBlock();
        if (block == null || block.getType() != Material.CAULDRON) return;

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItem(event.getHand());

        if (item == null) return;

        // Lava bucket interaction
        if (item.getType() == Material.LAVA_BUCKET) {
            CauldronManager.setCauldronType(block, CauldronType.LAVA);
            player.getInventory().setItem(event.getHand(), new ItemStack(Material.BUCKET));
            block.getWorld().spawnParticle(Particle.FLAME, block.getLocation().add(0.5, 1, 0.5), 10);
            player.sendMessage(ChatColor.RED + "Cauldron filled with lava.");
            event.setCancelled(true);
        }

        // Powdered snow bucket interaction
        if (item.getType() == Material.POWDER_SNOW_BUCKET) {
            CauldronManager.setCauldronType(block, CauldronType.POWDERED_SNOW);
            player.getInventory().setItem(event.getHand(), new ItemStack(Material.BUCKET));
            block.getWorld().spawnParticle(Particle.SNOWFLAKE, block.getLocation().add(0.5, 1, 0.5), 10);
            player.sendMessage(ChatColor.AQUA + "Cauldron filled with powdered snow.");
            event.setCancelled(true);
        }
    }
}
