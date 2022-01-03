package net.darktree.lotus;

import net.darktree.lotus.factory.Factory;
import net.darktree.lotus.factory.builder.old.variant.VariantIterator;
import net.darktree.lotus.model.Model;
import net.darktree.lotus.model.factory.ApplicableFactory;
import net.darktree.lotus.model.factory.VariantFactory;
import net.darktree.lotus.model.factory.common.ModelProvider;
import net.darktree.lotus.model.factory.model.Face;
import net.darktree.lotus.recipe.Recipe;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Items;


public class Lotus implements ModInitializer {

	private final Factory FACTORY = new Factory("lotus");
	private final VariantIterator TYPES = new VariantIterator("a", "b", "c", "d");

	@Override
	public void onInitialize() {

//		FACTORY.block("%0_%1_test_block", TYPES, TYPES).item().dropSelf().gen(TestBlock::new).get();

		FACTORY.block("test_block").of(TestBlock::new)
				.item()
				.drop(Items.DIRT)
				.recipe(Recipe.raw().shape2x2(Items.OBSIDIAN))
				.get();

		Recipe.of(Items.STONE).shapeless(Items.DIRT).add();
		Recipe.of(Items.DIAMOND).shapeless().put(Items.STONE).put(Items.COARSE_DIRT).add();
		Recipe.of(Items.DIAMOND).shape2x2(Items.ROTTEN_FLESH).add();

		Recipe.of(Items.APPLE).shapeless(Items.ROTTEN_FLESH).and().smelting(Items.DIAMOND).add();

		// define 3 recipes
		Recipe.of(Items.STONE).cooking(Items.DIRT).add();

//		ApplicableFactory model = Model.variant()
//				.always()
//					.model("block/dirt").weight(2).pop()
//					.model("block/stone").pop()
//					.pop()
//				.get();

		ModelProvider model = Model.model(ModelProvider.of("block/block"))
				.textures()
					.particle("block/magma")
					.texture("base", "block/stone")
					.pop()
				.elements()
					.element(0, 0, 0, 16, 16, 16).faces("#base")
					.pop()
				.provider();

		ApplicableFactory state = Model.variant()
				.always()
					.model(model).pop()
					.pop()
				.get();

		state.inject("lotus:test_block");

	}

}
