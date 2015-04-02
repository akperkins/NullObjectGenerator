package testClasses;

/**
 * Created by andreperkins on 3/31/15.
 */
public class IllegalAccessClass implements TestInterface {
    @Override
    public String getMoney() {
        throw new IllegalAccessError();
    }

    @Override
    public void doNotReturn() {
        throw new IllegalAccessError();
    }
}
