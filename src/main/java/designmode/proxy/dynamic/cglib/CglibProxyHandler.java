package designmode.proxy.dynamic.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib的动态代理可以代理没有实现接口的类，但是不能代理final的方法
 *
 * @author : 鄢云峰 yanyunfeng@bubugao.com
 * @date : 2019/11/20 11:52
 */
public class CglibProxyHandler implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib，开电脑，写bug");
        Object object = methodProxy.invokeSuper(o,objects);
        System.out.println("cglib，关显示器，下班");
        return object;
    }

}
