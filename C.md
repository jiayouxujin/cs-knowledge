# C

## Pointer

>前言，最近在做CS162，没想到竟然卡在`指针`这一块。

首先对于程序中的每个变量都有两个值，一个是其在内存的地址，一个是对应内存存储的值。

例如

```c
int a=5;
int *p=&a;
```



| 地址 | 值   |
| ---- | ---- |
| 204  | a=5  |
| 64   | p=&a |
|      |      |

首先解释两个符号，`*`表示的是引用，`&`是取值符号。

如上面表格所示：

1. 首先在地址204的地方给a分配内存，并且在对应的内存里填上5
2. 然后先定义个执行int类型的指针*p，该变量存储在64的位置。然后给该变量赋予a的地址。这样在64的内存空间里存储的值就是a的地址204

```c
*p=a=5
&p=64
&a=204
p=204
```

