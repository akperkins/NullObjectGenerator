package com.overnightapps;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;

import java.util.Objects;

/**
 * Created by andreperkins on 3/31/15.
 */
public class NullObjectGenerator {
    public <T extends Object> T generate(Class<T> testInterfaceClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(testInterfaceClass);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "Hello cglib!";
            }
        });
        T proxy = (T) enhancer.create();
        return proxy;
    }
}