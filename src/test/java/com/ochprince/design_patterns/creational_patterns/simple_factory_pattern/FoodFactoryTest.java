package com.ochprince.design_patterns.creational_patterns.simple_factory_pattern;

import com.ochprince.design_patterns.creational_patterns.simple_factory_pattern.Food.Food;
import org.junit.Test;

public class FoodFactoryTest {

	@Test
	public void testGetFood() {
		Food salad = FoodFactory.getFood("Salad");
		System.out.println(salad.toString());
	}
}
