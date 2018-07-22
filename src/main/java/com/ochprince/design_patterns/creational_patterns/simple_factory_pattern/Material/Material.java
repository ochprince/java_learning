package com.ochprince.design_patterns.creational_patterns.simple_factory_pattern.Material;

public abstract class Material {

	public String color;
	public String name;
	public Float price;
	public Integer count;
	public Float nqi;

	public Material(String color, String name, Float price, Integer count, Float nqi) {
		this.color = color;
		this.name = name;
		this.price = price;
		this.count = count;
		this.nqi = nqi;
	}

	public Float getTotalPrice() {
		return this.price * this.count;
	}
}
