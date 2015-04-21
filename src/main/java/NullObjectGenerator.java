import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by andreperkins on 3/31/15.
 */
public class NullObjectGenerator {
    private final boolean isSetToFailHard;
    private final Logger logger;

    public NullObjectGenerator(boolean isSetToFailHard, Logger logger) {
        this.isSetToFailHard = isSetToFailHard;
        this.logger = logger;
    }

    public NullObjectGenerator() {
        this.isSetToFailHard = false;
        this.logger = null;
    }

    public <T extends Object> T generate(Class<T> testInterfaceClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(testInterfaceClass);
        enhancer.setCallback(new net.sf.cglib.proxy.InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                if (logger != null) {
                    logger.logNullOccurrence();
                }
                if (isSetToFailHard) {
                    throw new AssertionError("A null object was not expected here.");
                }
                Class<?> returnType = method.getReturnType();
                if(returnType == String.class){
                    return "";
                } else if(returnType == int.class) {
                    return 0;
                } else if(returnType == long.class){
                    return 0L;
                } else if(returnType == double.class) {
                    return 0.0D;
                } else if(returnType == boolean.class){
                    return false;
                } else {
                    try {
                        return generate(returnType);
                    } catch (IllegalArgumentException e){
                        return null;
                    }
                }
            }
        });
        T nulledObject = (T) enhancer.create();
        return nulledObject;
    }

    public boolean isSetToFailHard() {
        return isSetToFailHard;
    }

    public Logger getLogger() {
        return logger;
    }

    public static class Builder {
        private boolean isSetToFailHard;
        private Logger logger;

        public Builder setFailHard() {
            isSetToFailHard = true;
            return this;
        }

        public NullObjectGenerator build() {
            return new NullObjectGenerator(isSetToFailHard, logger);
        }

        public Builder setLogger(Logger logger) {
            this.logger = logger;
            return this;
        }
    }

    public interface Logger {
        public void logNullOccurrence();
    }
}