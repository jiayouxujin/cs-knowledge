# MySQL

## 事务（ACID）

### A-原子性

### C-一致性

### I-隔离性

#### 可重复读

>可重复读表示在事务执行的过程中，看到的数据总是跟启动时看到的数据一致。【在该隔离级别下，未提交的事务对其它事务也是不可见的】

**查看隔离级别**

![](D:\Github\knowledge\mysql\transaction\可重复读.PNG)

**实验**

1. 关掉autocommit

![](D:\Github\knowledge\mysql\transaction\autocommit.PNG)

2. 查看一下原始数据

![](D:\Github\knowledge\mysql\transaction\原始数据.PNG)

3. 分别启动事务

![](D:\Github\knowledge\mysql\transaction\启动事务.PNG)

### D-持久性

