import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import IA.Player;
import IA.Reward;
import IA.State;
import pokemon.AvailablePokemon;
import pokemon.Movements;
import pokemon.Pokemon;
import pokemon.Team;

public class Game {

	/*
	 * Falta:
	 * 
	 * Ahora todos los estados tienen 4 acciones, pero si escojo cambiar a un
	 * Pokémon desmayado, tengo que bloquear esa acción
	 */
	public static void main(String[] args) {
		int rewardPlayer1 = 0;
		int rewardPlayer2 = 0;
		boolean restartGame = true;

		Team teamPlayer1 = new Team(generateRandomTeam());
		Player player1 = new Player(teamPlayer1, 1);
		Team teamPlayer2 = new Team(generateRandomTeam());
		Player player2 = new Player(teamPlayer2, 2);

		int episodes = 10;
		FileWriter file = null;
		PrintWriter pw = null;
		try {
			file = new FileWriter("salidaJuego.txt");
			pw = new PrintWriter(file);

			while (episodes > 0) {

				/* Comprobar si hay ganador */
				restartGame = checkWinner(restartGame, player1.getTeam(), player2.getTeam(), pw);

				/* Reiniciar el juego */
				if (restartGame) {
					episodes--;

					teamPlayer1 = new Team(generateRandomTeam());
					player1.setTeam(teamPlayer1);

					teamPlayer2 = new Team(generateRandomTeam());
					player2.setTeam(teamPlayer2);

					restartGame = false;
				}

				/* Jugar */
				/* Se guardan los Pokémon actuales de cada jugador, antes de hacer un cambio */
				Pokemon player1PokemonBeforeSwitch = player1.getTeam().getCurrentPokemon();
				Pokemon player2PokemonBeforeSwitch = player2.getTeam().getCurrentPokemon();

				printBattleInfo(player1, player2, pw);

				/***************** Jugador 1 *****************/

				/*
				 * Si el Pokémon actual del jugador 1 está desmayado, escoge otro aleatoriamente
				 */
				if (player1.getTeam().getCurrentPokemon().isFainted()) {
					pw.println("Player 1 " + player1.getTeam().getCurrentPokemon().getName() + " is fainted");
					player1.getTeam().setCurrentPokemon(player1.choosePokemonRandomly());
					pw.println("Player 1 switch to " + player1.getTeam().getCurrentPokemon().getName());
				}

				/* Estado actual del jugador uno */
				State player1State = new State(player1.getTeam().getCurrentPokemon(), player1.getTeam().getBackup1(),
						player1.getTeam().getBackup2(), player2.getTeam().getCurrentPokemon());

				/* Elección de movimiento del jugador 1 */
				int player1Movement = player1.chooseMovement(player1State, rewardPlayer1);

				/* El jugador 1 escogió cambiar */
				switch (player1Movement) {
				case Movements.switchToBackup1:
					player1.getTeam().setCurrentPokemon(player1.getTeam().backup1);
					pw.println("Player 1 switch to " + player1.getTeam().getCurrentPokemon());
					break;
				case Movements.switchToBackup2:
					player1.getTeam().setCurrentPokemon(player1.getTeam().backup2);
					pw.println("Player 1 switch to " + player1.getTeam().getCurrentPokemon());
					break;
				}

				/***************** Jugador 2 *****************/
				if (player2.getTeam().getCurrentPokemon().isFainted()) {
					pw.println("Player 2 " + player2.getTeam().getCurrentPokemon().getName() + " is fainted");
					player1.getTeam().setCurrentPokemon(player2.choosePokemonRandomly());
					pw.println("Player 2 switch to " + player2.getTeam().getCurrentPokemon().getName());
				}
				/* Estado actual del jugador dos */
				State player2State = new State(player2.getTeam().getCurrentPokemon(), player2.getTeam().getBackup1(),
						player2.getTeam().getBackup2(), player1.getTeam().getCurrentPokemon());

				/* Elección de movimiento del jugador 2 */
				int player2Movement = player2.chooseMovement(player2State, rewardPlayer2);

				/* El jugador 2 escogió cambiar */
				switch (player2Movement) {
				case Movements.switchToBackup1:
					player2.getTeam().setCurrentPokemon(player2.getTeam().backup1);
					pw.println("Player 2 switch to " + player2.getTeam().getCurrentPokemon());
					break;
				case Movements.switchToBackup2:
					player2.getTeam().setCurrentPokemon(player2.getTeam().backup2);
					pw.println("Player 2 switch to " + player2.getTeam().getCurrentPokemon());
					break;
				}

				/* Cálculo de los daños de los ataques */
				int damagePlayer1 = 0;
				int damagePlayer2 = 0;

				switch (player1Movement) {
				case Movements.neutralAttack:
					damagePlayer2 = attack(player1.getTeam().getCurrentPokemon(), player2.getTeam().getCurrentPokemon(),
							false, pw);
					break;
				case Movements.typeAttack:
					damagePlayer2 = attack(player1.getTeam().getCurrentPokemon(), player2.getTeam().getCurrentPokemon(),
							true, pw);
					break;
				}

				switch (player2Movement) {
				case Movements.neutralAttack:
					damagePlayer1 = attack(player2.getTeam().getCurrentPokemon(), player1.getTeam().getCurrentPokemon(),
							false, pw);
					break;
				case Movements.typeAttack:
					damagePlayer1 = attack(player2.getTeam().getCurrentPokemon(), player1.getTeam().getCurrentPokemon(),
							true, pw);
					break;
				}

				/* Cálculo de las recompensas */
				rewardPlayer1 = Reward.getReward(player2.getTeam().getCurrentPokemon(),
						player1.getTeam().getCurrentPokemon(), player2PokemonBeforeSwitch, player1PokemonBeforeSwitch,
						damagePlayer2, damagePlayer1);

				rewardPlayer2 = Reward.getReward(player1.getTeam().getCurrentPokemon(),
						player2.getTeam().getCurrentPokemon(), player1PokemonBeforeSwitch, player2PokemonBeforeSwitch,
						damagePlayer1, damagePlayer2);
			}

			player1.la.writeOutputFile(1);
			player2.la.writeOutputFile(2);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != file)
					file.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	private static void printBattleInfo(Player player1, Player player2, PrintWriter pw) {
		/* Info sobre la batalla actual */
		pw.println("********** Battle: " + player1.getTeam().getCurrentPokemon().getName() + "["
				+ player1.getTeam().getCurrentPokemon().getHp() + "] vs "
				+ player2.getTeam().getCurrentPokemon().getName() + "[" + player2.getTeam().getCurrentPokemon().getHp()
				+ "]");
	}

	private static boolean checkWinner(boolean restartGame, Team teamPlayer1, Team teamPlayer2, PrintWriter pw) {
		if (teamPlayer1.getFaintedPokemon() == 3 || teamPlayer1.getFaintedPokemon() == 3) {
			if (teamPlayer1.getFaintedPokemon() == 3) {
				pw.println("Player 2 won");
			} else if (teamPlayer2.getFaintedPokemon() == 3) {
				pw.println("Player 1 won");
			} else {
				pw.println("Tie");
			}
			restartGame = true;
		}
		return restartGame;
	}

	private static int attack(Pokemon attacker, Pokemon attacked, boolean special, PrintWriter pw) {
		int hpBefore = attacked.getHp();

		if (special) {
			pw.println(attacker + " used special attack");
			attacked.receiveAttack(attacker.getTypeAttack());
			pw.println(attacked + " HP = " + attacked.getHp());
		} else {
			pw.println(attacker + " used neutral attack");
			attacked.receiveAttack(attacker.getNeutralAttack());
			pw.println(attacked + " HP = " + attacked.getHp());
		}
		int hpAfter = attacked.getHp();

		return hpBefore - hpAfter;

	}

	private static Pokemon[] generateRandomTeam() {
		ArrayList<String> list = new ArrayList<>();
		AvailablePokemon ap;
		Pokemon[] team = new Pokemon[3];

		for (int i = 0; i < 3; i++) {
			ap = AvailablePokemon.randomPokemon();
			if (!list.contains(ap.getType())) {
				list.add(ap.getType());
				team[i] = new Pokemon(ap);
			} else
				i--;
		}
		return team;
	}
}
