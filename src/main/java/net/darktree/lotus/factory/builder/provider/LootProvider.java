package net.darktree.lotus.factory.builder.provider;

import net.darktree.interference.api.DefaultLoot;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;

import java.util.Collections;
import java.util.List;

public interface LootProvider {

	LootProvider SELF = (state, context, world) -> Collections.singletonList(new ItemStack(state.getBlock().asItem()));
	LootProvider NONE = null;

	static LootProvider of(Item item, int count) {
		return (state, context, world) -> Collections.singletonList(new ItemStack(item, count));
	}

	default DefaultLoot provider() {
		return (state, builder, identifier, lootContext, serverWorld, lootTable) -> this.get(state, lootContext, serverWorld);
	}

	List<ItemStack> get(BlockState state, LootContext context, ServerWorld world);

}
