package com.xy.dp.decorator;

import java.io.File;
import java.io.IOException;

public class DecoratorTest {

    /**
     * 这里的LowerCaseFileInputStream是一个装饰方法
     *
     * 装饰模式: 主要是为被装饰的类进行方法增强
     * 原本的类没有相关功能 或者不是自己想要的 可以通过装饰方法来对方法进行增强
     *
     * 实现形式上基于继承或者接口实现 装饰类和被装饰类继承自相同的接口 或者装饰类继承被装饰类 然后重写被装饰类待装饰的方法
     *
     * 和代理模式的区别:
     *  可以认为在实现形式上没有区别, 强行说区别的话 代理可以基于组合来实现, 而装饰模式一般就基于继承
     *
     *  主要区别在目的上:
     *      1. 装饰模式侧重对原方法的增强 增强的功能很重要 但是原方法又没有实现
     *      基于开闭原则通过装饰模式来增强原方法而不是直接修改原来的实现(如果第三方的实现还改不了)
     *
     *      2. 代理模式侧重于为原方法的调用增加一些边角功能，比如日志，耗时计算等。如果不进行代理，可以直接调用原方法的话，按理也不影响功能
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        File file = new File("src/java/com/xy/dp/decorator/test.txt");
        LowerCaseFileInputStream lowerCaseFileInputStream = new LowerCaseFileInputStream(file);

        byte[] bytes = new byte[1024];
        int n = lowerCaseFileInputStream.read(bytes);
        System.out.println(new String(bytes, 0, n));
    }
}
