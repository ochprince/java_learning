package com.ochprince.design_patterns.creational_patterns.abstract_factory_pattern.LG;

import com.ochprince.design_patterns.creational_patterns.abstract_factory_pattern.Washer;

public class Older_LGWasher implements Washer {
	@Override
	public String wash() {
		return "I use older LGWasher to wash clothes";
	}
}
