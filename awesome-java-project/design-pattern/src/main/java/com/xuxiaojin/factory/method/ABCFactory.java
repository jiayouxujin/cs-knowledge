package com.xuxiaojin.factory.method;

import java.util.HashMap;
import java.util.Map;

public class ABCFactory {
    private static final Map<String, AFactory> map = new HashMap<>();

    static {
        map.put("B", new BFactory());
        map.put("C", new CFactory());
    }

    public static AFactory create(String type) {
        return map.get(type);
    }
}
