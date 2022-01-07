package net.darktree.lotus.model.factory;

import com.google.gson.JsonObject;
import net.darktree.interference.ModelInjector;
import net.darktree.lotus.model.factory.common.ModelProvider;
import net.darktree.lotus.model.factory.common.PatternResolver;
import net.darktree.lotus.model.factory.model.DisplayBuilder;
import net.darktree.lotus.model.factory.model.ElementsBuilder;
import net.darktree.lotus.model.factory.model.TexturesBuilder;
import net.minecraft.util.Identifier;

public class ModelFactory {

	private final ModelProvider parent;

	private boolean ambientOcclusion = false;
	private DisplayBuilder display;
	private TexturesBuilder textures;
	private ElementsBuilder elements;

	public ModelFactory(ModelProvider provider) {
		this.parent = provider;
	}

	public ModelFactory useAmbientOcclusion() {
		this.ambientOcclusion = true;
		return this;
	}

	public DisplayBuilder display() {
		if(this.display == null) this.display = new DisplayBuilder(this);
		return this.display;
	}

	public TexturesBuilder textures() {
		if(this.textures == null) this.textures = new TexturesBuilder(this);
		return this.textures;
	}

	public ElementsBuilder elements() {
		if(this.elements == null) this.elements = new ElementsBuilder(this);
		return this.elements;
	}

	public JsonObject json(Identifier name, Identifier data) {
		JsonObject model = new JsonObject();
		model.addProperty("parent", this.parent.get(name, data));
		model.addProperty("ambientocclusion", this.ambientOcclusion);

		if(this.display != null) model.add("display", this.display.json());
		if(this.textures != null) model.add("textures", this.textures.json(data));
		if(this.elements != null) model.add("elements", this.elements.json());

		return model;
	}

	public ApplicableFactory get(String prefix) {
		return (name, data) -> {
			name = new Identifier(name.getNamespace(), prefix + name.getPath());
			System.out.println("name: " + name.toString() + " data: " + data.toString()) ;
			ModelInjector.injectModel(name, this.json(name, data));
		};
	}

	public ModelProvider provider(String pattern) {
		return (name, data) -> {
			name = PatternResolver.id(pattern, name);
			get("").inject(name, data);
			return name.toString();
		};
	}

}
