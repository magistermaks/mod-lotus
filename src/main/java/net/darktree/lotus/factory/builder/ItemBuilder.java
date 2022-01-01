package net.darktree.lotus.factory.builder;

import net.minecraft.item.Item;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

public class ItemBuilder extends PropertyProvider {

	private final Group group;
	private final String id;

	public ItemBuilder(Group group, String id) {
		this.group = group;
		this.id = id;
	}

	@Override
	@NotNull
	@ApiStatus.Internal
	public PropertyProvider parent() {
		return this.group;
	}

	public Item get() {
		return null;
	}

	public Group next() {
		this.get();
		return this.group;
	}

}
