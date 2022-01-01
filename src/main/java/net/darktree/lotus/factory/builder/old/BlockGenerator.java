package net.darktree.lotus.factory.builder.old;

import net.darktree.lotus.factory.builder.old.variant.VariantIterator;
import net.minecraft.block.Block;

public interface BlockGenerator {
	Block accept(VariantIterator.Variant<?>[] variants);
}