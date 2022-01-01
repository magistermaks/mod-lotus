package net.darktree.lotus.factory.builder.provider;

import net.darktree.lotus.factory.builder.PropertyProvider;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public interface ItemProvider {

	ItemProvider SELF = (provider, block) -> new BlockItem(block, provider.getItemSettings());
	ItemProvider NONE = null;

	Item get(PropertyProvider provider, Block block);

}
