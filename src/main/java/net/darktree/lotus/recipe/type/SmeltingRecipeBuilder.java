package net.darktree.lotus.recipe.type;

import com.google.gson.JsonObject;
import net.darktree.lotus.recipe.RecipeBuilder;
import net.minecraft.item.Item;

public class SmeltingRecipeBuilder extends IngredientRecipeBuilder {

	String type;
	int time;
	double experience = 0.35;

	public SmeltingRecipeBuilder(RecipeBuilder parent, FurnaceType type, Item[] items) {
		super(parent);
		this.type = type.type;
		this.time = type.time;

		for(Item item : items) {
			this.put(item);
		}
	}

	public SmeltingRecipeBuilder put(Item item) {
		this.addIngredient(item);
		return this;
	}

	public SmeltingRecipeBuilder put(String item) {
		this.addIngredient(item);
		return this;
	}

	public SmeltingRecipeBuilder exp(double experience) {
		this.experience = experience;
		return this;
	}

	public SmeltingRecipeBuilder time(int ticks) {
		this.time = ticks;
		return this;
	}

	@Override
	public JsonObject json() {
		JsonObject recipe = base(this.type, ResultType.STRING);
		recipe.add("ingredient", getIngredient());
		recipe.addProperty("experience", this.experience);
		recipe.addProperty("cookingtime", this.time);

		return recipe;
	}

}
