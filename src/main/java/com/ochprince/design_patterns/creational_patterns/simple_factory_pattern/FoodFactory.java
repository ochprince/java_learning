package com.ochprince.design_patterns.creational_patterns.simple_factory_pattern;

import com.ochprince.design_patterns.creational_patterns.simple_factory_pattern.Food.Food;
import com.ochprince.design_patterns.creational_patterns.simple_factory_pattern.Food.Salad;
import com.ochprince.design_patterns.creational_patterns.simple_factory_pattern.Material.Egg;
import com.ochprince.design_patterns.creational_patterns.simple_factory_pattern.Material.Lettuce;
import com.ochprince.design_patterns.creational_patterns.simple_factory_pattern.Material.Material;

import java.util.ArrayList;
import java.util.List;

public class FoodFactory {

	public static Food getFood(String foodName) {
		if ("Salad".equals(foodName)) {
			List<Material> materials = new ArrayList<>();
			Material eggs = new Egg("grey", "egg", 1.5f, 2, 2.45f, 3f);
			Material lettuce = new Lettuce("green", "lettuce", 2.5f, 3, 1.25f);
			materials.add(eggs);
			materials.add(lettuce);
			return new Salad("vegSalad", TasteEnum.SWEET, 4f).make(materials, 60000l);
		}
		throw new IllegalArgumentException("Do not have any food named: " + foodName);
	}
}
