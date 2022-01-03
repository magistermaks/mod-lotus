package net.darktree.lotus.model.factory.model;

public enum Face {
	DOWN("down"),
	UP("up"),
	NORTH("north"),
	SOUTH("south"),
	WEST("west"),
	EAST("east");

	public final String name;

	Face(String name) {
		this.name = name;
	}
}
