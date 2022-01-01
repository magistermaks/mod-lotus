package net.darktree.lotus.recipe.type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import net.darktree.lotus.recipe.RecipeBuilder;
import net.minecraft.item.Item;

public abstract class IngredientsRecipeBuilder extends RecipeBuilder {

	protected final JsonArray ingredients = new JsonArray();

	protected IngredientsRecipeBuilder(RecipeBuilder parent) {
		super(parent);
	}

	protected void addAllIngredients(Item[] ingredients) {
		for( Item item : ingredients ) {
			addIngredient(item);
		}
	}

	protected void addIngredient(Item item) {
		this.ingredients.add(ingredient(item));
	}

	protected void addIngredient(String item) {
		this.ingredients.add(ingredient(item));
	}

	protected void addAnyIngredientOf(Item... items) {
		JsonArray any = new JsonArray();

		for( Item item : items ) {
			any.add(ingredient(item));
		}

		this.ingredients.add(any);
	}

	protected void addAnyIngredientOf(String... items) {
		JsonArray any = new JsonArray();

		for( String item : items ) {
			any.add(ingredient(item));
		}

		this.ingredients.add(any);
	}

	protected JsonElement getIngredients() {
		if(this.ingredients.size() < 1) {
			throw new RuntimeException("At least one ingredient required!");
		}

		return this.ingredients;
	}

}
