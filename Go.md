# Go

## Functional Programming

### Say What,not How

```go
//这段代码告诉需要怎么做
func Func() int{
    sum:=0;
    for i:=10;i<10;i++{
        x:=math.Abs(i)
        if x%2==0{
            sum+=i
        }
    }
    return i
}
//这段代码告诉要做什么
func WhatFunc() int{
    sum:=IntRange(-10,10)
    	.Abs()
    .Filter(func(i int) bool{
        return i%2==0
    })
    .Sum()
}
```

### first class /higher orders functions

```go
func filter(s []string,p func(string string) bool)(out []string){
    for _,ss:=range s{
        if p(ss){
            out=append(out,ss)
        }
    }
    return 
}
```

### 幂等性

```go
//这个函数尽量不要出现，因为每次请求的结果都不一样，不可控，可以考虑使用下方那个函数
func update(r row) row{
    r.lastUpdateTime=time.Now()
    return r
}

func update2(r row,t time.Time) row{
    r.lastUpdateTime=t
    return r
}
```

### closure(闭包)

>里面的函数可以使用其外部函数的变量

```go
func closure(s string) string{
    drop:=func(i int) string{
        return s[i:]
    }
    drop(5)
}
```

### 使用闭包的特性，来减少参数

```go
func greet(p,n string) string{
    fmt.Sprintf("%v %v\n",p,n)
}

func prefixGreet(p string) func(s2 string) string{
    return funt(s2 string) string{
        return greet(p,s2)
    }
}

func main(){
    fmt.Println(greet("hello","go"))
    
    helloFn:=prefixGreet("hello")
    fmt.Println(helloFn("go"))
}
```

### Demo

```go
type Options struct{
    maxCon int
    transport string
    timeout int
}

type Server struct{
    opts Options
}

//old code
s:=Server{
    1,"tcp",100
}

//new code

type ServerOptions func(o Options) Options

func MaxCon(n int)ServerOptions{
    return func(o Options) Options{
        o.maxCon=n
        return o
    }
}

func TimeOut(n int) ServerOptions{
    return funt(o Options) Options{
        o.timeOut=n
        return 0
    }
}

func NewServer(os ...ServerOptions)Server{
    //Default Value
    opts:=Options{timeOut:100}
    
    for _,o:=range os{
        opts=o(opts)
    }
    server:=Server{
        opts:opts
    }
    return server
}

NewServer(MaxCon(100),TimOut(300))
```

