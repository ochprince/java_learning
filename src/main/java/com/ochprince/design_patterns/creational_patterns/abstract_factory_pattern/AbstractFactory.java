package com.ochprince.design_patterns.creational_patterns.abstract_factory_pattern;

import com.ochprince.design_patterns.creational_patterns.abstract_factory_pattern.LG.LGFactory;
import com.ochprince.design_patterns.creational_patterns.abstract_factory_pattern.TCL.TCLFactory;

public abstract class AbstractFactory {

	public static final String TCL = "TCL";
	public static final String LG = "LG";

	public abstract TV createTV(String serial_num);

	public abstract Washer createWasher(String serial_num);

	public static AbstractFactory getFactory(String brand) {
		if (TCL.equals(brand)) {
			return new TCLFactory();
		} else if (LG.equals(brand)) {
			return new LGFactory();
		} else {
			throw new IllegalArgumentException("Not have brand named: " + brand);
		}
	}

}
