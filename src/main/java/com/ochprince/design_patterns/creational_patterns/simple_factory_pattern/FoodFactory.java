package com.ochprince.design_patterns.creational_patterns.simple_factory_pattern;

public class FoodFactory {

	public static final String COLA = "cola";
	public static final String HAMBURGER = "hamburger";
	public static final String SALAD = "salad";

	public Food getFood(String foodName) {
		if (COLA.equals(foodName)) {
			return new Cola();
		} else if (HAMBURGER.equals(foodName)) {
			return new Hamburger();
		} else if (SALAD.equals(foodName)) {
			return new Salad();
		} else {
			throw new IllegalArgumentException("Not support foodName like: " + foodName);
		}
	}
}
