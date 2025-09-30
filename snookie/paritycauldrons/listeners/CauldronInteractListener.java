@EventHandler
public void onPlayerInteract(PlayerInteractEvent event) {
    if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

    Block block = event.getClickedBlock();
    if (block == null || block.getType() != Material.CAULDRON) return;

    Player player = event.getPlayer();
    ItemStack item = player.getInventory().getItem(event.getHand());
    if (item == null) return;

    // Filling with lava
    if (item.getType() == Material.LAVA_BUCKET) {
        CauldronManager.setCauldronType(block, CauldronType.LAVA);
        player.getInventory().setItem(event.getHand(), new ItemStack(Material.BUCKET));
        block.getWorld().spawnParticle(Particle.FLAME, block.getLocation().add(0.5, 1, 0.5), 10);
        player.sendMessage(ChatColor.RED + "Cauldron filled with lava.");
        event.setCancelled(true);
    }

    // Filling with powdered snow
    if (item.getType() == Material.POWDER_SNOW_BUCKET) {
        CauldronManager.setCauldronType(block, CauldronType.POWDERED_SNOW);
        player.getInventory().setItem(event.getHand(), new ItemStack(Material.BUCKET));
        block.getWorld().spawnParticle(Particle.SNOWFLAKE, block.getLocation().add(0.5, 1, 0.5), 10);
        player.sendMessage(ChatColor.AQUA + "Cauldron filled with powdered snow.");
        event.setCancelled(true);
    }

    // Emptying lava cauldron
    if (item.getType() == Material.BUCKET && CauldronManager.getCauldronType(block) == CauldronType.LAVA) {
        player.getInventory().setItem(event.getHand(), new ItemStack(Material.LAVA_BUCKET));
        CauldronManager.setCauldronType(block, CauldronType.WATER);
        block.getWorld().spawnParticle(Particle.SMOKE_LARGE, block.getLocation().add(0.5, 1, 0.5), 10);
        player.sendMessage(ChatColor.GRAY + "You scooped lava from the cauldron.");
        event.setCancelled(true);
    }

    // Emptying powdered snow cauldron
    if (item.getType() == Material.BUCKET && CauldronManager.getCauldronType(block) == CauldronType.POWDERED_SNOW) {
        player.getInventory().setItem(event.getHand(), new ItemStack(Material.POWDER_SNOW_BUCKET));
        CauldronManager.setCauldronType(block, CauldronType.WATER);
        block.getWorld().spawnParticle(Particle.CLOUD, block.getLocation().add(0.5, 1, 0.5), 10);
        player.sendMessage(ChatColor.GRAY + "You scooped powdered snow from the cauldron.");
        event.setCancelled(true);
    }
}
