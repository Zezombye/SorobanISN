import java.util.Random;

public class CalculFactory {
	enum TypeCalcul {
		TYPE_ADD,
		TYPE_SUB,
		TYPE_MULT,
		TYPE_DIV,
	}
	
	public Calcul getCalcul(TypeCalcul type, int lvl) {
		Random random = new Random();
		int nb1 = 0, nb2 = 0, nb3 = 0;
		int result = 0;
		String operation = "";
		if (type == TypeCalcul.TYPE_ADD) {
			if (lvl == 1) {
				nb1 = random.nextInt(40)+15;
				nb2 = random.nextInt(60)+35;
			} else if (lvl == 2) {
				nb1 = random.nextInt(200)+110;
				nb2 = random.nextInt(500)+200;
			} else if (lvl == 3) {
				nb1 = random.nextInt(5000)+2000;
				nb2 = random.nextInt(6000)+2500;
				nb3 = random.nextInt(1000)+500;
			}
			operation = "+";
			result = nb1+nb2+nb3;
		} else if (type == TypeCalcul.TYPE_SUB) {
			if (lvl == 1) {
				nb2 = random.nextInt(40)+15;
				nb1 = random.nextInt(60)+10+nb2;
			} else if (lvl == 2) {
				nb2 = random.nextInt(200)+110;
				nb1 = random.nextInt(500)+150+nb2;
			} else if (lvl == 3) {
				nb3 = random.nextInt(5000)+1000;
				nb2 = random.nextInt(6000)+200+nb3;
				nb1 = random.nextInt(4000)+500+nb2;
			}
			operation = "-";
			result = nb1-nb2-nb3;
		} else if (type == TypeCalcul.TYPE_MULT) {
			if (lvl == 1) {
				nb1 = random.nextInt(40)+15;
				nb2 = random.nextInt(5)+2;
			} else if (lvl == 2) {
				nb1 = random.nextInt(2000)+500;
				nb2 = random.nextInt(6)+3;
			} else if (lvl == 3) {
				nb1 = random.nextInt(5000)+2000;
				nb2 = random.nextInt(70)+30;
			}
			if (Math.random() < 0.5) {
				int swap = nb1;
				nb1 = nb2;
				nb2 = swap;
			}
			operation = "x";
			result = nb1*nb2;
		} else if (type == TypeCalcul.TYPE_DIV) {
			if (lvl == 1) {
				nb2 = random.nextInt(4)+2;
				nb1 = (random.nextInt(60)+35)*nb2;
			} else if (lvl == 2) {
				nb2 = random.nextInt(6)+3;
				nb1 = (random.nextInt(500)+200)*nb2;
			} else if (lvl == 3) {
				nb2 = random.nextInt(70)+30;
				nb1 = (random.nextInt(1000)+500)*nb2;
			}
			operation = "/";
			result = nb1/nb2;
		}
		
		return new Calcul(nb1 + operation + nb2 + (nb3 == 0 ? "" : operation + nb3), result);
		
	}
	
	
	
}

class Calcul {
	public String str;
	public int result;
	public Calcul(String str, int result) {
		this.str = str;
		this.result = result;
	}
}
