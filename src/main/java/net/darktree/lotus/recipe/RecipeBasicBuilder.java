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
		throw new RuntimeException("Serializing RecipeBasicBuilder is not allowed!");
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
		return this.smelting(0.35, items);
	}

	public SmeltingRecipeBuilder smoking(Item... items) {
		return this.smoking(0.35, items);
	}

	public SmeltingRecipeBuilder blasting(Item... items) {
		return this.blasting(0.35, items);
	}

	public SmeltingRecipeBuilder campfire(Item... items) {
		return this.campfire(0.35, items);
	}

	public SmeltingRecipeBuilder smelting(double experience, Item... items) {
		return new SmeltingRecipeBuilder(this, FurnaceType.DEFAULT, items, experience);
	}

	public SmeltingRecipeBuilder smoking(double experience, Item... items) {
		return new SmeltingRecipeBuilder(this, FurnaceType.SMOKING, items, experience);
	}

	public SmeltingRecipeBuilder blasting(double experience, Item... items) {
		return new SmeltingRecipeBuilder(this, FurnaceType.BLASTING, items, experience);
	}

	public SmeltingRecipeBuilder campfire(double experience, Item... items) {
		return new SmeltingRecipeBuilder(this, FurnaceType.CAMPFIRE, items, experience);
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

	public RecipeBuilder cooking(Item... items) {
		return this.cooking(0.35, items);
	}

	public RecipeBuilder cooking(double experience, Item... items) {
		return this.smelting(experience, items).and().smoking(experience, items).and().campfire(experience, items);
	}

}
