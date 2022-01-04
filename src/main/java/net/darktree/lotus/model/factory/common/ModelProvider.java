package net.darktree.lotus.model.factory.common;

import net.minecraft.util.Identifier;

public interface ModelProvider {

	static ModelProvider of(String model) {
		return name -> PatternResolver.of(model, name);
	}

	String get(Identifier name);
	
}
