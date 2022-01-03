package net.darktree.lotus.model.factory.blockstates;

public interface ModelProvider {

	static ModelProvider of(String path) {
		return name -> path.replaceAll("\\$\\{name}", name);
	}

	String get(String name);
	
}
