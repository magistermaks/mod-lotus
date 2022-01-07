package net.darktree.lotus.model.factory.blockstates;

import com.google.gson.JsonObject;
import net.darktree.lotus.model.factory.common.ModelProvider;
import net.minecraft.util.Identifier;

public class ModelLinkBuilder<T> implements Popable<T> {

	boolean lock = false;
	int x = 0;
	int y = 0;
	int weight = 1;

	final ModelProvider provider;

	private final T back;

	public ModelLinkBuilder(T back, ModelProvider provider) {
		this.provider = provider;
		this.back = back;
	}

	public ModelLinkBuilder<T> x(int angle) {
		if(angle % 90 != 0) {
			throw new RuntimeException("Only angles in 90 degree increments are allowed!");
		}

		this.x = angle;
		return this;
	}

	public ModelLinkBuilder<T> y(int angle) {
		if(angle % 90 != 0) {
			throw new RuntimeException("Only angles in 90 degree increments are allowed!");
		}

		this.y = angle;
		return this;
	}

	public ModelLinkBuilder<T> lock() {
		this.lock = true;
		return this;
	}

	public ModelLinkBuilder<T> weight(int weight) {
		this.weight = weight;
		return this;
	}

	public T pop() {
		return this.back;
	}

	public JsonObject json(Identifier name, Identifier data) {
		JsonObject model = new JsonObject();
		model.addProperty("x", this.x);
		model.addProperty("y", this.y);
		model.addProperty("uvlock", this.lock);
		model.addProperty("model", this.provider.get(name, data));
		model.addProperty("weight", this.weight);

		return model;
	}

}
