package designpatterns.proxy;

/**
 *
 * @author : 鄢云峰 yanyunfeng@bubugao.com
 * @date : 2019/11/19 15:52
 */
public interface Programmer {

    /**
     * 写代码
     */
    void code();

    /**
     * 处理bug
     * @param a 现有的bug数量
     * @return 处理后的bug数量
     */
    int debug(int a);

}
