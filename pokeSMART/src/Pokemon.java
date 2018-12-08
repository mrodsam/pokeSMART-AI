import java.util.LinkedHashMap;

public class Pokemon {
	private AvailablePokemon name;
	private String type;
	private int hp;
	private LinkedHashMap<String, String> typeAttacks;
	private boolean fainted;

	public Pokemon() {

	}

	public Pokemon(AvailablePokemon nameType) {
		this.name = nameType;
		this.type = nameType.getType();
		this.hp = 5;
		fainted = false;
		setDamages();
	}

	public void setDamages() {
		typeAttacks = new LinkedHashMap<>();
		if (this.type.equals("fire")) {
			typeAttacks.put("fire", "nonEffective");
			typeAttacks.put("water", "effective");
			typeAttacks.put("grass", "nonEffective");
			typeAttacks.put("fighting", "neutral");
			typeAttacks.put("dark", "neutral");
			typeAttacks.put("ghost", "neutral");
		} else if (this.type.equals("grass")) {
			typeAttacks.put("fire", "effective");
			typeAttacks.put("water", "nonEffective");
			typeAttacks.put("grass", "nonEffective");
			typeAttacks.put("fighting", "neutral");
			typeAttacks.put("dark", "neutral");
			typeAttacks.put("ghost", "neutral");
		} else if (this.type.equals("water")) {
			typeAttacks.put("fire", "nonEffective");
			typeAttacks.put("water", "nonEffective");
			typeAttacks.put("grass", "effective");
			typeAttacks.put("fighting", "neutral");
			typeAttacks.put("dark", "neutral");
			typeAttacks.put("ghost", "neutral");
		} else if (this.type.equals("fighting")) {
			typeAttacks.put("fire", "neutral");
			typeAttacks.put("water", "neutral");
			typeAttacks.put("grass", "neutral");
			typeAttacks.put("fighting", "neutral");
			typeAttacks.put("dark", "nonEffective");
			typeAttacks.put("ghost", "effective");
		} else if (this.type.equals("dark")) {
			typeAttacks.put("fire", "neutral");
			typeAttacks.put("water", "neutral");
			typeAttacks.put("grass", "neutral");
			typeAttacks.put("fighting", "effective");
			typeAttacks.put("dark", "neutral");
			typeAttacks.put("ghost", "nonEffective");
		} else if (this.type.equals("ghost")) {
			typeAttacks.put("fire", "neutral");
			typeAttacks.put("water", "neutral");
			typeAttacks.put("grass", "neutral");
			typeAttacks.put("fighting", "nonEffective");
			typeAttacks.put("dark", "effective");
			typeAttacks.put("ghost", "neutral");
		}
	}

	public void receiveAttack(String type) {
		if (!"neutral".equals(type)) {
			switch (typeAttacks.get(type)) {
			case "neutral":
				this.hp -= 2;
				break;
			case "effective":
				this.hp -= 4;
				break;
			case "nonEffective":
				this.hp -= 1;
				break;
			default:
				break;
			}
		} else {
			this.hp -= 2;
		}

		if (this.hp <= 0) {
			this.hp = 0;
			fainted = true;
			System.out.println(this.name.toString() + " fainted");
		}
	}

	public String faintedOrType() {
		if (fainted) {
			return "fainted";
		} else {
			return type;
		}
	}

	public AvailablePokemon getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public String getNeutralAttack() {
		return "neutral";
	}

	public String getTypeAttack() {
		return this.type;
	}

	public boolean isFainted() {
		return fainted;
	}

	public void setFainted(boolean fainted) {
		this.fainted = fainted;
	}

	public LinkedHashMap<String, String> getTypeAttacks() {
		return typeAttacks;
	}

	@Override
	public String toString() {
		return name.toString();
	}

}
