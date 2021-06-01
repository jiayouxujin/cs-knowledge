# Design Pattern

## Singleton

### 重点

1. 定义：指的是一个类只能有一个实例
2. 实现：
   1. 构造函数为private
   2. 通过类方法（static），来获取实例
   3. 考虑线程安全
3. 问题：
   1. 不方便扩展
   2. 有参构造函数
4. 是否要使用
   1. 如果该类不依赖外部类，并且全局可以共享的话可以使用
5. JDK-Example
   1. `java.lang.Runtime.getRuntime()`典型的使用方法（饿汉模式）

>有且只有一个实例，并且提供一个获取该实例的方法
>
>The singleton patter ensure a class has only one instance,and provides a global point of access to it.

### 经典模式

```java
public class Singleton{
    private static Singleton singleton;
    private Singleton(){}
    
    public static Singleton getInstance(){
        if(singleton==null){
            singleton=new Singleton();
        }
        return singleton;
    }
}
```

注意两点

1. 构造函数是private，也就是在外部，如果想要使用这个类的实例无法通过new来构造一个
2. 外部想要使用实例，只能通过`getInstance()`方法，该方法会判断是否已经初始化一个实例了。

### Multi-Thread

如果有两个线程同时执行`getInstance()`方法，此时还是只会有一个实例吗？

| Thread One                                      | Thread Two                                      | Instance |
| ----------------------------------------------- | ----------------------------------------------- | -------- |
| call getInstance()                              |                                                 | null     |
|                                                 | call getInstance()                              | null     |
| `if (instance==null) {instance=new Instance()}` |                                                 | object_1 |
|                                                 | `if (instance==null) {instance=new Instance()}` | object_2 |
| `return instance`                               |                                                 | object_1 |
|                                                 | `return instance`                               | object_2 |

出现以上的问题就是，两个线程可以同时进入到该方法，但是又缺少一个通知机制，通知另外一个线程，我这边已经new好了。

#### Solve_1

>不允许两个线程同时进入到该方法

```java
public static synchronized Singleton getInstance(){
    if(singleton==null){
        singleton=new Singleton();
    }
    return singleton;
}
```

不过synchronized这把锁加的有点大，有效损耗性能。

由于，我们只需要保证第一次初始化的操作只能由一个线程操作，在后面每个线程执行`getInstance()`的时候，直接返回已经初始化的实例即可，并不需要让这步操作也同步。

So，我们可以采取哪些措施来解决这个性能问题

1. 如果，此处并不是性能瓶颈，那么就不要考虑优化它。（很有趣的一个想法，不需要过度优化，你应该去寻找当前系统的性能瓶颈，如果这里getInstance()并不是损害性能的地方，那么就无需优化）
2. 如果在程序中肯定会使用该实例，那么就提前初始它。这里借助的是JVM在class loader时会初始化static变量

```java
//饿汉模式
public class Singleton{
    private static Singleton singleton=new Singleton();
    private Singleton(){
        
    }
    
    public static Singleton getInstance(){
        return singleton;
    }
}
```

3. double-check，如果当前实例为null，那么synchronized加锁，并且当线程获得到锁的时候，再一次判断是否为null，这样就解决了前面提到的无法同步和性能问题（锁的粒度变小的）。不过这里还需要注意一点：需要使用volatile关键字来修饰instance，因为这个可以保证每个线程看到的instance是一样的。

```java
public class Singleton{
    private volatile static Singleton singleton;
    private Singleton(){}
    
    public static Singleton getInstance(){
        if(singleton==null){
            //类级别的锁，所有对象都会竞争同一个资源
            synchronized(Singleton.class){
                //double-check
                if(singleton==null){
                    singleton=new Singleton();
                }
            }
        }
        return singleton;
    }
}
```

4. 静态内部类，通过JVM提供的能力，使用静态内部类，既可以保证延迟加载也可以保证线程安全

```java
public class Singleton{
    private Singleton(){}
    
    private static class SingletonHolder{
        private static Singleton singleton=new Singleton();
    }
    
    public static Singleton getInstance(){
        return SingletonHolder.singleton;
    }
}

//
Singleton.getInstance()
```

5. 通过枚举来实现单例，因为Java的枚举其实是特殊的类`public static final class`，在该类中的实例都是public static类型，所以利用该特性，我们可以显示简单的单例模式

```java
public enum Singleton{
    SINGLETON;
    
    public Singleton getInstance(){
        return SINGLETON;
    }
}
```

### 实战

#### Logger

先来看一个错误的代码

```java
public class Logger {
    private FileWriter writer;

    public Logger(){
        File file=new File("log.txt");
        try {
            writer=new FileWriter(file,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String message){
        try {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

上面这段代码如果两个线程同时执行`logger.log`会出现日志被覆盖的问题。

| thread_1           | thread_2            |
| ------------------ | ------------------- |
| file pos=105可以写 |                     |
|                    | file pos=105 可以写 |
| 写入               |                     |
|                    | 写入                |

那么为了解决多线程问题，一个方法就是加锁

```java
public void log(String message){
	synchronized(this){
        writer.write(message)
    }
}
```

这样加锁还是不行，因为这里是对象级别的锁，也就是说如果是不同线程不同对象，这个锁还是不起作用的。

所以需要把锁的粒度升级，升到类级别的锁

```java
public void log(String message){
    synchronized(Logger.class){
        writer.write(message)
    }
}
```

这样就保证了，一次只会有一个线程获取到该方法的锁去执行`writer.write()`。

通常来说解决共享资源冲突的问题可以通过一下这些方法：

1. 加锁
2. 使用BlockingQueue,多线程往这个队列里写数据，只有一个线程从这个队列里读数据
3. 单例模式

```java
public class Logger{
    private FileWriter writer;
    private static Logger logger=new Logger();
    
    private Logger(){
    	File file=new File("log.txt");
        writer=new FileWriter(file,true);
    }
    
    public static Logger getLogger(){
        return logger;
    }
    
    public void log(String message){
        writer.write(message);
    }
}
```

### anti-pattern

- 不方便扩展
  - 例如`Logger`单例，一开始我们把应用产生的所有日志全部放在log.txt文件里，但是随着业务的发展有可能出现有些应用需要放在不同的日志文件里，那么就需要去修改对应调用单例的代码
  - 如果是数据库连接池，一开始我们整个系统只有一个连接池，但是随着业务的发展，需要将某些SQL放到另外一个连接池，如果使用单例来创建连接池无法满足该需求`Pool.getConnection().execute(SQL)`
- 单例的构造函数隐藏里类的依赖关系
  - 由于我们使用单例的方法都是直接调用getInstance，无法直接使用构造函数，所以类的依赖关系被隐藏了
- 单例不支持有参数的构造函数
  - 解决方法-1
    - 通过init方法，在调用getInstance()时，先调用init(p1,p2)把参数传进去
  - 解决方法-2
    - 通过getInstance(p1,p2)初始化实例
  - 解决方法-3**（推荐）**
    - 通过全局的配置文件，在构造函数里使用`CONFIG.p1`来直接构造

### deal anti-pattern

```java
//old use
public demo(){
    Logger.getInstance().log(message)
}

//new use
public demo(Logger logger){
    logger.log(message)
}

Logger logger=Logger.getInstance()
demo(logger);
```

### 扩展

#### 线程间不唯一

单例模式指的是一个类只能有一个实例，不过这个只能有一个指的是`进程里`，不同进程可以有多个，即多活的情况下。但是我们如何创建一个进程里唯一，但是线程不唯一呢？

```java
public class Singleton{
    private static ConcurrentHashMap<Long,Singleton> map=new ConcurrentHashMap<>();
    private Singleton(){}
    
    public static Singleton getInstance(){
        Long threadId=Thread.currentThread().getId();
        map.putIfAbsent(threadId,new Singleton());
        return map.get(thread);
    }
}
```

#### 集群唯一

如何实现集群唯一，那么就是多个进程唯一。所以需要把Instance序列化存储在所有进程都可以访问的地方

#### 多例模式

如何实现多例模式，指的是一个类只能初始化有限个数的实例。可以通过map来存储实例，并且控制其个数。

