package net.darktree.lotus.recipe.type;

import com.google.gson.JsonObject;
import net.darktree.lotus.recipe.RecipeBuilder;
import net.minecraft.item.Item;

public class CuttingRecipeBuilder extends IngredientRecipeBuilder {

	public CuttingRecipeBuilder(RecipeBuilder parent, Item[] items) {
		super(parent);

		for( Item item : items ) {
			addIngredient(item);
		}
	}

	public CuttingRecipeBuilder put(Item item) {
		this.addIngredient(item);
		return this;
	}

	public CuttingRecipeBuilder put(String item) {
		this.addIngredient(item);
		return this;
	}

	@Override
	public JsonObject json() {
		JsonObject recipe = base("stonecutting", ResultType.DOUBLE);
		recipe.add("ingredient", getIngredient());

		return recipe;
	}

}
