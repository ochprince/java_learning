package com.ochprince.practice;

import java.math.BigInteger;

/**
 * basic programming structure
 */
public class Basic {

	/**
	 * get an integer and output it by binary/octal/hexadecimal, then outputs the reciprocal by hexadecimal floating-point number
	 */
	static class Hexadecimal {
		public static void main(String[] args) {
			int integer = 20;
			printByBin(integer);
			printByOct(integer);
			printByHex(integer);
			printByHexFloatReciprocal(integer);
		}

		private static void printByBin(int integer) {
			String binaryString = Integer.toBinaryString(integer);
			System.out.println("binaryString = " + binaryString);
		}

		private static void printByOct(int integer) {
			String octalString = Integer.toOctalString(integer);
			System.out.println("octalString = " + octalString);
		}

		private static void printByHex(int integer) {
			String hexString = Integer.toHexString(integer);
			System.out.println("hexString = " + hexString);
		}

		private static void printByHexFloatReciprocal(int integer) {
			String HexFloatReciprocal = Float.toHexString(1F / integer);
			System.out.println("HexFloatReciprocal = " + HexFloatReciprocal);
		}
	}

	/**
	 * get an integer and output a number between 0 and 359.
	 */
	static class ModularArithmetic {
		public static void main(String[] args) {
			int integer = -20;
			printModularNumber(integer);
		}

		private static void printModularNumber(int integer) {
			int modular1 = ((integer % 360) + 360) % 360;//if only use expression 'integer % 360', it maybe generate a negative result
			int modular2 = Math.floorMod(integer, 360);
			System.out.println("modular1 = " + modular1);
			System.out.println("modular2 = " + modular2);
		}
	}

	/**
	 * get maximum number from three numbers
	 */
	static class Maximum {
		public static void main(String[] args) {
			printMaximum(3, 6, 9);
		}

		private static void printMaximum(int int1, int int2, int int3) {
			int maximum1 = int1 > int2 ? (int1 > int3 ? int1 : int3) : (int2 > int3 ? int2 : int3);
			int maximum2 = Math.max(Math.max(int1, int2), int3);
			System.out.println("maximum1 = " + maximum1);
			System.out.println("maximum2 = " + maximum2);
		}
	}

	/**
	 * get maximum and minimum of type of double
	 */
	static class MaxMinOfDouble {
		public static void main(String[] args) {
			printMaximum();
			printMinimum();
		}

		private static void printMinimum() {
			System.out.println("Double.MIN_VALUE = " + Double.MIN_VALUE);
			System.out.println("Math.nextUp(0.0) = " + Math.nextUp(0.0));
			System.out.println("(-1.0/0.0) = " + (-1.0 / 0.0));
		}

		private static void printMaximum() {
			System.out.println("Double.MAX_VALUE = " + Double.MAX_VALUE);
			System.out.println("Math.nextUp(Double.MAX_VALUE) = " + Math.nextUp(Double.MAX_VALUE));//the infinity
			System.out.println("(1.0/0.0) = " + (1.0 / 0.0));
		}
	}

	/**
	 * a double-precision floating-point number is converted to an integer
	 */
	static class DoubleCastToInteger {
		public static void main(String[] args) {
			double normalDouble = 20.235D;
			double bigDouble = 1e+144D;
			printCastRes(normalDouble, bigDouble);
		}

		private static void printCastRes(double normalDouble, double bigDouble) {
			System.out.println("(int)normalDouble = " + (int) normalDouble);
			System.out.println("(int)bigDouble = " + (int) bigDouble);//get a maximum of integer type
			System.out.println("Integer.MAX_VALUE = " + Integer.MAX_VALUE);
		}
	}

	/**
	 * calculate factorial by using BigInteger
	 */
	static class Factorial {
		public static void main(String[] args) {
			BigInteger res = BigInteger.ONE;
			BigInteger n = BigInteger.valueOf(1000L);
			BigInteger factorial = getFactorial(n, res);
			System.out.println("factorial = " + factorial);
		}

		private static BigInteger getFactorial(BigInteger n, BigInteger res) {
			if (n.compareTo(BigInteger.ONE) > 0) {
				res = n.multiply(res);
				return getFactorial(n.subtract(BigInteger.ONE), res);
			} else {
				return res;
			}
		}
	}

}
