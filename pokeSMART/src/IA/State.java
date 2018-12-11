package IA;
import pokemon.Pokemon;

public class State {
	private Pokemon currentPokemon;
	private Pokemon myBackupPokemon1;
	private Pokemon myBackupPokemon2;
	private Pokemon rivalPokemon;

	public State(Pokemon currentPokemon, Pokemon myBackupPokemon1, Pokemon myBackupPokemon2, Pokemon rivalPokemon) {
		this.currentPokemon = currentPokemon;
		this.myBackupPokemon1 = myBackupPokemon1;
		this.myBackupPokemon2 = myBackupPokemon2;
		this.rivalPokemon = rivalPokemon;

	}

	public Pokemon getCurrentPokemon() {
		return currentPokemon;
	}

	public void setCurrentPokemon(Pokemon currentPokemon) {
		this.currentPokemon = currentPokemon;
	}

	public Pokemon getMyBackupPokemon1() {
		return myBackupPokemon1;
	}

	public void setMyBackupPokemon1(Pokemon myBackupPokemon1) {
		this.myBackupPokemon1 = myBackupPokemon1;
	}

	public Pokemon getMyBackupPokemon2() {
		return myBackupPokemon2;
	}

	public void setMyBackupPokemon2(Pokemon myBackupPokemon2) {
		this.myBackupPokemon2 = myBackupPokemon2;
	}

	public Pokemon getRivalPokemon() {
		return rivalPokemon;
	}

	public void setRivalPokemon(Pokemon rivalPokemon) {
		this.rivalPokemon = rivalPokemon;
	}

	public String getStateName() {
		StringBuilder sb = new StringBuilder();
		getCurrentPokemonEffectivity(sb);
		sb.append("_");
		getCurrentPokemonHPRange(sb);
		sb.append("_");
		getBackup1Effectivity(sb);
		sb.append("_");
		getBackup1HPRange(sb);
		sb.append("_");
		getBackup2Effectivity(sb);
		sb.append("_");
		getBackup2HpRange(sb);
		sb.append("_");
		getRivalHPRange(sb);

		return sb.toString();
	}

	private void getRivalHPRange(StringBuilder sb) {
		switch (rivalPokemon.getHp()) {
		case 5:
			sb.append("5-4");
			break;
		case 4:
			sb.append("5-4");
			break;
		case 3:
			sb.append("3-2");
			break;
		case 2:
			sb.append("3-2");
			break;
		case 1:
			sb.append("1");
			break;
		}
	}

	private void getBackup2HpRange(StringBuilder sb) {
		switch (myBackupPokemon2.getHp()) {
		case 5:
			sb.append("5-3");
			break;
		case 4:
			sb.append("5-3");
			break;
		case 3:
			sb.append("5-3");
			break;
		case 2:
			sb.append("2-0");
			break;
		case 1:
			sb.append("2-0");
			break;
		case 0:
			sb.append("2-0");
		}
	}

	private void getBackup2Effectivity(StringBuilder sb) {
		switch (rivalPokemon.getTypeAttacks().get(myBackupPokemon2.getType())) {
		case "effective":
			sb.append("1");
			break;
		case "neutral":
			sb.append("0");
			break;
		case "nonEffective":
			sb.append("-1");
			break;
		}
	}

	private void getBackup1HPRange(StringBuilder sb) {
		switch (myBackupPokemon1.getHp()) {
		case 5:
			sb.append("5-3");
			break;
		case 4:
			sb.append("5-3");
			break;
		case 3:
			sb.append("5-3");
			break;
		case 2:
			sb.append("2-0");
			break;
		case 1:
			sb.append("2-0");
			break;
		case 0:
			sb.append("2-0");
		}
	}

	private void getBackup1Effectivity(StringBuilder sb) {
		switch (rivalPokemon.getTypeAttacks().get(myBackupPokemon1.getType())) {
		case "effective":
			sb.append("1");
			break;
		case "neutral":
			sb.append("0");
			break;
		case "nonEffective":
			sb.append("-1");
			break;
		}
	}

	private void getCurrentPokemonHPRange(StringBuilder sb) {
		switch (currentPokemon.getHp()) {
		case 5:
			sb.append("5-4");
			break;
		case 4:
			sb.append("5-4");
			break;
		case 3:
			sb.append("3-2");
			break;
		case 2:
			sb.append("3-2");
			break;
		case 1:
			sb.append("1");
			break;
		}
	}

	private void getCurrentPokemonEffectivity(StringBuilder sb) {
		switch (rivalPokemon.getTypeAttacks().get(currentPokemon.getType())) {
		case "effective":
			sb.append("1");
			break;
		case "neutral":
			sb.append("0");
			break;
		case "nonEffective":
			sb.append("-1");
			break;
		}
	}

}
