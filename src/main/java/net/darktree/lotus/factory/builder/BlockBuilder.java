package net.darktree.lotus.factory.builder;

import net.darktree.interference.LootInjector;
import net.darktree.interference.RecipeInjector;
import net.darktree.lotus.factory.builder.provider.*;
import net.darktree.lotus.model.factory.ApplicableFactory;
import net.darktree.lotus.recipe.Recipe;
import net.darktree.lotus.recipe.RecipeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public class BlockBuilder extends PropertyProvider {

	private final Group group;
	private final String id;

	public BlockBuilder(Group group, String id) {
		this.group = group;
		this.id = id;
	}

	@Override
	@NotNull
	@ApiStatus.Internal
	public PropertyProvider parent() {
		return this.group;
	}

	public Block get() {
		Block block = this.getBlockProvider().get(this);
		Identifier id = new Identifier(this.group.modid, this.id);

		Registry.register(Registry.BLOCK, id, block);

		// attach loot provider
		LootProvider lootProvider = getLootProvider();
		if(lootProvider != null) {
			LootInjector.inject(id, lootProvider.provider());
		}

		// attach block model
		ApplicableFactory modelProvider = getModelProvider();
		if(modelProvider != null) {
			modelProvider.inject(id);
		}

		// attach block item
		ItemProvider itemProvider = getItemProvider();
		if(itemProvider != null) {
			Item item = itemProvider.get(this, block);
			Registry.register(Registry.ITEM, id, item);

			// attach item recipe
			RecipeProvider recipeProvider = getRecipeProvider();
			if(recipeProvider != null) {
				recipeProvider.get(this, item).inject();
			}
		}

		return block;
	}

	public Group next() {
		this.get();
		return this.group;
	}

	public BlockBuilder settings(FabricBlockSettings settings) {
		this.blockSettings = settings;
		return this;
	}

	public BlockBuilder of(BlockProvider provider) {
		this.blockProvider = provider;
		return this;
	}

	public BlockBuilder of(ClassBlockProvider provider) {
		this.blockProvider = provider.provider();
		return this;
	}

	public BlockBuilder drop(LootProvider provider) {
		this.lootProvider = provider;
		return this;
	}

	public BlockBuilder drop(Item item) {
		this.lootProvider = LootProvider.of(item, 1);
		return this;
	}

	public BlockBuilder dropSelf() {
		this.lootProvider = LootProvider.SELF;
		return this;
	}

	public BlockBuilder item(ItemProvider provider) {
		this.itemProvider = provider;
		return this;
	}

	public BlockBuilder item() {
		this.itemProvider = ItemProvider.SELF;
		return this;
	}

	public BlockBuilder recipe(RecipeBuilder recipe) {
		this.recipeProvider = RecipeProvider.of(recipe, 1, null);
		return this;
	}

	public BlockBuilder recipe(RecipeBuilder recipe, int count) {
		this.recipeProvider = RecipeProvider.of(recipe, count, null);
		return this;
	}

	public BlockBuilder recipe(RecipeBuilder recipe, int count, String group) {
		this.recipeProvider = RecipeProvider.of(recipe, count, group);
		return this;
	}

	public BlockBuilder model(ApplicableFactory model) {
		this.modelProvider = model;
		return this;
	}

}
