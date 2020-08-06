package designpatterns.proxy.statical;

import designpatterns.proxy.Programmer;

/**
 * @author : 鄢云峰 yanyunfeng@bubugao.com
 * @date : 2019/11/19 17:03
 */
public class StaticProgrammerProxy implements Programmer {

    private Programmer programmer;

    public StaticProgrammerProxy(Programmer programmer) {
        this.programmer = programmer;
    }

    @Override
    public void code() {
        System.out.println("开电脑，打开idea，google");
        programmer.code();
        System.out.println("关显示器");
    }

    @Override
    public int debug(int a) {
        a += 2;
        System.out.println("bug数+2");
        return programmer.debug(a);
    }
}
