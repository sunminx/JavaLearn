package com.xy.dp.chain;

public class ManagerHandler extends Handler{

    public ManagerHandler(Handler handler) {
        super(handler);
    }
    @Override
    void exec(Context context) {
        if (context.isWork())
            context.setSalary(true);
    }
}
