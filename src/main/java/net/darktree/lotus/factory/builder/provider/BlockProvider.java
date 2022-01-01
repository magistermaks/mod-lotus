package net.darktree.lotus.factory.builder.provider;

import net.darktree.lotus.factory.builder.PropertyProvider;
import net.minecraft.block.Block;

public interface BlockProvider {

	Block get(PropertyProvider provider);

}
