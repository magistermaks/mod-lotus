package net.darktree.lotus.factory.builder.old.variant;

import java.util.Arrays;
import java.util.function.Consumer;

public class VariantIterator {

	public record Variant<T>(String name, T extension) {

	}

	private final Variant<?>[] variants;

	public VariantIterator( Variant<?>... variants ) {
		this.variants = variants;
	}

	public VariantIterator(String... variants) {
		this.variants = Arrays.stream(variants).map(name -> new Variant<>(name, null)).toArray(Variant[]::new);
	}

	public static void consume(Consumer<Variant<?>[]> consumer, VariantIterator... iterators) {

		final int count = iterators.length;
		final int[] indexes = new int[count];
		final int[] lengths = new int[count];

		// initialize
		for( int i = 0; i < count; i ++ ) {
			lengths[i] = iterators[i].variants.length;
			indexes[i] = 0;
		}

		Iterator:
		for( int i = count - 1; true; ) {
			consumer.accept( deserialize(indexes, iterators, count) );

			while(true) {
				indexes[i] ++;

				if( indexes[i] >= lengths[i] ) {
					indexes[i] = 0;
					i --;

					if(i < 0) break Iterator;

					continue;
				}

				break;
			}

			if( i + 1 < count ) i ++;
		}
	}

	private static Variant<?>[] deserialize( int[] indexes, VariantIterator[] iterators, int count ) {
		Variant<?>[] variants = new Variant[count];

		for( int i = 0; i < count; i ++ ) {
			variants[i] = iterators[i].variants[indexes[i]];
		}

		return variants;
	}

}
