package net.darktree.lotus.factory.builder.provider;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;

public interface ClassBlockProvider {

	default BlockProvider provider() {
		return provider -> this.get(provider.getBlockSettings());
	}

	Block get(AbstractBlock.Settings provider);

}
