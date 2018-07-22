package com.ochprince.design_patterns.creational_patterns.simple_factory_pattern.Material;

public class Egg extends Material {

	private Float carriage;

	public Egg(String color, String name, Float price, Integer count, Float nqi, Float carriage) {
		super(color, name, price, count, nqi);
		this.carriage = carriage;
	}

	public Float getTotalPrice() {
		return this.price * this.count + carriage;
	}
}
