
public class State {
	private Pokemon currentPokemon;
	private Pokemon myBackupPokemon1;
	private Pokemon myBackupPokemon2;
	private Pokemon rivalPokemon;
	private Pokemon rivalBackupPokemon1;
	private Pokemon rivalBackupPokemon2;

	private int numActions;

	public State(Pokemon currentPokemon, Pokemon myBackupPokemon1, Pokemon myBackupPokemon2, Pokemon rivalPokemon,
			Pokemon rivalBackupPokemon1, Pokemon rivalBackupPokemon2) {
		this.currentPokemon = currentPokemon;
		this.myBackupPokemon1 = myBackupPokemon1;
		this.myBackupPokemon2 = myBackupPokemon2;
		this.rivalPokemon = rivalPokemon;
		this.rivalBackupPokemon1 = rivalBackupPokemon1;
		this.rivalBackupPokemon2 = rivalBackupPokemon2;
		setNumActions();
	}

	public int getNumActions() {
		return numActions;
	}

	public void setNumActions() {
		int numActions = 0;
		if (!currentPokemon.isFainted() && !myBackupPokemon1.isFainted() && !myBackupPokemon2.isFainted()) {
			numActions = 4;
			/*
			 * 0 - backup1 1 - backup2 2 - ataque neutro 3 - ataque de tipo
			 */
		} else if (myBackupPokemon1.isFainted() || myBackupPokemon2.isFainted()) {
			numActions = 3;
			/*
			 * 0 - ataque neutro 1 - ataque de tipo 2 - backup1/backup2 (el que no esté
			 * muerto)
			 */
		} else if (myBackupPokemon1.isFainted() && myBackupPokemon2.isFainted()) {
			numActions = 2;
			/*
			 * 0 - ataque neutro 1 - ataque de tipo
			 */
		}
		this.numActions = numActions;
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

	public Pokemon getRivalBackupPokemon1() {
		return rivalBackupPokemon1;
	}

	public void setRivalBackupPokemon1(Pokemon rivalBackupPokemon1) {
		this.rivalBackupPokemon1 = rivalBackupPokemon1;
	}

	public Pokemon getRivalBackupPokemon2() {
		return rivalBackupPokemon2;
	}

	public void setRivalBackupPokemon2(Pokemon rivalBackupPokemon2) {
		this.rivalBackupPokemon2 = rivalBackupPokemon2;
	}

	public String getStateName() {
		return currentPokemon.getType() + "_" + currentPokemon.getHp() + "_" + myBackupPokemon1.faintedOrType() + "_"
				+ myBackupPokemon2.faintedOrType() + "_" + rivalPokemon.getType() + "_" + rivalPokemon.getHp() + "_"
				+ rivalBackupPokemon1.faintedOrType() + "_" + rivalBackupPokemon2.faintedOrType();
	}

}
