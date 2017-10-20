package com.abrudbanyay.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Balance {

	private List<Integer> balance = new ArrayList<>();

	public Balance() {
		// initializing balance with random coins
		int[] coinTypes = { 200, 100, 50, 20, 10, 5 };

		for (int i = 0; i <= 25; i++) {
			addCoins(coinTypes[(int) (Math.random() * 6)]);
		}

	}

	// add coins to balance
	private void addCoins(int coin) {

		if (coin != 0) {
			if (coin == 5 || coin == 10 || coin == 20 || coin == 50 || coin == 100 || coin == 200) {
				balance.add(coin);
			}
		}
	}

	public List<Integer> getBalance() {
		return balance;
	}

	private int totalSum() {
		int sum = 0;

		if (!balance.isEmpty()) {
			for (int i = 0; i < balance.size(); i++) {
				sum += balance.get(i);
			}
		}
		return sum;
	}

	@Override
	public String toString() {
		return "Available balance: " + totalSum();
	}

}
