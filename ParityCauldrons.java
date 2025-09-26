@EventHandler
public void onPlayerInteract(PlayerInteractEvent event) {
    if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
    Block block = event.getClickedBlock();
    if (block == null || block.getType() != Material.CAULDRON) return;

    Player player = event.getPlayer();
    ItemStack item = player.getInventory().getItem(event.getHand());

    if (item.getType() == Material.LAVA_BUCKET) {
        block.setType(Material.CAULDRON); // Keep cauldron type
        block.setMetadata("cauldronType", new FixedMetadataValue(this, "LAVA"));
        player.getInventory().setItem(event.getHand(), new ItemStack(Material.BUCKET));
        block.getWorld().spawnParticle(Particle.FLAME, block.getLocation().add(0.5, 1, 0.5), 10);
        player.sendMessage(ChatColor.RED + "You filled the cauldron with lava!");
        event.setCancelled(true);
    }

    if (item.getType() == Material.POWDER_SNOW_BUCKET) {
        block.setType(Material.CAULDRON);
        block.setMetadata("cauldronType", new FixedMetadataValue(this, "SNOW"));
        player.getInventory().setItem(event.getHand(), new ItemStack(Material.BUCKET));
        block.getWorld().spawnParticle(Particle.SNOWFLAKE, block.getLocation().add(0.5, 1, 0.5), 10);
        player.sendMessage(ChatColor.AQUA + "You filled the cauldron with powdered snow!");
        event.setCancelled(true);
    }
}
