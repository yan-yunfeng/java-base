package designpatterns.proxy.dynamic.jdk;

import designpatterns.proxy.Programmer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk的动态代理类由jdk在运行时动态创建，只能代理实现了接口的类
 *
 * @author : 鄢云峰 yanyunfeng@bubugao.com
 * @date : 2019/11/19 17:20
 */
public class JdkProxyHandler implements  InvocationHandler{

    private Object programmer;

    public JdkProxyHandler(Programmer programmer) {
        this.programmer = programmer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开电脑，打开idea，google");
        Object object = method.invoke(programmer,args);
        System.out.println("关显示器");
        return object;

    }

    public Object createProxyInstance(){
        return Proxy.newProxyInstance(
                programmer.getClass().getClassLoader(),
                programmer.getClass().getInterfaces(),
                this
        );
    }

}
