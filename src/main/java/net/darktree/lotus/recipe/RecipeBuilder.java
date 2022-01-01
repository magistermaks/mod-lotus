package net.darktree.lotus.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import org.apache.commons.lang3.mutable.MutableObject;

public abstract class RecipeBuilder {

	protected RecipeBuilder chain;
	protected MutableObject<RecipeOutput> output;

	protected RecipeBuilder(MutableObject<RecipeOutput> output) {
		this.output = output;
	}

	public RecipeBuilder output(RecipeOutput output) {
		this.output.setValue(output);
		return this;
	}

	protected RecipeBasicBuilder link(RecipeBuilder other) {
		this.chain = other;
		return (RecipeBasicBuilder) this;
	}

	protected Recipe append(Recipe recipe) {
		recipe.add(this.json());
		if(this.chain != null) this.chain.append(recipe);
		return recipe;
	}

	public RecipeBasicBuilder and() {
		return new RecipeBasicBuilder(this.output).link(this);
	}

	public Recipe get() {
		return this.append(new Recipe(this.output.getValue().id()));
	}

	public void add() {
		this.get().inject();
	}

	protected JsonObject base(String type, ResultType result) {
		if(output.getValue() == null) {
			throw new RuntimeException("Unable to serialized un-configured recipe!");
		}

		JsonObject recipe = new JsonObject();
		recipe.addProperty("type", type);
		this.output.getValue().result(recipe, result);

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
