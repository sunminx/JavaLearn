package com.xy.multithread;

/**
 * ThreadLocal 可以看到是线程安全的变量
 *
 * 往threadLocal存储变量
 * threadLocal.set(val) -> currentThread.map.put(threadLocal, val)
 *
 * 从threadLocal读取变量
 * threadLocal.get() -> val = Thread.currentThread.map.get(threadLocal)
 *
 * 可以看出往一个threadLocal写入数据 本质上是以 threadLocal为key 将数据写入到线程对象本地变量map中
 *
 * 这当然是线程安全的，这个值不同线程是存储在不同位置（线程自身）。获取的时候也是从当前线程对象上获取。
 *
 * 一种空间换时间的思想。
 */
public class ThreadLocalDemo {

    public static void main(String[] args) {
        try (var ctx = new UserContext("Bob")) {
            step1();
            step1();
            step1();
        }
    }

    public static void step1() {
        System.out.printf("get user = %s in step1\n", UserContext.get());
    }

    public static void step2() {
        System.out.printf("get user = %s in step2\n", UserContext.get());
    }

    public static void step3() {
        System.out.printf("get user = %s in step3\n", UserContext.get());
    }

    /**
     * 封装一个往threadLocal中写入/获取/释放 该线程 上下文
     *
     * 实现AutoCloseable的类 可以使用 try(resource) {}语法糖。
     */
    public static class UserContext implements AutoCloseable {

        static final ThreadLocal<String> threadLocal = new ThreadLocal();

        public UserContext(String user) {
            this.set(user);
        }

        public void set(String user) {
            threadLocal.set(user);
        }

        public static String get() {
            return threadLocal.get();
        }

        @Override
        public void close() {
            threadLocal.remove();
        }
    }
}
