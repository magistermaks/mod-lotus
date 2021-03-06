package net.darktree.lotus.recipe;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class RecipeOutput {

	final String item;
	final int count;
	final String group;

	public RecipeOutput(Item item, int count, String group) {
		this(Registry.ITEM.getId(item).toString(), count, group);
	}

	public RecipeOutput(String item, int count, String group) {
		this.item = item;
		this.count = count;
		this.group = group;

		if(this.count == 0) {
			throw new RuntimeException("Invalid output count, must be >= 1");
		}
	}

	static RecipeOutput of(Item item, int count, String group) {
		return new RecipeOutput(item, count, group);
	}

	public void result(JsonObject recipe, RecipeBuilder.ResultType type) {
		if(this.group != null) recipe.addProperty("group", this.group);

		if(type == RecipeBuilder.ResultType.OBJECT) {
			JsonObject result = new JsonObject();
			result.addProperty("item", this.item);
			result.addProperty("count", this.count);

			recipe.add("result", result);
		}else if(type == RecipeBuilder.ResultType.STRING){
			if(this.count != 1) {
				throw new RuntimeException("Unable to set count greater than 1 for this recipe");
			}

			recipe.addProperty("result", this.item);
		}else{
			recipe.addProperty("item", this.item);
			recipe.addProperty("count", this.count);
		}
	}
}
