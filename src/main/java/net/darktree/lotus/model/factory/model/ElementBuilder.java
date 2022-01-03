package net.darktree.lotus.model.factory.model;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.darktree.lotus.model.factory.ModelFactory;
import net.darktree.lotus.model.factory.common.ArrayVector;
import net.minecraft.util.math.Direction;

public class ElementBuilder {

	private final ModelFactory model;
	private final JsonArray from;
	private final JsonArray to;

	private FacesBuilder faces = new FacesBuilder(this);
	private JsonObject rotation = null;
	private boolean shade = true;

	public ElementBuilder(ModelFactory model, float fx, float fy, float fz, float tx, float ty, float tz) {
		this.from = ArrayVector.of(fx, fy, fz);
		this.to = ArrayVector.of(tx, ty, tz);
		this.model = model;
	}

	public ElementBuilder noShade() {
		this.shade = false;
		return this;
	}

	public ElementBuilder rotation(float ox, float oy, float oz, Direction.Axis axis, float angle, boolean rescale) {
		rotation = new JsonObject();
		rotation.add("origin", ArrayVector.of(ox, oy, oz));
		rotation.addProperty("axis", axis.asString());
		rotation.addProperty("angle", angle);
		rotation.addProperty("rescale", rescale);
		return this;
	}

	public FaceBuilder face(Face face) {
		return this.faces.face(face.name);
	}

	public ElementBuilder faces(String texture) {
		this.faces = FacesBuilder.of(this, texture);
		return this;
	}

	public JsonObject json() {
		JsonObject object = new JsonObject();
		object.add("from", this.from);
		object.add("to", this.to);
		object.add("faces", this.faces.json());

		if(this.rotation != null) object.add("rotation", rotation);
		if(!this.shade) object.addProperty("shade", false);

		return object;
	}

	public ModelFactory pop() {
		return this.model;
	}

}
