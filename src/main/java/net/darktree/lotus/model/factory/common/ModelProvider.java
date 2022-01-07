package net.darktree.lotus.model.factory.common;

import net.minecraft.util.Identifier;

public interface ModelProvider {

	static ModelProvider ofName(String model) {
		return (name, data) -> PatternResolver.of(model, name);
	}

	String get(Identifier name, Identifier data);
	
}
