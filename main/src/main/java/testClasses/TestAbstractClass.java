package main.java.testClasses;

/**
 * Created by andreperkins on 4/3/15.
 */
public abstract class TestAbstractClass {
    protected final String message;

    public TestAbstractClass(String message){
        this.message = message;
    }

    public String getTransformedMessage(){
        return transform();
    }

    public abstract String transform();
}
