package net.darktree.lotus;

import net.darktree.lotus.factory.Factory;
import net.darktree.lotus.factory.builder.old.variant.VariantIterator;
import net.darktree.lotus.model.Models;
import net.darktree.lotus.recipe.Recipe;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Items;


public class Lotus implements ModInitializer {

//	FACTORY.block("%0_%1_test_block", TYPES, TYPES).item().dropSelf().gen(TestBlock::new).get();
//	private final Factory FACTORY = new Factory("lotus");
	private final VariantIterator TYPES = new VariantIterator("a", "b", "c", "d");

	private final Factory FACTORY = new Factory("lotus");

	@Override
	public void onInitialize() {

		FACTORY.block("test_block").of(TestBlock::new)
				.item() // create a block item, can be configured to use a custom lambda
				.drop(Items.DIRT) // defines the default loot, you can use lambdas for loot generations
				.recipe(Recipe.raw().shape2x2(Items.OBSIDIAN)) // define default item recipe
				.model(Models.CUBE) // set a default model factory, you can define your own model factories
				.get();

	}





	private void s() {

		Recipe.of(Items.STONE).shapeless(Items.DIRT).add();
		Recipe.of(Items.DIAMOND).shapeless().put(Items.STONE).put(Items.COARSE_DIRT).add();
		Recipe.of(Items.DIAMOND).shape2x2(Items.ROTTEN_FLESH).add();

		Recipe.of(Items.APPLE).shapeless(Items.ROTTEN_FLESH).and().smelting(Items.DIAMOND).add();

		// define 3 recipes
		Recipe.of(Items.STONE).cooking(Items.DIRT).add();

	}

}
