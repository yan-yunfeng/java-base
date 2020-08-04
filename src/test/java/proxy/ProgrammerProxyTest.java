package proxy;

import designmode.proxy.JavaProgrammer;
import designmode.proxy.Programmer;
import designmode.proxy.dynamic.cglib.CglibProgrammer;
import designmode.proxy.dynamic.cglib.CglibProxyHandler;
import designmode.proxy.dynamic.jdk.JdkProxyHandler;
import designmode.proxy.statical.StaticProgrammerProxy;
import net.sf.cglib.proxy.Enhancer;
import org.junit.Test;

/**
 * @author : 鄢云峰 yanyunfeng@bubugao.com
 * @date : 2019/11/19 17:06
 */
public class ProgrammerProxyTest {

    @Test
    public void programmerStaticProxyTest() {
        Programmer programmer = new StaticProgrammerProxy(new JavaProgrammer());
        programmer.code();
        programmer.debug(10);
    }

    @Test
    public void programmerJdkDynamicProxyTest() {
        Programmer programmer = new JavaProgrammer();
        Programmer proxy = (Programmer) new JdkProxyHandler(programmer).createProxyInstance();
        proxy.code();
        proxy.debug(10);
    }

    @Test
    public void programmerCglibDynamicProxyTest(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CglibProgrammer.class);
        enhancer.setCallback(new CglibProxyHandler());
        CglibProgrammer programmer = (CglibProgrammer) enhancer.create();
        programmer.code();
        programmer.debug(20);
    }

}
