package net.darktree.lotus.recipe.type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import net.darktree.lotus.recipe.RecipeBuilder;
import net.minecraft.item.Item;

public abstract class IngredientRecipeBuilder extends RecipeBuilder {

	protected JsonArray ingredients = new JsonArray();

	protected IngredientRecipeBuilder(RecipeBuilder parent) {
		super(parent);
	}

	protected void addIngredient(Item item) {
		this.ingredients.add(ingredient(item));
	}

	protected void addIngredient(String item) {
		this.ingredients.add(ingredient(item));
	}

	public JsonElement getIngredient() {
		if(this.ingredients.size() == 1) {
			return this.ingredients.get(0);
		}

		if(this.ingredients.size() > 1) {
			return this.ingredients;
		}

		throw new RuntimeException("At least one ingredient is required!");
	}

}

