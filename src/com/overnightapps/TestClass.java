package com.overnightapps;

/**
 * Created by andreperkins on 3/31/15.
 */
public class TestClass implements TestInterface {
    @Override
    public String getMoney() {
        throw new IllegalAccessError();
    }

    @Override
    public void doNotReturn() {
        throw new IllegalAccessError();
    }
}
