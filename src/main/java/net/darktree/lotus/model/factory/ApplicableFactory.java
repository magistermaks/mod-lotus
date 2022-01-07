package net.darktree.lotus.model.factory;

import net.minecraft.util.Identifier;

public interface ApplicableFactory {
	void inject(Identifier name, Identifier data);

	default ApplicableFactory wrap(String override) {
		return (name, data) -> this.inject(name, new Identifier(override));
	}

	default void inject(Identifier name) {
		this.inject(name, name);
	}
}
