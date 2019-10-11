package com.ochprince.design_patterns.structural_patterns.proxy_pattern;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 利用JDK的API，动态的在内存中构建代理对象(需要我们指定创建代理对象/目标对象实现的接口的类型)<br><br>
 * 被代理的目标对象一定要实现接口<br><br>
 *
 * <p>Copyright OchPrince 2019 Co.,Ltd.</p>
 *
 * @author shilong.jiang
 * @version 1.0.0.RELEASE
 * @since CreatedAt 2019-10-11 17:44:41
 */
public class ProxyOfInterface {

    public static void main(String[] args) {
        mapOperate();
        binarySearch();
    }

    /**
     * 将HashMap代理后，打印出其他信息。可以看出InvocationHandler中的方法的三个参数分别的作用。<br>
     * proxy为被代理的实例，method为被代理的方法，margs为方法的参数
     */
    private static void mapOperate() {
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
        System.out.println(Proxy.isProxyClass(o.getClass()));
    }

    private static void binarySearch() {
        Object[] values = new Object[1000];
        for (int i = 0; i < values.length; i++) {
            Integer integer = i;
            values[i] = Proxy.newProxyInstance(null, integer.getClass().getInterfaces(),
                    (Object proxy, Method method, Object[] margs) -> {
                        System.out.println(integer + "." + method.getName() + Arrays.toString(margs));
                        return method.invoke(integer, margs);
                    });
        }
        int i = Arrays.binarySearch(values, 500);
        System.out.println("i = " + i);
    }
}
