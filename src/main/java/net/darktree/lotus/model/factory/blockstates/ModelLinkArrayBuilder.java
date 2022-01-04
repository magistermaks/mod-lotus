package net.darktree.lotus.model.factory.blockstates;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import net.darktree.lotus.model.factory.common.ModelProvider;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class ModelLinkArrayBuilder<T> implements Popable<T> {

	ArrayList<ModelLinkBuilder<ModelLinkArrayBuilder<T>>> links = new ArrayList<>();

	private final T parent;

	public ModelLinkArrayBuilder(T parent) {
		this.parent = parent;
	}

	public ModelLinkBuilder<ModelLinkArrayBuilder<T>> model(ModelProvider provider) {
		ModelLinkBuilder<ModelLinkArrayBuilder<T>> link = new ModelLinkBuilder<>(this, provider);
		this.links.add(link);
		return link;
	}

	public JsonElement json(Identifier name) {
		if(links.size() == 1) {
			return this.links.get(0).json(name);
		}

		if(links.size() == 0) {
			throw new RuntimeException("At least one model link variant required!");
		}

		JsonArray array = new JsonArray();
		for(ModelLinkBuilder<ModelLinkArrayBuilder<T>> link : this.links) {
			array.add(link.json(name));
		}

		return array;
	}

	public T pop() {
		return parent;
	}

}
