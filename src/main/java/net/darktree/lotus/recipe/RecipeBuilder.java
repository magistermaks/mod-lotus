package net.darktree.lotus.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.Nullable;

public abstract class RecipeBuilder {

	protected RecipeOutput output;

	protected RecipeBuilder(@Nullable RecipeOutput output) {
		this.output = output;
	}

	public RecipeBuilder output(RecipeOutput output) {
		this.output = output;
		return this;
	}

	public Recipe get() {
		return new Recipe(null, this.json());
	}

	public void add() {
		this.get().inject();
	}

	protected JsonObject base(String type, ResultType result) {
		if(output == null) {
			throw new RuntimeException("Unable to serialized un-configured recipe!");
		}

		JsonObject recipe = new JsonObject();
		recipe.addProperty("type", type);
		this.output.result(recipe, result);

		return recipe;
	}

	protected static JsonObject ingredient(String item) {
		JsonObject ingredient = new JsonObject();

		if(item.startsWith("#")){
			ingredient.addProperty("tag", item);
		}else{
			ingredient.addProperty("item", item);
		}

		return ingredient;
	}

	protected static JsonObject ingredient(Item item) {
		return ingredient(Registry.ITEM.getId(item).toString());
	}

	public abstract JsonElement json();

	protected enum ResultType {
		OBJECT,
		STRING,
		DOUBLE
	}

}
