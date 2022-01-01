package net.darktree.lotus.recipe;

import com.google.gson.JsonObject;
import net.darktree.interference.RecipeInjector;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.ArrayList;

public class Recipe {

	private final ArrayList<JsonObject> list = new ArrayList<>();
	private final String id;

	public Recipe(String id) {
		this.id = id;
	}

	public void add(JsonObject recipe) {
		this.list.add(recipe);
	}

	private Identifier id(JsonObject recipe) {
		return new Identifier(this.id + "_from_" + recipe.get("type").getAsString());
	}

	public void inject() {
		for( JsonObject recipe : this.list ) {
			System.out.println("Injected recipe with id: " + id(recipe).toString());
			RecipeInjector.inject(id(recipe), recipe);
		}
	}

	public static RecipeBasicBuilder of(Item item) {
		return new RecipeBasicBuilder( new MutableObject<>(RecipeOutput.of(item, 1, null)) );
	}

	public static RecipeBasicBuilder of(Item item, int count) {
		return new RecipeBasicBuilder( new MutableObject<>(RecipeOutput.of(item, count, null)) );
	}

	public static RecipeBasicBuilder of(Item item, int count, String group) {
		return new RecipeBasicBuilder( new MutableObject<>(RecipeOutput.of(item, count, group)) );
	}

	public static RecipeBasicBuilder of(Item item, String group) {
		return new RecipeBasicBuilder( new MutableObject<>(RecipeOutput.of(item, 1, group)) );
	}

	public static RecipeBasicBuilder raw() {
		return new RecipeBasicBuilder( new MutableObject<>(null) );
	}

}
