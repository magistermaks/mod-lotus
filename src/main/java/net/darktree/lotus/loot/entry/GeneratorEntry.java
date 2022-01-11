package net.darktree.lotus.loot.entry;

import net.darktree.lotus.loot.LootContext;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Random;

public class GeneratorEntry extends AbstractEntry {

    private final Generator generator;

    public GeneratorEntry( Generator generator ) {
        this.generator = generator;
    }

    @Override
    public ArrayList<ItemStack> getLoot(Random random, LootContext context) {
        return generator.call(random, context);
    }

    public interface Generator {
        ArrayList<ItemStack> call(Random random, LootContext context);
    }
}
