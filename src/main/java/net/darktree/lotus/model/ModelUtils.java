package net.darktree.lotus.model;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.property.Property;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ModelUtils {

	private static final Function<Map.Entry<Property<?>, Comparable<?>>, String> MAP_TO_VARIANT = entry -> {
		Property<?> property = entry.getKey();
		return property.getName() + "=" + getPropertyName(property, entry.getValue());
	};

	public static <T extends Comparable<T>> String getPropertyName(Property<T> property, Comparable<?> value) {
		//noinspection unchecked
		return property.name((T) value);
	}

	public static void forEachVariant(Block block, BiConsumer<String, BlockState> consumer, Property<?>... blacklist) {
		ArrayList<?> excluded = Lists.newArrayList(blacklist);

		for(BlockState state : block.getStateManager().getStates()) {
			consumer.accept(state.getEntries().entrySet().stream()
					.filter(property -> !excluded.contains(property.getKey()))
					.map(MAP_TO_VARIANT).collect(Collectors.joining(",")), state);
		}
	}

}
