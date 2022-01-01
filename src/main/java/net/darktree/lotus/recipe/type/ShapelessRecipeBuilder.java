package net.darktree.lotus.recipe.type;

import com.google.gson.JsonObject;
import net.darktree.lotus.recipe.RecipeBuilder;
import net.minecraft.item.Item;

public class ShapelessRecipeBuilder extends IngredientsRecipeBuilder {

	public ShapelessRecipeBuilder(RecipeBuilder parent, Item[] ingredients) {
		super(parent);
		this.addAllIngredients(ingredients);
	}

	public ShapelessRecipeBuilder put(Item item) {
		this.addIngredient(item);
		return this;
	}

	public ShapelessRecipeBuilder put(String item) {
		this.addIngredient(item);
		return this;
	}

	public ShapelessRecipeBuilder any(Item... items) {
		this.addAnyIngredientOf(items);
		return this;
	}

	public ShapelessRecipeBuilder any(String... items) {
		this.addAnyIngredientOf(items);
		return this;
	}

	@Override
	public JsonObject json() {
		JsonObject recipe = base("crafting_shapeless", ResultType.OBJECT);
		recipe.add("ingredients", getIngredients());

		return recipe;
	}

}
