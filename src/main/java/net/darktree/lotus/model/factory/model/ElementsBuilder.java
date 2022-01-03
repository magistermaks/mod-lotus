package net.darktree.lotus.model.factory.model;

import com.google.gson.JsonArray;
import net.darktree.lotus.model.factory.ModelFactory;

import java.util.ArrayList;

public class ElementsBuilder {

	private final ArrayList<ElementBuilder> elements = new ArrayList<>();
	private final ModelFactory parent;

	public ElementsBuilder(ModelFactory parent) {
		this.parent = parent;
	}

	public ModelFactory pop() {
		return this.parent;
	}

	public ElementBuilder element(float fx, float fy, float fz, float tx, float ty, float tz) {
		ElementBuilder element = new ElementBuilder(this.parent, fx, fy, fz, tx, ty, tz);
		this.elements.add(element);
		return element;
	}

	public JsonArray json() {
		JsonArray array = new JsonArray(elements.size());

		for( ElementBuilder element : elements ) {
			array.add(element.json());
		}

		return array;
	}

}
