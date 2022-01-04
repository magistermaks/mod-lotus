package net.darktree.lotus.model;

import net.darktree.lotus.model.factory.ApplicableFactory;
import net.darktree.lotus.model.factory.common.ModelProvider;

public class Models {

	public static final ApplicableFactory CUBE = texturedCubeOf("${id}:blocks/${name}");

	/**
	 * Create a simple cube model provider that uses the given texture pattern for all sides
	 */
	public static ApplicableFactory texturedCubeOf(String texturePattern) {
		ModelProvider model = Model.model(ModelProvider.of("block/block"))
				.textures()
					.particle(texturePattern)
					.texture("base", texturePattern)
					.pop()
				.elements()
					.element(0, 0, 0, 16, 16, 16).faces("#base")
					.pop()
				.provider();

		return Model.link(model);
	}

}
