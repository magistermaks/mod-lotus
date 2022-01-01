package net.darktree.lotus.factory.builder.provider;

import net.darktree.lotus.factory.builder.BlockBuilder;
import net.darktree.lotus.factory.builder.Group;
import net.darktree.lotus.factory.builder.ItemBuilder;

public interface BuilderProvider {

	BlockBuilder block(String id);
	ItemBuilder item(String id);

	Group child();

}
