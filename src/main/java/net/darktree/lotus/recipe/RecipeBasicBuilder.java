package net.darktree.lotus.recipe;

import com.google.gson.JsonObject;
import net.darktree.lotus.recipe.type.*;
import net.minecraft.item.Item;
import org.apache.commons.lang3.mutable.MutableObject;
import org.jetbrains.annotations.Nullable;

public class RecipeBasicBuilder extends RecipeBuilder {

	protected RecipeBasicBuilder(@Nullable MutableObject<RecipeOutput> output) {
		super(output);
	}

	public JsonObject json() {
		throw new RuntimeException("Serializing RecipeBuilder is not allowed!");
	}

	public ShapelessRecipeBuilder shapeless(Item... items) {
		return new ShapelessRecipeBuilder(this, items);
	}

	public ShapedRecipeBuilder shaped(String pattern) {
		return new ShapedRecipeBuilder(this, pattern);
	}

	public ShapedRecipeBuilder shape2x2(Item item) {
		return new ShapedRecipeBuilder(this, "XX,XX").key('X', item);
	}

	public ShapedRecipeBuilder shape3x3(Item item) {
		return new ShapedRecipeBuilder(this, "XXX,XXX,XXX").key('X', item);
	}

	public SmeltingRecipeBuilder smelting(Item... items) {
		return new SmeltingRecipeBuilder(this, FurnaceType.DEFAULT, items);
	}

	public SmeltingRecipeBuilder smoking(Item... items) {
		return new SmeltingRecipeBuilder(this, FurnaceType.SMOKING, items);
	}

	public SmeltingRecipeBuilder blasting(Item... items) {
		return new SmeltingRecipeBuilder(this, FurnaceType.BLASTING, items);
	}

	public SmeltingRecipeBuilder campfire(Item... items) {
		return new SmeltingRecipeBuilder(this, FurnaceType.CAMPFIRE, items);
	}

	public SmithingRecipeBuilder smithing() {
		return new SmithingRecipeBuilder(this);
	}

	public SmithingRecipeBuilder smithing(Item base, Item addition) {
		return new SmithingRecipeBuilder(this).base(base).addition(addition);
	}

	public SmithingRecipeBuilder smithing(String base, String addition) {
		return new SmithingRecipeBuilder(this).base(base).addition(addition);
	}

	public CuttingRecipeBuilder cutting(Item... items) {
		return new CuttingRecipeBuilder(this, items);
	}

}
