package com.ochprince.design_patterns.creational_patterns.simple_factory_pattern.Food;

import com.alibaba.fastjson.JSONObject;
import com.ochprince.design_patterns.creational_patterns.simple_factory_pattern.Material.Material;
import com.ochprince.design_patterns.creational_patterns.simple_factory_pattern.TasteEnum;

import java.util.List;

public class Salad implements Food {

	public String name;
	public TasteEnum taste;
	public Float profit;
	public List<Material> materials;
	public Long neededTime;
	public Float price;

	public Salad(String name, TasteEnum taste, Float profit) {
		this.name = name;
		this.taste = taste;
		this.profit = profit;
	}

	@Override
	public Salad make(List<Material> materials, Long neededTime) {
		this.materials = materials;
		this.neededTime = neededTime;
		this.price = profit + materials.stream().map(material -> material.getTotalPrice()).reduce(0f, Float::sum);
		return this;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
