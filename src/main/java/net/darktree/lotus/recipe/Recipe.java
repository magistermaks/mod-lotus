package net.darktree.lotus.recipe;

import com.google.gson.JsonElement;
import net.darktree.interference.RecipeInjector;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import org.apache.commons.lang3.mutable.MutableObject;

import java.util.ArrayList;

public class Recipe {

	private final ArrayList<JsonElement> list = new ArrayList<>();
	private final Identifier id;

	public Recipe(Identifier id) {
		this.id = id;
	}

	public void add(JsonElement recipe) {
		this.list.add(recipe);
	}

	public void inject() {
		for( JsonElement recipe : this.list ) {
			RecipeInjector.inject(this.id, recipe);
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
