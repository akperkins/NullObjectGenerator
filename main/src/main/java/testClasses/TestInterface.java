package main.java.testClasses;

import java.util.Date;

/**
 * Created by andreperkins on 3/31/15.
 */
public interface TestInterface {
    String getString();
    int getInt();
    long getLong();
    double getDouble();
    boolean getBoolean();
    Date getRandomObject();
    void doNotReturn();
}