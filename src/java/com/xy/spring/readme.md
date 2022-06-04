# spring

## 1. Bean对象的创建过程
- spring注解扫描，将扫描范围内的被定义为Bean的类的信息加载进来（BeanDefinition）
- 类加载
- 对象的实例化（其中包括构造器推断，选择合适的构造器来new一个对象）
- 依赖注入（将使用了@Autowired注解的属性赋值）
- 初始化前
- 初始化
- AOP处理，将为普通对象升级为代理对象
- 将代理对象加入到spring单例池

## 2. 什么是单例池
本质：singleObjects Map<beanName, bean>

目的：
 - 保证系统内始终只有一个对象，避免对象的频繁创建造成资源浪费
 - 对象看作是一个资源，将对象的消费方和生产方之间解耦

## 3. Bean对象和普通对象的区别

## 4. @PostConstruct
spring会在创建Bean的生命周期之初始化前的阶段去调用注解@PostConstruct方法。
spring通过类对象可以获取所有方法的信息，其中就可以判断是不是有方法注解了@PostConstruct
一旦成立，将基于反射去调用这个方法。

## 5. Bean的初始化是如何工作的
实现了InitializeBean接口的方法必须实现一个afterPropertiesSet()方法，而这个方法将完成
Bean的初始化工作。spring将在创建Bean的初始化阶段去调用这个方法(spring会首先判断是不是
是不是实现了这个接口)。

## 6. 实例化和初始化的区别
实例化是spring通过构造方法得到一个对象
初始化是spring调用实例化出来的对象中的某个方法
（afterPropertiesSet()方法或者BeanDefinition指定的方法）

## 7. 什么是初始化后
初始化后主要完成AOP处理。
代理对象中有一个属性target，指向普通Bean对象，就是经过了AOP之前那些创建流程的Bean对象。

## 8. 推断构造方法
- 声明@Autowired注解的构造方法
- 无参构造方法
- 唯一的构造方法
- 报错（NoSuchMethodException）, spring无法确定构造器，无法完成实例化

## 9. byType byName
spring进行依赖注入的时候，需要确定要从单例池中找哪个Bean赋值给属性。原则就是先byType再byName
