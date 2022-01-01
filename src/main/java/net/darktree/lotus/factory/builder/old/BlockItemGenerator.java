package net.darktree.lotus.factory.builder.old;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public interface BlockItemGenerator {
	Item accept(Block block);
}
