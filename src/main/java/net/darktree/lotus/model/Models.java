package net.darktree.lotus.model;

import net.darktree.lotus.model.factory.ApplicableFactory;
import net.darktree.lotus.model.factory.common.ModelProvider;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.StairShape;
import net.minecraft.state.property.Properties;

public class Models {

	public static final ApplicableFactory CUBE = texturedCubeOf("${id}:blocks/${name}");
	public static final ApplicableFactory STAIR = texturedStairOf("${id}:blocks/${name}");
	public static final ApplicableFactory SLAB = texturedSlabOf("${id}:blocks/${name}");
	public static final ApplicableFactory ITEM_FLAT = itemGenerated("${id}:items/${name}");
//	public static final ApplicableFactory ITEM_INHERIT = itemInherit();

//	public static ApplicableFactory itemInherit() {
//		return Model.model(ModelProvider.ofName("${id}"))
//				.get("item/");
//	}

	public static ApplicableFactory itemGenerated(String texture) {
		return Model.model(ModelProvider.ofName("item/generated"))
				.textures().texture("layer0", texture).pop()
				.get("item/");
	}

	/**
	 * Create a simple cube model factory that uses the given texture pattern for all sides
	 */
	public static ApplicableFactory texturedCubeOf(String texture) {
		ModelProvider model = Model.texturedModelAll("minecraft:block/cube_all", texture, "${id}:block/${name}");
		return Model.link(model);
	}

	/**
	 * Create a simple slab model factory that uses the given texture pattern for all sides
	 */
	public static ApplicableFactory texturedSlabOf(String texture) {
		ModelProvider modelBottom = Model.texturedModelAll("lotus:block/slab_bottom_all", texture, "${id}:block/${name}_bottom");
		ModelProvider modelTop = Model.texturedModelAll("lotus:block/slab_top_all", texture, "${id}:block/${name}_top");
		ModelProvider modelDouble = Model.texturedModelAll("minecraft:block/cube_all", texture, "${id}:block/${name}_double");

		return Model.variant()
				.when("type", "bottom").model(modelBottom).pop().pop()
				.when("type", "top").model(modelTop).pop().pop()
				.when("type", "double").model(modelDouble).pop().pop()
				.get();
	}

	/**
	 * Create a simple stair model factory that uses the given texture pattern for all sides
	 */
	public static ApplicableFactory texturedStairOf(String texture) {
		ModelProvider modelStraight = Model.texturedModelAll("lotus:block/stairs_all", texture, "${id}:block/${name}");
		ModelProvider modelInner = Model.texturedModelAll("lotus:block/inner_stairs_all", texture, "${id}:block/${name}_inner");
		ModelProvider modelOuter = Model.texturedModelAll("lotus:block/outer_stairs_all", texture, "${id}:block/${name}_outer");

		var variants = Model.variant();

		ModelUtils.forEachVariant(Blocks.STONE_STAIRS, (name, state) -> {
			var variant = variants.when(name);

			StairShape shape = state.get(Properties.STAIR_SHAPE);
			boolean shift = shape == StairShape.OUTER_LEFT || shape == StairShape.INNER_LEFT;

			var link = switch( shape ) {
				case INNER_LEFT, INNER_RIGHT -> variant.model(modelInner);
				case OUTER_LEFT, OUTER_RIGHT -> variant.model(modelOuter);
				case STRAIGHT -> variant.model(modelStraight);
			};

			if(state.get(Properties.BLOCK_HALF) == BlockHalf.TOP) {
				link.x(180);
			}

			int y = switch(state.get(Properties.HORIZONTAL_FACING)) {
				case NORTH -> shift ? 180 : 270;
				case WEST -> shift ? 90 : 180;
				case SOUTH -> shift ? 0 : 90;
				case EAST -> shift ? 270 : 0;
				default -> throw new RuntimeException("Invalid state!");
			};

			if(y != 0) {
				link.y(y).lock();
			}

		}, Properties.WATERLOGGED);

		return variants.get();
	}

}
