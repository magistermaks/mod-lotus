package net.darktree.lotus.recipe;

import com.google.gson.JsonElement;
import net.darktree.lotus.recipe.type.*;
import net.minecraft.item.Item;
import org.apache.commons.lang3.mutable.MutableObject;
import org.jetbrains.annotations.Nullable;

public class RecipeBasicBuilder extends RecipeBuilder {

	protected RecipeBasicBuilder(@Nullable MutableObject<RecipeOutput> output) {
		super(output);
	}

	public JsonElement json() {
		throw new RuntimeException("Serializing RecipeBuilder is not allowed!");
	}

	public ShapelessRecipeBuilder shapeless(Item... items) {
		return new ShapelessRecipeBuilder(this.output, items);
	}

	public ShapedRecipeBuilder shaped(String pattern) {
		return new ShapedRecipeBuilder(this.output, pattern);
	}

	public ShapedRecipeBuilder shape2x2(Item item) {
		return new ShapedRecipeBuilder(this.output, "XX,XX").key('X', item);
	}

	public ShapedRecipeBuilder shape3x3(Item item) {
		return new ShapedRecipeBuilder(this.output, "XXX,XXX,XXX").key('X', item);
	}

	public SmeltingRecipeBuilder smelting(Item... items) {
		return new SmeltingRecipeBuilder(this.output, FurnaceType.DEFAULT, items);
	}

	public SmeltingRecipeBuilder smoking(Item... items) {
		return new SmeltingRecipeBuilder(this.output, FurnaceType.SMOKING, items);
	}

	public SmeltingRecipeBuilder blasting(Item... items) {
		return new SmeltingRecipeBuilder(this.output, FurnaceType.BLASTING, items);
	}

	public SmeltingRecipeBuilder campfire(Item... items) {
		return new SmeltingRecipeBuilder(this.output, FurnaceType.CAMPFIRE, items);
	}

	public SmithingRecipeBuilder smithing() {
		return new SmithingRecipeBuilder(this.output);
	}

	public SmithingRecipeBuilder smithing(Item base, Item addition) {
		return new SmithingRecipeBuilder(this.output).base(base).addition(addition);
	}

	public SmithingRecipeBuilder smithing(String base, String addition) {
		return new SmithingRecipeBuilder(this.output).base(base).addition(addition);
	}

	public CuttingRecipeBuilder cutting(Item... items) {
		return new CuttingRecipeBuilder(this.output, items);
	}

}
