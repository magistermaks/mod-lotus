package net.darktree.lotus.model.factory.blockstates;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.darktree.lotus.model.factory.MultipartFactory;
import net.minecraft.util.Identifier;

public class MultipartCondition {

	private final JsonArray conditions;
	private JsonObject condition = new JsonObject();
	private final boolean any;

	private final ModelLinkArrayBuilder<MultipartFactory> then;

	public MultipartCondition(boolean any, ModelLinkArrayBuilder<MultipartFactory> then) {
		this.any = any;
		this.then = then;

		if(any) {
			this.conditions = new JsonArray();
		}else{
			this.conditions = null;
		}
	}

	public MultipartCondition when(String state, String value) {
		this.condition.addProperty(state, value);
		return this;
	}

	public MultipartCondition or() {
		if(!this.any) {
			throw new RuntimeException("Unable to use optional condition in this context!");
		}

		this.next();
		return this;
	}

	private void next() {
		this.conditions.add(this.condition);
		this.condition = new JsonObject();
	}

	public ModelLinkArrayBuilder<MultipartFactory> then() {
		return this.then;
	}

	public JsonElement json(Identifier name) {
		JsonObject object = new JsonObject();

		if(any) {
			this.next();
			this.condition.add("OR", this.conditions);
		}

		object.add("when", this.condition);
		object.add("apply", this.then.json(name));

		return object;
	}

}
