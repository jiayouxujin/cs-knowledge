# JVM

>JVM的主要功能就是**加载和执行程序**

## Class Loader Subsystem

### Loader

- Boostrap Loaderr
  - 加载跟jvm一起发布的`rt.jar`
- Extention Loader
  - 加载/jre/lib/ext下的jar
- Application Loader
  - 加载class path下的class，通过-cp指定

### Link

- Vetify
  - 验证是否加载的字节码是否满足JVM规范
- Prepare
  - 为类级别的变量分配内存空间，这里仅仅是分配内存空间
    - 如果你有一个这样的定义`public static boolean b=true`在这个阶段会被分配空间，并且初始成boolean类型的默认值，即`false`
- Resolve
  - 解析，如果在Application里有一个类X引用了类Y，那么会解析类Y
    - 这个阶段如果没有找到类Y,那么会抛出`ClassDefNotFound`的异常

### Initialize

- 将`Prepare`阶段分配的内存空间的变量，初始化成其定义的变量。