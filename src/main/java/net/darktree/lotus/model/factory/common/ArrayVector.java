package net.darktree.lotus.model.factory.common;

import com.google.gson.JsonArray;

public class ArrayVector {

	public static JsonArray of(float x, float y, float z) {
		JsonArray vector = new JsonArray(3);
		vector.add(x);
		vector.add(y);
		vector.add(z);

		return vector;
	}

	public static JsonArray of(float x, float y, float z, float w) {
		JsonArray vector = new JsonArray(4);
		vector.add(x);
		vector.add(y);
		vector.add(z);
		vector.add(w);

		return vector;
	}

}
