package net.darktree.lotus.model.factory.model;

public enum DisplayType {
	THIRDPERSON_RIGHTHAND("thirdperson_righthand"),
	THIRDPERSON_LEFTHAND("thirdperson_lefthand"),
	FIRSTPERSON_RIGHTHAND("firstperson_righthand"),
	FIRSTPERSON_LEFTHAND("firstperson_lefthand"),
	GUI("gui"),
	HEAD("head"),
	GROUND("ground"),
	FIXED("fixed");

	public final String name;

	DisplayType(String name) {
		this.name = name;
	}

}
