package net.darktree.lotus.recipe.type;

public enum FurnaceType {
	BLASTING("blasting", 100),
	SMOKING("smoking", 100),
	DEFAULT("smelting", 200),
	CAMPFIRE("campfire_cooking", 600);

	final String type;
	final int time;

	FurnaceType(String type, int time) {
		this.type = type;
		this.time = time;
	}
}
