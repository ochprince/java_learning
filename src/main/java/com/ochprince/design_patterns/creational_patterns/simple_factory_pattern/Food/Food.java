package com.ochprince.design_patterns.creational_patterns.simple_factory_pattern.Food;

import com.ochprince.design_patterns.creational_patterns.simple_factory_pattern.Material.Material;

import java.util.List;

public interface Food {

	Food make(List<Material> materials, Long neededTime);
}
