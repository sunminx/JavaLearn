package com.xy.dp.chain;

public class LeaderHandler extends Handler{

    public LeaderHandler(Handler handler) {
        super(handler);
    }

    @Override
    void exec(Context context) {
        if (context != null)
            context.setWork(true);
    }
}
