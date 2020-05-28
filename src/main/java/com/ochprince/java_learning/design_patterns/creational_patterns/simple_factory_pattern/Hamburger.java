package com.ochprince.java_learning.design_patterns.creational_patterns.simple_factory_pattern;

import com.ochprince.java_learning.design_patterns.creational_patterns.simple_factory_pattern.Food;

public class Hamburger implements Food {
	@Override
	public String make() {
		return "Made a piece of Hamburger";
	}
}
