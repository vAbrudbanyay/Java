package com.abrudbanyay.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Penztarca {

	// Tárolja a pénztárcába helyezett pénzérméket
	private List<Integer> penztarca = new ArrayList<>();

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

		if (erme != 0) {
			if (erme == 5 || erme == 10 || erme == 20 || erme == 50 || erme == 100 || erme == 200) {
				penztarca.add(erme);
			}
		}
	}

	public List<Integer> getPenztarca() {
		return penztarca;
	}

	public int osszeg() {
		int sum = 0;

		if (!penztarca.isEmpty()) {
			for (int i = 0; i < penztarca.size(); i++) {
				sum += penztarca.get(i);
			}
		}
		return sum;
	}

	// pénztárca jelenlegi tartalma érmetípusokra lebontva
	private String ermekDarabra() {
		int sum = osszeg();
		otosDb = 0;
		tizesDb = 0;
		huszasDb = 0;
		otvenesDb = 0;
		szazasDb = 0;
		ketszazasDb = 0;

		for (int i = 0; i < penztarca.size(); i++) {
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
	
	@Override
	public String toString() {
		return ermekDarabra();
	}

}