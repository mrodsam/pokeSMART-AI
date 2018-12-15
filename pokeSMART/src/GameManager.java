//import java.io.FileWriter;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class GameManager {
//	static Scanner sc = new Scanner(System.in);
//
//	public static void main(String[] args) {
//		int reward1 = 0;
//		int reward2 = 0;
//		boolean end = false;
//
////		Team rivalTeam = new Team(generateRandomTeam());
////		Team team2 = new Team(generateRandomTeam());
//
//		/* Ambos jugadores tienen un equipo fijo, con los Pokémon del mismo tipo */
//		Pokemon[] pokemonTeam1 = new Pokemon[3];
//		pokemonTeam1[0] = new Pokemon(AvailablePokemon.Poliwag);
//		pokemonTeam1[1] = new Pokemon(AvailablePokemon.Poochyena);
//		pokemonTeam1[2] = new Pokemon(AvailablePokemon.Charmander);
//		Team teamPlayer1 = new Team(pokemonTeam1);
//
//		Pokemon[] pokemonTeam2 = new Pokemon[3];
//		pokemonTeam2[0] = new Pokemon(AvailablePokemon.Squirtle);
//		pokemonTeam2[1] = new Pokemon(AvailablePokemon.Umbreon);
//		pokemonTeam2[2] = new Pokemon(AvailablePokemon.Vulpix);
//		Team teamPlayer2 = new Team(pokemonTeam2);
//
//		Player player1 = new Player(teamPlayer1);
//		Player player2 = new Player(teamPlayer2);
//
//		System.out.println("Team 1: " + teamPlayer1.toString());
//		System.out.println("Team 2: " + teamPlayer2.toString());
//
//		System.out.println("Number of players[1 or 2]: ");
//		int players = Integer.parseInt(sc.nextLine());
//
//		/* Número de partidas que se quieren jugar */
//		int count = 0;
//		int episodes = 100;
//
//		FileWriter fichero = null;
//		PrintWriter pw = null;
//		try {
//			fichero = new FileWriter("salida.txt");
//			pw = new PrintWriter(fichero);
//
//			while (count < episodes) {
//
//				/* Se cuenta el número de Pokémon desmayados que tienen cada uno */
//				int count1 = 0;
//				int count2 = 0;
//
//				for (int i = 0; i < teamPlayer1.getTeam().length; i++) {
//					if (teamPlayer1.getTeam()[i].isFainted())
//						count1++;
//				}
//				for (int i = 0; i < teamPlayer2.getTeam().length; i++) {
//					if (teamPlayer2.getTeam()[i].isFainted())
//						count2++;
//				}
//
//				if (count1 == 3 && count2 == 3) {
//					pw.println("Game over");
//					end = true;
//				} else {
//					if (count1 == 3) {
//						pw.println("Player 1 won the battle.");
//						end = true;
//					}
//					if (count2 == 3) {
//						pw.println("Player 2 won the battle.");
//						end = true;
//					}
//				}
//
//				/*
//				 * Si acaba la partida se reinician los equipos y se vuelve a jugar hasta el
//				 * número de episodios que se haya configurado
//				 */
//				if (end) {
//					count++;
//					if (count == episodes)
//						break;
//					pokemonTeam1 = new Pokemon[3];
//					pokemonTeam1[0] = new Pokemon(AvailablePokemon.Poliwag);
//					pokemonTeam1[1] = new Pokemon(AvailablePokemon.Poochyena);
//					pokemonTeam1[2] = new Pokemon(AvailablePokemon.Charmander);
//					teamPlayer1 = new Team(pokemonTeam1);
//					player1.setTeam(teamPlayer1);
//
//					pokemonTeam2 = new Pokemon[3];
//					pokemonTeam2[0] = new Pokemon(AvailablePokemon.Squirtle);
//					pokemonTeam2[1] = new Pokemon(AvailablePokemon.Umbreon);
//					pokemonTeam2[2] = new Pokemon(AvailablePokemon.Vulpix);
//					teamPlayer2 = new Team(pokemonTeam2);
//					player2.setTeam(teamPlayer2);
//					end = false;
//				}
//				/* Se guardan los Pokémon de cada uno antes de hacer un cambio */
//				Pokemon player1BeforeSwitch = teamPlayer1.getCurrentPokemon();
//				Pokemon player2BeforeSwitch = teamPlayer2.getCurrentPokemon();
//
//				int movement[] = new int[2];
//				pw.println("*********** Battle: " + teamPlayer1.getCurrentPokemon().getName() + "["
//						+ teamPlayer1.getCurrentPokemon().getHp() + "]" + " vs "
//						+ teamPlayer2.getCurrentPokemon().getName() + "[" + teamPlayer2.getCurrentPokemon().getHp()
//						+ "]" + " **********");
//
//				switch (players) {
////				case 2:
////
////					if (teamPlayer1.getCurrentPokemon().isFainted()) {
////						changePokemon(teamPlayer1, pw);
////					}
////
////					pw.println("[" + teamPlayer1.getCurrentPokemon() + "] Choose a movement: ");
////					pw.println("1. Switch");
////					pw.println("2. Neutral Attack");
////					pw.println("3. Type Attack");
////
////					movement[0] = Integer.parseInt(sc.nextLine());
////
////					if (movement[0] == 1) {
////						changePokemon(teamPlayer1, pw);
////					}
////
////					if (teamPlayer2.getCurrentPokemon().isFainted()) {
////						changePokemon(teamPlayer2, pw);
////					}
////					pw.println("[" + teamPlayer2.getCurrentPokemon() + "] Choose a movement: ");
////					pw.println("1. Switch");
////					pw.println("2. Neutral Attack");
////					pw.println("3. Type Attack");
////
////					movement[1] = Integer.parseInt(sc.nextLine());
////
////					if (movement[1] == 1) {
////						changePokemon(teamPlayer2, pw);
////					}
////					break;
//
//				case 1:
//
//					if (teamPlayer1.getCurrentPokemon().isFainted()) {
//						teamPlayer1.setCurrentPokemon(player1.chooseMovement(true, null, 0)); // Si el Pokémon actual
//																								// está
//																								// desmayado, se escoge
//																								// otro
//																								// aleatoriamente entre
//																								// los
//																								// que queden vivos
//						pw.println("Player 1 switch to " + teamPlayer1.getCurrentPokemon());
//					}
//					
//					/* Estado que se enviará al algoritmo de Q-Learning posteriormente */
//					State s1 = new State(teamPlayer1.getCurrentPokemon(), teamPlayer1.getBackup1(),
//							teamPlayer1.getBackup2(), teamPlayer2.getCurrentPokemon(), teamPlayer2.getBackup1(),
//							teamPlayer2.getBackup2());
//
//					movement[0] = player1.chooseMovement(false, s1, reward1);
//					
//					if (movement[0] == 10) { // Cambiar a backup1
//						teamPlayer1.setCurrentPokemon(teamPlayer1.backup1);
//						pw.println("Player 1 switch to " + teamPlayer1.getCurrentPokemon());
//					} else if (movement[0] == 11) { // Cambiar a backup2
//						teamPlayer1.setCurrentPokemon(teamPlayer1.backup2);
//						pw.println("Player 1 switch to " + teamPlayer1.getCurrentPokemon());
//					}
//					if (movement[0] == 12) { // Cambiar al que no esté desmayado
//						if (teamPlayer1.getBackup1().isFainted())
//							teamPlayer1.setCurrentPokemon(teamPlayer1.backup2);
//						else
//							teamPlayer1.setCurrentPokemon(teamPlayer1.backup1);
//					}
//
//					/*************************************
//					 * Jugador 2
//					 *****************************************/
//					if (teamPlayer2.getCurrentPokemon().isFainted()) {
//						teamPlayer2.setCurrentPokemon(player2.chooseMovement(true, null, 0));
//						pw.println("Player 2 switch to " + teamPlayer2.getCurrentPokemon());
//					}
//					State s = new State(teamPlayer2.getCurrentPokemon(), teamPlayer2.getBackup1(),
//							teamPlayer2.getBackup2(), teamPlayer1.getCurrentPokemon(), teamPlayer1.getBackup1(),
//							teamPlayer1.getBackup2());
//					movement[1] = player2.chooseMovement(false, s, reward2);
//					if (movement[1] == 10) {
//						teamPlayer2.setCurrentPokemon(teamPlayer2.backup1);
//						pw.println("Player 2 switch to " + teamPlayer2.getCurrentPokemon());
//					} else if (movement[1] == 11) {
//						teamPlayer2.setCurrentPokemon(teamPlayer2.backup2);
//						pw.println("Player 2 switch to " + teamPlayer2.getCurrentPokemon());
//					}
//					if (movement[1] == 12) {
//						if (teamPlayer2.getBackup1().isFainted())
//							teamPlayer2.setCurrentPokemon(teamPlayer2.backup2);
//						else
//							teamPlayer2.setCurrentPokemon(teamPlayer2.backup1);
//					}
//					break;
//				}
//
//				/* Daños infligidos a los jugadores */
//				int damagePlayer1 = 0;
//				int damagePlayer2 = 0;
//
//				for (int i = 0; i < movement.length; i++) {
//					switch (movement[i]) {
//					case 2:
//						if (i == 0) {
//							damagePlayer1 = attack(teamPlayer2.getCurrentPokemon(), teamPlayer1.getCurrentPokemon(),
//									false, pw);
//						} else if (i == 1) {
//							damagePlayer2 = attack(teamPlayer1.getCurrentPokemon(), teamPlayer2.getCurrentPokemon(),
//									false, pw);
//						}
//						break;
//
//					case 3:
//						if (i == 0) {
//							damagePlayer1 = attack(teamPlayer2.getCurrentPokemon(), teamPlayer1.getCurrentPokemon(),
//									true, pw);
//						} else if (i == 1) {
//							damagePlayer2 = attack(teamPlayer1.getCurrentPokemon(), teamPlayer2.getCurrentPokemon(),
//									true, pw);
//						}
//						break;
//					case 4:
//						end = true;
//						break;
//					}
//				}
//
//				/*******************
//				 * Cálculo de las recompensas de cada jugador
//				 ***********************/
//				reward1 = Reward.getReward(teamPlayer2.getCurrentPokemon(), teamPlayer1.getCurrentPokemon(),
//						player2BeforeSwitch, player1BeforeSwitch, damagePlayer2, damagePlayer1); // Recompensa del
//																									// jugador 1
//
//				reward2 = Reward.getReward(teamPlayer1.getCurrentPokemon(), teamPlayer2.getCurrentPokemon(),
//						player1BeforeSwitch, player2BeforeSwitch, damagePlayer1, damagePlayer2); // Recompensa del
//																									// jugador 2
//				pw.println("Reward 1: " + reward1 + "");
//				pw.println("Reward 2: " + reward2 + "");
//			}
//			sc.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (null != fichero)
//					fichero.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//	}
//
//	private static void changePokemon(Team team, PrintWriter pw) {
//		pw.println("Current Pokemon: " + team.getCurrentPokemon());
//		pw.println("Choose a Pokemon: ");
//		for (int j = 0; j < team.getTeam().length; j++) {
//			if (!team.getTeam()[j].isFainted())
//				pw.println(j + ". " + team.getTeam()[j]);
//		}
//		int newPokemon = Integer.parseInt(sc.nextLine());
//		team.setCurrentPokemon(newPokemon);
//		pw.println("Your choice: " + team.getTeam()[newPokemon]);
//	}
//
//	private static int attack(Pokemon attacker, Pokemon attacked, boolean special, PrintWriter pw) {
//		int hpBefore = attacked.getHp();
//
//		if (special) {
//			pw.println(attacker + " used special attack");
//			attacked.receiveAttack(attacker.getTypeAttack());
//			pw.println(attacked + " HP = " + attacked.getHp());
//		} else {
//			pw.println(attacker + " used neutral attack");
//			attacked.receiveAttack(attacker.getNeutralAttack());
//			pw.println(attacked + " HP = " + attacked.getHp());
//		}
//		int hpAfter = attacked.getHp();
//
//		return hpBefore - hpAfter;
//
//	}
//
//	private static Pokemon[] generateRandomTeam() {
//		ArrayList<String> list = new ArrayList<>();
//		AvailablePokemon ap;
//		Pokemon[] team = new Pokemon[3];
//
//		for (int i = 0; i < 3; i++) {
//			ap = AvailablePokemon.randomPokemon();
//			if (!list.contains(ap.getType())) {
//				list.add(ap.getType());
//				team[i] = new Pokemon(ap);
//			} else
//				i--;
//		}
//		return team;
//	}
//
//}
