package com.ochprince.practice;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
			System.out.println("Math.nextDown(Double.POSITIVE_INFINITY) = " + Math.nextDown(Double.POSITIVE_INFINITY));
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
			BigInteger n = BigInteger.valueOf(1000L);
			BigInteger factorial = getFactorial(n);
			System.out.println("factorial = " + factorial);
		}

		private static BigInteger getFactorial(BigInteger n) {
			BigInteger subtract = n.subtract(BigInteger.ONE);
			if (subtract.compareTo(BigInteger.ONE) > 0) {
				return n.multiply(getFactorial(subtract));
			} else {
				return n.multiply(subtract);
			}
		}
	}

	/**
	 * calculate two short number between 0 to 65535
	 */
	static class ShortType {
		public static void main(String[] args) {
			Scanner scanner = new Scanner(System.in);
			System.out.println(" please enter first short (0 ~ 65535): ");
			boolean inputValid = false;
			if (scanner.hasNextInt()) {
				short first = (short) scanner.nextInt();
				System.out.println(" please enter second short (0 ~ 65535): ");
				if (scanner.hasNextInt()) {
					short second = (short) scanner.nextInt();
					inputValid = true;
					printCalculate(first, second);
				}
			}
			if (!inputValid) {
				System.out.println(" input data failed! ");
			}
		}

		private static void printCalculate(short num1, short num2) {
			int i1 = Short.toUnsignedInt(num1) + Short.toUnsignedInt(num2);
			int i2 = Short.toUnsignedInt(num1) - Short.toUnsignedInt(num2);
			int i3 = Short.toUnsignedInt(num1) * Short.toUnsignedInt(num2);
			int i4 = Short.toUnsignedInt(num1) / Short.toUnsignedInt(num2);
			int i5 = Short.toUnsignedInt(num1) % Short.toUnsignedInt(num2);

			System.out.println(Short.toUnsignedInt(num1) + "+" + Short.toUnsignedInt(num2) + "=" + i1);
			System.out.println(Short.toUnsignedInt(num1) + "-" + Short.toUnsignedInt(num2) + "=" + i2);
			System.out.println(Short.toUnsignedInt(num1) + "*" + Short.toUnsignedInt(num2) + "=" + i3);
			System.out.println(Short.toUnsignedInt(num1) + "/" + Short.toUnsignedInt(num2) + "=" + i4);
			System.out.println(Short.toUnsignedInt(num1) + "%" + Short.toUnsignedInt(num2) + "=" + i5);
		}
	}

	/**
	 * give a random set of lottery Numbers
	 */
	static class Lottery {
		public static void main(String[] args) {
			Random random = new Random();
			printLotteryNumbers(random);
		}

		private static void printLotteryNumbers(Random random) {
			List<Integer> redLs = Stream.iterate(1, i -> i + 1).limit(33).collect(Collectors.toList());
			List<Integer> blueLs = Stream.iterate(1, i -> i + 1).limit(16).collect(Collectors.toList());
			int[] redChoose = new int[6];
			for (int i = 0; i < 6; i++) {
				int randomIndex = random.nextInt(33 - i);
				redChoose[i] = redLs.remove(randomIndex);
			}
			Arrays.sort(redChoose);
			System.out.println("redChoose = " + Arrays.toString(redChoose) + ", blueChoose = " + blueLs.remove(random.nextInt(16)));
		}
	}

}
