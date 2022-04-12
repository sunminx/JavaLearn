# 多线程
## 多线程基础
任务执行的发展：
- 串行执行
- 基于时间片抢占
- 多进程 + 多线程 模式

多线程程序相较于单线程程序来说，无法预先确定线程调度顺序，也就无法确定哪个方法任务先被处理。
可以为线程设置优先级，可以认为操作系统对高优先级的线程可能调度更频繁，但是我们在编写多线程
程序的时候不能通过线程优先级来保证哪个任务先执行，进而觉得程序的逻辑，这是错误的。

## 创建线程的方法
- 自定义线程类 继承自Thread类
- 任务实现Runnable接口
- 任务实现Callable接口

Java 使用 Thread 来表示一个线程对象，调用其run方法将启动该线程。

## 线程状态
- New
- Runnable
- Block
- Timed Wait
- Terminated


- 正常退出
- 未捕获异常错误退出
- stop()强制退出（不推荐）

.join() 可以等待该线程退出

## 线程中断
- interrupt() (中断异常时需要小心判断如何响应中断信号 以保证线程可以合法退出)
- 中断标志变量 (该变量需要使用volatile修改 保证线程可见效)

当存在线程链的时候，要注意将顶层线程的中断信号传递下去，避免上层的线程中断退出，
但是下层线程还是一直在运行，此时这种线程运行一般是无意义的，只会造成内存和CPU资源的浪费。

## 守护线程
将一个需要在程序运行过程中一直执行的线程设置为守护线程，可以保证在所有工作线程退出，程序
准备结束运行的时候，主线程可以正确退出。
任何非守护线程仍在运行，都将导致主线程无法合法退出。
设置线程为守护线程操作在启动线程之前进行。

## 线程同步
对于在多线程场景下会因为并发修改发生错误的代码块，必须进行线程同步处理，保证在同一时刻
只有一个线程可以进行代码块（临界区）执行代码。一般是加锁，确保同一时刻只有一个线程可以
获取锁。（另一种方法是CAS）

- 注意使用同一个对象作为锁。如果有两个代码块，并且同一时刻只有一个线程可以执行其中的一个代码块
  特别注意两个代码块起始的位置要竞争同一把锁，否则是没有意义的，还是存在并发读写的问题。
- 单条原子操作的语句不需要进行同步处理
    - 原子类型（long/double ...）的赋值
    - 引用类型（list）赋值

## 同步方法
- 对象同步方法锁定的是对象本身
- 类同步方法锁定的是类对象

JVM允许一个线程重复获取同一把锁。这种可以被同一个线程反复获取的锁称之为可重入锁。
synchronized 是可重入锁，多个方法锁定同一对象时，当一个线程获取到锁，进入一个方法中执行
时，在这个方法内，如果调用了另一个方法，此时是可以进入的。因此，每次获取锁的时候，不仅要
判断是不是第一次获取锁，还要记录是第几次获取锁，每次退出synchronized块进行减一。当减到0
的时候，将释放锁（锁对象和线程解绑）。

## 死锁
一个线程持有另一个线程需要的锁，同时又需要另一个线程已持有的锁。
发生原因：就是错序的获取多个锁。一个线程发生了死锁，也不会主动释放已获取的锁，而是一直阻塞等待
下去。
解决办法：顺序获取锁

## 线程之间协调机制
获取到锁的线程 在运行过程中 继续执行条件不满足的时候 可以主动释放锁。
其他阻塞在synchronized等待获取锁的线程有机会获取锁，然后进入临界区。
执行结束的线程，在退出临界区，释放锁之前，可以唤醒其他线程，让之前wait()开始重新竞争锁
竞争到锁以后继续执行（此时这个线程的执行条件可能满足，可能还是不满足）。

wait/notify
- 前提是已经获取到了锁。wait()线程阻塞操作会释放锁。
- notify()随机唤醒一个线程。
- notifyAll()唤醒全部线程。
- 被唤醒的线程需要重新竞争锁 之后竞争到锁的线程才会继续执行。

## ReentrantLock
 可以认为是synchronized锁的高性能版本。
- ReentrantLock是基于AQS实现，
Synchronized是基于操作系统实现加锁（1.6以后锁升级过程，现在也不是很重）。
- ReentrantLock需要手动释放锁。
- ReentrantLock加锁方式更加灵活。有阻塞式的，也有立即返回，一定时间内尝试获取锁。
lock.lock() lock.tryLock() lock.tryLock(time, timeUnit)。

### condition——ReentrantLock的线程协调工具
类似于synchronized+object.wait/notify/notifyAll 组合 实现 线程之间互相通知。
ReentrantLock + condition.await/sign/signAll 也可以实现线程之间协调。
线程在临界区内可以主动释放锁（执行条件不成熟）。后面休眠的线程可以被唤醒。

## Concurrent 并发集合工具包

| interface | no-thread-safe | thread-safe        |
|-----------| ------------ |--------------------|
| List      | ArrayList | CopyOnWriteList    |
| Map       | HashMap      | ConcurrentHashMap  |
| Set | HashSet/TreeSet | CopyOnWriteSet |
| Queue | ArrayDeque/LinkedList | ArrayBlockingQueue/LinkedBlockingQueue |
| Deque | ArrayDeque/LinkedList | LinkedBlockingDeque |

**Collections.synchronizedMap**工具类可以将非线程安全集合转换成线程安全集合。但是
这个方法是一个包装类，对所有对读写方法加synchronized锁。所以性能很差，一般不推荐使用。

## Atomic
java.util.concurrent.Atomic包内提供的线程安全版本的基础数据类型。
基于CAS实现线程安全的读写。

## ForkJoinPool
fork/join线程池，接受一个任务，可以将任务拆分成小规模的子任务，最终多个线程并行跑多个任务。
利用多核进行加速。

## ThreadLocal
一种线程安全的变量存储读取方式（变量本质存储在线程对象本地）。

```java
// threadLocal写入变量方法
// 1. 首先获取线程对象的存储map（如果没有会初始化）
// 2. 然后(threadLocal, value)写入map
public void set(T value) {
    Thread t = Thread.currentThread();
    ThreadLocalMap map = getMap(t);
    if (map != null) {
        map.set(this, value);
    } else {
        createMap(t, value);
    }
}

// 从threadLocal中获取当前线程存储的变量
// 1. 从Thread变量中获取存储map 
// 2. 以threadLocal为key从map中查询值
public T get() {
    Thread t = Thread.currentThread();
    ThreadLocalMap map = getMap(t);
    if (map != null) {
        ThreadLocalMap.Entry e = map.getEntry(this);
        if (e != null) {
            @SuppressWarnings("unchecked")
            T result = (T)e.value;
            return result;
        }
    }
    return setInitialValue();
}
```
