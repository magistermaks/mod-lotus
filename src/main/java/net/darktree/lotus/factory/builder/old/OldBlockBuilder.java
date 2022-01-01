package net.darktree.lotus.factory.builder.old;

import net.darktree.interference.LootInjector;
import net.darktree.interference.api.DefaultLoot;
import net.darktree.lotus.factory.builder.old.variant.VariantIterator;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Collections;
import java.util.function.Function;

public class OldBlockBuilder {

	private final String modid;
	private final String pattern;
	private final VariantIterator[] iterators;
	private final AbstractBlock.Settings settings = AbstractBlock.Settings.of(Material.METAL);
	private final Item.Settings itemSettings = (new Item.Settings().group(ItemGroup.DECORATIONS));

	private BlockGenerator blockGenerator = null;
	private BlockItemGenerator itemGenerator = null;
	private DefaultLoot lootGenerator = null;

	public OldBlockBuilder(String modid, String pattern, VariantIterator... iterators) {
		this.modid = modid;
		this.pattern = pattern;
		this.iterators = iterators;
	}

	public OldBlockBuilder item(BlockItemGenerator generator) {
		this.itemGenerator = generator;
		return this;
	}

	public OldBlockBuilder item() {
		this.itemGenerator = block -> new BlockItem(block, itemSettings);
		return this;
	}

	public OldBlockBuilder loot(DefaultLoot generator) {
		this.lootGenerator = generator;
		return this;
	}

	public OldBlockBuilder dropSelf() {
		this.lootGenerator = (state, builder, id, ctx, world, table) -> {
			return Collections.singletonList( new ItemStack(state.getBlock().asItem()) );
		};
		return this;
	}

	public OldBlockBuilder gen(BlockGenerator generator) {
		this.blockGenerator = generator;
		return this;
	}

	public OldBlockBuilder gen(Function<AbstractBlock.Settings, Block> generator) {
		this.blockGenerator = variants -> generator.apply(this.settings);
		return this;
	}

	public void get() {
		if(blockGenerator == null) {
			throw new RuntimeException("Unable to use factory with null generator, from: '" + this.modid + "', pattern: '" + this.pattern + "'");
		}

		VariantIterator.consume(variants -> {
			Block block = this.blockGenerator.accept(variants);
			Identifier id = resolve(variants);

			Registry.register(Registry.BLOCK, id, block);

			if(itemGenerator != null) {
				Item item = this.itemGenerator.accept(block);
				Registry.register(Registry.ITEM, id, item);
			}

			if(lootGenerator != null) {
				LootInjector.inject(block.getLootTableId(), lootGenerator);
			}
		}, iterators);
	}

	private Identifier resolve(VariantIterator.Variant<?>[] variants) {
		String id = this.pattern;

		for(int i = 0; i < variants.length; i ++) {
			id = id.replaceAll("%" + i, variants[i].name());
		}

		return new Identifier(modid, id);
	}

}