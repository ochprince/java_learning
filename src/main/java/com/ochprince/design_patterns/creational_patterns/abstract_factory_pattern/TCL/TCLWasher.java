package com.ochprince.design_patterns.creational_patterns.abstract_factory_pattern.TCL;

import com.ochprince.design_patterns.creational_patterns.abstract_factory_pattern.Washer;

public class TCLWasher implements Washer {
	@Override
	public String wash() {
		return "I use latest TCLWasher to wash clothes";
	}
}
