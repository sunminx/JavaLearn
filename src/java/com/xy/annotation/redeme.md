# java注解
注解本身并不会改变代码的逻辑。注解配合读取注解并进行相应处理的代码一起发挥作用。

## 注解的分类
- 编译器使用：注解只存在于源码文件中，编译器会读这类注解，触发编译的某些行为。编译后的字节码文件
中不会再存在这类注解。
  - @Override 告诉编译器检查该方法是不是正确进行类重写。
  - @SupressWarning 告诉编译器忽略此处的代码警告
- 处理字节码文件时使用：这类注解会存在于字节码文件中，但是不会加载进内存。比如在加载class文件的
时候，某些工具会根据注解对字节码进行动态修改。一般底层库使用，开发不会处理这类注解。
- 运行时使用：代码运行时可以读取的注解。存在于JVM中。

## 注解的定义

注解本身也是一个类class

修饰符 @interface
配置参数 类似一个无参方法，返回值定义配置参数的类型。
配置参数类型可以是 基础数据类型，String， 枚举类型，数组。

一般都会定义一个Value的配置参数。写只有一个配置的注解时，Value可以不显示声明。
```java
// @Validation(value=39)
// 等价于
// @Validation(39)
```

定义配置参数的时候，最后同步定义一个默认值。

配置参数是一个常量，进行注解声明的时候就已经定义好了值。

```java
public @interface Validation {
    int value() default 50;
    int min() default 0;
    int max() default 100;
}
```

## 元注解
元注解：注解上声明的注解。
元注解由Java标准库定义，一般开发不声明元注解。元注解可以认为是以注解的形式来为注解进行一些定义，
或者说约束。比如规定这个注解可以标注在哪里，是不是可继承的，在哪个阶段生效等。

- @Target：声明注解可以声明在哪里。（必须）
  - ElementType.TYPE        类或者接口
  - ElementType.FIELD       属性
  - ElementType.METHOD      方法
  - ElementType.CONSTRUCTOR 构造方法
  - ElementType.PARAMETER   方法参数
  
- @Retention：声明哪个阶段可以读取到注解。一般自定义注解就是声明运行时。（必须）
  - RetentionPolicy.SOURCE
  - RetentionPolicy.CLASS
  - RetentionPolicy.RUNTIME
  
- @Repeatable：是否可以在一个位置重复声明该注解。（非必须）
- @Inherited：该注解是否可继承（只能用于声明在类上的注解）。（非必须）

## 处理注解
单纯的注解是不会改变代码逻辑的。需要对注解定义相应的处理函数，知道用户声明了这个注解，我们
应该如何处理。

获取注解的方法：
- Class.getAnnotation(Class) 参数是注解的class类型
- Field.getAnnotation(Class)
- Method.getAnnotation(Class)
- Constructor.getAnnotation(class)

获取注解，然后判断是否为null, 不是null说明该位置声明了该注解，继续后续的处理。

方法参数列表的注解获取，注意使用一个二维数组去接。因为一个参数可以声明多个注解，此时就是一个
注解数组，然后参数列表，表明又是一个数组的数组，因此返回值是二维数组。
```text
Method m = ...;
Annotation[][] paramAnnotations = m.getParameterAnnotation();
```

