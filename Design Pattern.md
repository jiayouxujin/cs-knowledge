# 设计原则

## 面向对象四大特性

>- 封装：如何隐藏信息、保护数据
>- 抽象：如何隐藏方法的具体实现，使用者无需关心其内部的具体实现，只要知道有这个存在即可。抽象这个概念用的很多，例如操作系统就是对底层硬件的抽象
>- 继承：代码复用，假设有两个类他们有相同的属性和方法，我们可以将这些公共部分抽离出来成为一个父类。让这两个类继承它。
>- 多态：子类可以替换父类，在实际运行的过程中，可以调用子类的方法
>  - 即在初始化的时候 `OriginInterface value=new WhoImplements() or ParentClass c=new ChildrenClass()`
>  - 调用的 `value.someCall()`会调用子类的实现方法
>  - 提高了代码的可扩展性，如果你需要为一组类编写print方法，没有多态的话，你需要为每个类都编写对应的方法。但是使用了多态，你可以为父类编写方法，子类传进来会调用自己的对应的方法。

## 面向对象VS面向过程

>面向过程的思考方式：第一步要做什么，下一步要做什么
>
>面对对象的思考方式：我要怎么组合，模块，类。从底向上

### 不慎写出面向过程的代码

- 滥用`getter()和setter()`方法
  - 这样就违背封装性，你只需要暴露使用到的数据，同时要小心List之类会被修改的数据
- 滥用全局变量和全局方法

## 接口VS抽象类

>抽象类其实是类，只是不能被实例化的特殊的类。
>
>接口定义具有某些功能，通常被成为协议

>如何选择：
>
>如果需要表示`is-a`的关系，我们就是用抽象类，如果需要表示`has-a`的关系就用接口
>
>在具体实现的过程中，抽象类的思考方式是自下而上，先有子类，然后我们把公共部分抽离出来变成抽象类。
>
>接口的思考方式是自上而下，我们先定义具体有哪些能力，然后在想具体实现。



### 抽象类

- 抽象类不允许被实例化，只能够被继承
- 抽象类既可以包含方法和属性，其中还可以包含实现的方法和没有实现的方法【也被称为抽现象方法】
- 子类继承抽象类必须实现抽象方法

为什么要有抽象类？

强制定义子类必须实现父类的某些方法。

### 接口

- 接口不能包含属性
- 接口只能声明方法，不能包含代码实现(`default方法可以`)
- 类实现接口，必须实现接口声明的所有方法

为什么需要接口？

将实现与定义相分离，调用者不需要知道对应的实现。【基于接口而非实现编程】

## 基于接口而非实现编程

>需求：写一个上传下载图片到阿里云
>
>需求更改：需要将图片放到私有云里

编写原则：

- 函数的命名不能暴露任何实现的细节【只能表明做什么，而不是怎么做】
- 封装具体的实现细节
- 为实现类定义抽象的接口

## 多用组合少用继承

>在实际开发中，如果类的结构比较稳定，那么可以使用继承
>
>如果系统不稳定，继承层次很深，那么就需要尽量使用组合来替代继承。
>
>另外如果无法改变一个函数的入参类型，为了支持多态，只能采用继承来实现。

## In Action

>一般来说我们都在service层做了很多业务逻辑
>
>现在将这部分拆分，把其吸入domain

# Design Pattern

## Factory

工厂设计模式总共有三种，分别是简单工厂、工厂方法和抽象工厂。其中抽象工厂用的很少。

什么时候用简单工厂、工厂方法？

>如果每个一个实例需要很复杂的操作，那么使用工厂方法会比较好
>
>如果一个实例只需要通过new,那么使用简单工厂会比较好
>
>重要：如果只有简单的if-else那么不要使用工厂，类太多反而不容易维护

### Simple Factory

在Java中我们使用new关键字来初始化一个实例，同时为了提高代码的灵活性，我们可以使用接口【我在说什么】。

举个例子

```java
interface A{
    
}

class B implements A{
    
}

class C implements A{
    
}
那么可能在某一个方法里会出现这样的代码
init(String type){
    A a=null;
    if(type.equals("B")){
        A=new B();
    }else if(type.equals("C")){
        A=new C();
    }
    //业务逻辑代码
}
```

以上就是我想说的，可能在某份代码里会出现这样的情况。那么我们能做的一个优化就是将那段if代码抽成一个function

```java
class SimpleFactory{
    public SimpleFactory(){}
    
    public A getA(String type){
        A a=null;
    if(type.equals("B")){
        A=new B();
    }else if(type.equals("C")){
        A=new C();
    }
       return A;
    }
}

class YouBussic{
    private SimpleFactory factory;
    public YouBussic(SimpleFactory factory){
        this.factory=factory;
    }
    
    public void init(String type){
        A=factory.getA(type);
        //业务逻辑代码
    }
}
```

当然我们也可以通过static方法，这样就不需要new SimpleFactory后再getA()

### Factory Method

>定义一个实现对象的接口，但是让每个子类决定具体实现的是哪一个接口

简单说，就是去掉上面的if?实际上并不能去掉，或者说让代码更加可读

```java
interface A{
    
}

interface AFactory{
    A create();
}

class B implements A{
    
}

class BFactory implements AFactory{
    A create(){
        return B();
    }
}

class C implements A{
    
}

class CFactory implements AFactory{
    A create(){
        return C();
    }
}

class ABCFactory{
    private static Map<String,AFactory> map=new HashMap<>();
    static{
        map.put("B",new BFactory());
        map.put("C",new CFactory());
    }
    
    public static AFactory create(String type){
        return map.get(type);
    }
}
```

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

## Builder

>建造者模式用来解决复杂的构造情况，或者说减少构造的参数

编写代码要点：

1. **原构造函数需要为private**，因为这样就保证了如果要实例化这个类，只能通过builder来实现

```java
public class BuilderDemo{
    //...
    private String p1;
    
    //构造函数需要为private
    private BuilderDemo(Builder builder){
        this.p1=builder.p1;
    }
    
   
    public static class Builder{
        private String p1;
        
        public BuilderDemo build(){
            //检验
            if(p1==null){
                //或者其它逻辑
            }
            return BuilderDemo(this);
        }
        
        public Builder setP1(String p){
            this.p1=p;
            return this;
        }
    }
}
```

## Proxy

>代理模式就是在不改变原始类的行为，为其添加附加功能

### 原始类是自己可以掌控的

利用`基于接口而不是类实现`的原则，可以把原始类变成一个接口，然后原始类和代理类都去实现。并且在代理类完成想要增加的附加功能。这样就将业务和想要增加的附加功能分开了。

```java
public interface IUserController{
    String login(String username,String password);
    String register(String telephone,String password);
}

public class UserController implements IUserController{
    @Override
    public String login(String username,String password){
        //业务逻辑
    }
    
    @Override
    public String register(String telephone,String password){
        //业务逻辑
    }
}

public class UserControllerProxy implements IUserController{
    private MetricsController metricsController; //要增加的附加功能
    private UserController userController;
    
    public UserControllerProxy(UserController userController){
        this.userController=userController;
        metricsController=new MetricsController();
    }
    
    @Override
    public String login(String username,String password){
        //业务逻辑
        userController.login(username,password);
        //增加的附加功能
        metricsController.record(requestInfo)
    }
    ...
}
```

### 原始类自己无法控制

原始类来自第三方或者其它部分的库。这个时候就无法编写接口，所以可以通过`继承`来实现

```java
public UserControllerProxy extends UserController{
    private MetricsContrller metricsController;
    
    public UserControllerProxy(){
        this.metricsController=new MetricsController();
    }
    
    public String login(String username,String password){
        //业务逻辑
        super.login(username,password);
        //附加功能
        metricsController.record(requestInfo);
    }
}
```

---

以上的方法都很不好，如果要添加很多的附加功能，要写多少个类？

所以我们可以通过动态代理来实现

### 动态代理

> 动态代理利用Java提供的反射的功能，可以在程序运行的过程中生成类。

```java
public class MetricsCollectorProxy{
    private MetricsController metricsCotroller;
    
    public MetricsCollectorProxy(){
        this.metricsController=new MetricsController();
    }
    
    public Object createproxy(Object proxiedObject){
        Class<?>[] interfaces=proxiedObject.getClass().getInterfaces();
        DynamicProxyHandler handler=new DynamicProxyHandler(proxiedObject);
        return Proxy.newProxyInstance(proxiedObject.getClass().getClassLoader(),interfaces,handler);
    }
    
    private class DynamicProxyHandler implements InvocationHandler{
        private Object proxiedObject;
        
        public DynamicProxyHandler(Object proxiedObject){
            this.proxiedObject=proxiedObject;
        }
        @Override
        public Object invoke(Object proxy,Method method,Object[] args) throw Throwable{
            //业务逻辑
            Object result=method.invoke(proxiedObject,args);
            //附加的功能
            metricsController.record(requestInfo);
            return result;
        }
    }
}


MetricsCollectorProxy proxy=new MetricsCollectorProxy();
IUserController userController=(IUserController)proxy.createProxy(new UserController());
```

#### 实战Spring AOP

[待补充]

## Observer

>观察者模式，在对象之间定义了一个一对多关系。当这个一的状态发生改变时，它所对应的多都会自动发生改变

观察者模式属于使用较为频繁的一种设计模式，主要可以看成`发布者-订阅者模式`

同时我们在实现的时候还可以选择是否阻塞，是否异步等。

对了，消息队列也是这种模式，你想想看？

### 简单例子

在这个例子里，我们将实现一个气象观察站，首先气象观察站会得到具体的温度、湿度等。然后会notify它的所有订阅者。快来一起看看吧

```java
public interface Observer{
    void update(float temp,float humi,float pressure);
}

public interface Subject{
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notify();
}

public interface Display{
    void display();
}
```

```java
public class WeatherData implements Subject{
    private List<Observer> observers;
    private float temp;
    private float humi;
    private float pressure;
    
    public WeatherData(){
        observers=new ArrayList<>();
    }
    
    @Override
    public void registerObserver(Observer o){
        observers.add(o);
    }
    
    @Override
    public void removeObserver(Observer o){
        int i=observers.indexOf(o);
        if(i>=0){
            observers.remove(i);
        }
    }
    
    @Override
    public void notify(){
        for(Observer o:observers){
            o.update(temp,humi,pressure);
        }
    }
    
    public void mesureDataDisplay(){
        notify();
    }
    
    public setMesureData(float temp,float humi,float pressure){
        this.temp=temp;
        this.humi=humi;
        this.pressure=pressure;
        mesureDataDisplay();
    }
}
```

```java
public class CurrentDataDispaly implements Observer,Display{
    private Subject subjet;
    private float temp;
    private float humi;
    
    public CurrentDataDisplay(Subject subject){
        this.subject=subject;
        this.subject.registerObserver(this);
    }
    
    @Override
    public void update(float temp,float humi,float pressure){
        this.temp=temp;
        this.humi=humi;
        display();
    }
    
    @Override
    public void display(){
        //显示逻辑
    }
}
```

### 使用built-in的Observer

现在我们使用内置的Observer来改造我们上面的例子

【但是这种写法其实不好，因为Java的类不支持多继承，所以复杂业务是无法实现的】

```java
public class WeatherData extends Observable{
    private float temp;
    private float humi;
    private float pressure;
    
    public void measureDataDisplay(){
        setChange();//这个方法表示 可以通知了，在前端通常有个search技巧，即不会每次都去请求，等输入差不多的时候会请求
        notifyObservers();
    }
    
    public void setMeasureData(float temp,float humi,float pressure){
        this.temp=temp;
        this.humi=humi;
        this.pressure=pressure;
        measureDataDisplay();
    }
    //getter
}
```

```java
public class CurrentDataDispaly implements Observer,Display{
    private float temp;
    private float humi;
    Observable observable;
    
    public CurrentDataDisplay(Observable o){
        this.observable=o;
        this.observable.addObserver(this);
    }
    
    @Override
    public void dispaly(){
        //业务逻辑
    }
    
    @Override
    public void update(Observable o,Object arg){
        if(o instanceof WeatherData){
            WeatherData weatherData=(WeatherData)o;
            this.temp=weatherData.getTemp();
            this.humi=weatherData.getHumi();
            display();
        }
    }
}
```

### In Action

这此我们要完成的例子是，在用户注册完，我们给其发送优惠卷，Welcom信息。

如果是以前的话，我会把这段代码耦合在register函数里。但是`行为型模式就是将不同行为的代码进行解耦`，让我们一起来看看吧。

```java
public interface RegObserver{
    void handleRegSuccess(long userId);
}

public class RegPromotionObserver implements RegObserver{
    @Override
    public void handleRegSuccess(long userId){
        //业务逻辑
        
    }
}

public class RegNotificationObserver implements RegObserver{
    @Override
    public void handleRegSuccess(long userId){
        //业务逻辑
    }
}
```

```java
public class UserController{
    List<RegObserver> observers;
    
    public UserController(){
        observers=new ArrayList<>();
    }
    
    public void setRegObserver(RegObserver observer){
        observers.add(observer);
	}
    
    public void register(String telephone,String password){
        //业务逻辑
        long userId=UserService.register(telephone,password);
        for(RegObserver ob:observers){
            ob.handleRegSuccess(userId);
        }
    }
}
```

### Guava In Action

Guava提供了EventBus来实现观察者模式，其中我们可以通过其的封装实现异步观察者。

>不过这里的Observer只能由一个参数，同时注意如果是long的话，那么在执行eventBus.post(event),event会被自动装箱成Long，如果你的@Subscribe里的参数是long会执行不到

