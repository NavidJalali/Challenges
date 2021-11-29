package io.navidjalali;

/*
Implement pow(x, n), which calculates x raised to the power n (i.e., x^n).
*/

class Pow {
	public double myPow(double x, int n) {
		if (x == 0 && n == 0) return 1; // What the fuck?!
		if (x == 1 || n == 0) return 1;

		return myPow(x * x, n / 2) * (n % 2 == 0 ? 1 : n >= 0 ? x : 1 / x);
	}
}
