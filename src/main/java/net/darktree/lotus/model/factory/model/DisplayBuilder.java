package net.darktree.lotus.model.factory.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.darktree.lotus.model.factory.ModelFactory;

public class DisplayBuilder {

	final JsonObject display = new JsonObject();
	final ModelFactory parent;

	public DisplayBuilder(ModelFactory modelFactory) {
		this.parent = modelFactory;
	}

	private JsonObject getType(DisplayType type) {
		if(!this.display.has(type.name)) {
			this.display.add(type.name, new JsonObject());
		}

		return (JsonObject) this.display.get(type.name);
	}

	public DisplayBuilder rotation(DisplayType type, float x, float y, float z) {
		JsonArray array = new JsonArray(3);
		array.add(x);
		array.add(y);
		array.add(z);

		this.getType(type).add("rotation", array);
		return this;
	}

	public DisplayBuilder translation(DisplayType type, float x, float y, float z) {
		JsonArray array = new JsonArray(3);
		array.add(x);
		array.add(y);
		array.add(z);

		this.getType(type).add("translation", array);
		return this;
	}

	public DisplayBuilder scale(DisplayType type, float x, float y, float z) {
		JsonArray array = new JsonArray(3);
		array.add(x);
		array.add(y);
		array.add(z);

		this.getType(type).add("scale", array);
		return this;
	}

	public JsonObject json() {
		return this.display;
	}


}
