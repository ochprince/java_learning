package com.ochprince.java_learning.design_patterns.creational_patterns.abstract_factory_pattern.TCL;


import com.ochprince.java_learning.design_patterns.creational_patterns.abstract_factory_pattern.AbstractFactory;
import com.ochprince.java_learning.design_patterns.creational_patterns.abstract_factory_pattern.TV;
import com.ochprince.java_learning.design_patterns.creational_patterns.abstract_factory_pattern.Washer;

public class TCLFactory extends AbstractFactory {

	public static final String LATEST = "latest";
	public static final String OLDER = "older";

	@Override
	public TV createTV(String serial_num) {
		if (LATEST.equals(serial_num)) {
			return new TCLTv();
		} else if (OLDER.equals(serial_num)) {
			return new Older_TCLTv();
		} else {
			throw new IllegalArgumentException("Not have the serial_num of TCLTv like: " + serial_num);
		}
	}

	@Override
	public Washer createWasher(String serial_num) {
		if (LATEST.equals(serial_num)) {
			return new TCLWasher();
		} else if (OLDER.equals(serial_num)) {
			return new Older_TCLWasher();
		} else {
			throw new IllegalArgumentException("Not have the serial_num of TCLWasher like: " + serial_num);
		}
	}
}
