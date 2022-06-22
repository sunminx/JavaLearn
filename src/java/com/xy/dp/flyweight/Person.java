package com.xy.dp.flyweight;

import java.util.Random;

public class Person {
    private String id;
    private String name;
    private int age;
    private boolean sex;
    private String address;
    private boolean checked;

    public Person(String id, String name, int age, boolean sex, String address, boolean checked) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.address = address;
        this.checked = checked;
    }

    public Person(String id, String name) {
        this(id, name, new Random().nextInt(100),
                new Random().nextBoolean(), "shanghai", new Random().nextBoolean());
    }

    @Override
    public String toString() {
        return "Person{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", address='" + address + '\'' +
                ", checked=" + checked +
                '}';
    }
}
