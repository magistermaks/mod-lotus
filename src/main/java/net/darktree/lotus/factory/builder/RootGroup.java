package net.darktree.lotus.factory.builder;

import net.darktree.lotus.factory.builder.provider.BlockProvider;
import net.darktree.lotus.factory.builder.provider.ItemProvider;
import net.darktree.lotus.factory.builder.provider.LootProvider;
import net.darktree.lotus.factory.builder.provider.RecipeProvider;
import net.darktree.lotus.model.factory.ApplicableFactory;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import org.jetbrains.annotations.NotNull;

public class RootGroup extends PropertyProvider {

	@Override
	@NotNull
	public PropertyProvider parent() {
		return this;
	}

	@Override
	public FabricBlockSettings getBlockSettings() {
		return FabricBlockSettings.of(Material.STONE);
	}

	@Override
	public FabricItemSettings getItemSettings() {
		return new FabricItemSettings();
	}

	@Override
	public BlockProvider getBlockProvider() {
		return (provider) -> new Block(provider.getBlockSettings());
	}

	@Override
	public LootProvider getLootProvider() {
		return LootProvider.NONE;
	}

	@Override
	public ItemProvider getItemProvider() {
		return ItemProvider.NONE;
	}

	@Override
	public RecipeProvider getRecipeProvider() {
		return RecipeProvider.NONE;
	}

	@Override
	public ApplicableFactory getBlockModelProvider() {
		return null;
	}

	@Override
	public ApplicableFactory getItemModelProvider() {
		return this.itemModelProvider;
	}

}
