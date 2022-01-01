package net.darktree.lotus.factory.builder.provider;

import com.google.gson.JsonElement;
import net.darktree.lotus.factory.builder.PropertyProvider;
import net.minecraft.item.Item;

public interface RecipeProvider {

	RecipeProvider NONE = null;

	JsonElement get(PropertyProvider provider, Item item);

}
