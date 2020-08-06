package designpatterns.proxy.dynamic.cglib;

/**
 * @author : 鄢云峰 yanyunfeng@bubugao.com
 * @date : 2019/11/20 14:18
 */
public class CglibProgrammer {

    public void code() {
        System.out.println("面向对象，写bug");
    }

    public int debug(int a) {
        System.out.println(a);
        return a;
    }
}
