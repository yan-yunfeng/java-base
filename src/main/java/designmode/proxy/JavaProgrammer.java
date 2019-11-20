package designmode.proxy;

/**
 * @author : 鄢云峰 yanyunfeng@bubugao.com
 * @date : 2019/11/19 17:02
 */
public class JavaProgrammer implements Programmer {

    @Override
    public void code() {
        System.out.println("面向对象，写bug");
    }

    @Override
    public int debug(int a) {
        System.out.println(a);
        return a;
    }
}
