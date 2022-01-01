package net.darktree.lotus.recipe.type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.darktree.lotus.recipe.RecipeOutput;
import net.minecraft.item.Item;
import org.apache.commons.lang3.mutable.MutableObject;

public class ShapelessRecipeBuilder extends IngredientsRecipeBuilder {

	public ShapelessRecipeBuilder(MutableObject<RecipeOutput> output, Item[] ingredients) {
		super(output);
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
	public JsonElement json() {
		JsonObject recipe = base("crafting_shapeless", ResultType.OBJECT);
		recipe.add("ingredients", getIngredients());

		return recipe;
	}

}
