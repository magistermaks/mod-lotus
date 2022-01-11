package net.darktree.lotus.loot.entry;

import net.darktree.lotus.loot.LootContext;
import net.darktree.lotus.loot.LootManager;
import net.darktree.lotus.util.RandUtils;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class ItemEntry extends AbstractEntry {

    private final float chance;
    private final ItemStack stack;
    private final byte amountMin;
    private final byte amountMax;

    public ItemEntry( ItemStack item, float chance, byte amountMin, byte amountMax ) {
        this.chance = chance;
        this.stack = item;
        this.amountMin = amountMin;
        this.amountMax = amountMax;
    }

    @Override
    public ArrayList<ItemStack> getLoot(Random random, LootContext context) {
        if( RandUtils.getBool( this.chance, random ) ) {
            this.stack.setCount( random.nextInt( (amountMax - amountMin) + 1 ) + amountMin );
            return asList( this.stack );
        }

        return LootManager.getEmpty();
    }

}
