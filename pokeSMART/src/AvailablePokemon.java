package pokeSMART;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum AvailablePokemon {

	Charmander("fire"), Vulpix("fire"), Bulbasaur("grass"), Oddish("grass"), Squirtle("water"), Poliwag("water"),
	Umbreon("dark"), Poochyena("dark"), Machop("fighting"), Mankey("fighting"), Gastly("ghost"), Misdreavus("ghost");

	private final String type;

	AvailablePokemon(final String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	private static final List<AvailablePokemon> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = VALUES.size();
	private static final Random RANDOM = new Random();

	public static AvailablePokemon randomPokemon() {
		return VALUES.get(RANDOM.nextInt(SIZE));
	}

}
