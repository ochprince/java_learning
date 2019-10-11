package com.ochprince.design_patterns.structural_patterns.proxy_pattern;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Cglib是一个强大的高性能的代码生成包吗，它可以在运行期扩展java类与实现java接口<br><br>
 *
 * <p>Copyright OchPrince 2019 Co.,Ltd.</p>
 *
 * @author shilong.jiang
 * @version 1.0.0.RELEASE
 * @since CreatedAt 2019-10-11 17:44:41
 */
public class ProxyOfSubClass {

    public static void main(String[] args) {
        User instance = (User) getProxyInstance(new User());
        instance.print();
    }

    private static Object getProxyInstance(Object obj) {
        return new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("before");
                Object res = method.invoke(obj, args);
                System.out.println("after");
                return res;
            }

            Object getProxy() {
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(obj.getClass());
                enhancer.setCallback(this);
                return enhancer.create();
            }
        }.getProxy();
    }

    public static class User {
        void print() {
            System.out.println("My name is ochPrince");
        }
    }
}
