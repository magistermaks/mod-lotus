package net.darktree.lotus;

import net.darktree.lotus.factory.Factory;
import net.darktree.lotus.factory.builder.old.variant.VariantIterator;
import net.darktree.lotus.recipe.Recipe;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.Items;


public class Lotus implements ModInitializer {

	private final Factory FACTORY = new Factory("lotus");
	private final VariantIterator TYPES = new VariantIterator("a", "b", "c", "d");

	@Override
	public void onInitialize() {

//		FACTORY.block("%0_%1_test_block", TYPES, TYPES).item().dropSelf().gen(TestBlock::new).get();

		FACTORY.block("test_block").of(TestBlock::new).item().drop(Items.DIRT).get();
		//Recipe.of(Items.STONE).shapeless(Items.DIRT).add();
		//Recipe.of(Items.WARPED_BUTTON).shapeless().any(Items.DIRT, Items.COARSE_DIRT).add();

	}

}
