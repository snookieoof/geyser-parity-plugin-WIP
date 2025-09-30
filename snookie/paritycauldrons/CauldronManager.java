package com.snookie.paritycauldrons;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.TileState;
import org.bukkit.persistence.PersistentDataType;

public class CauldronManager {

    private static final NamespacedKey TYPE_KEY = new NamespacedKey(ParityCauldrons.getInstance(), "cauldronType");

    public static void setCauldronType(Block block, CauldronType type) {
        if (!(block.getState() instanceof TileState tile)) return;
        tile.getPersistentDataContainer().set(TYPE_KEY, PersistentDataType.STRING, type.name());
        tile.update();
    }

    public static CauldronType getCauldronType(Block block) {
        if (!(block.getState() instanceof TileState tile)) return CauldronType.WATER;
        String stored = tile.getPersistentDataContainer().get(TYPE_KEY, PersistentDataType.STRING);
        return stored != null ? CauldronType.valueOf(stored) : CauldronType.WATER;
    }

    public static boolean isCustomCauldron(Block block) {
        return getCauldronType(block) != CauldronType.WATER && block.getType() == Material.CAULDRON;
    }
}
