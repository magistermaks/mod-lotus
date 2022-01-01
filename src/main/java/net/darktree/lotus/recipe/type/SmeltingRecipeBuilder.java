package net.darktree.lotus.recipe.type;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.darktree.lotus.recipe.RecipeOutput;
import net.minecraft.item.Item;
import org.apache.commons.lang3.mutable.MutableObject;

public class SmeltingRecipeBuilder extends IngredientRecipeBuilder {

	String type;
	int time;
	double experience = 0.35;

	public SmeltingRecipeBuilder(MutableObject<RecipeOutput> output, FurnaceType type, Item[] items) {
		super(output);
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
	public JsonElement json() {
		JsonObject recipe = base(this.type, ResultType.STRING);
		recipe.add("ingredient", getIngredient());
		recipe.addProperty("experience", this.experience);
		recipe.addProperty("cookingtime", this.time);

		return recipe;
	}

}
