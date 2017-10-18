package com.abrudbanyay.penztarca;

import java.util.ArrayList;
import java.util.List;

public class Penztarca {

	// Tárolja a pénztárcába helyezett pénzérméket
	List<Integer> penztarca = new ArrayList<>();

	// tárolja a pénzérmék darabszámát
	private int otosDb;
	private int tizesDb;
	private int huszasDb;
	private int otvenesDb;
	private int szazasDb;
	private int ketszazasDb;

	public Penztarca() {
		// inicializálja a pénztárcát random érmékkel
		int[] ermetipusok = { 200, 100, 50, 20, 10, 5 };

		for (int i = 0; i <= 25; i++) {
			feltoltErmevel(ermetipusok[(int) (Math.random() * 6)]);
		}

	}

	// pénzérmék hozzáadása a tárcához
	private void feltoltErmevel(int erme) {
		// try {
		if (erme != 0) {
			if (erme == 5 || erme == 10 || erme == 20 || erme == 50 || erme == 100 || erme == 200) {
				penztarca.add(erme);
			}
		} /*
			 * else { System.out.println(
			 * "Nem megfelelő pénztípus. Kérem, csak forgalomban lévő: 5, 10, 20, 50, 100, vagy 200-as fémpénzeket használjon!"
			 * ); }
			 * 
			 * } catch (NumberFormatException e) { System.out.
			 * println("Hiba! A pénztárcába csak pénzérméket lehet helyezni!");
			 * } catch (IllegalArgumentException e){ e.getMessage(); }
			 */

	}

	public List<Integer> getPenztarca() {
		return penztarca;
	}

	// pénztárca jelenlegi tartalma érmetípusokra lebontva
	private String ermekDarabra() {
		int sum = 0;
		otosDb = 0;
		tizesDb = 0;
		huszasDb = 0;
		otvenesDb = 0;
		szazasDb = 0;
		ketszazasDb = 0;

		for (int i = 0; i < penztarca.size(); i++) {
			sum += penztarca.get(i);
			switch (penztarca.get(i)) {
			case 5:
				otosDb++;
				break;
			case 10:
				tizesDb++;
				break;
			case 20:
				huszasDb++;
				break;
			case 50:
				otvenesDb++;
				break;
			case 100:
				szazasDb++;
				break;
			case 200:
				ketszazasDb++;
				break;
			}

		}

		String s = "";
		if (penztarca.isEmpty()) {
			s = " a pénztárca üres.";
		} else {
			s = " a pénztárca " + sum + " forintot tartalmaz. ";
			s += " Rendelkezésre álló pénzösszeg érmék szerint: ";
			if (otosDb > 0) {
				s += " " + otosDb + " db 5 forintos, ";
			}
			if (tizesDb > 0) {
				s += " " + tizesDb + " db 10 forintos, ";
			}
			if (huszasDb > 0) {
				s += " " + huszasDb + " db 20 forintos, ";
			}
			if (otvenesDb > 0) {
				s += " " + otvenesDb + " db 50 forintos, ";
			}
			if (szazasDb > 0) {
				s += " " + szazasDb + " db 100 forintos, ";
			}
			if (ketszazasDb > 0) {
				s += " " + ketszazasDb + " db 200 forintos, ";
			}
			s += "\n";

		}
		return s;
	}

	public String fizet(int osszeg) {

		String s = " A kifizetni kívánt összeg: " + osszeg + ".";
		int[] ermetipus = { 200, 100, 50, 20, 10, 5 };
		int index = 0;

		if (osszeg > 0) {
			while (osszeg > 0) {

				if (osszeg<10){
					if((osszeg%10 >= 1 || osszeg%10<= 7) && penztarca.contains("5")) {
						int indexOt = penztarca.indexOf(5);
						penztarca.remove(indexOt);
					} else if((osszeg%10 == 8 || osszeg%10 == 9) && penztarca.contains("10")) {
						int indexOt = penztarca.indexOf(10);
						penztarca.remove(indexOt);
					} 
				} 
				
				if (!penztarca.contains(ermetipus[index])) {
					index++;
				}

				while (osszeg < ermetipus[index]) { // a legnagyobb érmével
													// kezd, ha azok elfogynak,
													// vagy nem lehetséges,
													// akkor jön a következő
													// pénzérme
					index++;

				}



				// ermetipus[index] szerinti összeggel csökkenteni az penztarca
				// (arrayList)-t.
				int listIndex = penztarca.indexOf(ermetipus[index]);
				try {
					penztarca.remove(listIndex);

					osszeg = osszeg - ermetipus[index];

					if (osszeg == 0) {
						s += " A tranzakció sikeres volt. " + toString();
					}

					// kell még egy olyan, hogy 1, 2, 3, 4, 7, 8 és 9 végűek is
					// oszthatóak legyenek. A kerekítés miatt.

				} catch (ArrayIndexOutOfBoundsException e) {
					s += " Nincs rendelkezésre álló keret az összeg kifizetéséhez. " + toString();
					continue;
				}

			}

		}

		return s;
	}

	@Override
	public String toString() {
		return ermekDarabra();
	}

}
