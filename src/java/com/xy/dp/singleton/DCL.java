package com.xy.dp.singleton;

/**
 * Double Check Lazy Singleton
 *
 * 实例需要有volatile关键字修饰 禁止new对象的时候进行指令重排序
 */
public class DCL {

    private volatile DCL instance;

    private DCL() {}

    private DCL instance() {
        if (instance == null) {
            synchronized (this) {
                if (instance == null) {
                    instance = new DCL();
                }
            }
        }
        return instance;
    }
}
