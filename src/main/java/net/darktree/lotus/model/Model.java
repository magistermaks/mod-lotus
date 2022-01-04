package net.darktree.lotus.model;

import net.darktree.lotus.model.factory.ApplicableFactory;
import net.darktree.lotus.model.factory.ModelFactory;
import net.darktree.lotus.model.factory.MultipartFactory;
import net.darktree.lotus.model.factory.VariantFactory;
import net.darktree.lotus.model.factory.common.ModelProvider;

public class Model {

	public static VariantFactory variant() {
		return new VariantFactory();
	}

	public static MultipartFactory multipart() {
		return new MultipartFactory();
	}

	public static ModelFactory model(ModelProvider provider) {
		return new ModelFactory(provider);
	}

	/**
	 * Utility method for creating a blockstate model factory that always
	 * uses the one given model provider.
	 */
	public static ApplicableFactory link(ModelProvider model) {
		return variant().always().model(model).pop().pop().get();
	}

	/**
	 * Utility method for creating a model provider that inherits from given model
	 * and applies the texture string to texture tag #all
	 */
	public static ModelProvider texturedModelAll(String model, String texture, String name) {
		return Model.model(ModelProvider.of(model)).textures().texture("all", texture).pop().provider(name);
	}

}
