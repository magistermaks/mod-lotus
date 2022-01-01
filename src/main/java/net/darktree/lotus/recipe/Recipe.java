package net.darktree.lotus.recipe;

import com.google.gson.JsonElement;
import net.darktree.interference.RecipeInjector;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class Recipe {

	private final JsonElement json;
	private final Identifier id;

	public Recipe(Identifier id, JsonElement json) {
		this.id = id;
		this.json = json;
	}

	public void inject() {
		RecipeInjector.inject(this.id, this.json);
	}

	public static RecipeBasicBuilder of(Item item) {
		return new RecipeBasicBuilder( RecipeOutput.of(item, 1, null) );
	}

	public static RecipeBasicBuilder of(Item item, int count) {
		return new RecipeBasicBuilder( RecipeOutput.of(item, count, null) );
	}

	public static RecipeBasicBuilder of(Item item, int count, String group) {
		return new RecipeBasicBuilder( RecipeOutput.of(item, count, group) );
	}

	public static RecipeBasicBuilder of(Item item, String group) {
		return new RecipeBasicBuilder( RecipeOutput.of(item, 1, group) );
	}

	public static RecipeBasicBuilder raw() {
		return new RecipeBasicBuilder(null);
	}

}
