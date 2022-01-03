package net.darktree.lotus.model.factory.model;

import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;

import java.util.HashSet;
import java.util.Set;

public class FacesBuilder {

	Set<Pair<String, FaceBuilder>> faces = new HashSet<>();
	public ElementBuilder parent;

	public FacesBuilder(ElementBuilder parent) {
		this.parent = parent;
	}

	public static FacesBuilder of(ElementBuilder parent, String texture) {
		return new FacesBuilder(parent)
				.face(Face.NORTH, texture)
				.face(Face.SOUTH, texture)
				.face(Face.WEST, texture)
				.face(Face.EAST, texture)
				.face(Face.DOWN, texture)
				.face(Face.UP, texture);
	}

	public FaceBuilder face(String name) {
		FaceBuilder face = new FaceBuilder(this.parent);
		faces.add(Pair.of(name, face));
		return face;
	}

	public FacesBuilder face(Face face, String texture) {
		FaceBuilder builder = new FaceBuilder(this.parent).texture(texture).cull(face);
		faces.add(Pair.of(face.name, builder));
		return this;
	}

	public JsonObject json() {
		JsonObject faces = new JsonObject();

		for( Pair<String, FaceBuilder> face : this.faces ) {
			faces.add(face.getFirst(), face.getSecond().json());
		}

		return faces;
	}

}
