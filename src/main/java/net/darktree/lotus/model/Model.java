package net.darktree.lotus.model;

import net.darktree.lotus.model.factory.ModelFactory;
import net.darktree.lotus.model.factory.MultipartFactory;
import net.darktree.lotus.model.factory.VariantFactory;

public class Model {

	public static VariantFactory variant() {
		return new VariantFactory();
	}

	public static MultipartFactory multipart() {
		return new MultipartFactory();
	}

	public static ModelFactory model() {
		return new ModelFactory();
	}

}
