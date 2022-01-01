package net.darktree.lotus.recipe.type;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.darktree.lotus.recipe.RecipeBuilder;
import net.darktree.lotus.recipe.RecipeOutput;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class ShapedRecipeBuilder extends RecipeBuilder {

	private final JsonArray pattern = new JsonArray();
	private KeyBuilder keys = new KeyBuilder();

	public ShapedRecipeBuilder(RecipeOutput output, String pattern) {
		super(output);

		for( String line : pattern.split(",") ){
			this.pattern.add(line);
		}
	}

	public ShapedRecipeBuilder key(char key, Item item) {
		this.keys.key(key, item);
		return this;
	}

	public ShapedRecipeBuilder key(char key, String item) {
		this.keys.key(key, item);
		return this;
	}

	public ShapedRecipeBuilder keys(KeyBuilder builder) {
		this.keys = builder;
		return this;
	}

	@Override
	public JsonElement json() {
		JsonObject recipe = base("crafting_shaped", ResultType.OBJECT);
		recipe.add("pattern", this.pattern);
		recipe.add("key", this.keys.json);

		return recipe;
	}

	public static class KeyBuilder {

		private final JsonObject json = new JsonObject();

		public KeyBuilder key(char key, Item item) {
			this.json.add(key + "", ingredient(item));
			return this;
		}

		public KeyBuilder key(char key, String item) {
			this.json.add(key + "", ingredient(item));
			return this;
		}

	}

}
