package com.ochprince.design_patterns.creational_patterns.abstract_factory_pattern.TCL;

import com.ochprince.design_patterns.creational_patterns.abstract_factory_pattern.Washer;

public class Older_TCLWasher implements Washer {
	@Override
	public String wash() {
		return "I use older TCLWasher to wash clothes";
	}
}
