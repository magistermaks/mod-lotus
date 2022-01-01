package net.darktree.lotus.factory.builder;

import net.darktree.lotus.factory.builder.provider.BuilderProvider;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public class Group extends PropertyProvider implements BuilderProvider {

	final String modid;
	final PropertyProvider parent;

	public Group(@NotNull PropertyProvider parent, @NotNull String modid) {
		this.modid = modid;
		this.parent = parent;
	}

	@Override
	public BlockBuilder block(String id) {
		return new BlockBuilder(this, id);
	}

	@Override
	public ItemBuilder item(String id) {
		return new ItemBuilder(this, id);
	}

	@Override
	public Group child() {
		return new Group(this, this.modid);
	}

	@Override
	@NotNull
	@ApiStatus.Internal
	public PropertyProvider parent() {
		return this.parent;
	}

}
