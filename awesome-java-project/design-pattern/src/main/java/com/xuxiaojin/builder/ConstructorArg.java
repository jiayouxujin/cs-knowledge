package com.xuxiaojin.builder;

public class ConstructorArg {
    private boolean isRef;
    private Class type;
    private Object arg;

    private ConstructorArg(Builder builder) {
        this.isRef = builder.isRef;
        this.type = builder.type;
        this.arg = builder.arg;
    }

    public static class Builder {
        private boolean isRef;
        private Class type;
        private Object arg;

        public Builder() {
        }


        public Builder isRef(boolean isRef) {
            this.isRef = isRef;
            return this;
        }

        public Builder type(Class type) {
            this.type = type;
            return this;
        }

        public Builder arg(Object arg) {
            this.arg = arg;
            return this;
        }

        public ConstructorArg build() {
            if (isRef == true) {
                if (arg instanceof String) {
                    if (type != null) {
                        throw new IllegalArgumentException("当isRef为true，arg为String时，不需要设置type");
                    }
                }
            } else {
                if (arg == null || type == null) {
                    throw new IllegalArgumentException("当isRef为false时，arg和type都需要设置");
                }
            }
            return new ConstructorArg(this);
        }
    }


    @Override
    public String toString() {
        return "ConstructorArg{" +
                "isRef=" + isRef +
                ", type=" + type +
                ", arg=" + arg +
                '}';
    }

    public static void main(String[] args) {
//        ConstructorArg constructorArg = new Builder().isRef(true).arg("hello world").type(ConstructorArg.class).build();
//          ConstructorArg constructorArg=new Builder().isRef(false).arg("hello world").build();
        ConstructorArg constructorArg=new Builder().isRef(true).arg("hell world").build();
        System.out.println(constructorArg.toString());
    }
}
