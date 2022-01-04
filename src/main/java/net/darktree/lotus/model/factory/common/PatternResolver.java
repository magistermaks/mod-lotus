package net.darktree.lotus.model.factory.common;

import net.minecraft.util.Identifier;

import java.util.regex.Pattern;

public final class PatternResolver {

	private static final Pattern NAME = Pattern.compile("\\$\\{name}");
	private static final Pattern ID = Pattern.compile("\\$\\{id}");

	public static String of(String pattern, Identifier id) {
		return ID.matcher(NAME.matcher(pattern).replaceAll(id.getPath())).replaceAll(id.getNamespace());
	}

	public static Identifier id(String pattern, Identifier id) {
		return new Identifier(of(pattern, id));
	}

}
