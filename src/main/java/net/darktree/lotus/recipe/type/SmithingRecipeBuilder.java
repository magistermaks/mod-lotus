package net.darktree.lotus.recipe.type;

import com.google.gson.JsonObject;
import net.darktree.lotus.recipe.RecipeBuilder;
import net.minecraft.item.Item;

public class SmithingRecipeBuilder extends RecipeBuilder {

	JsonObject base;
	JsonObject addition;

	public SmithingRecipeBuilder(RecipeBuilder parent) {
		super(parent);
	}

	public SmithingRecipeBuilder base(Item item) {
		base = ingredient(item);
		return this;
	}

	public SmithingRecipeBuilder base(String item) {
		base = ingredient(item);
		return this;
	}

	public SmithingRecipeBuilder addition(Item item) {
		base = ingredient(item);
		return this;
	}

	public SmithingRecipeBuilder addition(String item) {
		base = ingredient(item);
		return this;
	}

	@Override
	public JsonObject json() {
		JsonObject recipe = base("smithing", ResultType.STRING);

		if(this.base == null) {
			throw new RuntimeException("No base item set!");
		}

		if(this.addition == null) {
			throw new RuntimeException("No addition item set!");
		}

		recipe.add("base", this.base);
		recipe.add("addition", this.addition);

		return recipe;
	}

}
