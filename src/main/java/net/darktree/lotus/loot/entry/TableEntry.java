package net.darktree.lotus.loot.entry;

import net.darktree.lotus.loot.LootContext;
import net.darktree.lotus.loot.LootManager;
import net.darktree.lotus.loot.LootTable;
import net.darktree.lotus.util.RandUtils;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class TableEntry extends AbstractEntry {

    private final LootTable table;
    private final float chance;

    public TableEntry( LootTable table, float chance ) {
        this.table = table;
        this.chance = chance;
    }

    public LootTable getTable() {
        return table;
    }

    @Override
    public ArrayList<ItemStack> getLoot(Random random, LootContext context) {
        if( RandUtils.getBool( this.chance, random ) ) {
            return table.getLoot(random, context);
        }

        return LootManager.getEmpty();
    }
}
