package com.xy.dp.chain;

/**
 * 在初始化的时候，就通过给next属性赋值 将Chain上的Handler串成一个单向链表
 */
public abstract class Handler {
    private Handler next;

    public Handler(Handler handler) {
        this.next = handler;
    }

    abstract void exec(Context context);

    public void process(Context context) {
        exec(context);
        if (next != null)
            next.process(context);
    }
}
