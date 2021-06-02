package com.xuxiaojin.factory.di;



import javafx.util.Builder;

import java.util.ArrayList;
import java.util.List;

public class BeanDefinition {
    private String id;
    private String className;
    private List<ConstructorArg> constructorArgList = new ArrayList<>();
    private Scope scope = Scope.PROTOTYPE;
    private boolean lazyInit = false;

    public BeanDefinition(String id, String className) {
        this.id = id;
        this.className = className;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<ConstructorArg> getConstructorArgList() {
        return constructorArgList;
    }

    public void setConstructorArgList(List<ConstructorArg> constructorArgList) {
        this.constructorArgList = constructorArgList;
    }

    public Scope getScope() {
        return scope;
    }

    public void setScope(Scope scope) {
        this.scope = scope;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public static enum Scope{
        SINGLETON,
        PROTOTYPE
    }
    public static class ConstructorArg {
        private boolean isRef;
        private Class type;
        private Object arg;

        private ConstructorArg(Builder builder) {
            this.isRef = builder.isRef();
            this.arg = builder.getArg();
            this.type = builder.getType();
        }

        public static class Builder {
            private boolean isRef = false;
            private Class type;
            private Object arg;

            public Builder setRef(Boolean isRef) {
                this.isRef = isRef;
                return this;
            }

            public Builder setType(Class type) {
                this.type = type;
                return this;
            }

            public Builder setArg(Object arg) {
                this.arg = arg;
                return this;
            }

            public boolean isRef() {
                return isRef;
            }

            public Class getType() {
                return type;
            }

            public Object getArg() {
                return arg;
            }

            public ConstructorArg build() {
                if (this.isRef) {
                    if (this.type != null) {
                        throw new IllegalArgumentException("当参数为引用类型，无需设置type参数");
                    }
                    if (!(arg instanceof String)) {
                        throw new IllegalArgumentException("请设置引用ID");
                    }
                } else {
                    if (this.type == null || this.arg == null) {
                        throw new IllegalArgumentException("当参数为非引用类型，arg和type参数必填");
                    }
                }

                return new ConstructorArg(this);
            }
        }
    }
}
