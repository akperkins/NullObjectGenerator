package testClasses;

import java.util.Date;

/**
 * Created by andreperkins on 3/31/15.
 */
public class IllegalAccessClass implements TestInterface {
    @Override
    public String getString() {
        throwIllegalAccessException();
        return "";
    }

    @Override
    public int getInt() {
        throwIllegalAccessException();
        return 0;
    }

    @Override
    public long getLong() {
        throwIllegalAccessException();
        return 0L;
    }

    @Override
    public double getDouble() {
        throwIllegalAccessException();
        return 0.0;
    }

    @Override
    public boolean getBoolean() {
        throwIllegalAccessException();
        return false;
    }

    @Override
    public Date getRandomObject() {
        throwIllegalAccessException();
        return null;
    }

    @Override
    public void doNotReturn() {
        throwIllegalAccessException();
    }

    private void throwIllegalAccessException() {
        throw new IllegalAccessError();
    }
}
