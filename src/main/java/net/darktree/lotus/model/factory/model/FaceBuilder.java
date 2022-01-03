package net.darktree.lotus.model.factory.model;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.darktree.lotus.model.factory.common.ArrayVector;

public class FaceBuilder {

	private final JsonObject object = new JsonObject();
	private final ElementBuilder grandparent;

	public FaceBuilder(ElementBuilder grandparent) {
		this.grandparent = grandparent;
	}

	public FaceBuilder uv(float u1, float v1, float u2, float v2) {
		this.object.add("uv", ArrayVector.of(u1, v1, u2, v2));
		return this;
	}

	public FaceBuilder texture(String texture) {
		this.object.addProperty("texture", texture);
		return this;
	}

	public FaceBuilder cull(Face face) {
		this.object.addProperty("cullface", face.name);
		return this;
	}

	public FaceBuilder tint(int index) {
		this.object.addProperty("tintindex", index);
		return this;
	}

	public FaceBuilder rotation(int rotation) {
		if(rotation % 90 != 0) {
			throw new RuntimeException("Only angles in 90 degree increments are allowed!");
		}

		this.object.addProperty("rotation", rotation);
		return this;
	}

	public ElementBuilder pop() {
		return this.grandparent;
	}

	public JsonElement json() {
		return this.object;
	}

}
