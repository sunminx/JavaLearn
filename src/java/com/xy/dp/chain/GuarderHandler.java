package com.xy.dp.chain;

public class GuarderHandler extends Handler{

    public GuarderHandler(Handler handler) {
        super(handler);
    }

    @Override
    void exec(Context context) {
        System.out.printf("name: %s, is_work: %s, is_salary: %s, is_tool: %s\n",
                context.getName(), context.isWork(), context.isSalary(), context.isSalary());
    }
}
