package com.ochprince.design_patterns.creational_patterns.abstract_factory_pattern.TCL;

import com.ochprince.design_patterns.creational_patterns.abstract_factory_pattern.TV;

public class TCLTv implements TV {
	@Override
	public String watch() {
		return "I watch latest TCLTv";
	}
}
