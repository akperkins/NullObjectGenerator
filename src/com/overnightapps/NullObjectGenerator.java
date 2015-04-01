package com.overnightapps;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;

/**
 * Created by andreperkins on 3/31/15.
 */
public class NullObjectGenerator {

    private final boolean isSetToFailHard;

    public NullObjectGenerator(boolean isSetToFailHard) {
        this.isSetToFailHard = isSetToFailHard;
    }

    public NullObjectGenerator() {
        this.isSetToFailHard = false;
    }

    public <T extends Object> T generate(Class<T> testInterfaceClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(testInterfaceClass);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                if(isSetToFailHard){
                    throw new AssertionError("A null object was not expected here.");
                }
                return "Hello cglib!";
            }
        });
        T proxy = (T) enhancer.create();
        return proxy;
    }

    public boolean isSetToFailHard() {
        return isSetToFailHard;
    }

    public static class Builder {
        private boolean isSetToFailHard;

        public Builder setFailHard() {
            isSetToFailHard = true;
            return this;
        }

        public NullObjectGenerator build() {
            return new NullObjectGenerator(isSetToFailHard);
        }
    }
}