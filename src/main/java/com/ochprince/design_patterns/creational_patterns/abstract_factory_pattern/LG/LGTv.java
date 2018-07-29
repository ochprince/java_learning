package com.ochprince.design_patterns.creational_patterns.abstract_factory_pattern.LG;

import com.ochprince.design_patterns.creational_patterns.abstract_factory_pattern.TV;

public class LGTv implements TV {
	@Override
	public String watch() {
		return "I watch latest LGTv";
	}
}
