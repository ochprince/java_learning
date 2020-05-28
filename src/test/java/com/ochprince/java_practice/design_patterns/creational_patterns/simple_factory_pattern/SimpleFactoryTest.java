package com.ochprince.java_practice.design_patterns.creational_patterns.simple_factory_pattern;

import com.ochprince.java_learning.design_patterns.creational_patterns.simple_factory_pattern.Food;
import com.ochprince.java_learning.design_patterns.creational_patterns.simple_factory_pattern.FoodFactory;
import org.junit.Test;

public class SimpleFactoryTest {

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
