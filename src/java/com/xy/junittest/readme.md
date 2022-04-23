# 单元测试
Junit是一个开源的Java语言的单元测试框架。

## 测试类基本框架
每次运行一个@Test方法之前，Junit框架会首先创建一个XxxTest测试类实例。
### 断言
测试方法中使用断言来判断被测试的方法是不是符合预期。
- assertEquals(expected, actual)
- assertTrue()
- assertFalse()
- assertArrayEquals()

### 基本规范
- 单元测试方法必须足够简单，不能过于复杂以至于还要验证单元测试方法的正确性。
- 各个单元测试方法应该互相独立，不能依赖运行顺序。
- 测试用例不仅要覆盖常见用例，更要注意测试边界条件。

## Fixture
单元测试依赖一些其他资源（被测试对象，文件， 数据库连接）。Junit框架中fixture部分就是
完成测试资源的准备和释放。
其实就是一些添加了特殊注解的方法，方法中定义了资源的初始化和释放的逻辑。然后Junit框架负责
在执行测试方法前后跑这些方法。

### 每个测试方法执行前后
一般这种情况初始化的是实例变量。
```java
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

// 每个测试方法测试之前执行
@BeforeEach
// 每个测试方法测试之后执行
@AfterEach
```

### 全部测试方法执行前后
一般这种情况初始化的是静态变量
```java
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

// 全部测试方法运行之前执行
@BeforeAll

// 全部测试方法运行完毕执行
@AfterAll
```

## 异常测试
测试方法在特定的情况下是不是会抛出预期的异常。（是方法返回的另一种可能的测试。一个方法要么
执行正确，正常退出，要么向上抛出异常。上面的是判断方法的返回值是不是符合预期，异常测试判断
异常情况发生，方法会不会向上排除声明的异常）。

```java
// 异常判断 第一个参数是预期抛出的异常，第二个参数是Executable匿名类
assertThrows(expectExpection.class, execable);
```

## 条件测试
条件话测试就是利用某些注解，跳过测试类中的部分测试方法。
完全跳过，不执行；设置执行条件，条件不满足不执行。

- @Disabled
- @EnableOnOs({OS.WINDOWS, OS.LINUX}) 
- @DisabledOnOs({OS.WINDOWS, OS.LINUX})
- @EnabledIfSystemProperties(name = "os.arch", matches = ".*64.*")
- @EnabledIfEnvironmentVariable(name = "DEBUG", matches = "true")

## 参数化测试
之前的测试方法中，测试方法的入参就是直接定义的，就一个，实际上这是不够的。充分的测试一个方法
是不是正确，可能要输入各种的参数，来考察在各种场景下，方法是不是都可以正确工作。

- ValueSource(ints = {1, 3, 5, 7, 9})
- MethodSource 定义一个和测试方法同名的静态方法，这个方法会返回一个列表，包含全部的测试参数
- CsvSource({"a, b", "c, d", "e, f"})
- CsvSource(resources = {"/data/test.csv"})