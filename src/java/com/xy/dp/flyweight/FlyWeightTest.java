package com.xy.dp.flyweight;

public class FlyWeightTest {

    public static void main(String[] args) {
        String[] ids = {"1001", "2904", "1001", "1001", "2904", "5003"};
        String[] names = {"tom", "jerry", "tom", "tom", "jerry", "kim"};

        Checker checker = new Checker();

        long start, end;
        for (int i = 0; i < 6; i++) {
            start = System.currentTimeMillis();
            Person person = checker.check(ids[i], names[i]);
            end = System.currentTimeMillis();
            System.out.printf("[check] result: %s, timeCost: %dms\n", person, (end - start));
        }
    }
}
