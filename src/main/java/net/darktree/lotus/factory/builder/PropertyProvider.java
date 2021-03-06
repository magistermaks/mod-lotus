package net.darktree.lotus.factory.builder;

import net.darktree.interference.api.DefaultLoot;
import net.darktree.lotus.factory.builder.provider.BlockProvider;
import net.darktree.lotus.factory.builder.provider.ItemProvider;
import net.darktree.lotus.factory.builder.provider.RecipeProvider;
import net.darktree.lotus.model.factory.ApplicableFactory;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public abstract class PropertyProvider {

	FabricBlockSettings blockSettings = null;
	FabricItemSettings itemSettings = null;
	BlockProvider blockProvider = null;
	DefaultLoot lootProvider = null;
	ItemProvider itemProvider = null;
	RecipeProvider recipeProvider = null;
	ApplicableFactory blockModelProvider = null;
	ApplicableFactory itemModelProvider = null;

	@NotNull
	@ApiStatus.Internal
	public abstract PropertyProvider parent();

	public FabricBlockSettings getBlockSettings() {
		if(this.blockSettings != null) return this.blockSettings;
		return parent().getBlockSettings();
	}

	public FabricItemSettings getItemSettings() {
		if(this.itemSettings != null) return this.itemSettings;
		return parent().getItemSettings();
	}

	public BlockProvider getBlockProvider() {
		if(this.blockProvider != null) return this.blockProvider;
		return parent().getBlockProvider();
	}

	public DefaultLoot getLootProvider() {
		if(this.lootProvider != null) return this.lootProvider;
		return parent().getLootProvider();
	}

	public ItemProvider getItemProvider() {
		if(this.itemProvider != null) return this.itemProvider;
		return parent().getItemProvider();
	}

	public RecipeProvider getRecipeProvider() {
		if(this.recipeProvider != null) return this.recipeProvider;
		return parent().getRecipeProvider();
	}

	public ApplicableFactory getBlockModelProvider() {
		if(this.blockModelProvider != null) return this.blockModelProvider;
		return parent().getBlockModelProvider();
	}

	public ApplicableFactory getItemModelProvider() {
		if(this.itemModelProvider != null) return this.itemModelProvider;
		return parent().getItemModelProvider();
	}

}
