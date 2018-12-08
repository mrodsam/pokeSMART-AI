
public class Reward {

	public static int getReward(Pokemon rivalCurrentPokemon, Pokemon myCurrentPokemon, Pokemon rivalBeforeSwitch,
			Pokemon myBeforeSwitch, int rivalDamage, int myDamage) {
		int reward = 0;
		System.out.println("My damage: " + myDamage + ", rival damage: " + rivalDamage);

		if (myDamage != 0 && rivalDamage != 0) {
			return rivalDamage - myDamage;

		} else if (myDamage != 0 && rivalDamage == 0) {

			if (!myCurrentPokemon.isFainted()) {
				return getDamage(myCurrentPokemon, rivalCurrentPokemon) - myDamage;
			} else {
				return -myDamage;
			}

		} else if (myDamage == 0 && rivalDamage != 0) {

			if (!rivalCurrentPokemon.isFainted()) {
				return (rivalDamage + getDamage(myCurrentPokemon, rivalCurrentPokemon));
			} else {
				return (rivalDamage);
			}

		} else if (myDamage == 0 && rivalDamage == 0) {
			return (getDamage(myCurrentPokemon, rivalCurrentPokemon)
					- getPastDamage(myBeforeSwitch, rivalBeforeSwitch));
		}
		return reward;

	}

	public static int getDamage(Pokemon myPokemon, Pokemon rivalPokemon) {

		String myEffective = myPokemon.getTypeAttacks().get(rivalPokemon.getType());
		String rivalEffective = rivalPokemon.getTypeAttacks().get(myPokemon.getType());

		int myDamage = 0;
		int rivalDamage = 0;

		if (myEffective.equals("effective")) {
			if (myPokemon.getHp() <= 4) {
				myDamage = myPokemon.getHp();
			} else {
				myDamage = 4;
			}
		}

		if (myEffective.equals("neutral") || myEffective.equals("nonEffective")) {
			if (myPokemon.getHp() <= 2) {
				myDamage = myPokemon.getHp();
			} else {
				myDamage = 2;
			}
		}
		if (rivalEffective.equals("effective")) {
			if (rivalPokemon.getHp() <= 4) {
				rivalDamage = rivalPokemon.getHp();
			} else {
				rivalDamage = 4;
			}
		}

		if (rivalEffective.equals("neutral") || rivalEffective.equals("nonEffective")) {
			if (rivalPokemon.getHp() <= 2) {
				rivalDamage = rivalPokemon.getHp();
			} else {
				rivalDamage = 2;
			}
		}
		return rivalDamage - myDamage;
	}

	public static int getPastDamage(Pokemon myBeforeSwitch, Pokemon rivalBeforeSwitch) {
		return 0;
	}
}
