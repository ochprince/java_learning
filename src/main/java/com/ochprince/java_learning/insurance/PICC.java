package com.ochprince.java_learning.insurance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PICC {

	public static void main(String[] args) {
		/**
		 * 50w保额,
		 */
		List<Insurance> list = new ArrayList<>();
		list.add(new Insurance(50, 8650, Insurance.Unit.YEAR, 20, 20, 1.2));
		list.add(new Insurance(50, 778.5, Insurance.Unit.MOUTH, 20, 20 * 12, 1.2));
		list.add(new Insurance(50, 5000, Insurance.Unit.YEAR, 30, 20, 1.2));
		list.add(new Insurance(50, 450, Insurance.Unit.MOUTH, 30, 20 * 12, 1.2));
		list.add(new Insurance(50, 5400, Insurance.Unit.YEAR, 30, 30, 1.2));
		list.add(new Insurance(50, 486, Insurance.Unit.MOUTH, 30, 30 * 12, 1.2));
		list.add(new Insurance(50, 5500, Insurance.Unit.YEAR, 43, 20, 1.2));
		list.add(new Insurance(50, 495, Insurance.Unit.MOUTH, 43, 20 * 12, 1.2));
		list.add(new Insurance(50, 4750, Insurance.Unit.YEAR, 43, 30, 1.2));
		list.add(new Insurance(50, 427.5, Insurance.Unit.MOUTH, 43, 30 * 12, 1.2));


		list.add(new Insurance(50, 1000, Insurance.Unit.YEAR, 20, 20));
		list.add(new Insurance(50, 90, Insurance.Unit.MOUTH, 20, 20 * 12));
		list.add(new Insurance(50, 2200, Insurance.Unit.YEAR, 30, 20));
		list.add(new Insurance(50, 198, Insurance.Unit.MOUTH, 30, 20 * 12));
		list.add(new Insurance(50, 1750, Insurance.Unit.YEAR, 30, 30));
		list.add(new Insurance(50, 157.5, Insurance.Unit.MOUTH, 30, 30 * 12));
		list.add(new Insurance(50, 4100, Insurance.Unit.YEAR, 43, 20));
		list.add(new Insurance(50, 369, Insurance.Unit.MOUTH, 43, 20 * 12));
		list.add(new Insurance(50, 3300, Insurance.Unit.YEAR, 43, 30));
		list.add(new Insurance(50, 297, Insurance.Unit.MOUTH, 43, 30 * 12));


		list.stream().sorted(Comparator.comparingDouble(Insurance::getCostPerformance)).forEach(i -> System.out.println(i.toString()));
	}
}
