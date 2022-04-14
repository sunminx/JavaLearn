# 范型

## 范型接口

```java
public interface Comparable<T> {
    int compare(T t);
}
```

## 范型类

## 范型修饰方法
### 对象方法
### 类方法

## 多个范型类型

## 擦拭法

## 通配符
### extends通配符
### super通配符

## 范型与反射

## 范型数组

## 谨慎使用范型和可变数组

```java
public class Demo {
    
    //  会报ClassCastException
    static <T> T[] toArray(T t1, T t2, T t3) {
        return asArray(t1, t2, t3);
    }
    
    static <T> T[] asArray(T... t) {
        return t;
    }
}
```