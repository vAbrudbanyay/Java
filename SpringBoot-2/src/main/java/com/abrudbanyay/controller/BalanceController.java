package com.abrudbanyay.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abrudbanyay.model.Balance;

@Controller
public class BalanceController {
	
	@Autowired
	private Balance balance;
	
	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
	@RequestMapping("/confirmation")
	public String confirmation(@RequestParam(value = "amountToPay") String amountToPay, Model model) {
		
		String result = "";
		int osszeg;
		try {
			osszeg = (Math.abs(Integer.parseInt(amountToPay)));
			result = pay(osszeg);
			model.addAttribute("confirmation", result);
		} catch (NumberFormatException e) {
			result = "Please use numbers only!";
			model.addAttribute("confirmation", result);
		}
		return "confirmation";
	}

	private String pay(int sum) {

		String s = " Amount to pay: " + sum + ".";
		int[] coinType = { 200, 100, 50, 20, 10, 5 };
		int index = 0;

		int availableBalance = 0;

		List<Integer> thisBalance = balance.getBalance();

		if (!thisBalance.isEmpty()) {
			for (int i = 0; i < thisBalance.size(); i++) {
				availableBalance += thisBalance.get(i);
			}
		}

		if (sum <= 2 && sum >= 0) {
			s += " The amount is low, no deduction required. "
					+ "The smalles amount to be paid is 3. For this a coint with the value of 5 is required if available. "
					+ coinsPerType(thisBalance);
		}

		if (sum > 2) {
			if (sum > availableBalance) {
				s += " No balance available to pay the requested amount. " + coinsPerType(thisBalance);

			}

			while (sum > 0 && sum < availableBalance) {

				try {

					if (!thisBalance.contains(coinType[index])) {
						index++;
					}

					int listIndex = thisBalance.indexOf(coinType[index]);

					sum -= coinType[index];
					availableBalance -= coinType[index];
					thisBalance.remove(listIndex);

					if (sum < 10) {
						try {
							if ((sum >= 3 && sum <= 7) && thisBalance.contains(5)) {
								int index5 = thisBalance.indexOf(5);
								sum -= 5;
								availableBalance -= 5;
								thisBalance.remove(index5);
								// break;
							} else if ((sum == 8 || sum == 9) && thisBalance.contains(10)) {
								int index10 = thisBalance.indexOf(10);
								sum -= 10;
								availableBalance -= 10;
								thisBalance.remove(index10);
								// break;
							} else if (sum <= 2) {
								s += " Success, amount paid. " + coinsPerType(thisBalance);
							}
						} catch (ArrayIndexOutOfBoundsException e) {
							s += " No balance available to pay the requested amount. " + coinsPerType(thisBalance);
							// continue;
						}
					}

				} catch (ArrayIndexOutOfBoundsException e) {
					s += " No balance available to pay the requested amount. " + coinsPerType(thisBalance);
					// continue;
				}

			}

		}

		return s;

	}

	private String coinsPerType(List<Integer> balance) {
		int fives = 0;
		int tens = 0;
		int twenties = 0;
		int fifties = 0;
		int hundreds = 0;
		int twohundreds = 0;

		int availableBalance = 0;

		if (!balance.isEmpty()) {
			for (int i = 0; i < balance.size(); i++) {
				availableBalance += balance.get(i);
			}
		}

		for (int i = 0; i < balance.size(); i++) {
			switch (balance.get(i)) {
			case 5:
				fives++;
				break;
			case 10:
				tens++;
				break;
			case 20:
				twenties++;
				break;
			case 50:
				fifties++;
				break;
			case 100:
				hundreds++;
				break;
			case 200:
				twohundreds++;
				break;
			}

		}

		String s = "";
		if (balance.isEmpty()) {
			s = " No balance available.";
		} else {
			s = " Balance: " + availableBalance + " . ";
			s += " Available coins: ";
			if (fives > 0) {
				s += "[ " + fives + "  - coin type: 5] ";
			}
			if (tens > 0) {
				s += "[ " + tens + " - coin type: 10] ";
			}
			if (twenties > 0) {
				s += "[ " + twenties + " - coin type: 20] ";
			}
			if (fifties > 0) {
				s += "[ " + fifties + " - coin type: 50] ";
			}
			if (hundreds > 0) {
				s += "[ " + hundreds + " - coin type: 100] ";
			}
			if (twohundreds > 0) {
				s += "[ " + twohundreds + " - coin type: 200] ";
			}
			s += "\n";

		}
		return s;
	}
}
