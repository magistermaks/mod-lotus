package net.darktree.lotus;

import net.darktree.lotus.factory.Factory;
import net.darktree.lotus.factory.builder.old.variant.VariantIterator;
import net.darktree.lotus.factory.builder.provider.BlockProvider;
import net.darktree.lotus.model.Models;
import net.darktree.lotus.recipe.Recipe;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.item.Items;
import org.lwjgl.system.CallbackI;


public class Lotus implements ModInitializer {

//	FACTORY.block("%0_%1_test_block", TYPES, TYPES).item().dropSelf().gen(TestBlock::new).get();
//	private final Factory FACTORY = new Factory("lotus");
	private final VariantIterator TYPES = new VariantIterator("a", "b", "c", "d");

	private final Factory FACTORY = new Factory("lotus");

	@Override
	public void onInitialize() {

		Block test = FACTORY.block("test_block").of(TestBlock::new)
				.item()
				.drop(Items.DIRT)
				.recipe(Recipe.raw().shape2x2(Items.OBSIDIAN))
				.model(Models.CUBE)
				.get();

		FACTORY.block("test_block_stairs").of((BlockProvider) provider -> new TestStairsBlock(test.getDefaultState(), provider.getBlockSettings()))
				.item()
				.drop(Items.DIRT)
				//.recipe(Recipe.raw().stair(Items.OBSIDIAN))
				.model(Models.STAIR)
				.get();

		FACTORY.block("test_block_slab").of(SlabBlock::new)
				.item()
				.drop(Items.DIRT)
				//.recipe(Recipe.raw().shape2x2(Items.OBSIDIAN))
				.model(Models.SLAB)
				.get();

		Recipe.of(Items.STONE).shapeless(Items.DIRT).add();
		Recipe.of(Items.DIAMOND).shapeless().put(Items.STONE).put(Items.COARSE_DIRT).add();
		Recipe.of(Items.DIAMOND).shape2x2(Items.ROTTEN_FLESH).add();

		Recipe.of(Items.APPLE).shapeless(Items.ROTTEN_FLESH).and().smelting(Items.DIAMOND).add();

		// define 3 recipes
		Recipe.of(Items.STONE).cooking(Items.DIRT).add();

	}

}
