package com.ochprince.design_patterns.creational_patterns.abstract_factory_pattern.LG;

import com.ochprince.design_patterns.creational_patterns.abstract_factory_pattern.Washer;

public class LGWasher implements Washer {
	@Override
	public String wash() {
		return "I use latest LGWasher to wash clothes";
	}
}
