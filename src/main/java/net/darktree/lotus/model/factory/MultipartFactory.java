package net.darktree.lotus.model.factory;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.darktree.interference.ModelInjector;
import net.darktree.lotus.model.factory.blockstates.ModelLinkArrayBuilder;
import net.darktree.lotus.model.factory.blockstates.MultipartCondition;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class MultipartFactory {

	ArrayList<MultipartCondition> conditions = new ArrayList<>();

	public MultipartCondition any() {
		MultipartCondition condition = new MultipartCondition(true, new ModelLinkArrayBuilder<>(this));
		conditions.add(condition);
		return condition;
	}

	public MultipartCondition all() {
		MultipartCondition condition = new MultipartCondition(false, new ModelLinkArrayBuilder<>(this));
		conditions.add(condition);
		return condition;
	}

	public JsonObject json(String name) {
		JsonObject object = new JsonObject();
		JsonArray array = new JsonArray();

		for( MultipartCondition condition : this.conditions ) {
			array.add(condition.json(name));
		}

		object.add("multipart", array);
		return object;
	}

	public ApplicableFactory get() {
		return name -> ModelInjector.injectBlockState(new Identifier(name), this.json(name));
	}

}
