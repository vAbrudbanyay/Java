package com.abrudbanyay.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abrudbanyay.model.Penztarca;

@Controller
public class PenztarcaController {

	@Autowired
	private Penztarca tarca;

	@RequestMapping("/processForm")
	public String osszegBe(HttpServletRequest request, Model model) {

		String result = "";
		int osszeg;
		try {
			osszeg = (Math.abs(Integer.parseInt(request.getParameter("fizetendoOsszeg"))));
			result = fizet(osszeg);
			model.addAttribute("message", result);
		} catch (NumberFormatException e) {
			result = "A megadott összeg csak szám lehet!";
			model.addAttribute("message", result);
		}
		return "index";
	}

	public String fizet(int osszeg) {

		String s = " A kifizetni kívánt összeg: " + osszeg + ".";
		int[] ermetipus = { 200, 100, 50, 20, 10, 5 };
		int index = 0;

		// ha eleve nem fizethető ki a legalacsonyabb érmével sem
		if (osszeg <= 2 && osszeg >= 0) {
			s += " Az alacsony összeg nem indokolja a pénzlevonást. "
					+ "A legkisebb fizetendo összeg 3 Ft, 5 forintos érmével, " + tarca.toString();
		}

		if (osszeg > 2) {
			if (osszeg > tarca.osszeg()) {
				s += " Nincs rendelkezésre álló keret az összeg kifizetéséhez. " + tarca.toString();

			}

			while (osszeg > 0 && osszeg < tarca.osszeg()) {

				try {

					if (!tarca.getPenztarca().contains(ermetipus[index])) {
						index++;
					}

					int listIndex = tarca.getPenztarca().indexOf(ermetipus[index]);

					tarca.getPenztarca().remove(listIndex);

					osszeg -= ermetipus[index];

					if (osszeg < 10) {
						try {
							if ((osszeg >= 3 && osszeg <= 7) && tarca.getPenztarca().contains(5)) {
								int indexOt = tarca.getPenztarca().indexOf(5);
								tarca.getPenztarca().remove(indexOt);
								osszeg -= 5;
								break;
							} else if ((osszeg == 8 || osszeg == 9) && tarca.getPenztarca().contains(10)) {
								int indexTiz = tarca.getPenztarca().indexOf(10);
								tarca.getPenztarca().remove(indexTiz);
								osszeg -= 10;
								break;
							} else if (osszeg <= 2) {
								s += " A tranzakció sikeres volt. " + tarca.toString();
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							s += " Nincs rendelkezésre álló keret az összeg kifizetéséhez. " + tarca.toString();
							continue;
						}
					}

				} catch (ArrayIndexOutOfBoundsException e) {
					s += " Nincs rendelkezésre álló keret az összeg kifizetéséhez. " + tarca.toString();
					continue;
				}

			}

		}

		return s;

	}

}