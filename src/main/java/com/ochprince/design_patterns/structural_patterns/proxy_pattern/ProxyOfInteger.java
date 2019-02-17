package com.ochprince.design_patterns.structural_patterns.proxy_pattern;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProxyOfInteger {

	public static void test(String[] args) {
		Object[] values = new Object[1000];
		for (int i = 0; i < values.length; i++) {
			Integer integer = new Integer(i);
			values[i] = Proxy.newProxyInstance(null, integer.getClass().getInterfaces(),
					(Object proxy, Method method, Object[] margs) -> {
						System.out.println(integer + "." + method.getName() + Arrays.toString(margs));
						return method.invoke(integer, margs);
					});
		}
		Arrays.binarySearch(values, 500);
	}

	/**
	 * 将HashMap代理后，打印出其他信息。可以看出InvocationHandler中的方法的三个参数分别的作用。proxy为被代理的实例，method为被代理的方法，margs为方法的参数。可以看出代理很彻底的交接了方法的一切
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		Object o = Proxy.newProxyInstance(null, HashMap.class.getInterfaces(),
				(Object proxy, Method method, Object[] margs) -> {
					if (Objects.equals(method.getName(), "put")) {
						System.out.println(">>" + method.getName() + Arrays.toString(margs) + ((Map) proxy).get("name"));
					}
					return method.invoke(map, margs);
				});
		if (o instanceof Map) {
			Map o1 = (Map) o;
			o1.put("name", "jason");
			o1.put("key", "mona");
			System.out.println(o1.get("name"));
		}
	}
}
