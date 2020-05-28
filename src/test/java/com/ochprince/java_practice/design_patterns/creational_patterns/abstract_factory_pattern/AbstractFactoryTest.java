package com.ochprince.java_practice.design_patterns.creational_patterns.abstract_factory_pattern;

import com.ochprince.java_learning.design_patterns.creational_patterns.abstract_factory_pattern.AbstractFactory;
import com.ochprince.java_learning.design_patterns.creational_patterns.abstract_factory_pattern.LG.LGFactory;
import com.ochprince.java_learning.design_patterns.creational_patterns.abstract_factory_pattern.TCL.TCLFactory;
import com.ochprince.java_learning.design_patterns.creational_patterns.abstract_factory_pattern.TV;
import com.ochprince.java_learning.design_patterns.creational_patterns.abstract_factory_pattern.Washer;
import org.junit.Test;

public class AbstractFactoryTest {

	@Test
	public void TCLFactroyTest() {
		AbstractFactory factory = AbstractFactory.getFactory(AbstractFactory.TCL);
		TV latest_tv = factory.createTV(TCLFactory.LATEST);
		TV older_tv = factory.createTV(TCLFactory.OLDER);
		Washer latest_washer = factory.createWasher(TCLFactory.LATEST);
		Washer older_washer = factory.createWasher(TCLFactory.OLDER);
		System.out.println(latest_tv.watch());
		System.out.println(older_tv.watch());
		System.out.println(latest_washer.wash());
		System.out.println(older_washer.wash());
	}

	@Test
	public void LGFactroyTest() {
		AbstractFactory factory = AbstractFactory.getFactory(AbstractFactory.LG);
		TV latest_tv = factory.createTV(LGFactory.LATEST);
		TV older_tv = factory.createTV(LGFactory.OLDER);
		Washer latest_washer = factory.createWasher(LGFactory.LATEST);
		Washer older_washer = factory.createWasher(LGFactory.OLDER);
		System.out.println(latest_tv.watch());
		System.out.println(older_tv.watch());
		System.out.println(latest_washer.wash());
		System.out.println(older_washer.wash());
	}
}
