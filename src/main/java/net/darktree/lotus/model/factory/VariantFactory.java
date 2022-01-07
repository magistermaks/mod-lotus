package net.darktree.lotus.model.factory;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import net.darktree.interference.ModelInjector;
import net.darktree.lotus.model.factory.blockstates.ModelLinkArrayBuilder;
import net.minecraft.util.Identifier;

import java.util.HashSet;
import java.util.Set;

public class VariantFactory {

	Set<Pair<String, ModelLinkArrayBuilder<VariantFactory>>> variants = new HashSet<>();

	public ModelLinkArrayBuilder<VariantFactory> always() {
		return this.when("", "");
	}

	public ModelLinkArrayBuilder<VariantFactory> when(String state, String value) {
		return this.when(state + "=" + value);
	}

	public ModelLinkArrayBuilder<VariantFactory> when(String variant) {
		ModelLinkArrayBuilder<VariantFactory> linkArray = new ModelLinkArrayBuilder<>(this);
		variants.add(Pair.of(variant, linkArray));
		return linkArray;
	}

	public JsonObject json(Identifier name, Identifier data) {
		JsonObject model = new JsonObject();
		JsonObject variants = new JsonObject();

		for(Pair<String, ModelLinkArrayBuilder<VariantFactory>> variant : this.variants) {
			variants.add(variant.getFirst(), variant.getSecond().json(name, data));
		}

		model.add("variants", variants);

		return model;
	}

	public ApplicableFactory get() {
		return (name, data) -> ModelInjector.injectBlockState(name, this.json(name, data));
	}

}
