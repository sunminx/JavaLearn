package com.xy.dp.chain;

public class Context {
    private String name;
    private boolean work;
    private boolean salary;
    private boolean tool;

    public Context(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWork() {
        return work;
    }

    public void setWork(boolean work) {
        this.work = work;
    }

    public boolean isSalary() {
        return salary;
    }

    public void setSalary(boolean salary) {
        this.salary = salary;
    }

    public boolean isTool() {
        return tool;
    }

    public void setTool(boolean tool) {
        this.tool = tool;
    }
}
