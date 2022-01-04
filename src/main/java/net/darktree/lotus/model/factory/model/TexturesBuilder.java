package net.darktree.lotus.model.factory.model;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import net.darktree.lotus.model.factory.ModelFactory;
import net.darktree.lotus.model.factory.common.PatternResolver;
import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Set;

public class TexturesBuilder {

	final ModelFactory parent;
	Set<Pair<String, String>> textures = new HashSet<>();

	public TexturesBuilder(ModelFactory modelFactory) {
		this.parent = modelFactory;
	}

	public TexturesBuilder particle(String value) {
		return this.texture("particle", value);
	}

	public TexturesBuilder texture(String name, String value) {
		this.textures.add(Pair.of(name, value));
		return this;
	}

	public ModelFactory pop() {
		return this.parent;
	}

	public JsonObject json(Identifier name) {
		JsonObject object = new JsonObject();

		for(Pair<String, String> texture : this.textures) {
			object.addProperty(texture.getFirst(), PatternResolver.of(texture.getSecond(), name));
		}

		return object;
	}

}
