package IA;

import pokemon.Pokemon;

public class Reward {

	public static int getReward(Pokemon rivalCurrentPokemon, Pokemon myCurrentPokemon, Pokemon rivalBeforeSwitch,
			Pokemon myBeforeSwitch, int rivalDamage, int myDamage) {
		int reward = 0;

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
					- getDamage(myBeforeSwitch, rivalBeforeSwitch));
		}
		return reward;

	}

	public static int getDamage(Pokemon myPokemon, Pokemon rivalPokemon) {

		String rivalEffectivityAgainstMe = myPokemon.getTypeAttacks().get(rivalPokemon.getType());
		String myEffectivityAgainstRival = rivalPokemon.getTypeAttacks().get(myPokemon.getType());

		int myDamage = 0;
		int rivalDamage = 0;

		if (rivalEffectivityAgainstMe.equals("effective")) {
			if (myPokemon.getHp() <= 4) {
				myDamage = myPokemon.getHp();
			} else {
				myDamage = 4;
			}
		}

		if (rivalEffectivityAgainstMe.equals("neutral") || rivalEffectivityAgainstMe.equals("nonEffective")) {
			if (myPokemon.getHp() <= 2) {
				myDamage = myPokemon.getHp();
			} else {
				myDamage = 2;
			}
		}
		if (myEffectivityAgainstRival.equals("effective")) {
			if (rivalPokemon.getHp() <= 4) {
				rivalDamage = rivalPokemon.getHp();
			} else {
				rivalDamage = 4;
			}
		}

		if (myEffectivityAgainstRival.equals("neutral") || myEffectivityAgainstRival.equals("nonEffective")) {
			if (rivalPokemon.getHp() <= 2) {
				rivalDamage = rivalPokemon.getHp();
			} else {
				rivalDamage = 2;
			}
		}
		return rivalDamage - myDamage;
	}
}
