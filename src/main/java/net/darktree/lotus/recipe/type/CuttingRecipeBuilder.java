package net.darktree.lotus.recipe.type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.darktree.lotus.recipe.RecipeOutput;
import net.minecraft.item.Item;
import org.apache.commons.lang3.mutable.MutableObject;

public class CuttingRecipeBuilder extends IngredientRecipeBuilder {

	public CuttingRecipeBuilder(MutableObject<RecipeOutput> output, Item[] items) {
		super(output);

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
	public JsonElement json() {
		JsonObject recipe = base("stonecutting", ResultType.DOUBLE);
		recipe.add("ingredient", getIngredient());

		return recipe;
	}

}
