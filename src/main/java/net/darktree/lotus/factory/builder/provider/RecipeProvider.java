package net.darktree.lotus.factory.builder.provider;

import net.darktree.lotus.factory.builder.PropertyProvider;
import net.darktree.lotus.recipe.Recipe;
import net.darktree.lotus.recipe.RecipeBuilder;
import net.darktree.lotus.recipe.RecipeOutput;
import net.minecraft.item.Item;

public interface RecipeProvider {

	RecipeProvider NONE = null;

	static RecipeProvider of(RecipeBuilder recipe, int count, String group) {
		return (provider, item) -> recipe.output( new RecipeOutput(item, count, group) ).get();
	}

	Recipe get(PropertyProvider provider, Item item);

}
