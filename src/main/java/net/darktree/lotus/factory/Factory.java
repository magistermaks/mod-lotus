package net.darktree.lotus.factory;

import net.darktree.lotus.factory.builder.BlockBuilder;
import net.darktree.lotus.factory.builder.ItemBuilder;
import net.darktree.lotus.factory.builder.provider.BuilderProvider;
import net.darktree.lotus.factory.builder.Group;
import net.darktree.lotus.factory.builder.RootGroup;

public class Factory implements BuilderProvider {

	private final Group group;

	public Factory(String modid) {
		this.group = new Group(new RootGroup(), modid);
	}

	@Override
	public BlockBuilder block(String id) {
		return this.group.block(id);
	}

	@Override
	public ItemBuilder item(String id) {
		return this.group.item(id);
	}

	@Override
	public Group child() {
		return this.group.child();
	}

}
