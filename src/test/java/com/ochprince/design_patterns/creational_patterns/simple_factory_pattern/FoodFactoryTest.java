package com.ochprince.design_patterns.creational_patterns.simple_factory_pattern;

import org.junit.Test;

public class FoodFactoryTest {

	@Test
	public void testGetFood() {
		FoodFactory foodFactory = new FoodFactory();
		Food salad = foodFactory.getFood(FoodFactory.SALAD);
		Food hamburger = foodFactory.getFood(FoodFactory.HAMBURGER);
		Food cola = foodFactory.getFood(FoodFactory.COLA);
		System.out.println(salad.make());
		System.out.println(hamburger.make());
		System.out.println(cola.make());
	}
}
