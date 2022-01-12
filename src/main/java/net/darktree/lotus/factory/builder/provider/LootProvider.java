package net.darktree.lotus.factory.builder.provider;

import net.darktree.interference.api.DefaultLoot;
import net.darktree.lotus.loot.LootManager;
import net.darktree.lotus.loot.LootTable;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;

import java.util.Collections;
import java.util.List;

public interface LootProvider {

	DefaultLoot SELF = (state, builder, identifier, context, world, lootTable) -> Collections.singletonList(new ItemStack(state.getBlock().asItem()));
	DefaultLoot NONE = null;

	static DefaultLoot of(Item item, int count) {
		return (state, builder, identifier, context, world, lootTable) -> Collections.singletonList(new ItemStack(item, count));
	}

	static DefaultLoot of(LootTable table) {
		return (state, builder, identifier, lootContext, serverWorld, lootTable) -> LootManager.dispatch(state, builder, table);
	}

	default DefaultLoot provider() {
		return (state, builder, identifier, lootContext, serverWorld, lootTable) -> this.get(state, lootContext, serverWorld);
	}

	List<ItemStack> get(BlockState state, LootContext context, ServerWorld world);

}
