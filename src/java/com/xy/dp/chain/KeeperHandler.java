package com.xy.dp.chain;

public class KeeperHandler extends Handler{

    public KeeperHandler(Handler handler) {
        super(handler);
    }

    @Override
    void exec(Context context) {
        if (context.isSalary())
            context.setTool(true);
    }
}
